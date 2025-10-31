package org.typesense.api;

import java.util.Arrays;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.typesense.model.CurationItemCreateSchema;
import org.typesense.model.CurationSetCreateSchema;
import org.typesense.model.CurationSetSchema;

import static org.junit.jupiter.api.Assertions.*;

class CurationSetsTest {

    private Client client;
    private Helper helper;
    private String curationSetName;

    @BeforeEach
    void setUp() throws Exception {
        helper = new Helper();
        client = helper.getClient();
        
        if (!Helper.isV30OrAbove(client)) {
            org.junit.jupiter.api.Assumptions.assumeTrue(false, "Skipping test - requires Typesense v30 or above");
        }
        
        helper.teardown();
        helper.createTestCollection();
        curationSetName = "test-curation-set-" + System.currentTimeMillis();
    }

    @AfterEach
    void tearDown() throws Exception {
        try {
            client.curationSet(curationSetName).delete();
        } catch (Exception e) {
        }
        helper.teardown();
    }

    @Test
    void testUpsert() throws Exception {
        CurationSetCreateSchema curationSetData = helper.createTestCurationSetData();

        CurationSetSchema result = client.curationSets().upsert(curationSetName, curationSetData);

        assertEquals(curationSetName, result.getName());
        assertEquals(1, result.getItems().size());
        assertEquals("dummy", result.getItems().get(0).getId());
        assertEquals("apple", result.getItems().get(0).getRule().getQuery());
        assertNotNull(result.getItems().get(0).getIncludes());
        assertEquals(2, result.getItems().get(0).getIncludes().size());
        assertEquals("422", result.getItems().get(0).getIncludes().get(0).getId());
        assertEquals("54", result.getItems().get(0).getIncludes().get(1).getId());
        assertNotNull(result.getItems().get(0).getExcludes());
        assertEquals(1, result.getItems().get(0).getExcludes().size());
        assertEquals("287", result.getItems().get(0).getExcludes().get(0).getId());
    }

    @Test
    void testRetrieve() throws Exception {
        CurationSetCreateSchema curationSetData = helper.createTestCurationSetData();
        client.curationSets().upsert(curationSetName, curationSetData);

        CurationSetSchema[] result = client.curationSets().retrieve();

        assertNotNull(result);
        assertTrue(result.length >= 1);

        CurationSetSchema foundCurationSet = null;
        for (CurationSetSchema cs : result) {
            if (curationSetName.equals(cs.getName())) {
                foundCurationSet = cs;
                break;
            }
        }

        assertNotNull(foundCurationSet);
        assertEquals(curationSetName, foundCurationSet.getName());
        assertEquals(1, foundCurationSet.getItems().size());
        assertEquals("dummy", foundCurationSet.getItems().get(0).getId());
        assertEquals("apple", foundCurationSet.getItems().get(0).getRule().getQuery());
        assertNotNull(foundCurationSet.getItems().get(0).getIncludes());
        assertEquals(2, foundCurationSet.getItems().get(0).getIncludes().size());
        assertEquals("422", foundCurationSet.getItems().get(0).getIncludes().get(0).getId());
        assertEquals("54", foundCurationSet.getItems().get(0).getIncludes().get(1).getId());
        assertNotNull(foundCurationSet.getItems().get(0).getExcludes());
        assertEquals(1, foundCurationSet.getItems().get(0).getExcludes().size());
        assertEquals("287", foundCurationSet.getItems().get(0).getExcludes().get(0).getId());
    }
}
