package org.typesense.api;

import junit.framework.TestCase;
import org.typesense.api.exceptions.TypesenseError;
import org.typesense.model.SearchSynonymSchema;

public class SynonymsTest extends TestCase {

    private Client client;
    private Helper helper;


    public void setUp() throws Exception {
        super.setUp();
        helper = new Helper();
        client = helper.getClient();
        helper.createTestCollection();
        helper.createTestSynonym();
    }

    public void tearDown() throws Exception {
        super.tearDown();
        helper.teardown();
    }

    public void testUpsert() throws Exception {
        SearchSynonymSchema synonym = new SearchSynonymSchema();

        //One-way
        synonym.addSynonymsItem("dictionary").addSynonymsItem("guide").addSynonymsItem("encyclopedia");
        synonym.root("books");

        System.out.println(this.client.collections("books").synonyms().upsert("books-synonyms",synonym));
    }

    public void testRetrieve() throws Exception {
        System.out.println(this.client.collections("books").synonyms("coat-synonyms").retrieve());
    }

    public void testRetrieveAll() throws Exception {
        System.out.println(this.client.collections("books").synonyms().retrieve());
    }

    public void testDelete() throws Exception {
        System.out.println(this.client.collections("books").synonyms("coat-synonyms").delete());
    }
}