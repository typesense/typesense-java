package org.typesense.api;

import org.typesense.api.utils.URLEncoding;
import org.typesense.model.AnalyticsRuleDeleteResponse;
import org.typesense.model.AnalyticsRuleSchema;

public class AnalyticsRule {
    private final ApiCall apiCall;
    private final String ruleId;

    public AnalyticsRule(String ruleId, ApiCall apiCall) {
        this.apiCall  = apiCall;
        this.ruleId = ruleId;
    }


    public AnalyticsRuleSchema retrieve() throws Exception {
        return this.apiCall.get(this.getEndpoint(), null, AnalyticsRuleSchema.class);
    }

    public AnalyticsRuleDeleteResponse delete() throws Exception {
        return this.apiCall.delete(this.getEndpoint(), null, AnalyticsRuleDeleteResponse.class);
    }

    private String getEndpoint() {
        return AnalyticsRules.RESOURCE_PATH + "/" + URLEncoding.encodeURIComponent(ruleId);
    }
    
}
