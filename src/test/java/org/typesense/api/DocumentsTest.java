package org.typesense.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;
import org.junit.Assert;
import org.typesense.model.*;
import org.typesense.api.exceptions.ObjectNotFound;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocumentsTest extends TestCase {

    public Client client;
    private Helper helper;

    public void setUp() throws Exception {
        super.setUp();
        helper = new Helper();
        this.client = helper.getClient();
        helper.teardown();
        helper.createTestCollection();
    }

    public void testRetrieveDocument() throws Exception {
        helper.createTestDocument();
        Map<String, Object> resp = client.collections("books").documents("1").retrieve();
        Assert.assertEquals(6, resp.size());
        Assert.assertEquals("1", resp.get("id"));
    }

    public void testCreateDocument() throws Exception {
        String[] authors = {"shakspeare", "william"};
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
        Assert.assertEquals(9, resp.size());
        Assert.assertEquals("1", resp.get("id"));
    }

    public void testUpsertDocument() throws Exception {
        helper.createTestDocument();

        Map<String, Object> resp = client.collections("books").documents("1").retrieve();
        Assert.assertEquals("Romeo and juliet", resp.get("title"));

        String[] authors = new String[]{"jk", "Rowling"};
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
        Assert.assertEquals(9, resp.size());
        Assert.assertEquals("1", resp.get("id"));
        Assert.assertEquals("harry potter", resp.get("title"));

        // try fetching the document back
        resp = client.collections("books").documents("1").retrieve();
        Assert.assertEquals("harry potter", resp.get("title"));
    }

    public void testUpdateDocument() throws Exception {
        helper.createTestDocument();

        String[] authors = new String[]{"JK Rowling"};
        HashMap<String, Object> document = new HashMap<>();
        document.put("title", "harry potter");
        document.put("authors", authors);
        document.put("publication_year", 2000);
        document.put("id", "1");
        client.collections("books").documents("1").update(document);

        // try fetching the document back
        Map<String, Object> resp = client.collections("books").documents("1").retrieve();
        Assert.assertEquals("harry potter", resp.get("title"));
        Assert.assertEquals(2000, resp.get("publication_year"));
    }

    public void testDeleteDocument() throws Exception {
        helper.createTestDocument();
        client.collections("books").documents("1").delete();

        try {
            client.collections("books").documents("1").retrieve();
            fail("Delete document failed.");
        } catch (ObjectNotFound expectedException) {

        }
    }

    public void testDeleteDocumentByQuery() throws Exception {
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

    public void testSearchDocuments() throws Exception {
        helper.createTestDocument();
        SearchParameters searchParameters = new SearchParameters()
                .q("romeo")
                .queryBy("title,authors")
                .prefix("false,true");

        SearchResult searchResult = client.collections("books").documents().search(searchParameters);
        Assert.assertEquals(1, searchResult.getFound().intValue());
        Assert.assertEquals(1, searchResult.getHits().size());
    }

    public void testImport() throws Exception {
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
        queryParameters.action("create");

        String countriesImport = this.client.collections("books").documents()
                .import_(documentList, queryParameters);
        Assert.assertFalse(countriesImport.contains("\"success\":false"));
    }

    public void testImportAsString() throws Exception {
        ImportDocumentsParameters queryParameters = new ImportDocumentsParameters();
        queryParameters.action("create");
        String documentList = "{\"countryName\": \"India\", \"capital\": \"Washington\", \"gdp\": 5215}\n" +
                "{\"countryName\": \"Iran\", \"capital\": \"London\", \"gdp\": 5215}";
        String booksImport = this.client.collections("books").documents().import_(documentList, queryParameters);
        Assert.assertFalse(booksImport.contains("\"success\":false"));
    }

    public void testExportDocuments() throws Exception {
        helper.createTestDocument();
        ExportDocumentsParameters exportDocumentsParameters = new ExportDocumentsParameters();
        exportDocumentsParameters.setExcludeFields("id,publication_year,authors");
        String exportStr = client.collections("books").documents().export(exportDocumentsParameters);
        String expectedExportStr = "{\"average_rating\":3.2,\"ratings_count\":124,\"title\":\"Romeo and juliet\"}";
        Assert.assertEquals(expectedExportStr, exportStr);
    }

    public void testDirtyCreate() throws Exception {
        helper.createTestDocument();

        ImportDocumentsParameters queryParameters = new ImportDocumentsParameters();
        queryParameters.dirtyValues(ImportDocumentsParameters.DirtyValuesEnum.COERCE_OR_REJECT);
        queryParameters.action("upsert");

        String[] authors = {"shakspeare", "william"};
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
        Assert.assertEquals("1984", resp.get("title"));
    }

    public void testNestedObjectImport() throws Exception {
        // create collection with nested objects support
        List<Field> fields = new ArrayList<>();
        fields.add(new Field().name("address").type(FieldTypes.OBJECT).optional(true));
        fields.add(new Field().name("tags").type(FieldTypes.OBJECT_ARRAY).optional(true));

        CollectionSchema collectionSchema = new CollectionSchema();
        collectionSchema.name("items").fields(fields).setEnableNestedFields(true);
        client.collections().create(collectionSchema);

        ImportDocumentsParameters queryParameters = new ImportDocumentsParameters();
        queryParameters.action("create");

        List<NestedDocument> docs = new ArrayList<>();
        NestedDocument doc = new NestedDocument("LA", "CA", "USA")
                        .addTag("color", "Red")
                        .addTag("weight", "LOW");
        docs.add(doc);
        String itemImport = this.client.collections("items").documents().import_(docs, queryParameters);
        Assert.assertFalse(itemImport.contains("\"success\":false"));

        // try searching on the nested document
        SearchParameters searchParameters = new SearchParameters()
                .q("red")
                .queryBy("tags");

        SearchResult searchResult = client.collections("items").documents().search(searchParameters);
        Assert.assertEquals(1, searchResult.getFound().intValue());
        Assert.assertEquals(1, searchResult.getHits().size());
    }
}