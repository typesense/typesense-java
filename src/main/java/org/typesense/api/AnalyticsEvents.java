package org.typesense.api;

import org.typesense.model.AnalyticsEvent;
import org.typesense.model.AnalyticsEventCreateResponse;
import org.typesense.model.AnalyticsEventsResponse;
import java.util.Map;

public class AnalyticsEvents {
    private final ApiCall apiCall;
    public final static String RESOURCE_PATH = "/analytics/events";

    public AnalyticsEvents(ApiCall apiCall) {
        this.apiCall = apiCall;
    }

    public AnalyticsEventCreateResponse create(AnalyticsEvent event) throws Exception {
        return this.apiCall.post(RESOURCE_PATH, event, null, AnalyticsEventCreateResponse.class);
    }

    public AnalyticsEventsResponse retrieve(Map<String, Object> params) throws Exception {
        return this.apiCall.get(RESOURCE_PATH, params, AnalyticsEventsResponse.class);
    }
}
