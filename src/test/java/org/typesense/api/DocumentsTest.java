package org.typesense.api;

import junit.framework.TestCase;
import org.typesense.model.*;
import org.typesense.resources.Node;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class DocumentsTest extends TestCase {

    public Client client;

    public void setUp() throws Exception {
        super.setUp();

        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(new Node("http","localhost","3001"));

        Configuration configuration = new Configuration(nodes, Duration.ofSeconds(3),"xyz");

        this.client = new Client(configuration);

        ArrayList<Field> fields = new ArrayList<>();
        fields.add(new Field().name(".*").type(Field.TypeEnum.AUTO).optional(true));

        CollectionSchema collectionSchema = new CollectionSchema();
        collectionSchema.name("intbooks").fields(fields);

        CollectionResponse cr = client.collections().create(collectionSchema);
    }

    public void tearDown() throws Exception {
        super.tearDown();

        client.collections("intbooks").delete();
    }

    public void testRetrieveDocument(){
        System.out.println(client.collections("intbooks").documents("1").retrieve());
    }

    public void testCreateDocument(){

        String[] authors = {"shakspeare","william"};
        HashMap<String, Object> hmap = new HashMap<>();
        hmap.put("title","Romeo and juliet");
        hmap.put("authors",authors);
        hmap.put("image_url","fgfg");
        hmap.put("publication_year",1666);
        hmap.put("ratings_count",124);
        hmap.put("average_rating",3.2);
        hmap.put("publication_year_facet","dff");
        hmap.put("authors_facet",authors);
        hmap.put("id","1");

        System.out.println(client.collections("intbooks").documents().create(hmap));

        authors = new String[]{"jk", "Rowling"};
        hmap.put("title","harry potter");
        hmap.put("authors",authors);
        hmap.put("image_url","fgfg");
        hmap.put("publication_year",2001);
        hmap.put("ratings_count",231);
        hmap.put("average_rating",5.6);
        hmap.put("publication_year_facet","2001");
        hmap.put("authors_facet",authors);
        hmap.put("id","2");

        System.out.println(client.collections("intbooks").documents().create(hmap));
    }

    public void testUpsertDocument(){

        String[] authors = {"shakspeare","william"};
        HashMap<String, Object> hmap = new HashMap<>();
        hmap.put("title","Romeo and juliet");
        hmap.put("authors",authors);
        hmap.put("image_url","fgfg");
        hmap.put("publication_year",1666);
        hmap.put("ratings_count",124);
        hmap.put("average_rating",3.2);
        hmap.put("publication_year_facet","dff");
        hmap.put("authors_facet",authors);
        hmap.put("id","1");

        System.out.println(client.collections("intbooks").documents().upsert(hmap));

        authors = new String[]{"jk", "Rowling"};
        hmap.put("title","harry potter");
        hmap.put("authors",authors);
        hmap.put("image_url","fgfg");
        hmap.put("publication_year",2001);
        hmap.put("ratings_count",231);
        hmap.put("average_rating",5.6);
        hmap.put("publication_year_facet","2001");
        hmap.put("authors_facet",authors);
        hmap.put("id","3");

        System.out.println(client.collections("intbooks").documents().upsert(hmap));

    }

    public void testDeleteDocument(){
        String[] authors = {"shakspeare","william"};
        HashMap<String, Object> hmap = new HashMap<>();
        hmap.put("title","Romeo and juliet");
        hmap.put("authors",authors);
        hmap.put("image_url","fgfg");
        hmap.put("publication_year",1666);
        hmap.put("ratings_count",124);
        hmap.put("average_rating",3.2);
        hmap.put("publication_year_facet","dff");
        hmap.put("authors_facet",authors);
        hmap.put("id","1");

        client.collections("intbooks").documents().upsert(hmap);

        System.out.println(client.collections("intbooks").documents("1").delete());
    }

    public void testDeleteDocumentByQuery(){
        String[] authors = {"shakspeare","william"};
        HashMap<String, Object> hmap = new HashMap<>();
        hmap.put("title","Romeo and juliet");
        hmap.put("authors",authors);
        hmap.put("image_url","fgfg");
        hmap.put("publication_year",1666);
        hmap.put("ratings_count",124);
        hmap.put("average_rating",3.2);
        hmap.put("publication_year_facet","dff");
        hmap.put("authors_facet",authors);
        hmap.put("id","1");

        client.collections("intbooks").documents().upsert(hmap);

        DeleteDocumentsParameters deleteDocumentsParameters = new DeleteDocumentsParameters();
        deleteDocumentsParameters.filterBy("publication_year:=[1666]");
        deleteDocumentsParameters.batchSize(10);
        System.out.println(client.collections("intbooks").documents().delete(deleteDocumentsParameters));
    }

    public void testUpdateDocument(){
        String[] authors = {"shakspeare","william"};
        HashMap<String, Object> hmap = new HashMap<>();
        hmap.put("title","Romeo and juliet");
        hmap.put("authors",authors);
        hmap.put("image_url","fgfg");
        hmap.put("publication_year",1666);
        hmap.put("ratings_count",124);
        hmap.put("average_rating",3.2);
        hmap.put("publication_year_facet","dff");
        hmap.put("authors_facet",authors);
        hmap.put("id","1");

        client.collections("intbooks").documents().upsert(hmap);

        authors = new String[]{"Shakespeare", "william"};
        HashMap<String , Object> document = new HashMap<>();
        document.put("title","Romeo and juliet");
        document.put("authors",authors);
        hmap.put("id","1");

        System.out.println(client.collections("intbooks").documents("1").update(document));
    }

    public void testExportDocuments(){
        ExportDocumentsParameters exportDocumentsParameters = new ExportDocumentsParameters();
        exportDocumentsParameters.addExcludeFieldsItem("image_url");
        exportDocumentsParameters.addIncludeFieldsItem("publication_year_facet");
        exportDocumentsParameters.addIncludeFieldsItem("authors_facet");
        System.out.println(client.collections("intbooks").documents().export(exportDocumentsParameters));
    }

    public void testSearchDocuments(){
        String[] authors = {"shakspeare","william"};
        HashMap<String, Object> hmap = new HashMap<>();
        hmap.put("title","Romeo and juliet");
        hmap.put("authors",authors);
        hmap.put("image_url","fgfg");
        hmap.put("publication_year",1666);
        hmap.put("ratings_count",124);
        hmap.put("average_rating",3.2);
        hmap.put("publication_year_facet","dff");
        hmap.put("authors_facet",authors);
        hmap.put("id","1");

        client.collections("intbooks").documents().upsert(hmap);

        SearchParameters searchParameters = new SearchParameters()
                                                .q("romeo")
                                                .addQueryByItem("title").addQueryByItem("authors")
                                                .addPrefixItem(false).addPrefixItem(true);
        org.typesense.model.SearchResult searchResult = client.collections("intbooks").documents().search(searchParameters);

        System.out.println(searchResult);
    }

    public void testImport(){
        HashMap<String, Object> document1 = new HashMap<>();
        HashMap<String, Object> document2 = new HashMap<>();
        ImportDocumentsParameters queryParameters = new ImportDocumentsParameters();
        ArrayList<HashMap<String, Object>> documentList = new ArrayList<>();

        document1.put("countryName","India");
        document1.put("capital","Delhi");
        document1.put("gdp",23);
        document2.put("countryName","Us");
        document2.put("capital","Washington");
        document2.put("gdp",233);

        documentList.add(document1);
        documentList.add(document2);

        queryParameters.action("create");
        System.out.println(this.client.collections("Countries").documents().import_(documentList, queryParameters));
    }

    public void testImportAsString(){
        ImportDocumentsParameters queryParameters = new ImportDocumentsParameters();
        queryParameters.action("create");
        String documentList = "{\"countryName\": \"India\", \"capital\": \"Washington\", \"gdp\": 5215}\n" +
                "{\"countryName\": \"Iran\", \"capital\": \"London\", \"gdp\": 5215}";
        System.out.println(this.client.collections("Countries").documents().import_(documentList, queryParameters));
    }

    public void testImportFromFile() throws FileNotFoundException {
        ArrayList<Field> fields = new ArrayList<>();
        fields.add(new Field().name("title").type(Field.TypeEnum.STRING));
        fields.add(new Field().name("authors").type(Field.TypeEnum.STRING_));
        fields.add(new Field().name("image_url").type(Field.TypeEnum.STRING));
        fields.add(new Field().name("publication_year").type(Field.TypeEnum.INT32));
        fields.add(new Field().name("ratings_count").type(Field.TypeEnum.INT32));
        fields.add(new Field().name("average_rating").type(Field.TypeEnum.FLOAT));
        fields.add(new Field().name("publication_year_facet").type(Field.TypeEnum.STRING).facet(true));
        fields.add(new Field().name("authors_facet").type(Field.TypeEnum.STRING_).facet(true));

        CollectionSchema collectionSchema = new CollectionSchema();
        collectionSchema.name("books").fields(fields).defaultSortingField("ratings_count");

        CollectionResponse collectionResponse = client.collections().create(collectionSchema);

        File myObj = new File("books.jsonl");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            this.client.collections("books").documents().create(data);
        }
    }

    public void testDirtyCreate(){
        HashMap<String, Object > document = new HashMap<>();
        ImportDocumentsParameters queryParameters = new ImportDocumentsParameters();

        document.put("countryName",1984);
        document.put("capital","Tokyo");
        document.put("gdp",29);


        System.out.println(this.client.collections("Countries").documents().create(document,queryParameters));
    }
}