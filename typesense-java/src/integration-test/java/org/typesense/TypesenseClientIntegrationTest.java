package org.typesense;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.typesense.model.*;

import javax.ws.rs.ClientErrorException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
/*
public class TypesenseClientIntegrationTest {

    private static final String TEST_COLLECTION_NAME = "intbooks";

    private static final Logger logger = LoggerFactory.getLogger(TypesenseClientIntegrationTest.class);

    private static final int APPLICATION_PORT = 3001;
    private static final String API_KEY = "xyz";
    private static final String BASE_URL = "http://localhost:" + APPLICATION_PORT;

    private TypesenseClient client;

    @BeforeClass
    public static void setupTypesense() {
        // Create a client for bootstrapping the test data
        TypesenseClient client = new TypesenseClient(BASE_URL, API_KEY);
        // Create the test collection
        Collection schema = new Collection(TEST_COLLECTION_NAME);
        schema.addField("title", "string", false, true, true);
        schema.addField("authors", "string[]", false, true, false);
        schema.addField("image_url", "string", false, true, false);
        schema.addField("publication_year", "int32", false, true, false);
        schema.addField("ratings_count", "int32", false, false, true);
        schema.addField("average_rating", "float", false, true, false);
        schema.addField("authors_facet", "string[]", true, false, true);
        schema.addField("publication_year_facet", "string", true, false, true);
        schema.defaultSortingField = "ratings_count";
        try {
            client.createCollection(schema);
            createBook(client,"Book1", new String[] {"John Smith", "Jane Smith"}, "http://someUrl1", 2021, 10, 5.33f);
            createBook(client,"Book2", new String[] {"Jack Smith", "Jill  Smith"}, "http://someUrl2", 2022, 20, 6.33f);
            createBook(client,"Book3", new String[] {"John Smith", "Jane Smith"}, "http://someUrl3", 2023, 30, 7.33f);
            createBook(client,"Book4", new String[] {"Jack Smith", "Jill Smith"}, "http://someUrl4", 2024, 40, 8.33f);
            createBook(client,"Book5", new String[] {"Aiken Drum", "Marc Remillard"}, "http://someUrl5", 2025,  50, 9.33f);
            logger.info("Created integration test book collection and documents");
        } catch (ClientErrorException ex) {
            if (ex.getResponse().getStatus() == 409) {}
        }
    }

    @Before
    public void init() {
        client = new TypesenseClient(BASE_URL, API_KEY);
    }

    @Test
    public void shouldCreateCollection() throws Exception {
        Collection schema = new Collection();
        schema.name = UUID.randomUUID().toString().replace("-", "");
        schema.fields = new ArrayList<>();
        schema.fields.add(createField("field1", "int32", false, true, false));
        schema.fields.add(createField("field2", "string", false, false, true));
        schema.fields.add(createField("field3", "string[]", false, false, true));
        schema.defaultSortingField = "field1";
        Collection response = client.createCollection(schema);
        assertTrue(response != null);
        logger.info("Created collection: " + response.name);
    }

    @Test
    public void shouldGetCollection() throws Exception {
        Collection collection = client.getCollection(TEST_COLLECTION_NAME);
        logger.info("Retrieved collection: " + collection.name);
        assertTrue(collection != null);
        assertThat(collection.name , equalTo(TEST_COLLECTION_NAME));
    }

    @Test
    public void shouldGetCollections() throws Exception {
        List<Collection> collections = client.getCollections();
        logger.info("Retrieved collections list of size: " + collections.size());
        assertThat(collections.size(), greaterThan(0));
    }

    @Test
    public void shouldDeleteCollection() throws Exception {
        Collection schema = new Collection();
        schema.name = UUID.randomUUID().toString().replace("-", "");
        schema.fields = new ArrayList<>();
        schema.fields.add(createField("field1", "int32", false, true, false));
        schema.defaultSortingField = "field1";
        Collection response = client.createCollection(schema);
        assertTrue(response != null);
        Collection deletedCollection = client.deleteCollection(schema.name);
        logger.info("Deleted collection: " + deletedCollection.name);
        assertTrue(deletedCollection != null);
        assertThat(deletedCollection.name , equalTo(schema.name));
    }

    @Test
    public void shouldGetDocument() throws Exception {
        TypesenseIntegrationBook book = client.getDocument(TEST_COLLECTION_NAME, "Book1", TypesenseIntegrationBook.class);
        logger.info("Retrieved book: " + book);
        assertTrue(book != null);
        assertThat(book.title, equalTo("Book1"));
    }

    @Test
    public void shouldCreateDocument() throws Exception {
        String randomTitle = randomTitle();
        TypesenseIntegrationBook book = createBook(client,randomTitle, new String[] {"New Author"},
                "http://someUrl1", 2021, 1, 1.33f);
        assertTrue(book != null);
        logger.info("Created book: " + book);
    }

    @Test
    public void shouldUpdateDocument() throws Exception {
        String randomTitle = randomTitle();
        TypesenseIntegrationBook book = createBook(client,randomTitle, new String[] {"New Author"},
                "http://someUrl1", 2021, 1, 1.33f);
        book.title = "Updated Title";
        assertTrue(book != null);
        TypesenseIntegrationBook updatedBook = client.updateDocument(TEST_COLLECTION_NAME, book.id, book, TypesenseIntegrationBook.class);
        logger.info("Updated book: " + updatedBook);
    }

    @Test
    public void shouldUpsertDocument() throws Exception {
        String randomTitle = randomTitle();
        TypesenseIntegrationBook book = createBook(client,randomTitle, new String[] {"New Author"},
                "http://someUrl1", 2021, 1, 1.33f);
        book.title = "New Title";
        TypesenseIntegrationBook upsertedBook = client.upsertDocument(TEST_COLLECTION_NAME, book, TypesenseIntegrationBook.class);
        assertTrue(upsertedBook != null);
        logger.info("Upserted book: " + upsertedBook);
    }

    @Test
    public void shouldDeleteDocument() throws Exception {
        String randomTitle = randomTitle();
        TypesenseIntegrationBook book = createBook(client,randomTitle, new String[] {"New Author"},
                "http://someUrl1", 2021, 1, 1.33f);
        TypesenseIntegrationBook deletedBook = client.deleteDocument(TEST_COLLECTION_NAME, book.id, TypesenseIntegrationBook.class);
        assertTrue(deletedBook != null);
        logger.info("Deleted book: " + deletedBook);
    }

    @Test
    public void shouldRetrieveBooksViaQuery() throws Exception {
        SearchParameters searchParameters = new SearchParameters();
        searchParameters
                .query("Book")
                .queryBy("title")
                .sortBy("ratings_count:desc");
        SearchResult searchResult = client.searchCollection(TEST_COLLECTION_NAME, searchParameters);
        assertThat(searchResult.found, equalTo(5));
        for (Hit hit : searchResult.hits) {
            logger.info(searchResult.page + ":" + searchResult.outOf + " -> " + hit.document.toString());
        }
    }

    private CollectionField createField(String name, String type, boolean facet, boolean index, boolean optional) {
        CollectionField field = new CollectionField();
        field.name = name;
        field.type = type;
        field.facet = facet;
        field.index = index;
        field.optional = optional;
        return field;
    }

    private static TypesenseIntegrationBook createBook(
            TypesenseClient client,
            String title, String[] authors, String imageUrl,
            int publicationYear, int ratingsCount, float averageRating) {
        TypesenseIntegrationBook book = new TypesenseIntegrationBook();
        book.id = title;
        book.title = title;
        book.authors = authors;
        book.authorsFacet = book.authors;
        book.imageUrl = imageUrl;
        book.publicationYear = publicationYear;
        book.publicationYearFacet = Integer.toString(book.publicationYear);
        book.ratingsCount = ratingsCount;
        book.averageRating = averageRating;
        return client.createDocument(TEST_COLLECTION_NAME, book, TypesenseIntegrationBook.class);
    }

    private String randomTitle() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
*/