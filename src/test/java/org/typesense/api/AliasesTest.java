package org.typesense.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.typesense.TypesenseContainer;
import org.typesense.model.CollectionAlias;
import org.typesense.model.CollectionAliasSchema;
import org.typesense.model.CollectionAliasesResponse;

@Testcontainers
class AliasesTest {
    
    @Container
    static TypesenseContainer typesense = new TypesenseContainer(Helper.IMAGE);

    private Client client;
    private Helper helper;

    @BeforeEach
    void setUp() throws Exception {
        helper = new Helper(typesense);
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
    void testUpsertWithURLEncodedName() throws Exception {
        CollectionAliasSchema collectionAliasSchema = new CollectionAliasSchema();
        collectionAliasSchema.collectionName("books_june11");

        CollectionAlias res = client.aliases().upsert("books1 ~!@#$%^&*()_++-=/'", collectionAliasSchema);
        assertEquals("books1 ~!@#$%^&*()_++-=/'", res.getName());
        System.out.println(res);
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