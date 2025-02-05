package org.typesense.api;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.typesense.TypesenseContainer;
import org.typesense.api.exceptions.ObjectNotFound;
import org.typesense.model.CollectionSchema;
import org.typesense.model.DeleteDocumentsParameters;
import org.typesense.model.DirtyValues;
import org.typesense.model.ExportDocumentsParameters;
import org.typesense.model.Field;
import org.typesense.model.ImportDocumentsParameters;
import org.typesense.model.IndexAction;
import org.typesense.model.SearchParameters;
import org.typesense.model.SearchResult;
import org.typesense.model.UpdateDocumentsParameters;

@Testcontainers
class DocumentsTest {

        @Container
        static TypesenseContainer typesense = new TypesenseContainer(Helper.IMAGE);

        Client client;
        private Helper helper;

        @BeforeEach
        void setUp() throws Exception {
                helper = new Helper(typesense);
                this.client = helper.getClient();
                helper.createTestCollection();
        }

        @AfterEach
        void tearDown() throws Exception {
                helper.teardown();
        }

        @Test
        void testRetrieveDocument() throws Exception {
                helper.createTestDocument();
                Map<String, Object> resp = client.collections("books").documents("1").retrieve();
                assertEquals(6, resp.size());
                assertEquals("1", resp.get("id"));
        }

        @Test
        void testCreateDocument() throws Exception {
                String[] authors = { "shakspeare", "william" };
                HashMap<String, Object> hmap = new HashMap<>();
                hmap.put("title", "Romeo and juliet");
                hmap.put("authors", authors);
                hmap.put("image_url", "fgfg");
                hmap.put("publication_year", 1666);
                hmap.put("ratings_count", 124);
                hmap.put("average_rating", 3.2);
                hmap.put("publication_year_facet", "dff");
                hmap.put("authors_facet", authors);
                hmap.put("id", "1");

                Map<String, Object> resp = client.collections("books").documents().create(hmap);
                assertEquals(9, resp.size());
                assertEquals("1", resp.get("id"));
        }

        @Test
        void testUnknownPresetsDoNotFailSearches() throws Exception {
                helper.createTestDocument();
                Map<String, Object> resp = client.collections("books").documents("1").retrieve();
                assertEquals("Romeo and juliet", resp.get("title"));

                SearchParameters params = new SearchParameters()
                                .q("Romeo")
                                .queryBy("title")
                                .preset("non_existent_preset");

                SearchResult searchResult = client.collections("books").documents().search(params);
                assertEquals(1, searchResult.getFound().intValue());
                assertEquals(1, searchResult.getHits().size());
        }

        @Test
        void testUpsertDocument() throws Exception {
                helper.createTestDocument();

                Map<String, Object> resp = client.collections("books").documents("1").retrieve();
                assertEquals("Romeo and juliet", resp.get("title"));

                String[] authors = new String[] { "jk", "Rowling" };
                HashMap<String, Object> hmap = new HashMap<>();
                hmap.put("title", "harry potter");
                hmap.put("authors", authors);
                hmap.put("image_url", "fgfg");
                hmap.put("publication_year", 2001);
                hmap.put("ratings_count", 231);
                hmap.put("average_rating", 5.6);
                hmap.put("publication_year_facet", "2001");
                hmap.put("authors_facet", authors);
                hmap.put("id", "1");

                resp = client.collections("books").documents().upsert(hmap);
                assertEquals(9, resp.size());
                assertEquals("1", resp.get("id"));
                assertEquals("harry potter", resp.get("title"));

                // try fetching the document back
                resp = client.collections("books").documents("1").retrieve();
                assertEquals("harry potter", resp.get("title"));
        }

        @Test
        void testUpdateDocument() throws Exception {
                helper.createTestDocument();

                String[] authors = new String[] { "JK Rowling" };
                HashMap<String, Object> document = new HashMap<>();
                document.put("title", "harry potter");
                document.put("authors", authors);
                document.put("publication_year", 2000);
                document.put("id", "1");
                client.collections("books").documents("1").update(document);

                // try fetching the document back
                Map<String, Object> resp = client.collections("books").documents("1").retrieve();
                assertEquals("harry potter", resp.get("title"));
                assertEquals(2000, resp.get("publication_year"));
        }

        @Test
        void testDeleteDocument() throws Exception {
                helper.createTestDocument();
                client.collections("books").documents("1").delete();

                try {
                        client.collections("books").documents("1").retrieve();
                        fail("Delete document failed.");
                } catch (ObjectNotFound expectedException) {

                }
        }

        @Test
        void testDeleteDocumentByQuery() throws Exception {
                helper.createTestDocument();
                DeleteDocumentsParameters deleteDocumentsParameters = new DeleteDocumentsParameters();
                deleteDocumentsParameters.filterBy("publication_year:=[1666]");
                deleteDocumentsParameters.batchSize(10);
                client.collections("books").documents().delete(deleteDocumentsParameters);
                try {
                        client.collections("books").documents("1").retrieve();
                        fail("Delete document by query failed.");
                } catch (ObjectNotFound expectedException) {

                }
        }

        @Test
        void testSearchDocuments() throws Exception {
                helper.createTestDocument();
                SearchParameters searchParameters = new SearchParameters()
                                .q("romeo")
                                .queryBy("title,authors")
                                .prefix("false,true");

                SearchResult searchResult = client.collections("books").documents().search(searchParameters);
                assertEquals(1, searchResult.getFound().intValue());
                assertEquals(1, searchResult.getHits().size());
        }

        @Test
        void testImport() throws Exception {
                HashMap<String, Object> document1 = new HashMap<>();
                HashMap<String, Object> document2 = new HashMap<>();
                ImportDocumentsParameters queryParameters = new ImportDocumentsParameters();
                List<Map<String, Object>> documentList = new ArrayList<>();

                document1.put("countryName", "India");
                document1.put("capital", "Delhi");
                document1.put("gdp", 23);
                document2.put("countryName", "Us");
                document2.put("capital", "Washington");
                document2.put("gdp", 233);

                documentList.add(document1);
                documentList.add(document2);
                queryParameters.action(IndexAction.CREATE);

                String countriesImport = this.client.collections("books").documents()
                                .import_(documentList, queryParameters);
                assertFalse(countriesImport.contains("\"success\":false"));
        }

        @Test
        void testImportAsString() throws Exception {
                ImportDocumentsParameters queryParameters = new ImportDocumentsParameters();
                queryParameters.action(IndexAction.CREATE);
                String documentList = "{\"countryName\": \"India\", \"capital\": \"Washington\", \"gdp\": 5215}\n" +
                                "{\"countryName\": \"Iran\", \"capital\": \"London\", \"gdp\": 5215}";
                String booksImport = this.client.collections("books").documents().import_(documentList,
                                queryParameters);
                assertFalse(booksImport.contains("\"success\":false"));
        }

        @Test
        void testExportDocuments() throws Exception {
                helper.createTestDocument();
                ExportDocumentsParameters exportDocumentsParameters = new ExportDocumentsParameters();
                exportDocumentsParameters.setExcludeFields("id,publication_year,authors");
                String exportStr = client.collections("books").documents().export(exportDocumentsParameters);
                String expectedExportStr = "{\"average_rating\":3.2,\"ratings_count\":124,\"title\":\"Romeo and juliet\"}";
                assertEquals(expectedExportStr, exportStr);
        }

        @Test
        void testDirtyCreate() throws Exception {
                helper.createTestDocument();

                ImportDocumentsParameters queryParameters = new ImportDocumentsParameters();
                queryParameters.dirtyValues(DirtyValues.COERCE_OR_REJECT);
                queryParameters.action(IndexAction.UPSERT);

                String[] authors = { "shakspeare", "william" };
                HashMap<String, Object> hmap = new HashMap<>();

                hmap.put("id", "1");
                hmap.put("authors", authors);
                hmap.put("publication_year", 1666);
                hmap.put("ratings_count", 124);
                hmap.put("average_rating", 3.2);
                // title is sent as an integer and not as string for testing coercion
                hmap.put("title", 1984);

                this.client.collections("books").documents().create(hmap, queryParameters);
                Map<String, Object> resp = client.collections("books").documents("1").retrieve();
                assertEquals("1984", resp.get("title"));
        }

        @Test
        void testNestedObjectImport() throws Exception {
                // create collection with nested objects support
                List<Field> fields = new ArrayList<>();
                fields.add(new Field().name("address").type(FieldTypes.OBJECT).optional(true));
                fields.add(new Field().name("tags").type(FieldTypes.OBJECT_ARRAY).optional(true));

                CollectionSchema collectionSchema = new CollectionSchema();
                collectionSchema.name("items").fields(fields).setEnableNestedFields(true);
                client.collections().create(collectionSchema);

                ImportDocumentsParameters queryParameters = new ImportDocumentsParameters();
                queryParameters.action(IndexAction.CREATE);

                List<NestedDocument> docs = new ArrayList<>();
                NestedDocument doc = new NestedDocument("LA", "CA", "USA")
                                .addTag("color", "Red")
                                .addTag("weight", "LOW");
                docs.add(doc);
                String itemImport = this.client.collections("items").documents().import_(docs, queryParameters);
                assertFalse(itemImport.contains("\"success\":false"));

                // try searching on the nested document
                SearchParameters searchParameters = new SearchParameters()
                                .q("red")
                                .queryBy("tags");

                SearchResult searchResult = client.collections("items").documents().search(searchParameters);
                assertEquals(1, searchResult.getFound().intValue());
                assertEquals(1, searchResult.getHits().size());
        }

        @Test
        void testUpdateDocumentsByQuery() throws Exception {
                helper.createTestDocument();

                UpdateDocumentsParameters updateDocumentsParameters = new UpdateDocumentsParameters()
                                .filterBy("publication_year:1666");
                Map<String, Object> fieldToAdd = new HashMap<>();
                fieldToAdd.put("ratings_count", "200");
                Map<String, Integer> response = client.collections("books").documents().update(fieldToAdd,
                                updateDocumentsParameters);
                assertThat(response.get("num_updated"), is(1));

                // try fetching the document back
                Map<String, Object> resp = client.collections("books").documents("1").retrieve();
                assertThat(resp.get("ratings_count"), is(200));
        }
}
