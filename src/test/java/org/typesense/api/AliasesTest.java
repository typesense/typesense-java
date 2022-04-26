package org.typesense.api;

import junit.framework.TestCase;
import org.typesense.api.exceptions.TypesenseError;
import org.typesense.model.CollectionAlias;
import org.typesense.model.CollectionAliasSchema;
import org.typesense.model.CollectionAliasesResponse;
import org.typesense.resources.Node;

import java.time.Duration;
import java.util.ArrayList;

public class AliasesTest extends TestCase {

    private Client client;
    private Helper helper;

    public void setUp() throws Exception {
        super.setUp();
        helper = new Helper();
        client = helper.getClient();
        helper.createTestAlias();
    }

    public void tearDown() throws Exception {
        super.tearDown();
        helper.teardown();
    }

    public void testUpsert() throws Exception {
        CollectionAliasSchema collectionAliasSchema = new CollectionAliasSchema();
        collectionAliasSchema.collectionName("books_june11");

        System.out.println(client.aliases().upsert("books1", collectionAliasSchema));
    }

    public void testRetrieveAll() throws Exception {
        CollectionAliasesResponse collectionAliasesResponse = client.aliases().retrieve();

        System.out.println(collectionAliasesResponse);
    }

    public void testRetrieveSingleAlias() throws Exception {
        CollectionAlias collectionAlias = client.aliases("books").retrieve();

        System.out.println(collectionAlias);
    }

    public void testDelete() throws Exception {
        CollectionAlias collectionAlias = client.aliases("books").delete();

        System.out.println(collectionAlias);
    }
}