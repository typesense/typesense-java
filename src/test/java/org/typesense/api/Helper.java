package org.typesense.api;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.typesense.model.AnalyticsRuleParameters;
import org.typesense.model.AnalyticsRuleParametersDestination;
import org.typesense.model.AnalyticsRuleParametersSource;
import org.typesense.model.AnalyticsRuleParametersSourceEvents;
import org.typesense.model.AnalyticsRuleSchema;
import org.typesense.model.AnalyticsRuleUpsertSchema;
import org.typesense.model.ApiKey;
import org.typesense.model.ApiKeySchema;
import org.typesense.model.ApiKeysResponse;
import org.typesense.model.CollectionAlias;
import org.typesense.model.CollectionAliasSchema;
import org.typesense.model.CollectionAliasesResponse;
import org.typesense.model.CollectionResponse;
import org.typesense.model.CollectionSchema;
import org.typesense.model.ConversationModelCreateSchema;
import org.typesense.model.ConversationModelSchema;
import org.typesense.model.Field;
import org.typesense.model.SearchOverrideInclude;
import org.typesense.model.SearchOverrideRule;
import org.typesense.model.SearchOverrideSchema;
import org.typesense.model.SearchSynonymSchema;
import org.typesense.model.StemmingDictionaryWords;
import org.typesense.model.StopwordsSetSchema;
import org.typesense.model.StopwordsSetUpsertSchema;
import org.typesense.model.StopwordsSetsRetrieveAllSchema;
import org.typesense.resources.Node;

public class Helper {
    private final Client client;

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

    public void createConversationCollection() throws Exception {
        List<Field> fields = new ArrayList<>();
        fields.add(new Field().name("conversation_id").type(FieldTypes.STRING));
        fields.add(new Field().name("model_id").type(FieldTypes.STRING));
        fields.add(new Field().name("timestamp").type(FieldTypes.INT32));
        fields.add(new Field().name("role").type(FieldTypes.STRING).index(false));
        fields.add(new Field().name("message").type(FieldTypes.STRING).index(false));

        CollectionSchema collectionSchema = new CollectionSchema();
        collectionSchema.name("conversations").fields(fields);

        client.collections().create(collectionSchema);
    }

    public void createTestConversationModel() throws Exception {
        ConversationModelCreateSchema model = new ConversationModelCreateSchema();
        model.setId("conv-model");
        model.setHistoryCollection("conversations");
        model.setSystemPrompt(
                "You are an assistant for question-answering. You can only make conversations based on the provided context. If a response cannot be formed strictly using the provided context, politely say you do not have knowledge about that topic.");
        model.setApiKey(System.getenv("OPENAI_API_KEY"));
        model.setConversationModelCreateSchemaModelName("openai/gpt-3.5-turbo");
        model.setConversationModelCreateSchemaMaxBytes(16384);
        model.setConversationModelCreateSchemaHistoryCollection("conversations");

        client.conversations().models().create(model);
    }

    public void createTestQueryCollection() throws Exception {
        List<Field> fields = new ArrayList<>();
        fields.add(new Field().name("q").type((FieldTypes.STRING)));
        fields.add(new Field().name("count").type((FieldTypes.INT32)));

        CollectionSchema collectionSchema = new CollectionSchema();
        collectionSchema.name("queries").fields(fields);

        client.collections().create(collectionSchema);
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

    public void createTestAnalyticsRule() throws Exception {
        AnalyticsRuleUpsertSchema analyticsRuleSchema = new AnalyticsRuleUpsertSchema()
                .type(AnalyticsRuleUpsertSchema.TypeEnum.NOHITS_QUERIES)
                .params(new AnalyticsRuleParameters()
                        .source(new AnalyticsRuleParametersSource()
                                .collections(Arrays.asList("books"))
                                .events(Arrays.asList(
                                        new AnalyticsRuleParametersSourceEvents()
                                                .type("search")
                                                .name("products_search_event"))))
                        .destination(new AnalyticsRuleParametersDestination()
                                .collection("queries")));

        client.analytics().rules().upsert("analytics-rule", analyticsRuleSchema);
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
        for (AnalyticsRuleSchema r : analyticsRules.retrieve().getRules()) {
            client.analytics().rules(r.getName()).delete();
        }

        ApiKeysResponse apiKeysResponse = client.keys().retrieve();
        for (ApiKey k : apiKeysResponse.getKeys()) {
            client.keys(k.getId()).delete();
        }

        StopwordsSetsRetrieveAllSchema stopwords = client.stopwords().retrieve();
        for (StopwordsSetSchema s : stopwords.getStopwords()) {
            client.stopwords(s.getId()).delete();
        }

        ConversationModelSchema[] models = client.conversations().models().retrieve();
        for (ConversationModelSchema s : models) {
            client.conversations().models(s.getConversationModelSchemaId()).delete();
        }
    }
}
