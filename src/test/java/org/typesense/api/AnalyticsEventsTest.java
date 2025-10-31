package org.typesense.api;

import java.util.HashMap;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.typesense.model.AnalyticsEvent;
import org.typesense.model.AnalyticsEventCreateResponse;
import org.typesense.model.AnalyticsEventData;

public class AnalyticsEventsTest {

    private Client client;
    private Helper helper;
    
    private String ruleName;

    @BeforeEach
    void setUp() throws Exception {
        helper = new Helper();
        client = helper.getClient();
        
        if (!Helper.isV30OrAbove(client)) {
            org.junit.jupiter.api.Assumptions.assumeTrue(false, "Skipping test - requires Typesense v30 or above");
        }
        
        helper.teardown();
        helper.createTestCollection();
        helper.createTestQueryCollection();
        helper.createTestQueryDocument();
        helper.createTestAnalyticsCollection();
        String ruleName = helper.createTestAnalyticsRule();
        
        this.ruleName = ruleName;
    }

    @AfterEach
    void tearDown() throws Exception {
        helper.teardown();
    }

    @Test
    void testCreate() throws Exception {
        
        AnalyticsEventData eventData = new AnalyticsEventData()
                .q("running shoes")
                .userId("1234")
                .docId("query1");
        
        AnalyticsEvent analyticsEvent = new AnalyticsEvent()
                .eventType("search")
                .name(ruleName)
                .data(eventData);

        AnalyticsEventCreateResponse result = this.client.analytics().events().create(analyticsEvent);
        assertNotNull(result);
        assertEquals(true, result.isOk());
    }
}
