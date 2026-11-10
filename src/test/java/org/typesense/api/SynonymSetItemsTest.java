package org.typesense.api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.typesense.api.exceptions.ObjectNotFound;
import org.typesense.model.SynonymItemDeleteSchema;
import org.typesense.model.SynonymItemSchema;
import org.typesense.model.SynonymItemUpsertSchema;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SynonymSetItemsTest {

    private Client client;
    private Helper helper;
    private String synonymSetName;

    @BeforeEach
    void setUp() throws Exception {
        helper = new Helper();
        client = helper.getClient();

        if (!Helper.isV30OrAbove(client)) {
            org.junit.jupiter.api.Assumptions.assumeTrue(false, "Skipping test - requires Typesense v30 or above");
        }

        helper.teardown();
        synonymSetName = "test-synonym-set-" + System.currentTimeMillis();
        client.synonymSets().upsert(synonymSetName, helper.createTestSynonymSetData());
    }

    @AfterEach
    void tearDown() throws Exception {
        helper.teardown();
    }

    @Test
    void testRetrieveAll() throws Exception {
        SynonymItemSchema[] result = client.synonymSet(synonymSetName).items().retrieve();

        assertEquals(1, result.length);
        assertEquals("dummy", result[0].getId());
        assertEquals(Arrays.asList("foo", "bar", "baz"), result[0].getSynonyms());
    }

    @Test
    void testRetrieve() throws Exception {
        SynonymItemSchema result = client.synonymSet(synonymSetName).items("dummy").retrieve();

        assertEquals("dummy", result.getId());
        assertEquals(Arrays.asList("foo", "bar", "baz"), result.getSynonyms());
    }

    @Test
    void testUpsert() throws Exception {
        SynonymItemUpsertSchema item = new SynonymItemUpsertSchema()
                .root("smart phone")
                .synonyms(Arrays.asList("iphone", "android"));

        SynonymItemSchema result = client.synonymSet(synonymSetName).items("smart?phone").upsert(item);

        assertEquals("smart?phone", result.getId());
        assertEquals("smart phone", result.getRoot());
        assertEquals(Arrays.asList("iphone", "android"), result.getSynonyms());
        assertEquals(2, client.synonymSet(synonymSetName).items().retrieve().length);
    }

    @Test
    void testUpsertReplacesExistingItem() throws Exception {
        SynonymItemUpsertSchema item = new SynonymItemUpsertSchema().synonyms(Arrays.asList("foo", "qux"));

        client.synonymSet(synonymSetName).items("dummy").upsert(item);

        SynonymItemSchema result = client.synonymSet(synonymSetName).items("dummy").retrieve();
        assertEquals(Arrays.asList("foo", "qux"), result.getSynonyms());
        assertEquals(1, client.synonymSet(synonymSetName).items().retrieve().length);
    }

    @Test
    void testUpsertIntoMissingSet() {
        SynonymItemUpsertSchema item = new SynonymItemUpsertSchema().synonyms(Arrays.asList("foo", "bar"));

        assertThrows(ObjectNotFound.class, () -> client.synonymSet("missing-set").items("dummy").upsert(item));
    }

    @Test
    void testDelete() throws Exception {
        SynonymItemDeleteSchema result = client.synonymSet(synonymSetName).items("dummy").delete();

        assertEquals("dummy", result.getId());
        assertThrows(ObjectNotFound.class, () -> client.synonymSet(synonymSetName).items("dummy").retrieve());
    }
}
