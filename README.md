# Typesense Java Client

Java client for the Typesense API: https://github.com/typesense/typesense

## Installation

```

```

## Usage

### Create a new client
```java
ArrayList<Node> nodes = new ArrayList<>();
nodes.add(new Node("http","localhost","3001"));
Configuration configuration = new Configuration(nodes, Duration.ofSeconds(3),"xyz");
Client client = client = new Client(configuration);
 ```

### Create a collection
```java
ArrayList<Field> fields = new ArrayList<>();
fields.add(new Field().name("countryName").type("string"));
fields.add(new Field().name("capital").type("string"));
fields.add(new Field().name("gdp").type("int32").facet(true));

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