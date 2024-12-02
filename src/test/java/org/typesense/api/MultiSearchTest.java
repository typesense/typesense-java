package org.typesense.api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.typesense.TypesenseContainer;
import org.typesense.model.CollectionSchema;
import org.typesense.model.Field;
import org.typesense.model.MultiSearchCollectionParameters;
import org.typesense.model.MultiSearchResult;
import org.typesense.model.MultiSearchSearchesParameter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
class MultiSearchTest {

    @Container
    static TypesenseContainer typesense = new TypesenseContainer(Helper.IMAGE);

    private Client client;
    private Helper helper;

    @BeforeEach
    void setUp() throws Exception {
        helper = new Helper(typesense);
        client = helper.getClient();

        // create a collection with 2 fields: title and vec to store embeddings
        List<Field> fields = new ArrayList<>();
        fields.add(new Field().name("title").type(FieldTypes.STRING));
        fields.add(new Field().name("vec").type(FieldTypes.FLOAT_ARRAY).numDim(4));
        CollectionSchema collectionSchema = new CollectionSchema();
        collectionSchema.name("embeddings").fields(fields);
        client.collections().create(collectionSchema);

        float[] vecVals = {0.12f, 0.45f, 0.87f, 0.18f};
        Map<String, Object> doc = new HashMap<>();
        doc.put("title", "Romeo and Juliet");
        doc.put("vec", vecVals);
        client.collections("embeddings").documents().create(doc);
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

        MultiSearchSearchesParameter multiSearchParameters = new MultiSearchSearchesParameter().addSearchesItem(search1);
        MultiSearchResult response = this.client.multiSearch.perform(multiSearchParameters, null);
        assertEquals(1, response.getResults().size());
        assertEquals(1, response.getResults().get(0).getHits().size());
        assertEquals("0", response.getResults().get(0).getHits().get(0).getDocument().get("id"));
    }
}
