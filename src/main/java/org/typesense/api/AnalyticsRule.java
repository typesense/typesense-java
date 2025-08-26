package org.typesense.api;

import org.typesense.api.utils.URLEncoding;
import org.typesense.model.AnalyticsRuleUpdate;

public class AnalyticsRule {
    private final ApiCall apiCall;
    private final String ruleId;
    private final AnalyticsRuleSerializer serializer;

    public AnalyticsRule(String ruleId, ApiCall apiCall) {
        this.apiCall = apiCall;
        this.ruleId = ruleId;
        this.serializer = new AnalyticsRuleSerializer();
    }
    
    public AnalyticsRule(String ruleId, ApiCall apiCall, AnalyticsRuleSerializer serializer) {
        this.apiCall = apiCall;
        this.ruleId = ruleId;
        this.serializer = serializer;
    }

    public org.typesense.model.AnalyticsRule retrieve() throws Exception {
        String response = this.apiCall.get(this.getEndpoint(), null, String.class);
        return serializer.parseFromJson(response);
    }

    public org.typesense.model.AnalyticsRule delete() throws Exception {
        String response = this.apiCall.delete(this.getEndpoint(), null, String.class);
        org.typesense.model.AnalyticsRule result = new org.typesense.model.AnalyticsRule();
        result.name(this.ruleId);
        return result;
    }

    public org.typesense.model.AnalyticsRule update(AnalyticsRuleUpdate rule) throws Exception {
        return this.apiCall.put(this.getEndpoint(), rule, null, org.typesense.model.AnalyticsRule.class);
    }

    private String getEndpoint() {
        return AnalyticsRules.RESOURCE_PATH + "/" + URLEncoding.encodeURIComponent(ruleId);
    }
}
