package org.typesense.api;

import junit.framework.TestCase;
import org.typesense.model.CollectionSchema;
import org.typesense.model.Field;
import org.typesense.resources.Node;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MultiSearchTest extends TestCase {

    private Client client;
    private Helper helper;

    public void setUp() throws Exception {
        super.setUp();
        helper = new Helper();
        client = helper.getClient();
        helper.createTestCollection();
        helper.createTestDocument();

        ArrayList<Field> fields = new ArrayList<>();
        fields.add(new Field().name(".*").type(FieldTypes.AUTO).optional(true));
        CollectionSchema collectionSchema = new CollectionSchema();
        collectionSchema.name("brands").fields(fields);
        client.collections().create(collectionSchema);

        String[] authors = {"shakspeare","william"};
        HashMap<String, Object> hmap = new HashMap<>();
        hmap.put("title","Romeo and juliet");
        hmap.put("authors",authors);
        hmap.put("publication_year",1666);
        hmap.put("ratings_count",124);
        hmap.put("average_rating",3.2);
        hmap.put("id","1");
        client.collections("brands").documents().create(hmap);
    }

    public void testSearch(){
        HashMap<String,String > val1 = new HashMap<>();
        HashMap<String,String > val2 = new HashMap<>();

        val1.put("collection","books");
        val1.put("q","romeo");

        val2.put("collection","brands");
        val2.put("q","juliet");

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