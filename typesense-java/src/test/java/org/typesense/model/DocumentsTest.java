package org.typesense.model;

import junit.framework.TestCase;
import org.junit.Test;
import org.typesense.api.SearchParameters;
import org.typesense.resources.Node;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;

public class DocumentsTest extends TestCase {

    public Client client;

    public void setUp() throws Exception {
        super.setUp();

        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(new Node("http","localhost","3001"));

        Configuration configuration = new Configuration(nodes, Duration.ofSeconds(3),"xyz");

        this.client = new Client(configuration);
    }

    @Test
    public void testRetrieveDocument(){
        System.out.println(client.collections("intbooks").documents("1").retrieve());
    }

    @Test
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

        authors = new String[]{"jk rowling", "william"};
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

    @Test
    public void testDeleteDocument(){
        System.out.println(client.collections("intbooks").documents("1").delete());
    }

    @Test
    public void testDeleteDocumentByQuery(){
        HashMap<String, String> queryParameters= new HashMap<>();
        queryParameters.put("filter_by", "publication_year:=2001");
        System.out.println(client.collections("intbooks").documents().delete(queryParameters));
    }

    @Test
    public void testUpdateDocumet(){
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

    @Test
    public void testExportDocuments(){
        System.out.println(client.collections("intbooks").documents().export());
    }

    @Test
    public void testSearchDocuments(){
        SearchParameters searchParameters = new SearchParameters()
                                                .query("h")
                                                .queryBy("title");
        org.typesense.api.SearchResult searchResult = client.collections("intbooks").documents().search(searchParameters);

        System.out.println(searchResult);
    }
}