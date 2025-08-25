package org.typesense.api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.typesense.model.SearchSynonymSchema;

class SynonymsTest {

    private Client client;
    private Helper helper;

    @BeforeEach
    void setUp() throws Exception {
        helper = new Helper();
        client = helper.getClient();

        if (Helper.isV30OrAbove(client)) {
            org.junit.jupiter.api.Assumptions.assumeTrue(false, "Skipping test - requires Typesense v30 or above");
        }

        helper.teardown();
        helper.createTestCollection();
        helper.createTestSynonym();
    }

    @AfterEach
    void tearDown() throws Exception {
        helper.teardown();
    }

    @Test
    void testUpsert() throws Exception {
        SearchSynonymSchema synonym = new SearchSynonymSchema();

        //One-way
        synonym.addSynonymsItem("dictionary").addSynonymsItem("guide").addSynonymsItem("encyclopedia");
        synonym.root("books");

        System.out.println(this.client.collections("books").synonyms().upsert("books-synonyms", synonym));
    }

    @Test
    void testRetrieve() throws Exception {
        System.out.println(this.client.collections("books").synonyms("coat-synonyms").retrieve());
    }

    @Test
    void testRetrieveAll() throws Exception {
        System.out.println(this.client.collections("books").synonyms().retrieve());
    }

    @Test
    void testDelete() throws Exception {
        System.out.println(this.client.collections("books").synonyms("coat-synonyms").delete());
    }
}
