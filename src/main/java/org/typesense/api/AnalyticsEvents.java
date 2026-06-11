package org.typesense.api;

import org.typesense.model.AnalyticsEvent;
import org.typesense.model.AnalyticsEventCreateResponse;
import org.typesense.model.AnalyticsEventsResponse;
import java.util.Map;

/**
 * Typesense analytics events API wrapper.
 */
public class AnalyticsEvents {
    private final ApiCall apiCall;
    public final static String RESOURCE_PATH = "/analytics/events";

    /**
     * Creates a new AnalyticsEvents instance.
     * @param apiCall the {@code ApiCall} instance used to send requests
     */
    public AnalyticsEvents(ApiCall apiCall) {
        this.apiCall = apiCall;
    }

    /**
     * Create an analytics event.
     *
     * <p>
     * Submit a single analytics event. The event must correspond to an existing analytics rule by name.
     *
     * <p>
     * HTTP: POST /analytics/events
     *
     * @param event the {@code AnalyticsEvent} request body
     * @return the {@code AnalyticsEventCreateResponse} response
     * @throws Exception if the request fails
     *
     * @see <a href="https://typesense.org/docs/latest/api/analytics-query-suggestions.html">Typesense docs</a>
     */
    public AnalyticsEventCreateResponse create(AnalyticsEvent event) throws Exception {
        return this.apiCall.post(RESOURCE_PATH, event, null, AnalyticsEventCreateResponse.class);
    }

    /**
     * Retrieve analytics events.
     *
     * <p>
     * Retrieve the most recent events for a user and rule.
     *
     * <p>
     * HTTP: GET /analytics/events
     *
     * @param params the {@code Map<String,Object>} query parameters
     * @return the {@code AnalyticsEventsResponse} response
     * @throws Exception if the request fails
     *
     * @see <a href="https://typesense.org/docs/latest/api/analytics-query-suggestions.html">Typesense docs</a>
     */
    public AnalyticsEventsResponse retrieve(Map<String, Object> params) throws Exception {
        return this.apiCall.get(RESOURCE_PATH, params, AnalyticsEventsResponse.class);
    }
}
