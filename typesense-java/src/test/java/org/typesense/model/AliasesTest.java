package org.typesense.model;

import junit.framework.TestCase;
import org.typesense.api.CollectionAlias;
import org.typesense.api.CollectionAliasesResponse;
import org.typesense.resources.Node;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;

public class AliasesTest extends TestCase {

    private Client client;

    public void setUp() throws Exception {
        super.setUp();

        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(new Node("http","localhost","3001"));

        Configuration configuration = new Configuration(nodes, Duration.ofSeconds(3),"xyz");

        this.client = new Client(configuration);
    }

    public void testUpsert() {
        CollectionAlias collectionAlias = new CollectionAlias();
        collectionAlias.name("companies").collectionName("companies_june11");

        System.out.println(this.client.aliases().upsert("companies", collectionAlias));


        collectionAlias.name("books").collectionName("books_june11");

        System.out.println(this.client.aliases().upsert("books", collectionAlias));
    }

    public void testRetrieveAll() {
        CollectionAliasesResponse collectionAliasesResponse = this.client.aliases().retrieve();

        System.out.println(collectionAliasesResponse);
    }

    public void testRetrieveSingleAlias() {
        CollectionAlias collectionAlias = this.client.aliases("books").retrieve();

        System.out.println(collectionAlias);
    }

    public void testDelete() {
        CollectionAlias collectionAlias = this.client.aliases("books").delete();

        System.out.println(collectionAlias);
    }
}