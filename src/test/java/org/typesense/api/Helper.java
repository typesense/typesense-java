package org.typesense.api;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;
import org.typesense.model.ApiKey;
import org.typesense.model.ApiKeySchema;
import org.typesense.model.ApiKeysResponse;
import org.typesense.model.CollectionAlias;
import org.typesense.model.CollectionAliasSchema;
import org.typesense.model.CollectionAliasesResponse;
import org.typesense.model.CollectionResponse;
import org.typesense.model.CollectionSchema;
import org.typesense.model.Field;
import org.typesense.model.SearchOverrideInclude;
import org.typesense.model.SearchOverrideRule;
import org.typesense.model.SearchOverrideSchema;
import org.typesense.model.SearchSynonymSchema;
import org.typesense.model.StemmingDictionaryWords;
import org.typesense.model.StopwordsSetSchema;
import org.typesense.model.StopwordsSetUpsertSchema;
import org.typesense.model.StopwordsSetsRetrieveAllSchema;
import org.typesense.model.SynonymItemSchema;
import org.typesense.model.SynonymSetCreateSchema;
import org.typesense.model.SynonymSetSchema;
import org.typesense.model.SynonymSetsRetrieveSchema;
import org.typesense.resources.Node;
import org.typesense.model.AnalyticsRuleCreate;
import org.typesense.model.AnalyticsRuleCreateParams;
import org.typesense.model.AnalyticsRule;
import org.typesense.api.AnalyticsRules;

public class Helper {
    private final Client client;

    public static boolean isV30OrAbove(Client client) {
        try {
            Map<String, Object> debugResponse = client.debug.retrieve();
            if (debugResponse == null) {
                return false;
            }
            
            Object version = debugResponse.get("version");
            if (version == null) {
                return false;
            }
            
            String versionStr = version.toString();
            if ("nightly".equals(versionStr)) {
                return true;
            }
            
            if (!versionStr.startsWith("v")) {
                return false;
            }
            
            String numberedVersion = versionStr.substring(1);
            String[] parts = numberedVersion.split("\\.");
            if (parts.length == 0) {
                return false;
            }
            
            int majorVersion = Integer.parseInt(parts[0]);
            return majorVersion >= 30;
            
        } catch (Exception e) {
            return false;
        }
    }

    Helper() {
        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node("http", "localhost", "8108"));

        Configuration configuration = new Configuration(nodes, Duration.ofSeconds(3), "xyz");

        this.client = new Client(configuration);
    }

    public void createTestCollection() throws Exception {
        List<Field> fields = new ArrayList<>();
        fields.add(new Field().name(".*").type(FieldTypes.AUTO).optional(true));

        CollectionSchema collectionSchema = new CollectionSchema();
        collectionSchema.name("books").fields(fields);

        client.collections().create(collectionSchema);
    }

    public void createTestQueryCollection() throws Exception {
        List<Field> fields = new ArrayList<>();
        fields.add(new Field().name("q").type((FieldTypes.STRING)));
        fields.add(new Field().name("count").type((FieldTypes.INT32)));

        CollectionSchema collectionSchema = new CollectionSchema();
        collectionSchema.name("queries").fields(fields);

        client.collections().create(collectionSchema);
    }

    public void createTestQueryDocument() throws Exception {
        HashMap<String, Object> hmap = new HashMap<>();
        hmap.put("q", "running shoes");
        hmap.put("count", 42);
        hmap.put("id", "query1");

        client.collections("queries").documents().create(hmap);
    }

    public void createTestDocument() throws Exception {
        String[] authors = { "shakspeare", "william" };
        HashMap<String, Object> hmap = new HashMap<>();
        hmap.put("title", "Romeo and juliet");
        hmap.put("authors", authors);
        hmap.put("publication_year", 1666);
        hmap.put("ratings_count", 124);
        hmap.put("average_rating", 3.2);
        hmap.put("id", "1");

        client.collections("books").documents().create(hmap);
    }

    public Client getClient() {
        return this.client;
    }

    public void createTestAlias() throws Exception {
        CollectionAliasSchema collectionAliasSchema = new CollectionAliasSchema();
        collectionAliasSchema.collectionName("books_june11");
        client.aliases().upsert("books", collectionAliasSchema);
    }

    public ApiKey createTestKey() throws Exception {
        ApiKeySchema apiKeySchema = new ApiKeySchema();
        List<String> actionValues = new ArrayList<>();
        List<String> collectionValues = new ArrayList<>();
        actionValues.add("*");
        collectionValues.add("*");
        apiKeySchema.description("Admin Key").actions(actionValues).collections(collectionValues);
        return client.keys().create(apiKeySchema);
    }

    public void createTestOverrirde() throws Exception {
        SearchOverrideSchema searchOverrideSchema = new SearchOverrideSchema();
        List<SearchOverrideInclude> searchOverrideIncludes = new ArrayList<>();
        searchOverrideIncludes.add(new SearchOverrideInclude().id("422").position(1));
        searchOverrideSchema.rule(new SearchOverrideRule().query("apple").match(SearchOverrideRule.MatchEnum.EXACT))
                .includes(searchOverrideIncludes);
        client.collections("books").overrides().upsert("customize-apple", searchOverrideSchema);
    }

    public void createTestSynonym() throws Exception {
        SearchSynonymSchema synonym = new SearchSynonymSchema();
        synonym.addSynonymsItem("blazer").addSynonymsItem("coat").addSynonymsItem("jacket");

        client.collections("books").synonyms().upsert("coat-synonyms", synonym);
    }

    public void createTestAnalyticsCollection() throws Exception {
        List<Field> fields = new ArrayList<>();
        fields.add(new Field().name("rule_name").type(FieldTypes.STRING));
        fields.add(new Field().name("event_type").type(FieldTypes.STRING));
        fields.add(new Field().name("counter_value").type(FieldTypes.INT32));
        fields.add(new Field().name("timestamp").type(FieldTypes.INT64));

        CollectionSchema collectionSchema = new CollectionSchema();
        collectionSchema.name("analytics_data").fields(fields);

        client.collections().create(collectionSchema);
    }

    public String createTestAnalyticsRule() throws Exception {
        String ruleName = "analytics-rule-" + System.currentTimeMillis();
        
        AnalyticsRuleCreate analyticsRule = new AnalyticsRuleCreate()
                .name(ruleName)
                .type(AnalyticsRuleCreate.TypeEnum.COUNTER)
                .collection("analytics_data")
                .eventType("click")
                .params(new AnalyticsRuleCreateParams()
                        .counterField("num_employees")
                        .weight(1));

        List<AnalyticsRuleCreate> rules = new ArrayList<>();
        rules.add(analyticsRule);

        client.analytics().rules().create(rules);
        return ruleName;
    }

    public void createTestStopwordsSet() throws Exception {
        List<String> stopwords = new ArrayList<>();
        stopwords.add("the");
        stopwords.add("of");
        stopwords.add("and");

        StopwordsSetUpsertSchema stopwordsSetSchema = new StopwordsSetUpsertSchema();
        stopwordsSetSchema.stopwords(stopwords);

        client.stopwords().upsert("common-words", stopwordsSetSchema);
    }


    public void createStemmingDictionary() throws Exception{
        List<StemmingDictionaryWords> stemmingDictionaryWords = new ArrayList<>();

        stemmingDictionaryWords.add(new StemmingDictionaryWords().word("ran").root("run"));
        stemmingDictionaryWords.add(new StemmingDictionaryWords().word("running").root("run"));

        client.stemming().dictionaries().upsert("irregular-plurals", stemmingDictionaryWords);
    }

    public SynonymSetCreateSchema createTestSynonymSetData() {
        SynonymItemSchema synonymItem = new SynonymItemSchema();
        synonymItem.setId("dummy");
        synonymItem.setSynonyms(Arrays.asList("foo", "bar", "baz"));

        SynonymSetCreateSchema synonymSetData = new SynonymSetCreateSchema();
        synonymSetData.setItems(Arrays.asList(synonymItem));
        return synonymSetData;
    }

    public void teardown() throws Exception {
        CollectionResponse[] collectionResponses = client.collections().retrieve();
        for (CollectionResponse c : collectionResponses) {
            client.collections(c.getName()).delete();
        }

        CollectionAliasesResponse collectionAliasesResponse = client.aliases().retrieve();
        for (CollectionAlias a : collectionAliasesResponse.getAliases()) {
            client.aliases(a.getName()).delete();
        }

        AnalyticsRules analyticsRules = client.analytics().rules();
        List<AnalyticsRule> rules = analyticsRules.retrieve();
        if (rules != null) {
            for (AnalyticsRule r : rules) {
                client.analytics().rules(r.getName()).delete();
            }
        }

        ApiKeysResponse apiKeysResponse = client.keys().retrieve();
        for (ApiKey k : apiKeysResponse.getKeys()) {
            client.keys(k.getId()).delete();
        }

        StopwordsSetsRetrieveAllSchema stopwords = client.stopwords().retrieve();
        for (StopwordsSetSchema s : stopwords.getStopwords()) {
            client.stopwords(s.getId()).delete();
        }

        try {
            SynonymSetSchema[] synonymSets = client.synonymSets().retrieve();
            for (SynonymSetSchema s : synonymSets) {
                client.synonymSet(s.getName()).delete();
            }
        } catch (Exception e) {
        }
    }
}
