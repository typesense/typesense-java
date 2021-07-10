package org.typesense.model;

import junit.framework.TestCase;
import org.junit.BeforeClass;
import org.junit.Test;
import org.typesense.api.CollectionSchema;
import org.typesense.api.old.TestDoc;
import org.typesense.resources.Node;


import java.time.Duration;
import java.util.*;

public class ApiCallTest extends TestCase {

    Configuration configuration;

    @BeforeClass
    public void setUp() throws Exception {
        super.setUp();

        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(new Node("http","localhost","port"));

        this.configuration = new Configuration(nodes, Duration.ofSeconds(3), "xyz");

    }

    @Test
    public void testDelete(){
        ApiCall apiCall = new ApiCall(configuration);

        TestDoc book = apiCall.delete("/collections/intbooks/documents/Odyssey", TestDoc.class);
        System.out.println(book);

    }

    @Test
    public void testPost(){
        ApiCall apiCall = new ApiCall(configuration);

        TestDoc book = TestDoc.createBook("Odyssey", new String[]{"Homer"}, "url", 1000, 3, 4);
        TestDoc d = apiCall.post("/collections/intbooks/documents", book, TestDoc.class);

        System.out.println(d);
    }

    @Test
    public void testGet() {
        ApiCall apiCall = new ApiCall(configuration);

        CollectionSchema c = apiCall.get("/collections/intbooks",CollectionSchema.class);

        //System.out.println(c);
        assertNotNull(c);
    }

    @Test
    public void testGetDocument(){
        ApiCall apiCall = new ApiCall(configuration);

        HashMap<String,Object> hm = apiCall.get("/collections/intbooks/documents/5");

        System.out.println(hm);
    }
}