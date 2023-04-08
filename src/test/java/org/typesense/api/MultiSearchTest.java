package org.typesense.api;

import junit.framework.TestCase;
import org.junit.Assert;
import org.typesense.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class MultiSearchTest extends TestCase {

    private Client client;
    private Helper helper;

    public void setUp() throws Exception {
        super.setUp();
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

        float[] vecVals = {0.12f, 0.45f, 0.87f, 0.18f};
        Map<String, Object> doc = new HashMap<>();
        doc.put("title", "Romeo and Juliet");
        doc.put("vec",vecVals);
        client.collections("embeddings").documents().create(doc);
    }

    public void tearDown() throws Exception {
        super.tearDown();
        helper.teardown();
    }

    public void testSearch() throws Exception {
        MultiSearchCollectionParameters search1 = new MultiSearchCollectionParameters();
        search1.setCollection("embeddings");
        search1.setQ("*");
        search1.setVectorQuery("vec:([0.96826,0.94,0.39557,0.306488], k:10)");

        MultiSearchSearchesParameter multiSearchParameters = new MultiSearchSearchesParameter().addSearchesItem(search1);
        MultiSearchResponse response = this.client.multiSearch.perform(multiSearchParameters, null);
        Assert.assertEquals(1, response.getResults().size());
        Assert.assertEquals(1, response.getResults().get(0).getHits().size());
        Assert.assertEquals("0", response.getResults().get(0).getHits().get(0).getDocument().get("id"));
    }
}