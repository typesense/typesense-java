package org.typesense.model;

import junit.framework.TestCase;
import org.typesense.api.SearchOverrideExclude;
import org.typesense.api.SearchOverrideInclude;
import org.typesense.api.SearchOverrideRule;
import org.typesense.api.SearchOverrideSchema;
import org.typesense.resources.Node;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class OverridesTest extends TestCase {

    private Client client;

    public void setUp() throws Exception {
        super.setUp();

        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(new Node("http","localhost","3001"));

        Configuration configuration = new Configuration(nodes, Duration.ofSeconds(3),"xyz");

        this.client = new Client(configuration);
    }

    public void testUpsert() {
        SearchOverrideSchema searchOverrideSchema = new SearchOverrideSchema();

        List<SearchOverrideInclude> searchOverrideIncludes = new ArrayList<>();
        searchOverrideIncludes.add(new SearchOverrideInclude().id("422").position(1));
        searchOverrideIncludes.add(new SearchOverrideInclude().id("54").position(2));

        List<SearchOverrideExclude> searchOverrideExcludes = new ArrayList<>();
        searchOverrideExcludes.add(new SearchOverrideExclude().id("287"));

        searchOverrideSchema.rule(new SearchOverrideRule().query("apple").match(SearchOverrideRule.MatchEnum.EXACT))
                            .includes(searchOverrideIncludes)
                            .excludes(searchOverrideExcludes);

        System.out.println(client.collections("intbooks").overrides().upsert("customize-apple", searchOverrideSchema));
    }

    public void testRetrieveAll(){
        System.out.println(this.client.collections("intbooks").overrides().retrieve());
    }

    public void testRetrieve(){
        System.out.println(this.client.collections("intbooks").overrides("customize-apple").retrieve());
    }

    public void testDelete(){
        System.out.println(this.client.collections("intbooks").overrides("customize-apple").delete());
    }
}