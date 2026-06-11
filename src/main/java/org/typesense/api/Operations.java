package org.typesense.api;

import java.util.Map;

/**
 * Typesense operations API wrapper.
 */
public class Operations {

    private ApiCall apiCall;
    public static final String RESOUCEPATH = "/operations";

    /**
     * Creates a new Operations instance.
     * @param apiCall the {@code ApiCall} instance used to send requests
     */
    public Operations(ApiCall apiCall) {
        this.apiCall = apiCall;
    }

    public Map<String, String> perform(String operationName, Map<String, String> queryParameters) throws Exception {
        return this.apiCall.post(RESOUCEPATH + "/" + operationName, "{}", queryParameters, Map.class);
    }

    public Map<String, String> perform(String operationName) throws Exception {
        return this.apiCall.post(RESOUCEPATH + "/" + operationName, "{}", null, Map.class);
    }
}
