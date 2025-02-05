# Typesense Java Client ☕ 🔍

Java client library for accessing the HTTP API of [Typesense](https://typesense.org) search engine.

## Installation

The client is available on Maven central:

```groovy
dependencies {
    implementation 'org.typesense:typesense-java:1.2.0'
}
```

```xml
<dependency>
    <groupId>org.typesense</groupId>
    <artifactId>typesense-java</artifactId>
    <version>1.2.0</version>
</dependency>
```

```java
import org.typesense.api.*;
import org.typesense.model.*;
import org.typesense.resources.*;
```

## Usage

### Create a new client
```java
List<Node> nodes = new ArrayList<>();
nodes.add(
  new Node(
    "http",       // For Typesense Cloud use https
    "localhost",  // For Typesense Cloud use xxx.a1.typesense.net
    "8108"        // For Typesense Cloud use 443
  )
);

Configuration configuration = new Configuration(nodes, Duration.ofSeconds(2),"<API_KEY>");

Client client = new Client(configuration);
 ```

### Create a new collection
```java
List<Field> fields = new ArrayList<>();
fields.add(new Field().name("countryName").type(FieldTypes.STRING));
fields.add(new Field().name("capital").type(FieldTypes.STRING));
fields.add(new Field().name("gdp").type(FieldTypes.INT32).facet(true).sort(true));

CollectionSchema collectionSchema = new CollectionSchema();
collectionSchema.name("Countries").fields(fields).defaultSortingField("gdp");

client.collections().create(collectionSchema);
```

### Create a new collection from JSON file
```java
String schemaJson = new String(
    Files.readAllBytes(Paths.get("schema.json")),
    StandardCharsets.UTF_8
);
client.collections().create(schemaJson);
```

### Index a document
```java
Map<String, Object> hmap = new HashMap<>();
hmap.put("countryName","India");
hmap.put("capital","Delhi");
hmap.put("gdp", 10);

client.collections("Countries").documents().create(hmap);
```

### Upserting a document
```java
Map<String, Object> hmap = new HashMap<>();
hmap.put("countryName","India");
hmap.put("capital","Delhi");
hmap.put("gdp", 5);

client.collections("Countries").documents().upsert(hmap);
```

### Import batch of documents
```java
ImportDocumentsParameters queryParameters = new ImportDocumentsParameters();
queryParameters.action("create");

String documentList = "{\"countryName\": \"India\", \"capital\": \"Washington\", \"gdp\": 5215}\n" +
                      "{\"countryName\": \"Iran\", \"capital\": \"London\", \"gdp\": 5215}";
// Import your document as JSONL string from a file.
client.collections("Countries").documents().import_(documentList, queryParameters)
```

### Search in a collection
```java
SearchParameters searchParameters = new SearchParameters()
                                        .q("tokoyo")
                                        .queryBy("countryName,capital")
                                        .prefix("true,false");
SearchResult searchResult = client.collections("Countries").documents().search(searchParameters);
```

### Update a document
```java
Map<String, Object> hmap = new HashMap<>();
hmap.put("gdp", 8);
client.collections("Countries").documents("28").update(hmap);
```

### Retrieve a document
```java
client.collections("Countries").documents("28").retrieve();
```

### Delete a document
```java
client.collections("Countries").documents("28").delete();
```

### Delete documents using query
```java
DeleteDocumentsParameters deleteDocumentsParameters = new DeleteDocumentsParameters();
deleteDocumentsParameters.filterBy("gdp:=[2,8]");
deleteDocumentsParameters.batchSize(10);
```

### Retrieve a collection
```java
client.collections("Countries").retrieve();
```

### Retrieve all collections
```java
client.collections().retrieve();
```

### Drop a collection
```java
client.collections("Countries").delete();   
```

### Export a collection
```java
client.collections("Countries").documents().export();
```

### Create an analytics rule
```java
AnalyticsRuleSchema analyticsRule = new AnalyticsRuleSchema();
analyticsRule.setName("popular-queries");
analyticsRule.setType(AnalyticsRuleSchema.TypeEnum.POPULAR_QUERIES);
analyticsRule.setParams(new AnalyticsRuleParameters()
        .source(new AnalyticsRuleParametersSource()
                .collections(Arrays.asList("Countries")))
        .destination(new AnalyticsRuleParametersDestination()
                .collection("top_searches")));

client.analytics().rules().create(analyticsRule);
```

### Upsert an analytics rule
```java
AnalyticsRuleUpsertSchema analyticsRule = new AnalyticsRuleUpsertSchema()
        .type(AnalyticsRuleUpsertSchema.TypeEnum.NOHITS_QUERIES)
        .params(new AnalyticsRuleParameters()
                .source(new AnalyticsRuleParametersSource()
                        .collections(Arrays.asList("Countries")))
                .destination(new AnalyticsRuleParametersDestination()
                        .collection("failed_searches")));

client.analytics().rules().upsert("failed-searches", analyticsRule);
```

### Retrieve all analytics rules
```java
AnalyticsRulesRetrieveSchema rules = client.analytics().rules().retrieve();
```

### Retrieve a single analytics rule
```java
AnalyticsRuleSchema rule = client.analytics().rules("failed-searches").retrieve();
```

### Delete an analytics rule
```java
client.analytics().rules("failed-searches").delete();
```

#### Create an analytics event
```java
AnalyticsEventCreateSchema analyticsEvent = new AnalyticsEventCreateSchema()
        .type("conversion")
        .name("purchase_made")
        .data(Map.of(
                "product_id", "123",
                "user_id", "user_456",
                "amount", "99.99"
        ));

client.analytics().events().create(analyticsEvent);
```

### Upsert a stopwords set
```java
List<String> stopwords = new ArrayList<>();
stopwords.add("the");
stopwords.add("of");
stopwords.add("and");

StopwordsSetUpsertSchema stopwordsSet = new StopwordsSetUpsertSchema();
stopwordsSet.stopwords(stopwords);

client.stopwords().upsert("common-words", stopwordsSet);
```

### Retrieve a stopwords set
```java
StopwordsSetRetrieveSchema set = client.stopwords("common-words").retrieve();
```

### Retrieve all stopwords sets
```java
StopwordsSetsRetrieveAllSchema sets = client.stopwords().retrieve();
```

### Delete a stopwords set
```java
client.stopwords("common-words").delete();
```

### Create or update a search preset
```java
SearchParameters params = new SearchParameters()
        .q("bestseller")
        .queryBy("title,author")
        .sortBy("ratings_count:desc");

PresetUpsertSchema preset = new PresetUpsertSchema()
        .value(params);

client.presets().upsert("bestsellers_view", preset);
```

### Retrieve a search preset
```java
PresetSchema preset = client.presets("bestsellers_view").retrieve();
```

### Retrieve all search presets
```java
PresetsRetrieveSchema presets = client.presets().retrieve();
```

### Delete a search preset
```java
client.presets("bestsellers_view").delete();
```

### Create an API key
```java
ApiKeySchema apiKeySchema = new ApiKeySchema();
List<String> actionValues = new ArrayList<>();
List<String> collectionValues = new ArrayList<>();

actionValues.add("*");
collectionValues.add("*");

apiKeySchema.description("Admin Key").actions(actionValues).collections(collectionValues);

client.keys().create(apiKeySchema);
```

### Create search only API key
```java
ApiKeySchema apiKeySchema = new ApiKeySchema();
List<String> actionValues = new ArrayList<>();
List<String> collectionValues = new ArrayList<>();

actionValues.add("documents:search");
collectionValues.add("Countries");

apiKeySchema.description("Search only Key").actions(actionValues).collections(collectionValues);

client.keys().create(apiKeySchema);
```

### Retrieve an API key
```java
client.keys("6").retrieve();
```

### List all the API keys
```java
client.keys().retrieve();
```

### Delete an API keys
```java
client.keys("6").delete();
```

### Create or update an override
```java
SearchOverrideSchema searchOverrideSchema = new SearchOverrideSchema();

List<SearchOverrideInclude> searchOverrideIncludes = new ArrayList<>();
searchOverrideIncludes.add(new SearchOverrideInclude().id("422").position(1));
searchOverrideIncludes.add(new SearchOverrideInclude().id("54").position(2));

List<SearchOverrideExclude> searchOverrideExcludes = new ArrayList<>();
searchOverrideExcludes.add(new SearchOverrideExclude().id("287"));

searchOverrideSchema.rule(new SearchOverrideRule().query("new york").match("exact"))
                    .includes(searchOverrideIncludes)
                    .excludes(searchOverrideExcludes);

client.collections("Countries").overrides().upsert("new-york", searchOverrideSchema)
```

### Retrieve an Alias
```java
client.collections("Countries").overrides("new-york").retrieve();
```

### List all overrides
```java
client.collections("Countries").overrides().retrieve();
```

### Delete an override
```java
client.collections("Countries").overrides("new-york").delete();
```

### Upsert an Alias
```java
CollectionAliasSchema collectionAliasSchema = new CollectionAliasSchema();
collectionAliasSchema.collectionName("Countries");

client.aliases().upsert("countries2", collectionAliasSchema)
```

### Retrieve an Alias
```java
client.aliases("countries2").retrieve();
```

### List all Aliases
```java
client.aliases().retrieve();
```

### Delete an Alias
```java
client.aliases("countries2").delete();
```

### Upsert a multi-way synonym
```java
SearchSynonymSchema synonym = new SearchSynonymSchema();
synonym.addSynonymsItem("France").addSynonymsItem("Germany").addSynonymsItem("Sweden");

client.collections("Countries").synonyms().upsert("country-synonyms",synonym)
```

### Upsert a single-way synonym
```java
SearchSynonymSchema synonym = new SearchSynonymSchema();
synonym.root("europe");
synonym.addSynonymsItem("France").addSynonymsItem("Germany").addSynonymsItem("Sweden");

client.collections("Countries").synonyms().upsert("continent-synonyms",synonym)
```

### Retrieve a synonym
```java
client.collections("Countries").synonyms("continent-synonyms").retrieve();
```

### Retrieve all synonyms
```java
client.collections("Countries").synonyms().retrieve();
```

### Delete a synonym
```java
client.collections("Countries").synonyms("continent-synonyms").delete();
```

### Create snapshot (for backups)
```java
Map<String, String> query = new HashMap<>();
query.put("snapshot_path","/tmp/typesense-data-snapshot");

client.operations.perform("snapshot",query);
```

### Re-elect Leader
```java
client.operations.perform("vote");
```

### Check health
```java
client.health.retrieve();
```
## Contributing

Please read [CONTRIBUTING.md](https://github.com/typesense/typesense-java/blob/master/CONTRIBUTING.md) for details on the process for submitting pull requests to this repository.

## License
`typesense-java` is distributed under the Apache 2 license.

## Support
Please open a Github issue or join our [Slack Community](https://join.slack.com/t/typesense-community/shared_invite/zt-mx4nbsbn-AuOL89O7iBtvkz136egSJg)
