package org.typesense.api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.typesense.model.CollectionAlias;
import org.typesense.model.CollectionAliasSchema;
import org.typesense.model.CollectionAliasesResponse;

class AliasesTest {

    private Client client;
    private Helper helper;

    @BeforeEach
    void setUp() throws Exception {
        helper = new Helper();
        helper.teardown();
        client = helper.getClient();
        helper.createTestAlias();
    }

    @AfterEach
    void tearDown() throws Exception {
        helper.teardown();
    }

    @Test
    void testUpsert() throws Exception {
        CollectionAliasSchema collectionAliasSchema = new CollectionAliasSchema();
        collectionAliasSchema.collectionName("books_june11");

        System.out.println(client.aliases().upsert("books1", collectionAliasSchema));
    }

    @Test
    void testRetrieveAll() throws Exception {
        CollectionAliasesResponse collectionAliasesResponse = client.aliases().retrieve();

        System.out.println(collectionAliasesResponse);
    }

    @Test
    void testRetrieveSingleAlias() throws Exception {
        CollectionAlias collectionAlias = client.aliases("books").retrieve();

        System.out.println(collectionAlias);
    }

    @Test
    void testDelete() throws Exception {
        CollectionAlias collectionAlias = client.aliases("books").delete();

        System.out.println(collectionAlias);
    }
}