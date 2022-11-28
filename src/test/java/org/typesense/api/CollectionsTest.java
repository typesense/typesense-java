package org.typesense.api;

import junit.framework.TestCase;
import org.typesense.model.CollectionResponse;
import org.typesense.model.CollectionSchema;
import org.typesense.model.Field;

import java.util.ArrayList;


public class CollectionsTest extends TestCase {

    public Client client;
    private Helper helper;

    public void setUp() throws Exception {
        super.setUp();
        helper = new Helper();
        this.client = helper.getClient();
    }

    public void tearDown() throws Exception {
        super.tearDown();
        helper.teardown();
    }


    public void testRetrieveAllCollections() throws Exception {
        helper.createTestCollection();
        CollectionResponse[] collectionResponses = client.collections().retrieve();
        for(CollectionResponse c:collectionResponses)
            System.out.println(c);
    }

    public void testRetrieveSingleCollection() throws Exception {
        helper.createTestCollection();
        System.out.println(client.collections("books").retrieve());
    }

    public void testDeleteCollection() throws Exception {
        helper.createTestCollection();
        System.out.println(client.collections("books").delete());
    }

    public void testCreateCollection() throws Exception {

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