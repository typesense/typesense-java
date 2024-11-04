package org.typesense.api;

import java.util.HashMap;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.typesense.model.AnalyticsEventCreateResponse;
import org.typesense.model.AnalyticsEventCreateSchema;

public class AnalyticsEventsTest {

    private Client client;
    private Helper helper;

    @BeforeEach
    void setUp() throws Exception {
        helper = new Helper();
        client = helper.getClient();
        helper.teardown();
        helper.createTestCollection();
        helper.createTestQueryCollection();
        helper.createTestAnalyticsRule();
    }

    @AfterEach
    void tearDown() throws Exception {
        helper.teardown();
    }

    @Test
    void testCreate() throws Exception {
        HashMap<String, Object> eventData = new HashMap<>();
        eventData.put("q", "running shoes");
        eventData.put("user_id", "1234");
        AnalyticsEventCreateSchema analyticsEvent = new AnalyticsEventCreateSchema()
                .type("search")
                .name("products_search_event")
                .data(eventData);

        AnalyticsEventCreateResponse result = this.client.analytics().events().create(analyticsEvent);
        assertNotNull(result);
        assertEquals(true, result.isOk());

    }
}
