package org.typesense.model;

import junit.framework.TestCase;
import org.typesense.api.SearchSynonymSchema;
import org.typesense.resources.Node;

import java.time.Duration;
import java.util.ArrayList;

public class SynonymsTest extends TestCase {

    private Client client;

    public void setUp() throws Exception {
        super.setUp();

        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(new Node("http","localhost","3001"));

        Configuration configuration = new Configuration(nodes, Duration.ofSeconds(3),"xyz");

        this.client = new Client(configuration);
    }

    public void testUpsert(){
        SearchSynonymSchema synonym = new SearchSynonymSchema();
        synonym.addSynonymsItem("blazer").addSynonymsItem("coat").addSynonymsItem("jacket");
        synonym.root("blazer");

        System.out.println(this.client.collections("intbooks").synonyms().upsert("coat-synonyms",synonym));

        synonym.addSynonymsItem("dictionary").addSynonymsItem("guide").addSynonymsItem("encyclopedia");
        synonym.root("books");

        System.out.println(this.client.collections("intbooks").synonyms().upsert("books-synonyms",synonym));
    }

    public void testRetrieve(){
        System.out.println(this.client.collections("intbooks").synonyms("coat-synonyms").retrieve());
    }

    public void testRetrieveAll(){
        System.out.println(this.client.collections("intbooks").synonyms().retrieve());
    }

    public void testDelete(){
        System.out.println(this.client.collections("intbooks").synonyms("coat-synonyms").delete());
    }
}