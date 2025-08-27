package org.typesense.api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.typesense.model.SynonymItemSchema;
import org.typesense.model.SynonymSetCreateSchema;
import org.typesense.model.SynonymSetSchema;
import org.typesense.model.SynonymSetDeleteSchema;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SynonymSetTest {

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
        helper.createTestCollection();
        synonymSetName = "test-synonym-set-" + System.currentTimeMillis();
    }

    @AfterEach
    void tearDown() throws Exception {
        try {
            client.synonymSet(synonymSetName).delete();
        } catch (Exception e) {
        }
        helper.teardown();
    }

    @Test
    void testRetrieve() throws Exception {
        SynonymSetCreateSchema synonymSetData = helper.createTestSynonymSetData();
        client.synonymSets().upsert(synonymSetName, synonymSetData);

        SynonymSetCreateSchema result = client.synonymSet(synonymSetName).retrieve();

        assertNotNull(result);
        assertEquals(1, result.getItems().size());
        assertEquals("dummy", result.getItems().get(0).getId());
        assertEquals(Arrays.asList("foo", "bar", "baz"), result.getItems().get(0).getSynonyms());
    }

    @Test
    void testUpdate() throws Exception {
        SynonymSetCreateSchema originalData = helper.createTestSynonymSetData();
        client.synonymSets().upsert(synonymSetName, originalData);

        SynonymSetCreateSchema updatedData = new SynonymSetCreateSchema();
        List<SynonymItemSchema> items = new ArrayList<>();
        SynonymItemSchema item = new SynonymItemSchema();
        item.setId("dummy");
        item.setSynonyms(Arrays.asList("foo", "bar", "baz", "qux"));
        items.add(item);
        updatedData.setItems(items);

        SynonymSetSchema result = client.synonymSet(synonymSetName).upsert(updatedData);

        assertEquals(synonymSetName, result.getName());
        assertEquals(1, result.getItems().size());
        assertEquals("dummy", result.getItems().get(0).getId());
        assertEquals(Arrays.asList("foo", "bar", "baz", "qux"), result.getItems().get(0).getSynonyms());
    }

    @Test
    void testDelete() throws Exception {
        SynonymSetCreateSchema synonymSetData = helper.createTestSynonymSetData();
        client.synonymSets().upsert(synonymSetName, synonymSetData);

        SynonymSetDeleteSchema result = client.synonymSet(synonymSetName).delete();

        assertEquals(synonymSetName, result.getName());

        assertThrows(Exception.class, () -> {
            client.synonymSet(synonymSetName).retrieve();
        });
    }
} 