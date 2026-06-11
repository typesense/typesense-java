package org.typesense.api;

import java.util.HashMap;
import java.util.Map;

/**
 * Typesense stemming API wrapper.
 */
public class Stemming {
    private final ApiCall apiCall;
    private final StemmingDictionaries dictionaries;
    private final Map<String, StemmingDictionary> individualDictionaries;


    /**
     * Creates a new Stemming instance.
     * @param apiCall the {@code ApiCall} instance used to send requests
     */
    public Stemming(ApiCall apiCall) {
        this.apiCall = apiCall;
        this.dictionaries = new StemmingDictionaries(this.apiCall);
        this.individualDictionaries = new HashMap<>();
    }

    public StemmingDictionaries dictionaries() {
        return this.dictionaries;
    }

    public StemmingDictionary dictionaries(String dictionaryId) {
        StemmingDictionary retVal;

        if (!this.individualDictionaries.containsKey(dictionaryId)) {
            this.individualDictionaries.put(dictionaryId, new StemmingDictionary(dictionaryId, apiCall));
        }

        retVal = this.individualDictionaries.get(dictionaryId);
        return retVal;
    }
}
