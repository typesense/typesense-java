# Typesense Java Client ☕ 🔍

Java client library for accessing the HTTP API of [Typesense](https://typesense.org) search engine.

Note: This package is still under development. Some existing APIs might change or new APIs might be available in the future.

## Installation

Download the JAR file from the [releases](https://github.com/typesense/typesense-java/releases) section of this repository and add it to your project.

```java
import org.typesense.api.*;
import org.typesense.model.*;
import org.typesense.resources.*;
```

## Usage

### Create a new client
```java
ArrayList<Node> nodes = new ArrayList<>();
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
ArrayList<Field> fields = new ArrayList<>();
fields.add(new Field().name("countryName").type(FieldTypes.STRING));
fields.add(new Field().name("capital").type(FieldTypes.STRING));
fields.add(new Field().name("gdp").type(FieldTypes.INT32).facet(true));

CollectionSchema collectionSchema = new CollectionSchema();
collectionSchema.name("Countries").fields(fields).defaultSortingField("gdp");

client.collections().create(collectionSchema);
```

### Index a document
```java
HashMap<String, Object> hmap = new HashMap<>();
hmap.put("countryName","India");
hmap.put("capital","Delhi");
hmap.put("gdp", 10);

client.collections("contryName").documents().create(hmap);
```

### Upserting a document
```java
HashMap<String, Object> hmap = new HashMap<>();
hmap.put("countryName","India");
hmap.put("capital","Delhi");
hmap.put("gdp", 5);

client.collections("contryName").documents().upsert(hmap);
```

### Import batch of documents
```java
ImportDocumentsParameters queryParameters = new ImportDocumentsParameters();
queryParameters.action("create");

String documentList = "{\"countryName\": \"India\", \"capital\": \"Washington\", \"gdp\": 5215}\n" +
                      "{\"countryName\": \"Iran\", \"capital\": \"London\", \"gdp\": 5215}";
// Import your document as JSONL string from a file.
client.collections("countries").documents().import_(documentList, queryParameters)
```

### Search in a collection
```java
SearchParameters searchParameters = new SearchParameters()
                                        .q("tokoyo")
                                        .queryBy("countryName,capital")
                                        .prefix("true,false");
SearchResult searchResult = client.collections("countries").documents().search(searchParameters);
```

### Update a document
```java
HashMap<String, Object> hmap = new HashMap<>();
hmap.put("gdp", 8);
client.collections("countries").documents("28").update(hmap);
```

### Retrieve a document
```java
client.collections("countries").documents("28").retrieve();
```

### Delete a document
```java
client.collections("countries").documents("28").delete();
```

### Delete documents using query
```java
DeleteDocumentsParameters deleteDocumentsParameters = new DeleteDocumentsParameters();
deleteDocumentsParameters.filterBy("gdp:=[2,8]");
deleteDocumentsParameters.batchSize(10);
```

### Retrieve a collection
```java
client.collections("countries").retrieve();
```

### Retrieve all collections
```java
client.collections().retrieve();
```

### Drop a collection
```java
client.collections("countries").delete();   
```

### Export a collection
```java
client.collections("Countries").documents().export();
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
collectionValues.add("countries");

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

client.collections("countries").overrides().upsert("new-york", searchOverrideSchema)
```

### Retrieve an Alias
```java
client.collections("countries").overrides("new-york").retrieve();
```

### List all overrides
```java
client.collections("countries").overrides().retrieve();
```

### Delete an override
```java
client.collections("countries").overrides("new-york").delete();
```

### Upsert an Alias
```java
CollectionAliasSchema collectionAliasSchema = new CollectionAliasSchema();
collectionAliasSchema.collectionName("countries");

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

client.collections("countries").synonyms().upsert("country-synonyms",synonym)
```

### Upsert a single-way synonym
```java
SearchSynonymSchema synonym = new SearchSynonymSchema();
synonym.root("europe");
synonym.addSynonymsItem("France").addSynonymsItem("Germany").addSynonymsItem("Sweden");

client.collections("countries").synonyms().upsert("continent-synonyms",synonym)
```

### Retrieve a synonym
```java
client.collections("countries").synonyms("continent-synonyms").retrieve();
```

### Retrieve all synonyms
```java
client.collections("countries").synonyms().retrieve();
```

### Delete a synonym
```java
client.collections("countries").synonyms("continent-synonyms").delete();
```

### Create snapshot (for backups)
```java
HashMap<String, String> query = new HashMap<>();
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
