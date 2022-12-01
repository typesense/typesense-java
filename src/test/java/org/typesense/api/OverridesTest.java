package org.typesense.api;

import junit.framework.TestCase;
import org.typesense.api.exceptions.TypesenseError;
import org.typesense.model.SearchOverrideExclude;
import org.typesense.model.SearchOverrideInclude;
import org.typesense.model.SearchOverrideRule;
import org.typesense.model.SearchOverrideSchema;

import java.util.ArrayList;
import java.util.List;

public class OverridesTest extends TestCase {

    private Client client;
    private Helper helper;

    public void setUp() throws Exception {
        super.setUp();
        helper = new Helper();
        helper.teardown();
        client = helper.getClient();
        helper.createTestCollection();
        helper.createTestOverrirde();
    }

    public void tearDown() throws Exception {
        super.tearDown();
        helper.teardown();
    }

    public void testUpsert() throws Exception {
        SearchOverrideSchema searchOverrideSchema = new SearchOverrideSchema();

        List<SearchOverrideInclude> searchOverrideIncludes = new ArrayList<>();
        searchOverrideIncludes.add(new SearchOverrideInclude().id("422").position(1));
        searchOverrideIncludes.add(new SearchOverrideInclude().id("54").position(2));

        List<SearchOverrideExclude> searchOverrideExcludes = new ArrayList<>();
        searchOverrideExcludes.add(new SearchOverrideExclude().id("287"));

        searchOverrideSchema.rule(new SearchOverrideRule().query("apple").match(SearchOverrideRule.MatchEnum.EXACT))
                .includes(searchOverrideIncludes)
                .excludes(searchOverrideExcludes);

        System.out.println(client.collections("books").overrides().upsert("apple", searchOverrideSchema));
    }

    public void testRetrieveAll() throws Exception {
        System.out.println(this.client.collections("books").overrides().retrieve());
    }

    public void testRetrieve() throws Exception {
        System.out.println(this.client.collections("books").overrides("customize-apple").retrieve());
    }

    public void testDelete() throws Exception {
        System.out.println(this.client.collections("books").overrides("customize-apple").delete());
    }
}