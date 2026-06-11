package org.typesense.api;

import org.typesense.api.utils.URLEncoding;

/**
 * Typesense stemming dictionary API wrapper.
 */
public class StemmingDictionary {
    private final ApiCall apiCall;
    private final String dictionaryId;

    /**
     * Creates a new StemmingDictionary instance.
     * @param dictionaryId the {@code String} path parameter
     * @param apiCall the {@code ApiCall} instance used to send requests
     */
    public StemmingDictionary(String dictionaryId, ApiCall apiCall) {
        this.apiCall = apiCall;
        this.dictionaryId = dictionaryId;
    }


    /**
     * Retrieve a stemming dictionary.
     *
     * <p>
     * Fetch details of a specific stemming dictionary.
     *
     * <p>
     * HTTP: GET /stemming/dictionaries/{dictionaryId}
     *
     * @return the {@code org.typesense.model.StemmingDictionary} response
     * @throws Exception if the request fails
     *
     * @see <a href="https://typesense.org/docs/latest/api/stemming.html">Typesense docs</a>
     */
    public org.typesense.model.StemmingDictionary retrieve() throws Exception {
        return this.apiCall.get(this.getEndpoint(), null, org.typesense.model.StemmingDictionary.class);
    }

    private String getEndpoint() {
        return StemmingDictionaries.RESOURCE_PATH + "/" + URLEncoding.encodeURIComponent(this.dictionaryId);
    }
    
}