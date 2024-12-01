package org.typesense.api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.typesense.model.*;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


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

        assertNotNull(collectionResponses);
        assertEquals(1, collectionResponses.length);
    }

    @Test
    void testRetrieveSingleCollection() throws Exception {
        helper.createTestCollection();
        CollectionResponse books = client.collections("books").retrieve();
        System.out.println(books);

        assertNotNull(books);
        assertEquals("books", books.getName());
        assertEquals(0, books.getNumDocuments());
    }

    @Test
    void testDeleteCollection() throws Exception {
        helper.createTestCollection();
        CollectionResponse books = client.collections("books").delete();
        System.out.println(books);

        assertNotNull(books);
        assertEquals("books", books.getName());
        assertEquals(0, books.getNumDocuments());
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

        assertNotNull(cr);
        assertEquals("Countries", cr.getName());
        assertEquals(0, cr.getNumDocuments());
        assertEquals(3, cr.getFields().size());
    }

    @Test
    void testCreateCollectionWithModel() throws Exception {
        ArrayList<Field> fields = new ArrayList<>();
        fields.add(new Field().name("title").type(FieldTypes.STRING));

        ArrayList<String> embedFrom = new ArrayList<>();
        embedFrom.add("title");

        fields.add(new Field().name("embedding").type(FieldTypes.FLOAT_ARRAY).embed(
            new FieldEmbed().from(embedFrom).modelConfig(new FieldEmbedModelConfig().modelName("ts/e5-small"))
        ));

        CollectionSchema collectionSchema = new CollectionSchema();
        collectionSchema.name("titles").fields(fields);

        CollectionResponse cr = client.collections().create(collectionSchema);
        System.out.println(cr);

        assertNotNull(cr);
        assertEquals("titles", cr.getName());
        assertEquals(0, cr.getNumDocuments());
        assertEquals(2, cr.getFields().size());
        FieldEmbed fieldEmbed = cr.getFields().stream().filter(f -> f.getName().equals("embedding")).map(Field::getEmbed)
            .findFirst()
            .orElse(null);
        assertNotNull(fieldEmbed);
        assertEquals("ts/e5-small", fieldEmbed.getModelConfig().getModelName());
    }
}