package org.typesense.api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.typesense.model.ApiKey;
import org.typesense.model.ApiKeySchema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class KeysTest {

    private Client client;
    private Helper helper;
    private String testKey;
    private Long id;

    @BeforeEach
    void setUp() throws Exception {
        helper = new Helper();
        helper.teardown();
        client = helper.getClient();
        ApiKey key = helper.createTestKey();
        testKey = key.getValue();
        id = key.getId();
    }

    @AfterEach
    void tearDown() throws Exception {
        helper.teardown();
    }

    @Test
    void testCreate() throws Exception {
        ApiKeySchema apiKeySchema = new ApiKeySchema();
        List<String> actionValues = new ArrayList<>();
        List<String> collectionValues = new ArrayList<>();

        actionValues.add("*");
        collectionValues.add("*");

        apiKeySchema.description("Admin Key").actions(actionValues).collections(collectionValues);

        System.out.println(client.keys().create(apiKeySchema));
    }

    @Test
    void testCreateSearchOnly() throws Exception {
        ApiKeySchema apiKeySchema = new ApiKeySchema();
        List<String> actionValues = new ArrayList<>();
        List<String> collectionValues = new ArrayList<>();

        actionValues.add("documents:search");
        collectionValues.add("books");

        apiKeySchema.description("Search only Key").actions(actionValues).collections(collectionValues);

        System.out.println(this.client.keys().create(apiKeySchema));
    }

    @Test
    void testRetrieve() throws Exception {
        System.out.println(this.client.keys(id).retrieve());
    }

    @Test
    void testRetrieveAll() throws Exception {
        System.out.println(client.keys().retrieve());
    }

    @Test
    void testDelete() throws Exception {
        System.out.println(this.client.keys(id).delete());
    }

    @Test
    void testScopedKey(){
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("filter_by", "company_id:124");
        System.out.println(this.client.keys().generateScopedSearchKey(testKey,parameters));
    }
}