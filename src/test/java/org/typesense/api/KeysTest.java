package org.typesense.api;

import jdk.javadoc.internal.doclets.toolkit.util.Utils;
import junit.framework.TestCase;
import org.typesense.model.ApiKey;
import org.typesense.model.ApiKeySchema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KeysTest extends TestCase {

    private Client client;
    private Helper helper;
    private String testKey;
    private Long id;

    public void setUp() throws Exception {
        super.setUp();
        helper = new Helper();
        client = helper.getClient();
        ApiKey key = helper.createTestKey();
        testKey = key.getValue();
        id = key.getId();
    }

    public void tearDown() throws Exception {
        super.tearDown();
        helper.teardown();
    }

    public void testCreate() {
        ApiKeySchema apiKeySchema = new ApiKeySchema();
        List<String> actionValues = new ArrayList<>();
        List<String> collectionValues = new ArrayList<>();

        actionValues.add("*");
        collectionValues.add("*");

        apiKeySchema.description("Admin Key").actions(actionValues).collections(collectionValues);

        System.out.println(client.keys().create(apiKeySchema));
    }

    public void testCreateSearchOnly(){
        ApiKeySchema apiKeySchema = new ApiKeySchema();
        List<String> actionValues = new ArrayList<>();
        List<String> collectionValues = new ArrayList<>();

        actionValues.add("documents:search");
        collectionValues.add("books");

        apiKeySchema.description("Search only Key").actions(actionValues).collections(collectionValues);

        System.out.println(this.client.keys().create(apiKeySchema));
    }

    public void testRetrieve() {
        System.out.println(this.client.keys(id).retrieve());
    }

    public void testRetrieveAll() {
        System.out.println(client.keys().retrieve());
    }

    public void testDelete(){
        System.out.println(this.client.keys(id).delete());
    }

    public void testScopedKey(){
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("filter_by", "company_id:124");
        System.out.println(this.client.keys().generateScopedSearchKey(testKey,parameters));
    }
}