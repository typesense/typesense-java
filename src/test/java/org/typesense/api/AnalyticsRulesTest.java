package org.typesense.api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.typesense.model.AnalyticsRule;
import org.typesense.model.AnalyticsRuleCreate;
import org.typesense.model.AnalyticsRuleCreateParams;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.typesense.model.InlineResponse2003;
import org.typesense.model.CollectionSchema;
import org.typesense.model.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;


class AnalyticsRulesTest {

    private Client client;
    private Helper helper;

    @BeforeEach
    void setUp() throws Exception {
        helper = new Helper();
        client = helper.getClient();
        
        if (!Helper.isV30OrAbove(client)) {
            org.junit.jupiter.api.Assumptions.assumeTrue(false, "Skipping test - requires Typesense v30 or above");
        }

        try {
            CollectionSchema collectionSchema = new CollectionSchema()
                    .name("companies")
                    .fields(Arrays.asList(
                            new Field().name("company_name").type("string"),
                            new Field().name("num_employees").type("int32"),
                            new Field().name("country").type("string")
                    ));
            client.collections().create(collectionSchema);
        } catch (Exception e) {
        }
        
        try {
            CollectionSchema analyticsCollectionSchema = new CollectionSchema()
                    .name("analytics_data")
                    .fields(Arrays.asList(
                            new Field().name("rule_name").type("string"),
                            new Field().name("event_type").type("string"),
                            new Field().name("counter_value").type("int32"),
                            new Field().name("timestamp").type("int64")
                    ));
            client.collections().create(analyticsCollectionSchema);
        } catch (Exception e) {
        }
    }

    @AfterEach
    void tearDown() throws Exception {
        helper.teardown();
    }

    @Test
    void testCreate() throws Exception {
        
        String ruleName = "test-rule-" + System.currentTimeMillis();
        
        AnalyticsRuleCreate analyticsRule = new AnalyticsRuleCreate()
                .name(ruleName)
                .type(AnalyticsRuleCreate.TypeEnum.COUNTER)
                .collection("analytics_data")
                .eventType("click")
                .params(new AnalyticsRuleCreateParams()
                        .counterField("num_employees")
                        .weight(1));

        List<AnalyticsRuleCreate> rules = new ArrayList<>();
        rules.add(analyticsRule);

        AnalyticsRules.AnalyticsRulesResponse result = this.client.analytics().rules().create(rules);
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.getCount());
        
        AnalyticsRule createdRule = result.getFirstRule();
        assertNotNull(createdRule);
        assertEquals(ruleName, createdRule.getName());
        assertEquals(AnalyticsRuleCreate.TypeEnum.COUNTER, createdRule.getType());
        assertEquals("analytics_data", createdRule.getCollection());
        assertEquals("click", createdRule.getEventType());
    }

    @Test
    void testRetrieve() throws Exception {
        
        String ruleName = "test-rule-" + System.currentTimeMillis();
        
        AnalyticsRuleCreate analyticsRule = new AnalyticsRuleCreate()
                .name(ruleName)
                .type(AnalyticsRuleCreate.TypeEnum.COUNTER)
                .collection("analytics_data")
                .eventType("click")
                .params(new AnalyticsRuleCreateParams()
                        .counterField("num_employees")
                        .weight(1));

        List<AnalyticsRuleCreate> rules = new ArrayList<>();
        rules.add(analyticsRule);

        this.client.analytics().rules().create(rules);

        List<AnalyticsRule> result = this.client.analytics().rules().retrieve();
        assertNotNull(result);
        assertTrue(result.size() >= 1, "number of rules is invalid");

        AnalyticsRule foundRule = null;
        for (AnalyticsRule rule : result) {
            if (ruleName.equals(rule.getName())) {
                foundRule = rule;
                break;
            }
        }

        assertNotNull(foundRule, "rule not found");
        assertEquals(ruleName, foundRule.getName());
        assertEquals(AnalyticsRuleCreate.TypeEnum.COUNTER, foundRule.getType());
        assertEquals("analytics_data", foundRule.getCollection());
        assertEquals("click", foundRule.getEventType());
    }
    
    @Test
    void testRetrieveSingle() throws Exception {
        
        String ruleName = "test-rule-" + System.currentTimeMillis();
        
        AnalyticsRuleCreate analyticsRule = new AnalyticsRuleCreate()
                .name(ruleName)
                .type(AnalyticsRuleCreate.TypeEnum.COUNTER)
                .collection("analytics_data")
                .eventType("click")
                .params(new AnalyticsRuleCreateParams()
                        .counterField("num_employees")
                        .weight(1));

        List<AnalyticsRuleCreate> rules = new ArrayList<>();
        rules.add(analyticsRule);

        this.client.analytics().rules().create(rules);

        AnalyticsRule result = this.client.analytics().rules(ruleName).retrieve();
        assertNotNull(result);
        assertEquals(ruleName, result.getName());
        assertEquals(AnalyticsRuleCreate.TypeEnum.COUNTER, result.getType());
        assertEquals("analytics_data", result.getCollection());
        assertEquals("click", result.getEventType());
    }
    
    @Test
    void testDelete() throws Exception {
        
        String ruleName = "test-rule-" + System.currentTimeMillis();
        
        AnalyticsRuleCreate analyticsRule = new AnalyticsRuleCreate()
                .name(ruleName)
                .type(AnalyticsRuleCreate.TypeEnum.COUNTER)
                .collection("analytics_data")
                .eventType("click")
                .params(new AnalyticsRuleCreateParams()
                        .counterField("num_employees")
                        .weight(1));

        List<AnalyticsRuleCreate> rules = new ArrayList<>();
        rules.add(analyticsRule);

        this.client.analytics().rules().create(rules);

        AnalyticsRule result = this.client.analytics().rules(ruleName).delete();
        assertNotNull(result);
        assertEquals(ruleName, result.getName());
        
        try {
            this.client.analytics().rules(ruleName).retrieve();
            fail("Rule should have been deleted");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("not found") || e.getMessage().contains("404"));
        }
    }
}
