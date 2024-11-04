package org.typesense.api;

import org.typesense.model.AnalyticsEventCreateResponse;
import org.typesense.model.AnalyticsEventCreateSchema;


public class AnalyticsEvents {
    private final ApiCall apiCall;
    public final static String RESOURCE_PATH = "/analytics/events";

    public AnalyticsEvents(ApiCall apiCall) {
        this.apiCall = apiCall;
    }

    public AnalyticsEventCreateResponse create(AnalyticsEventCreateSchema event) throws Exception {
        return this.apiCall.post(RESOURCE_PATH, event, null, AnalyticsEventCreateResponse.class);
    }
}
