package org.typesense.api;

import org.typesense.api.utils.URLEncoding;
import org.typesense.model.AnalyticsRuleUpdate;

/**
 * Typesense analytics rule API wrapper.
 */
public class AnalyticsRule {
    private final ApiCall apiCall;
    private final String ruleId;
    private final AnalyticsRuleSerializer serializer;

    /**
     * Creates a new AnalyticsRule instance.
     * @param ruleId the {@code String} path parameter
     * @param apiCall the {@code ApiCall} instance used to send requests
     */
    public AnalyticsRule(String ruleId, ApiCall apiCall) {
        this.apiCall = apiCall;
        this.ruleId = ruleId;
        this.serializer = new AnalyticsRuleSerializer();
    }
    
    /**
     * Creates a new AnalyticsRule instance.
     * @param ruleId the {@code String} path parameter
     * @param apiCall the {@code ApiCall} instance used to send requests
     * @param serializer the {@code AnalyticsRuleSerializer} serializer
     */
    public AnalyticsRule(String ruleId, ApiCall apiCall, AnalyticsRuleSerializer serializer) {
        this.apiCall = apiCall;
        this.ruleId = ruleId;
        this.serializer = serializer;
    }

    /**
     * Retrieves an analytics rule.
     *
     * <p>
     * Retrieve the details of an analytics rule, given it's name
     *
     * <p>
     * HTTP: GET /analytics/rules/{ruleName}
     *
     * @return the {@code org.typesense.model.AnalyticsRule} response
     * @throws Exception if the request fails
     *
     * @see <a href="https://typesense.org/docs/latest/api/analytics-query-suggestions.html">Typesense docs</a>
     */
    public org.typesense.model.AnalyticsRule retrieve() throws Exception {
        String response = this.apiCall.get(this.getEndpoint(), null, String.class);
        return serializer.parseFromJson(response);
    }

    /**
     * Delete an analytics rule.
     *
     * <p>
     * Permanently deletes an analytics rule, given it's name
     *
     * <p>
     * HTTP: DELETE /analytics/rules/{ruleName}
     *
     * @return the {@code org.typesense.model.AnalyticsRule} response
     * @throws Exception if the request fails
     *
     * @see <a href="https://typesense.org/docs/latest/api/analytics-query-suggestions.html">Typesense docs</a>
     */
    public org.typesense.model.AnalyticsRule delete() throws Exception {
        String response = this.apiCall.delete(this.getEndpoint(), null, String.class);
        org.typesense.model.AnalyticsRule result = new org.typesense.model.AnalyticsRule();
        result.name(this.ruleId);
        return result;
    }

    /**
     * Upserts an analytics rule.
     *
     * <p>
     * Upserts an analytics rule with the given name.
     *
     * <p>
     * HTTP: PUT /analytics/rules/{ruleName}
     *
     * @param rule the {@code AnalyticsRuleUpdate} request body
     * @return the {@code org.typesense.model.AnalyticsRule} response
     * @throws Exception if the request fails
     *
     * @see <a href="https://typesense.org/docs/latest/api/analytics-query-suggestions.html">Typesense docs</a>
     */
    public org.typesense.model.AnalyticsRule update(AnalyticsRuleUpdate rule) throws Exception {
        return this.apiCall.put(this.getEndpoint(), rule, null, org.typesense.model.AnalyticsRule.class);
    }

    private String getEndpoint() {
        return AnalyticsRules.RESOURCE_PATH + "/" + URLEncoding.encodeURIComponent(ruleId);
    }
}
