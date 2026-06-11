package org.typesense.docgen;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.ConditionalExpr;
import com.github.javaparser.ast.expr.EnclosedExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.expr.ThisExpr;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.type.ReferenceType;
import com.github.javaparser.printer.lexicalpreservation.LexicalPreservingPrinter;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class DocGen {

    private static final String DOCS_BASE_URL = "https://typesense.org/docs/latest/api/";

    private static final Map<String, String> TAG_TO_DOCS;
    static {
        TAG_TO_DOCS = new HashMap<String, String>();
        TAG_TO_DOCS.put("collections", "collections.html");
        TAG_TO_DOCS.put("documents", "documents.html");
        TAG_TO_DOCS.put("keys", "api-keys.html");
        TAG_TO_DOCS.put("aliases", "collection-alias.html");
        TAG_TO_DOCS.put("synonyms", "synonyms.html");
        TAG_TO_DOCS.put("curation_sets", "curation.html");
        TAG_TO_DOCS.put("stopwords", "stopwords.html");
        TAG_TO_DOCS.put("presets", "search.html#presets");
        TAG_TO_DOCS.put("analytics", "analytics-query-suggestions.html");
        TAG_TO_DOCS.put("conversations", "conversational-search-rag.html");
        TAG_TO_DOCS.put("stemming", "stemming.html");
        TAG_TO_DOCS.put("nl_search_models", "natural-language-search.html");
        TAG_TO_DOCS.put("debug", "cluster-operations.html#debug");
        TAG_TO_DOCS.put("health", "cluster-operations.html#health");
        TAG_TO_DOCS.put("operations", "cluster-operations.html");
    }

    private static final Set<String> VERBS = new HashSet<String>(
            Arrays.asList("get", "post", "put", "patch", "delete"));

    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.err.println("usage: DocGen <openapi.yml> <wrapper-dir>");
            System.exit(2);
        }
        Path specPath = Paths.get(args[0]);
        Path wrapperDir = Paths.get(args[1]);

        Map<String, OpInfo> opIndex = loadOpIndex(specPath);
        System.out.println("docgen: loaded " + opIndex.size() + " operations from spec");

        List<Path> files;
        Stream<Path> s = Files.list(wrapperDir);
        try {
            files = s.filter(p -> p.toString().endsWith(".java"))
                    .sorted()
                    .collect(Collectors.toList());
        } finally {
            s.close();
        }

        Registry registry = new Registry();
        Map<Path, CompilationUnit> parsed = new LinkedHashMap<Path, CompilationUnit>();
        for (Path f : files) {
            CompilationUnit cu = StaticJavaParser.parse(f);
            LexicalPreservingPrinter.setup(cu);
            parsed.put(f, cu);
            indexUnit(cu, registry);
        }

        int touched = 0;
        for (Map.Entry<Path, CompilationUnit> e : parsed.entrySet()) {
            Path f = e.getKey();
            CompilationUnit cu = e.getValue();
            boolean changed = annotateUnit(cu, registry, opIndex);
            if (changed) {
                String out = LexicalPreservingPrinter.print(cu);
                Files.write(f, out.getBytes());
                touched++;
                System.out.println("docgen: updated " + f.getFileName());
            }
        }
        System.out.println("docgen: " + touched + " wrapper file(s) updated");
    }

    static final class OpInfo {
        final String operationId;
        final String method;
        final String path;
        final String summary;
        final String description;
        final String tag;

        OpInfo(String operationId, String method, String path, String summary, String description, String tag) {
            this.operationId = operationId;
            this.method = method;
            this.path = path;
            this.summary = summary == null ? "" : summary.trim();
            this.description = description == null ? "" : description.trim();
            this.tag = tag == null ? "" : tag;
        }
    }

    @SuppressWarnings("unchecked")
    private static Map<String, OpInfo> loadOpIndex(Path specPath) throws IOException {
        Yaml yaml = new Yaml();
        Object root;
        InputStream in = Files.newInputStream(specPath);
        try {
            root = yaml.load(in);
        } finally {
            in.close();
        }
        if (!(root instanceof Map)) {
            throw new IOException("spec root is not a map");
        }
        Map<String, Object> spec = (Map<String, Object>) root;
        Object pathsObj = spec.get("paths");
        if (!(pathsObj instanceof Map)) {
            throw new IOException("spec missing paths");
        }
        Map<String, OpInfo> ops = new LinkedHashMap<String, OpInfo>();
        for (Map.Entry<String, Object> e : ((Map<String, Object>) pathsObj).entrySet()) {
            String path = e.getKey();
            if (!(e.getValue() instanceof Map)) continue;
            Map<String, Object> item = (Map<String, Object>) e.getValue();
            for (Map.Entry<String, Object> me : item.entrySet()) {
                String method = me.getKey().toLowerCase(Locale.ROOT);
                if (!VERBS.contains(method)) continue;
                if (!(me.getValue() instanceof Map)) continue;
                Map<String, Object> op = (Map<String, Object>) me.getValue();
                String opId = stringOrEmpty(op.get("operationId"));
                String summary = stringOrEmpty(op.get("summary"));
                String description = stringOrEmpty(op.get("description"));
                String tag = "";
                Object tagsObj = op.get("tags");
                if (tagsObj instanceof List && !((List<?>) tagsObj).isEmpty()) {
                    Object t = ((List<?>) tagsObj).get(0);
                    if (t != null) tag = t.toString();
                }
                String key = method.toUpperCase(Locale.ROOT) + " " + normalizePath(path);
                ops.put(key, new OpInfo(opId, method.toUpperCase(Locale.ROOT), path, summary, description, tag));
            }
        }
        return ops;
    }

    private static String stringOrEmpty(Object o) {
        return o == null ? "" : o.toString();
    }

    static final class Registry {
        final Map<String, String> constants = new HashMap<String, String>();
        final Map<String, MethodDeclaration> helpers = new HashMap<String, MethodDeclaration>();
        final Map<String, Expression> instanceFields = new HashMap<String, Expression>();
    }

    private static void indexUnit(CompilationUnit cu, Registry reg) {
        for (ClassOrInterfaceDeclaration cls : cu.findAll(ClassOrInterfaceDeclaration.class)) {
            String name = cls.getNameAsString();
            for (FieldDeclaration fd : cls.getFields()) {
                for (VariableDeclarator vd : fd.getVariables()) {
                    if (!"String".equals(vd.getTypeAsString())) continue;
                    Optional<Expression> init = vd.getInitializer();
                    if (!init.isPresent()) continue;
                    Expression e = init.get();
                    if (e instanceof StringLiteralExpr) {
                        reg.constants.put(name + "." + vd.getNameAsString(),
                                ((StringLiteralExpr) e).asString());
                    }
                }
            }
            for (MethodDeclaration m : cls.getMethods()) {
                if (!"String".equals(m.getTypeAsString())) continue;
                reg.helpers.put(name + "." + m.getNameAsString(), m);
            }
            for (ConstructorDeclaration c : cls.getConstructors()) {
                List<AssignExpr> assigns = c.getBody().findAll(AssignExpr.class);
                for (AssignExpr ae : assigns) {
                    Expression target = ae.getTarget();
                    if (target instanceof FieldAccessExpr) {
                        FieldAccessExpr fae = (FieldAccessExpr) target;
                        if (fae.getScope() instanceof ThisExpr) {
                            reg.instanceFields.put(name + "." + fae.getNameAsString(), ae.getValue());
                        }
                    } else if (target instanceof NameExpr) {
                        NameExpr ne = (NameExpr) target;
                        reg.instanceFields.put(name + "." + ne.getNameAsString(), ae.getValue());
                    }
                }
            }
        }
    }

    private static boolean annotateUnit(CompilationUnit cu, Registry reg, Map<String, OpInfo> ops) {
        boolean changed = false;
        for (ClassOrInterfaceDeclaration cls : cu.findAll(ClassOrInterfaceDeclaration.class)) {
            String className = cls.getNameAsString();
            if (writeClassJavadoc(cls)) {
                changed = true;
            }
            for (ConstructorDeclaration c : cls.getConstructors()) {
                if (writeConstructorJavadoc(c, className)) {
                    changed = true;
                }
            }
            for (MethodDeclaration m : cls.getMethods()) {
                if (!m.getBody().isPresent()) continue;
                ApiCallSite site = findUniqueApiCall(m);
                if (site == null) continue;
                String resolved = resolvePath(site.pathArg, className, m, reg);
                if (resolved == null) {
                    System.out.println("docgen: cannot resolve path for " + className + "." + m.getNameAsString());
                    continue;
                }
                String key = site.verb + " " + normalizePath(resolved);
                OpInfo op = ops.get(key);
                if (op == null) {
                    System.out.println("docgen: no spec match for " + className + "." + m.getNameAsString()
                            + " -> " + key);
                    continue;
                }
                if (writeJavadoc(m, op, site)) {
                    changed = true;
                }
            }
        }
        return changed;
    }

    static final class ApiCallSite {
        final String verb;
        final Expression pathArg;
        final MethodCallExpr call;

        ApiCallSite(String verb, Expression pathArg, MethodCallExpr call) {
            this.verb = verb;
            this.pathArg = pathArg;
            this.call = call;
        }
    }

    private static ApiCallSite findUniqueApiCall(MethodDeclaration m) {
        List<MethodCallExpr> all = m.getBody().get().findAll(MethodCallExpr.class);
        List<MethodCallExpr> calls = new ArrayList<MethodCallExpr>();
        for (MethodCallExpr c : all) {
            if (isApiCall(c)) calls.add(c);
        }
        if (calls.size() != 1) return null;
        MethodCallExpr call = calls.get(0);
        String verb = call.getNameAsString().toUpperCase(Locale.ROOT);
        if (call.getArguments().isEmpty()) return null;
        return new ApiCallSite(verb, call.getArgument(0), call);
    }

    private static boolean isApiCall(MethodCallExpr call) {
        if (!VERBS.contains(call.getNameAsString())) return false;
        Optional<Expression> scope = call.getScope();
        if (!scope.isPresent()) return false;
        Expression s = scope.get();
        if (s instanceof NameExpr) {
            return "apiCall".equals(((NameExpr) s).getNameAsString());
        }
        if (s instanceof FieldAccessExpr) {
            return "apiCall".equals(((FieldAccessExpr) s).getNameAsString());
        }
        return false;
    }


    private static String resolvePath(Expression expr, String className, MethodDeclaration owner, Registry reg) {
        return resolve(expr, className, new HashMap<String, String>(), reg, 0, owner);
    }

    private static String resolve(Expression expr, String className, Map<String, String> scope, Registry reg,
                                   int depth, MethodDeclaration owner) {
        if (depth > 8) return null;
        if (expr instanceof EnclosedExpr) {
            return resolve(((EnclosedExpr) expr).getInner(), className, scope, reg, depth + 1, owner);
        }
        if (expr instanceof StringLiteralExpr) {
            return ((StringLiteralExpr) expr).asString();
        }
        if (expr instanceof BinaryExpr) {
            BinaryExpr be = (BinaryExpr) expr;
            if (be.getOperator() == BinaryExpr.Operator.PLUS) {
                String l = resolve(be.getLeft(), className, scope, reg, depth + 1, owner);
                String r = resolve(be.getRight(), className, scope, reg, depth + 1, owner);
                if (l == null || r == null) return null;
                return l + r;
            }
            return null;
        }
        if (expr instanceof NameExpr) {
            String n = ((NameExpr) expr).getNameAsString();
            if (scope.containsKey(n)) return scope.get(n);
            String fq = className + "." + n;
            if (reg.constants.containsKey(fq)) return reg.constants.get(fq);
            if (owner != null) {
                for (Parameter p : owner.getParameters()) {
                    if (p.getNameAsString().equals(n)) return "{" + n + "}";
                }
            }
            if (reg.instanceFields.containsKey(fq)) {
                return resolve(reg.instanceFields.get(fq), className, scope, reg, depth + 1, owner);
            }
            return null;
        }
        if (expr instanceof FieldAccessExpr) {
            FieldAccessExpr fae = (FieldAccessExpr) expr;
            if (fae.getScope() instanceof NameExpr) {
                String fq = ((NameExpr) fae.getScope()).getNameAsString() + "." + fae.getNameAsString();
                if (reg.constants.containsKey(fq)) return reg.constants.get(fq);
            }
            if (fae.getScope() instanceof ThisExpr) {
                String fq = className + "." + fae.getNameAsString();
                Expression init = reg.instanceFields.get(fq);
                if (init == null) return "{" + fae.getNameAsString() + "}";
                // Field assigned directly from a ctor param (e.g. this.id = id) -> placeholder.
                if (init instanceof NameExpr) return "{" + fae.getNameAsString() + "}";
                return resolve(init, className, scope, reg, depth + 1, owner);
            }
            return null;
        }
        if (expr instanceof ConditionalExpr) {
            ConditionalExpr ce = (ConditionalExpr) expr;
            String t = resolve(ce.getThenExpr(), className, scope, reg, depth + 1, owner);
            String el = resolve(ce.getElseExpr(), className, scope, reg, depth + 1, owner);
            if (!isEmpty(el) && isEmpty(t)) return el;
            if (!isEmpty(t) && isEmpty(el)) return t;
            if (t != null && t.indexOf('{') >= 0) return t;
            if (el != null && el.indexOf('{') >= 0) return el;
            return t != null ? t : el;
        }
        if (expr instanceof MethodCallExpr) {
            MethodCallExpr mc = (MethodCallExpr) expr;
            String name = mc.getNameAsString();
            if ("encodeURIComponent".equals(name) && !mc.getArguments().isEmpty()) {
                Expression arg = mc.getArgument(0);
                String var = extractVarName(arg);
                return "{" + (var != null ? var : "x") + "}";
            }
            String targetClass = className;
            if (mc.getScope().isPresent()) {
                Expression sc = mc.getScope().get();
                if (sc instanceof NameExpr) {
                    String n = ((NameExpr) sc).getNameAsString();
                    if (!"this".equals(n)) targetClass = n;
                }
            }
            String fq = targetClass + "." + name;
            MethodDeclaration helper = reg.helpers.get(fq);
            if (helper != null) {
                return inlineHelper(helper, targetClass, mc.getArguments(), scope, reg, depth + 1, owner);
            }
            return null;
        }
        return null;
    }

    private static String inlineHelper(MethodDeclaration helper, String targetClass,
                                        NodeList<Expression> args, Map<String, String> outerScope,
                                        Registry reg, int depth, MethodDeclaration owner) {
        if (!helper.getBody().isPresent()) return null;
        Map<String, String> scope = new HashMap<String, String>();
        for (int i = 0; i < helper.getParameters().size(); i++) {
            String pname = helper.getParameter(i).getNameAsString();
            String pval = null;
            if (i < args.size()) {
                pval = resolve(args.get(i), targetClass, outerScope, reg, depth + 1, owner);
            }
            if (pval == null) pval = "{" + pname + "}";
            scope.put(pname, pval);
        }
        List<ReturnStmt> returns = helper.getBody().get().findAll(ReturnStmt.class);
        if (returns.size() != 1) return null;
        Optional<Expression> ret = returns.get(0).getExpression();
        if (!ret.isPresent()) return null;
        return resolve(ret.get(), targetClass, scope, reg, depth + 1, helper);
    }

    private static String extractVarName(Expression arg) {
        if (arg instanceof NameExpr) return ((NameExpr) arg).getNameAsString();
        if (arg instanceof FieldAccessExpr) return ((FieldAccessExpr) arg).getNameAsString();
        if (arg instanceof EnclosedExpr) return extractVarName(((EnclosedExpr) arg).getInner());
        return null;
    }

    private static boolean isEmpty(String s) {
        return s == null || s.isEmpty();
    }

    private static String normalizePath(String path) {
        if (path == null) return "";
        while (path.length() > 1 && path.endsWith("/")) {
            path = path.substring(0, path.length() - 1);
        }
        path = path.replaceAll("/+", "/");
        path = path.replaceAll("\\{[^}]*\\}", "{}");
        return path;
    }

    private static boolean writeClassJavadoc(ClassOrInterfaceDeclaration cls) {
        if (!cls.isPublic()) {
            return false;
        }
        String content = renderClassJavadocContent(cls);
        if (cls.getJavadocComment().isPresent()) {
            String existing = cls.getJavadocComment().get().getContent();
            if (!isGeneratedClassJavadoc(existing)) {
                return false;
            }
            if (normalizeJavadocContent(existing).equals(normalizeJavadocContent(content))) {
                return false;
            }
        }
        cls.setJavadocComment(content);
        return true;
    }

    private static String renderClassJavadocContent(ClassOrInterfaceDeclaration cls) {
        List<String> lines = new ArrayList<String>();
        lines.add("Typesense " + humanizeIdentifier(cls.getNameAsString()) + " API wrapper.");
        return renderJavadocBlock(lines, javadocIndent(cls));
    }

    private static boolean writeConstructorJavadoc(ConstructorDeclaration c, String className) {
        if (!c.isPublic()) {
            return false;
        }
        String content = renderConstructorJavadocContent(c, className);
        if (c.getJavadocComment().isPresent()) {
            String existing = c.getJavadocComment().get().getContent();
            if (!isGeneratedConstructorJavadoc(existing)) {
                return false;
            }
            if (normalizeJavadocContent(existing).equals(normalizeJavadocContent(content))) {
                return false;
            }
        }
        c.setJavadocComment(content);
        return true;
    }

    private static String renderConstructorJavadocContent(ConstructorDeclaration c, String className) {
        List<String> lines = new ArrayList<String>();
        lines.add("Creates a new " + className + " instance.");
        addConstructorParamTags(lines, c.getParameters());
        return renderJavadocBlock(lines, javadocIndent(c));
    }

    private static boolean writeJavadoc(MethodDeclaration m, OpInfo op, ApiCallSite site) {
        String content = renderJavadocContent(m, op, site);
        if (m.getJavadocComment().isPresent()) {
            String existing = m.getJavadocComment().get().getContent();
            if (!isGeneratedJavadoc(existing)) {
                return false;
            }
            if (normalizeJavadocContent(existing).equals(normalizeJavadocContent(content))) {
                return false;
            }
        }
        m.setJavadocComment(content);
        return true;
    }

    private static boolean isGeneratedJavadoc(String content) {
        return content != null
                && content.contains("HTTP: ")
                && content.contains("Typesense docs");
    }

    private static boolean isGeneratedClassJavadoc(String content) {
        return content != null
                && content.contains("Typesense ")
                && content.contains(" API wrapper");
    }

    private static boolean isGeneratedConstructorJavadoc(String content) {
        return content != null
                && content.contains("Creates a new ")
                && content.contains(" instance.");
    }

    private static String normalizeJavadocContent(String content) {
        if (content == null) return "";
        return content.replace("\r\n", "\n").trim();
    }

    private static String renderJavadocContent(MethodDeclaration m, OpInfo op, ApiCallSite site) {
        List<String> lines = new ArrayList<String>();
        String summary = sanitizeJavadocText(firstSentence(op.summary));
        if (summary.isEmpty()) summary = sanitizeJavadocText(firstSentence(op.description));
        if (!summary.isEmpty()) {
            lines.add(ensureTrailingPeriod(upperFirst(summary)));
        }
        if (!op.description.isEmpty()
                && !op.description.equalsIgnoreCase(op.summary)
                && !firstSentence(op.description).equalsIgnoreCase(firstSentence(op.summary))) {
            lines.add("");
            lines.add("<p>");
            for (String line : op.description.split("\n", -1)) {
                lines.add(sanitizeJavadocText(line));
            }
        }
        lines.add("");
        lines.add("<p>");
        lines.add("HTTP: " + op.method + " " + op.path);

        boolean hasParamTags = !m.getParameters().isEmpty();
        boolean hasReturnTag = !"void".equals(m.getTypeAsString());
        boolean hasThrowsTags = !m.getThrownExceptions().isEmpty();
        if (hasParamTags || hasReturnTag || hasThrowsTags || TAG_TO_DOCS.containsKey(op.tag)) {
            lines.add("");
        }
        addMethodParamTags(lines, m.getParameters(), site);
        if (hasReturnTag) {
            lines.add("@return " + describeReturn(m));
        }
        for (ReferenceType thrown : m.getThrownExceptions()) {
            lines.add("@throws " + thrown.asString() + " if the request fails");
        }

        String section = TAG_TO_DOCS.get(op.tag);
        if (section != null) {
            lines.add("");
            lines.add("@see <a href=\"" + DOCS_BASE_URL + section + "\">Typesense docs</a>");
        }
        return renderJavadocBlock(lines, javadocIndent(m));
    }

    private static void addMethodParamTags(List<String> lines, NodeList<Parameter> parameters, ApiCallSite site) {
        for (Parameter p : parameters) {
            String name = p.getNameAsString();
            lines.add("@param " + name + " " + describeMethodParam(p, site));
        }
    }

    private static void addConstructorParamTags(List<String> lines, NodeList<Parameter> parameters) {
        for (Parameter p : parameters) {
            String name = p.getNameAsString();
            lines.add("@param " + name + " " + describeConstructorParam(p));
        }
    }

    private static String describeMethodParam(Parameter p, ApiCallSite site) {
        String name = p.getNameAsString();
        String type = codeType(p.getTypeAsString());
        if (site != null) {
            if (expressionReferencesName(apiCallBodyArg(site), name)) {
                return "the " + type + " request body";
            }
            if (expressionReferencesName(apiCallQueryArg(site), name)) {
                return "the " + type + " query parameters";
            }
            if (expressionReferencesName(site.pathArg, name)) {
                return "the " + type + " path parameter";
            }
        }
        return "the " + type + " " + humanizeIdentifier(name);
    }

    private static String describeConstructorParam(Parameter p) {
        String name = p.getNameAsString();
        String type = p.getTypeAsString();
        if ("ApiCall".equals(type)) {
            return "the " + codeType(type) + " instance used to send requests";
        }
        if ("Configuration".equals(type)) {
            return "the client configuration";
        }
        if ("OkHttpClient".equals(type)) {
            return "the HTTP client";
        }
        if (name.endsWith("Id") || name.endsWith("Name")) {
            return "the " + codeType(type) + " path parameter";
        }
        return "the " + codeType(type) + " " + humanizeIdentifier(name);
    }

    private static String describeReturn(MethodDeclaration m) {
        String type = m.getTypeAsString();
        if ("String".equals(type)) {
            return "the raw response body";
        }
        if (type.endsWith("[]")) {
            return "the " + codeType(type) + " response array";
        }
        if (type.startsWith("Map<")) {
            return "the " + codeType(type) + " response map";
        }
        return "the " + codeType(type) + " response";
    }

    private static Expression apiCallBodyArg(ApiCallSite site) {
        if (!"POST".equals(site.verb) && !"PUT".equals(site.verb) && !"PATCH".equals(site.verb)) {
            return null;
        }
        NodeList<Expression> args = site.call.getArguments();
        return args.size() > 1 ? args.get(1) : null;
    }

    private static Expression apiCallQueryArg(ApiCallSite site) {
        NodeList<Expression> args = site.call.getArguments();
        if ("GET".equals(site.verb) || "DELETE".equals(site.verb)) {
            return args.size() > 1 ? args.get(1) : null;
        }
        if ("POST".equals(site.verb) || "PUT".equals(site.verb) || "PATCH".equals(site.verb)) {
            return args.size() > 2 ? args.get(2) : null;
        }
        return null;
    }

    private static boolean expressionReferencesName(Expression expr, String name) {
        if (expr == null || name == null) return false;
        for (NameExpr n : expr.findAll(NameExpr.class)) {
            if (name.equals(n.getNameAsString())) {
                return true;
            }
        }
        return false;
    }

    private static String codeType(String type) {
        return "{@code " + type + "}";
    }

    private static String renderJavadocBlock(List<String> lines, String indent) {
        StringBuilder sb = new StringBuilder();
        sb.append('\n');
        for (String line : lines) {
            if (line.isEmpty()) {
                sb.append(indent).append(" *\n");
            } else {
                sb.append(indent).append(" * ").append(line).append('\n');
            }
        }
        sb.append(indent).append(" ");
        return sb.toString();
    }

    private static String javadocIndent(Node node) {
        int depth = 0;
        Optional<Node> parent = node.getParentNode();
        while (parent.isPresent()) {
            Node current = parent.get();
            if (current instanceof ClassOrInterfaceDeclaration) {
                depth++;
            }
            parent = current.getParentNode();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append("    ");
        }
        return sb.toString();
    }

    private static String humanizeIdentifier(String identifier) {
        if (identifier == null || identifier.isEmpty()) return "";
        String spaced = identifier.replaceAll("([a-z0-9])([A-Z])", "$1 $2")
                .replace('_', ' ')
                .replace('-', ' ')
                .toLowerCase(Locale.ROOT);
        return sanitizeJavadocText(spaced);
    }

    private static String sanitizeJavadocText(String s) {
        if (s == null || s.isEmpty()) return s;
        return s.replaceAll("\\s*&amp;\\s*", " and ")
                .replaceAll("\\s*&(?!#?[A-Za-z0-9]+;)\\s*", " and ")
                .trim();
    }

    private static String firstSentence(String s) {
        if (s == null) return "";
        s = s.trim();
        if (s.isEmpty()) return "";
        int nl = s.indexOf('\n');
        if (nl >= 0) s = s.substring(0, nl);
        return s.trim();
    }

    private static String upperFirst(String s) {
        if (s == null || s.isEmpty()) return s;
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }

    private static String ensureTrailingPeriod(String s) {
        if (s == null || s.isEmpty()) return s;
        char c = s.charAt(s.length() - 1);
        if (c == '.' || c == '?' || c == '!') return s;
        return s + ".";
    }

    private DocGen() {}
}
