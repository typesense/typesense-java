package org.typesense.api;

import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.typesense.model.AnalyticsRuleDeleteResponse;
import org.typesense.model.AnalyticsRuleParameters;
import org.typesense.model.AnalyticsRuleParametersDestination;
import org.typesense.model.AnalyticsRuleParametersSource;
import org.typesense.model.AnalyticsRuleSchema;
import org.typesense.model.AnalyticsRuleUpsertSchema;
import org.typesense.model.AnalyticsRulesRetrieveSchema;

public class AnalyticsRulesTest {

    private Client client;
    private Helper helper;

    @BeforeEach
    void setUp() throws Exception {
        helper = new Helper();
        client = helper.getClient();
        helper.teardown();
        helper.createTestCollection();
        helper.createTestQueryCollection();
    }

    @AfterEach
    void tearDown() throws Exception {
        helper.teardown();
    }

    @Test
    void testCreate() throws Exception {
        AnalyticsRuleSchema analyticsRuleSchema = new AnalyticsRuleSchema();
        analyticsRuleSchema.setName("nohits-queries");
        analyticsRuleSchema.setType(AnalyticsRuleSchema.TypeEnum.NOHITS_QUERIES);
        analyticsRuleSchema.setParams(new AnalyticsRuleParameters()
                .source(new AnalyticsRuleParametersSource()
                        .collections(Arrays.asList("books")))
                .destination(new AnalyticsRuleParametersDestination()
                        .collection("queries")));

        AnalyticsRuleSchema result = this.client.analytics().rules().create(analyticsRuleSchema);
        assertNotNull(result);
        assertEquals("nohits-queries", result.getName());

        AnalyticsRuleParameters params = result.getParams();
        assertNotNull(params);

        assertNotNull(params.getSource());
        assertEquals(Arrays.asList("books"), params.getSource().getCollections());

        assertNotNull(params.getDestination());
        assertEquals("queries", params.getDestination().getCollection());
    }

    @Test
    void testUpsert() throws Exception {
        AnalyticsRuleUpsertSchema analyticsRuleSchema = new AnalyticsRuleUpsertSchema()
                .type(AnalyticsRuleUpsertSchema.TypeEnum.NOHITS_QUERIES)
                .params(new AnalyticsRuleParameters()
                        .source(new AnalyticsRuleParametersSource()
                                .collections(Arrays.asList("books")))
                        .destination(new AnalyticsRuleParametersDestination()
                                .collection("queries")));

        AnalyticsRuleSchema result = this.client.analytics().rules().upsert("nohits-queries", analyticsRuleSchema);
        assertNotNull(result);
        assertEquals("nohits-queries", result.getName());

        AnalyticsRuleParameters params = result.getParams();
        assertNotNull(params);

        assertNotNull(params.getSource());
        assertEquals(Arrays.asList("books"), params.getSource().getCollections());

        assertNotNull(params.getDestination());
        assertEquals("queries", params.getDestination().getCollection());
    }

    @Test
    void testRetrieve() throws Exception {
        helper.createTestAnalyticsRule();
        AnalyticsRuleSchema result = this.client.analytics().rules("analytics-rule").retrieve();

        assertNotNull(result);
        assertEquals("analytics-rule", result.getName());

        AnalyticsRuleParameters params = result.getParams();
        assertNotNull(params);

        assertNotNull(params.getSource());
        assertEquals(Arrays.asList("books"), params.getSource().getCollections());

        assertNotNull(params.getDestination());
        assertEquals("queries", params.getDestination().getCollection());
    }

    @Test
    void testRetrieveAll() throws Exception {
        helper.createTestAnalyticsRule();
        AnalyticsRulesRetrieveSchema result = this.client.analytics().rules().retrieve();

        assertNotNull(result);
        assertEquals("analytics-rule", result.getRules().get(0).getName());
        assertEquals(1, result.getRules().size());

        AnalyticsRuleParameters params = result.getRules().get(0).getParams();
        assertNotNull(params);

        assertNotNull(params.getSource());
        assertEquals(Arrays.asList("books"), params.getSource().getCollections());

        assertNotNull(params.getDestination());
        assertEquals("queries", params.getDestination().getCollection());
    }

    @Test
    void testDelete() throws Exception {
        helper.createTestAnalyticsRule();
        AnalyticsRuleDeleteResponse result = this.client.analytics().rules("analytics-rule").delete();

        assertNotNull(result);
        assertEquals("analytics-rule", result.getName());
    }
}
