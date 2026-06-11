package org.typesense.api;

import java.util.HashMap;
import java.util.Map;

/**
 * Typesense analytics API wrapper.
 */
public class Analytics {
    private final ApiCall apiCall;
    private final AnalyticsRules rules;
    private final Map<String, AnalyticsRule> individualRules;
    private final AnalyticsEvents events;

    /**
     * Creates a new Analytics instance.
     * @param apiCall the {@code ApiCall} instance used to send requests
     */
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
