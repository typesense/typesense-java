package org.typesense.api;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.typesense.model.StemmingDictionary;
import org.typesense.model.StemmingDictionaryWords;

public class StemmingTest {
    private Client client;
    private Helper helper;

    @BeforeEach
    void setUp() throws Exception {
        helper = new Helper();
        helper.teardown();
        client = helper.getClient();
        helper.createStemmingDictionary();
    }

    @Test
    void testUpsert() throws Exception {
        List<StemmingDictionaryWords> stemmingDictionaryWords = new ArrayList<>();

        stemmingDictionaryWords.add(new StemmingDictionaryWords().word("ran").root("run"));
        stemmingDictionaryWords.add(new StemmingDictionaryWords().word("running").root("run"));

        List<StemmingDictionaryWords> res = client.stemming().dictionaries().upsert("irregular-plurals",
                stemmingDictionaryWords);

        assertEquals(2, res.size());
        assertEquals("ran", res.get(0).getWord());
        assertEquals("run", res.get(0).getRoot());
        assertEquals("running", res.get(1).getWord());
        assertEquals("run", res.get(1).getRoot());
    }

    @Test
    void testRetrieveOne() throws Exception {
        StemmingDictionary res = client.stemming().dictionaries("irregular-plurals").retrieve();
        assertEquals("irregular-plurals", res.getId());
        assertEquals(2, res.getWords().size());
    }

    @Test
    void testRetrieveAll() throws Exception {
         StemmingDictionariesRetrieveSchema res = client.stemming().dictionaries().retrieve();
        assertEquals(1, res.getDictionaries().size());
        assertEquals("irregular-plurals", res.getDictionaries().get(0));
    }
}
