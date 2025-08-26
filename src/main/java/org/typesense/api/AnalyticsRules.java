package org.typesense.api;

import org.typesense.model.AnalyticsRule;
import org.typesense.model.AnalyticsRuleCreate;
import java.util.List;

public class AnalyticsRules {

    private final ApiCall apiCall;
    private final AnalyticsRuleSerializer serializer;
    public final static String RESOURCE_PATH = "/analytics/rules";

    public AnalyticsRules(ApiCall apiCall) {
        this.apiCall = apiCall;
        this.serializer = new AnalyticsRuleSerializer();
    }
    
    public AnalyticsRules(ApiCall apiCall, AnalyticsRuleSerializer serializer) {
        this.apiCall = apiCall;
        this.serializer = serializer;
    }

    public AnalyticsRulesResponse create(List<AnalyticsRuleCreate> rules) throws Exception {
        String response = this.apiCall.post(RESOURCE_PATH, rules, null, String.class);
        return parseCreateResponse(response);
    }

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
