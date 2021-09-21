package org.typesense.api;

import junit.framework.TestCase;
import org.typesense.model.ApiKeySchema;
import org.typesense.resources.Node;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KeysTest extends TestCase {

    private Client client;

    public void setUp() throws Exception {
        super.setUp();

        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(new Node("http","localhost","3001"));

        /**
         * @param
         */
        Configuration configuration = new Configuration(nodes, Duration.ofSeconds(3),"xyz");

        this.client = new Client(configuration);
    }

    public void testCreate() {
        ApiKeySchema apiKeySchema = new ApiKeySchema();
        List<String> actionValues = new ArrayList<>();
        List<String> collectionValues = new ArrayList<>();

        actionValues.add("*");
        collectionValues.add("*");

        apiKeySchema.description("Admin Key").actions(actionValues).collections(collectionValues);

        System.out.println(this.client.keys().create(apiKeySchema));
    }

    public void testCreateSearchOnly(){
        ApiKeySchema apiKeySchema = new ApiKeySchema();
        List<String> actionValues = new ArrayList<>();
        List<String> collectionValues = new ArrayList<>();

        actionValues.add("documents:search");
        collectionValues.add("intbooks");

        apiKeySchema.description("Search only Key").actions(actionValues).collections(collectionValues);

        System.out.println(this.client.keys().create(apiKeySchema));
    }

    public void testRetrieve() {
        System.out.println(this.client.keys("6").retrieve());
    }

    public void testRetrieveAll() {
        System.out.println(this.client.keys().retrieve());
    }

    public void testDelete(){
        System.out.println(this.client.keys("6").delete());
    }

    public void testScopedKey(){
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("filter_by", "company_id:124");
        System.out.println(this.client.keys().generateScopedSearchKey("B9qqshFAliUDq7dsgRyrXB5WPhOIpgG9",parameters));
    }
}