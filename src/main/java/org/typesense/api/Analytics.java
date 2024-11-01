package org.typesense.api;

import java.util.HashMap;
import java.util.Map;

public class Analytics {
    private final ApiCall apiCall;
    private final AnalyticsRules rules;
    private final Map<String, AnalyticsRule> individualRules;
    private final AnalyticsEvents events;

    public Analytics(ApiCall apiCall) {
        this.apiCall = apiCall;
        this.rules = new AnalyticsRules(this.apiCall);
        this.individualRules = new HashMap<>();
        this.events = new AnalyticsEvents(this.apiCall);
    }

    public AnalyticsRules rules() {
        return this.rules;
    }

    public AnalyticsRule rules(String ruleId) {
        AnalyticsRule retVal;

        if (!this.individualRules.containsKey(ruleId)) {
            this.individualRules.put(ruleId, new AnalyticsRule(ruleId, apiCall));
        }

        retVal = this.individualRules.get(ruleId);
        return retVal;
    }

    public AnalyticsEvents events() {
        return this.events;
    }
}
