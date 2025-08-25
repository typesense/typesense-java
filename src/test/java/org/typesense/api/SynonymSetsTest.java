package org.typesense.api;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.typesense.model.SynonymItemSchema;
import org.typesense.model.SynonymSetCreateSchema;
import org.typesense.model.SynonymSetSchema;

import static org.junit.jupiter.api.Assertions.*;

class SynonymSetsTest {

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
    void testUpsert() throws Exception {
        SynonymSetCreateSchema synonymSetData = helper.createTestSynonymSetData();

        SynonymSetSchema result = client.synonymSets().upsert(synonymSetName, synonymSetData);

        assertEquals(synonymSetName, result.getName());
        assertEquals(1, result.getItems().size());
        assertEquals("dummy", result.getItems().get(0).getId());
        assertEquals(List.of("foo", "bar", "baz"), result.getItems().get(0).getSynonyms());
    }

    @Test
    void testRetrieve() throws Exception {
        SynonymSetCreateSchema synonymSetData = helper.createTestSynonymSetData();
        client.synonymSets().upsert(synonymSetName, synonymSetData);

        SynonymSetSchema[] result = client.synonymSets().retrieve();

        assertNotNull(result);
        assertTrue(result.length >= 1);

        SynonymSetSchema foundSynonymSet = null;
        for (SynonymSetSchema ss : result) {
            if (synonymSetName.equals(ss.getName())) {
                foundSynonymSet = ss;
                break;
            }
        }

        assertNotNull(foundSynonymSet);
        assertEquals(synonymSetName, foundSynonymSet.getName());
        assertEquals(1, foundSynonymSet.getItems().size());
        assertEquals("dummy", foundSynonymSet.getItems().get(0).getId());
        assertEquals(List.of("foo", "bar", "baz"), foundSynonymSet.getItems().get(0).getSynonyms());
    }
} 
