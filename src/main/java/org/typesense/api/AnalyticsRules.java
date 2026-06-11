package org.typesense.api;

import org.typesense.model.AnalyticsRule;
import org.typesense.model.AnalyticsRuleCreate;
import java.util.List;

/**
 * Typesense analytics rules API wrapper.
 */
public class AnalyticsRules {

    private final ApiCall apiCall;
    private final AnalyticsRuleSerializer serializer;
    public final static String RESOURCE_PATH = "/analytics/rules";

    /**
     * Creates a new AnalyticsRules instance.
     * @param apiCall the {@code ApiCall} instance used to send requests
     */
    public AnalyticsRules(ApiCall apiCall) {
        this.apiCall = apiCall;
        this.serializer = new AnalyticsRuleSerializer();
    }
    
    /**
     * Creates a new AnalyticsRules instance.
     * @param apiCall the {@code ApiCall} instance used to send requests
     * @param serializer the {@code AnalyticsRuleSerializer} serializer
     */
    public AnalyticsRules(ApiCall apiCall, AnalyticsRuleSerializer serializer) {
        this.apiCall = apiCall;
        this.serializer = serializer;
    }

    /**
     * Create analytics rule(s).
     *
     * <p>
     * Create one or more analytics rules. You can send a single rule object or an array of rule objects.
     *
     * <p>
     * HTTP: POST /analytics/rules
     *
     * @param rules the {@code List<AnalyticsRuleCreate>} request body
     * @return the {@code AnalyticsRulesResponse} response
     * @throws Exception if the request fails
     *
     * @see <a href="https://typesense.org/docs/latest/api/analytics-query-suggestions.html">Typesense docs</a>
     */
    public AnalyticsRulesResponse create(List<AnalyticsRuleCreate> rules) throws Exception {
        String response = this.apiCall.post(RESOURCE_PATH, rules, null, String.class);
        return parseCreateResponse(response);
    }

    /**
     * Retrieve analytics rules.
     *
     * <p>
     * Retrieve all analytics rules. Use the optional rule_tag filter to narrow down results.
     *
     * <p>
     * HTTP: GET /analytics/rules
     *
     * @return the {@code List<AnalyticsRule>} response
     * @throws Exception if the request fails
     *
     * @see <a href="https://typesense.org/docs/latest/api/analytics-query-suggestions.html">Typesense docs</a>
     */
    public List<AnalyticsRule> retrieve() throws Exception {
        String response = this.apiCall.get(RESOURCE_PATH, null, String.class);
        return parseRetrieveResponse(response);
    }

    /**
     * Parse the create response which can be either a single AnalyticsRule or an array
     */
    private AnalyticsRulesResponse parseCreateResponse(String jsonResponse) throws Exception {
        List<AnalyticsRule> rules = serializer.parseListFromJson(jsonResponse);
        
        for (AnalyticsRule rule : rules) {
            if (rule.getName() == null) {
                throw new RuntimeException("Analytics rule creation failed: rule name is null");
            }
        }
        
        return new AnalyticsRulesResponse(rules);
    }
    
    /**
     * Parse the retrieve response which is always an array of AnalyticsRule objects
     */
    private List<AnalyticsRule> parseRetrieveResponse(String jsonResponse) throws Exception {
        List<AnalyticsRule> rules = serializer.parseListFromJson(jsonResponse);
        
        return rules;
    }

    /**
     * Response wrapper for analytics rules operations
     */
    public static class AnalyticsRulesResponse {
        private final List<AnalyticsRule> rules;

        /**
         * Creates a new AnalyticsRulesResponse instance.
         * @param rules the {@code List<AnalyticsRule>} rules
         */
        public AnalyticsRulesResponse(List<AnalyticsRule> rules) {
            this.rules = rules;
        }

        public List<AnalyticsRule> getRules() {
            return rules;
        }

        public AnalyticsRule getFirstRule() {
            return rules.isEmpty() ? null : rules.get(0);
        }

        public int getCount() {
            return rules.size();
        }

        public boolean isEmpty() {
            return rules.isEmpty();
        }
    }
}
