package org.typesense.model;

import junit.framework.TestCase;
import org.junit.BeforeClass;
import org.junit.Test;
import org.typesense.api.CollectionSchema;
import org.typesense.api.old.TestDoc;
import org.typesense.resources.Node;


import java.time.Duration;
import java.util.*;

public class ApiTest extends TestCase {

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
        Api api = new Api(configuration);

        TestDoc book = api.delete("/collections/intbooks/documents/Odyssey", TestDoc.class);
        System.out.println(book);

    }

    @Test
    public void testPost(){
        Api api = new Api(configuration);

        TestDoc book = TestDoc.createBook("Odyssey", new String[]{"Homer"}, "url", 1000, 3, 4);
        TestDoc d = api.post("/collections/intbooks/documents", book, TestDoc.class);

        System.out.println(d);
    }

    @Test
    public void testGet() {
        Api api = new Api(configuration);

        CollectionSchema c = api.get("/collections/intbooks",CollectionSchema.class);

        //System.out.println(c);
        assertNotNull(c);
    }

    @Test
    public void testGetDocument(){
        Api api = new Api(configuration);

        HashMap<String,Object> hm = api.get("/collections/intbooks/documents/5");

        System.out.println(hm);
    }
}