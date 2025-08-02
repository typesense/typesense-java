package org.typesense.api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.typesense.model.CollectionSchema;
import org.typesense.model.Field;
import org.typesense.model.MultiSearchCollectionParameters;
import org.typesense.model.MultiSearchResult;
import org.typesense.model.SearchResult;
import org.typesense.model.MultiSearchSearchesParameter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MultiSearchTest {

    private Client client;
    private Helper helper;

    @BeforeEach
    void setUp() throws Exception {
        helper = new Helper();
        client = helper.getClient();

        helper.teardown();

        // create a collection with 2 fields: title and vec to store embeddings
        List<Field> fields = new ArrayList<>();
        fields.add(new Field().name("title").type(FieldTypes.STRING));
        fields.add(new Field().name("vec").type(FieldTypes.FLOAT_ARRAY).numDim(4));
        CollectionSchema collectionSchema = new CollectionSchema();
        collectionSchema.name("embeddings").fields(fields);
        client.collections().create(collectionSchema);
        // create another collection for union search
        collectionSchema.name("embeddings-2").fields(fields);
        client.collections().create(collectionSchema);

        float[] vecVals = { 0.12f, 0.45f, 0.87f, 0.18f };
        Map<String, Object> doc1 = new HashMap<>();
        doc1.put("title", "Romeo and Juliet");
        doc1.put("vec", vecVals);
        doc1.put("source", "embeddings_1");
        client.collections("embeddings").documents().create(doc1);

        // Document for the second collection
        Map<String, Object> doc2 = new HashMap<>();
        doc2.put("title", "Romeo and Juliet from collection 2");
        doc2.put("vec", new float[] { 0.12f, 0.45f, 0.87f, 0.18f });
        doc2.put("source", "embeddings_2");
        client.collections("embeddings-2").documents().create(doc2);
    }

    @AfterEach
    void tearDown() throws Exception {
        helper.teardown();
    }

    @Test
    void testSearch() throws Exception {
        MultiSearchCollectionParameters search1 = new MultiSearchCollectionParameters();
        search1.setCollection("embeddings");
        search1.setQ("*");
        search1.setVectorQuery("vec:([0.96826,0.94,0.39557,0.306488], k:10)");

        MultiSearchSearchesParameter multiSearchParameters = new MultiSearchSearchesParameter()
                .addSearchesItem(search1);
        MultiSearchResult response = this.client.multiSearch.perform(multiSearchParameters, null);
        assertEquals(1, response.getResults().size());
        assertEquals(1, response.getResults().get(0).getHits().size());
        assertEquals("0", response.getResults().get(0).getHits().get(0).getDocument().get("id"));
    }

    @Test
    void testUnionSearch() throws Exception {
        MultiSearchCollectionParameters search1 = new MultiSearchCollectionParameters();
        search1.setCollection("embeddings");
        search1.setQ("*");

        MultiSearchCollectionParameters search2 = new MultiSearchCollectionParameters();
        search2.setCollection("embeddings-2");
        search2.setQ("*");

        MultiSearchSearchesParameter multiSearchParameters = new MultiSearchSearchesParameter()
                .addSearchesItem(search1).addSearchesItem(search2);
        SearchResult response = this.client.multiSearch.performUnion(multiSearchParameters, null);

        assertEquals(2, response.getHits().size());
        assertEquals(2, response.getUnionRequestParams().size());

        Set<String> sources = new HashSet<>();
        sources.add((String) response.getHits().get(0).getDocument().get("source"));
        sources.add((String) response.getHits().get(1).getDocument().get("source"));

        assertTrue(sources.contains("embeddings_1"), "Results should contain a document from embeddings_1");
        assertTrue(sources.contains("embeddings_2"), "Results should contain a document from embeddings_2");
    }
}
