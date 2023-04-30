package org.typesense.api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.typesense.model.SearchOverrideExclude;
import org.typesense.model.SearchOverrideInclude;
import org.typesense.model.SearchOverrideRule;
import org.typesense.model.SearchOverrideSchema;

import java.util.ArrayList;
import java.util.List;

class OverridesTest {

    private Client client;
    private Helper helper;

    @BeforeEach
    void setUp() throws Exception {
        helper = new Helper();
        helper.teardown();
        client = helper.getClient();
        helper.createTestCollection();
        helper.createTestOverrirde();
    }

    @AfterEach
    void tearDown() throws Exception {
        helper.teardown();
    }

    @Test
    void testUpsert() throws Exception {
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

    @Test
    void testRetrieveAll() throws Exception {
        System.out.println(this.client.collections("books").overrides().retrieve());
    }

    @Test
    void testRetrieve() throws Exception {
        System.out.println(this.client.collections("books").overrides("customize-apple").retrieve());
    }

    @Test
    void testDelete() throws Exception {
        System.out.println(this.client.collections("books").overrides("customize-apple").delete());
    }
}