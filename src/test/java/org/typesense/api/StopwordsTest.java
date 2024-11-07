package org.typesense.api;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.typesense.model.StopwordsSetRetrieveSchema;
import org.typesense.model.StopwordsSetSchema;
import org.typesense.model.StopwordsSetUpsertSchema;
import org.typesense.model.StopwordsSetsRetrieveAllSchema;

public class StopwordsTest {

    private Client client;
    private Helper helper;

    @BeforeEach
    void setUp() throws Exception {
        helper = new Helper();
        client = helper.getClient();
        helper.teardown();
        helper.createTestCollection();
    }

    @AfterEach
    void tearDown() throws Exception {
        helper.teardown();
    }

    @Test
    void testUpsert() throws Exception {
        List<String> stopwords = new ArrayList<>();
        stopwords.add("the");
        stopwords.add("of");
        stopwords.add("and");

        StopwordsSetUpsertSchema stopwordsSetSchema = new StopwordsSetUpsertSchema();
        stopwordsSetSchema.stopwords(stopwords);

        client.stopwords().upsert("common-words", stopwordsSetSchema);
    }

    @Test
    void testRetrieve() throws Exception {
        helper.createTestStopwordsSet();

        StopwordsSetRetrieveSchema result = this.client.stopwords("common-words").retrieve();

        assertNotNull(result);

        StopwordsSetSchema set = result.getStopwords();

        assertEquals("common-words", set.getId());
        assertEquals(3, set.getStopwords().size());
        assertEquals("and", set.getStopwords().get(0));
        assertEquals("the", set.getStopwords().get(1));
        assertEquals("of", set.getStopwords().get(2));
    }

    @Test
    void testRetrieveAll() throws Exception {
        helper.createTestStopwordsSet();

        StopwordsSetsRetrieveAllSchema result = this.client.stopwords().retrieve();

        assertNotNull(result);
        assertEquals(1, result.getStopwords().size());

        StopwordsSetSchema set = result.getStopwords().get(0);

        assertEquals("common-words", set.getId());
        assertEquals(3, set.getStopwords().size());
        assertEquals("and", set.getStopwords().get(0));
        assertEquals("the", set.getStopwords().get(1));
        assertEquals("of", set.getStopwords().get(2));
    }

    @Test
    void testDelete() throws Exception {
        helper.createTestStopwordsSet();

        StopwordsSetSchema result = this.client.stopwords("common-words").delete();

        assertNotNull(result);
        assertEquals("common-words", result.getId());
    }
}
