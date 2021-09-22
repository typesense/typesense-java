package org.typesense.api;

import junit.framework.TestCase;
import org.typesense.resources.Node;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MultiSearchTest extends TestCase {

    private Client client;

    public void setUp() throws Exception {
        super.setUp();

        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(new Node("http","localhost","3001"));

        Configuration configuration = new Configuration(nodes, Duration.ofSeconds(3),"xyz");

        this.client = new Client(configuration);
    }

    public void testSearch(){
        HashMap<String,String > val1 = new HashMap<>();
        HashMap<String,String > val2 = new HashMap<>();

        val1.put("collection","intbooks");
        val1.put("q","h");

        val2.put("collection","Brands");
        val2.put("q","Nike");

        List<HashMap<String, String>> list = new ArrayList<>();
        list.add(val2);
        list.add(val1);

        HashMap<String, List<HashMap<String ,String>>> map = new HashMap<>();
        map.put("searches",list);

        /*ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(maplist);
            System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }*/
        HashMap<String,String> common_params = new HashMap<>();
        common_params.put("query_by","title");

        System.out.println(this.client.multiSearch.perform(map, common_params));
    }

}
