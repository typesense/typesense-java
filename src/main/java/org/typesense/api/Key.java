package org.typesense.api;

import org.typesense.model.ApiKey;

/**
 * Typesense key API wrapper.
 */
public class Key {

    private Long id;
    private ApiCall apiCall;

    /**
     * Creates a new Key instance.
     * @param id the {@code Long} id
     * @param apiCall the {@code ApiCall} instance used to send requests
     */
    public Key(Long id, ApiCall apiCall) {
        this.id = id;
        this.apiCall = apiCall;
    }

    /**
     * Retrieve (metadata about) a key.
     *
     * <p>
     * Retrieve (metadata about) a key. Only the key prefix is returned when you retrieve a key. Due to security reasons, only the create endpoint returns the full API key.
     *
     * <p>
     * HTTP: GET /keys/{keyId}
     *
     * @return the {@code ApiKey} response
     * @throws Exception if the request fails
     *
     * @see <a href="https://typesense.org/docs/latest/api/api-keys.html">Typesense docs</a>
     */
    public ApiKey retrieve() throws Exception {
        return this.apiCall.get(this.getEndpoint(), null, ApiKey.class);
    }

    /**
     * Delete an API key given its ID.
     *
     * <p>
     * HTTP: DELETE /keys/{keyId}
     *
     * @return the {@code ApiKey} response
     * @throws Exception if the request fails
     *
     * @see <a href="https://typesense.org/docs/latest/api/api-keys.html">Typesense docs</a>
     */
    public ApiKey delete() throws Exception {
        return this.apiCall.delete(this.getEndpoint(), null, ApiKey.class);
    }

    private String getEndpoint(){
        return Keys.RESOURCEPATH + "/" + this.id;
    }
}
