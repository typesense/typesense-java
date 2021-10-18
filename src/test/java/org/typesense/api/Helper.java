package org.typesense.api;

import org.typesense.model.*;
import org.typesense.resources.Node;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;

public class Helper {
    private final Client client;

    Helper() {
        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(new Node("http","localhost","8108"));

        Configuration configuration = new Configuration(nodes, Duration.ofSeconds(3),"xyz");

        this.client = new Client(configuration);
    }

    public void createTestCollection() {
        ArrayList<Field> fields = new ArrayList<>();
        fields.add(new Field().name(".*").type(Field.TypeEnum.AUTO).optional(true));

        CollectionSchema collectionSchema = new CollectionSchema();
        collectionSchema.name("books").fields(fields);

        client.collections().create(collectionSchema);
    }

    public void createTestDocument() {
        String[] authors = {"shakspeare","william"};
        HashMap<String, Object> hmap = new HashMap<>();
        hmap.put("title","Romeo and juliet");
        hmap.put("authors",authors);
        hmap.put("publication_year",1666);
        hmap.put("ratings_count",124);
        hmap.put("average_rating",3.2);
        hmap.put("id","1");

        client.collections("books").documents().create(hmap);
    }

    public Client getClient() {
        return this.client;
    }

    public void createTestAlias() {
        CollectionAliasSchema collectionAliasSchema = new CollectionAliasSchema();
        collectionAliasSchema.collectionName("books_june11");
        client.aliases().upsert("books", collectionAliasSchema);
    }

    public void teardown() {
        CollectionResponse[] collectionResponses = client.collections().retrieve();
        for(CollectionResponse c:collectionResponses) {
            client.collections(c.getName()).delete();
        }

        CollectionAliasesResponse collectionAliasesResponse = client.aliases().retrieve();
        for(CollectionAlias a:collectionAliasesResponse.getAliases()) {
            client.aliases(a.getName()).delete();
        }
    }
}
