package org.typesense.model;

import junit.framework.TestCase;
import org.junit.BeforeClass;
import org.junit.Test;


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

        Document book = api.delete("/collections/intbooks/documents/Odyssey",Document.class);
        System.out.println(book);

    }

    @Test
    public void testPost(){
        Api api = new Api(configuration);

        Document book = Document.createBook("Odyssey", new String[]{"Homer"}, "url", 1000, 3, 4);
        Document d = api.post("/collections/intbooks/documents", book, Document.class);

        System.out.println(d);
    }

    @Test
    public void testGet() {
        Api api = new Api(configuration);

        Document d = api.get("/collections/intbooks/documents/Odyssey",Document.class);
        System.out.println(d);
    }
}