package org.typesense.api;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.typesense.model.CurationItemCreateSchema;
import org.typesense.model.CurationSetCreateSchema;
import org.typesense.model.CurationSetSchema;
import org.typesense.model.CurationSetDeleteSchema;
import org.typesense.model.CurationRule;
import org.typesense.model.CurationInclude;
import org.typesense.model.CurationExclude;

import static org.junit.jupiter.api.Assertions.*;

class CurationSetTest {

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
    void testRetrieve() throws Exception {
        CurationSetCreateSchema curationSetData = helper.createTestCurationSetData();
        client.curationSets().upsert(curationSetName, curationSetData);

        CurationSetCreateSchema result = client.curationSet(curationSetName).retrieve();

        assertNotNull(result);
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
    void testUpdate() throws Exception {
        CurationSetCreateSchema originalData = helper.createTestCurationSetData();
        client.curationSets().upsert(curationSetName, originalData);

        CurationRule updatedRule = new CurationRule();
        updatedRule.setQuery("updated query");
        updatedRule.setMatch(CurationRule.MatchEnum.EXACT);

        CurationInclude include1 = new CurationInclude();
        include1.setId("422");
        include1.setPosition(1);
        CurationInclude include2 = new CurationInclude();
        include2.setId("54");
        include2.setPosition(2);
        CurationInclude include3 = new CurationInclude();
        include3.setId("999");
        include3.setPosition(3);

        CurationExclude exclude1 = new CurationExclude();
        exclude1.setId("287");

        CurationItemCreateSchema updatedItem = new CurationItemCreateSchema();
        updatedItem.setId("dummy");
        updatedItem.setRule(updatedRule);
        updatedItem.setIncludes(Arrays.asList(include1, include2, include3));
        updatedItem.setExcludes(Arrays.asList(exclude1));
        updatedItem.setRemoveMatchedTokens(true);
        updatedItem.setFilterBy("category:=Electronics");
        updatedItem.setStopProcessing(true);

        CurationSetCreateSchema updatedData = new CurationSetCreateSchema();
        updatedData.setItems(Arrays.asList(updatedItem));
        updatedData.setDescription("Updated test curation set");

        CurationSetSchema result = client.curationSet(curationSetName).upsert(updatedData);

        assertEquals(curationSetName, result.getName());
        assertEquals(1, result.getItems().size());
        assertEquals("dummy", result.getItems().get(0).getId());
        assertEquals("updated query", result.getItems().get(0).getRule().getQuery());
        assertNotNull(result.getItems().get(0).getIncludes());
        assertEquals(3, result.getItems().get(0).getIncludes().size());
        assertEquals("422", result.getItems().get(0).getIncludes().get(0).getId());
        assertEquals("54", result.getItems().get(0).getIncludes().get(1).getId());
        assertEquals("999", result.getItems().get(0).getIncludes().get(2).getId());
        assertNotNull(result.getItems().get(0).getExcludes());
        assertEquals(1, result.getItems().get(0).getExcludes().size());
        assertEquals("287", result.getItems().get(0).getExcludes().get(0).getId());
    }

    @Test
    void testDelete() throws Exception {
        CurationSetCreateSchema curationSetData = helper.createTestCurationSetData();
        client.curationSets().upsert(curationSetName, curationSetData);

        CurationSetDeleteSchema result = client.curationSet(curationSetName).delete();

        assertEquals(curationSetName, result.getName());

        assertThrows(Exception.class, () -> {
            client.curationSet(curationSetName).retrieve();
        });
    }
}
