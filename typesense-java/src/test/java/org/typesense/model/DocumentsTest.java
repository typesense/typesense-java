package org.typesense.model;

import junit.framework.TestCase;
import org.typesense.api.CollectionResponse;
import org.typesense.api.CollectionSchema;
import org.typesense.api.Field;
import org.typesense.api.SearchParameters;
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

    public void testDeleteDocument(){
        System.out.println(client.collections("intbooks").documents("1").delete());
    }

    public void testDeleteDocumentByQuery(){
        HashMap<String, String> queryParameters= new HashMap<>();
        queryParameters.put("filter_by", "publication_year:=2001");
        System.out.println(client.collections("intbooks").documents().delete(queryParameters));
    }

    public void testUpdateDocument(){
        String[] authors = {"Shakespeare","william"};
        HashMap<String , Object> document = new HashMap<>();
        document.put("title","Romeo and juliet");
        document.put("authors",authors);
        document.put("image_url","fgfg");
        document.put("publication_year",2010);
        document.put("ratings_count",500);
        document.put("average_rating",3.2);
        document.put("publication_year_facet","dff");
        document.put("authors_facet",authors);

        System.out.println(client.collections("intbooks").documents("1").update(document));
    }

    public void testExportDocuments(){
        System.out.println(client.collections("books").documents().export());
    }

    public void testSearchDocuments(){
        SearchParameters searchParameters = new SearchParameters()
                                                .query("i")
                                                .queryBy("countryName")
                                                .page(1);
        org.typesense.api.SearchResult searchResult = client.collections("Countries").documents().search(searchParameters);

        System.out.println(searchResult);
    }

    public void testImport(){
        HashMap<String, Object> document1 = new HashMap<>();
        HashMap<String, Object> document2 = new HashMap<>();
        HashMap<String, String> queryParameters = new HashMap<>();
        ArrayList<HashMap<String, Object>> documentList = new ArrayList<>();

        document1.put("countryName","India");
        document1.put("capital","Delhi");
        document1.put("gdp",23);
        document2.put("countryName","Us");
        document2.put("capital","Washington");
        document2.put("gdp",233);

        documentList.add(document1);
        documentList.add(document2);

        queryParameters.put("action","create");
        System.out.println(this.client.collections("Countries").documents().import_(documentList, queryParameters));
    }

    public void testImportAsString(){
        HashMap<String, String> queryParameters = new HashMap<>();
        queryParameters.put("action","create");
        String documentList = "{\"countryName\": \"India\", \"capital\": \"Washington\", \"gdp\": 5215}\n" +
                "{\"countryName\": \"Iran\", \"capital\": \"London\", \"gdp\": 5215}";
        System.out.println(this.client.collections("Countries").documents().import_(documentList, queryParameters));
    }

    public void testImportFromFile() throws FileNotFoundException {
        ArrayList<Field> fields = new ArrayList<>();
        fields.add(new Field().name("title").type("string"));
        fields.add(new Field().name("authors").type("string[]"));
        fields.add(new Field().name("image_url").type("string"));
        fields.add(new Field().name("publication_year").type("int32"));
        fields.add(new Field().name("ratings_count").type("int32"));
        fields.add(new Field().name("average_rating").type("float"));
        fields.add(new Field().name("publication_year_facet").type("string").facet(true));
        fields.add(new Field().name("authors_facet").type("string[]").facet(true));

        CollectionSchema collectionSchema = new CollectionSchema();
        collectionSchema.name("books").fields(fields).defaultSortingField("ratings_count");

        CollectionResponse cr = client.collections().create(collectionSchema);

        File myObj = new File("/home/shiva/Desktop/typesense-java/typesense-java/src/test/java/org/typesense/model/books.jsonl");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            this.client.collections("books").documents().create(data);
        }
    }
}