package org.typesense.api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.typesense.model.CollectionResponse;
import org.typesense.model.CollectionSchema;
import org.typesense.model.Field;

import java.util.ArrayList;


class CollectionsTest {

    Client client;
    private Helper helper;

    @BeforeEach
    void setUp() throws Exception {
        helper = new Helper();
        helper.teardown();
        this.client = helper.getClient();
    }

    @AfterEach
    void tearDown() throws Exception {
        helper.teardown();
    }


    @Test
    void testRetrieveAllCollections() throws Exception {
        helper.createTestCollection();
        CollectionResponse[] collectionResponses = client.collections().retrieve();
        for(CollectionResponse c:collectionResponses)
            System.out.println(c);
    }

    @Test
    void testRetrieveSingleCollection() throws Exception {
        helper.createTestCollection();
        System.out.println(client.collections("books").retrieve());
    }

    @Test
    void testDeleteCollection() throws Exception {
        helper.createTestCollection();
        System.out.println(client.collections("books").delete());
    }

    @Test
    void testCreateCollection() throws Exception {

        ArrayList<Field> fields = new ArrayList<>();
        fields.add(new Field().name("countryName").type(FieldTypes.STRING));
        fields.add(new Field().name("capital").type(FieldTypes.STRING));
        fields.add(new Field().name("gdp").type(FieldTypes.INT32).facet(true));

        CollectionSchema collectionSchema = new CollectionSchema();
        collectionSchema.name("Countries").fields(fields);

        CollectionResponse cr = client.collections().create(collectionSchema);
        System.out.println(cr);
    }
}