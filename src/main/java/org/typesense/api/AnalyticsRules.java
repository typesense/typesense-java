package org.typesense.api;

import org.typesense.api.utils.URLEncoding;
import org.typesense.model.AnalyticsRuleSchema;
import org.typesense.model.AnalyticsRuleUpsertSchema;
import org.typesense.model.AnalyticsRulesRetrieveSchema;

public class AnalyticsRules {

    private final ApiCall apiCall;
    public final static String RESOURCE_PATH = "/analytics/rules";

    public AnalyticsRules(ApiCall apiCall) {
        this.apiCall = apiCall;
    }

    public AnalyticsRuleSchema create(AnalyticsRuleSchema rule) throws Exception {
        return this.apiCall.post(RESOURCE_PATH, rule, null, AnalyticsRuleSchema.class);
    }

    public AnalyticsRuleSchema upsert(String name, AnalyticsRuleUpsertSchema rule) throws Exception {
        return this.apiCall.put(RESOURCE_PATH + "/" + URLEncoding.encodeURIComponent(name), rule, null,
                AnalyticsRuleSchema.class);
    }

    public AnalyticsRulesRetrieveSchema retrieve() throws Exception {
        return this.apiCall.get(RESOURCE_PATH, null, AnalyticsRulesRetrieveSchema.class);
    }

}
