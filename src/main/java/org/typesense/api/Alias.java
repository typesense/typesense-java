package org.typesense.api;

import org.typesense.api.utils.URLEncoding;
import org.typesense.model.CollectionAlias;

/**
 * Typesense alias API wrapper.
 */
public class Alias {

    public ApiCall apiCall;
    public String name;

    /**
     * Creates a new Alias instance.
     * @param apiCall the {@code ApiCall} instance used to send requests
     * @param name the {@code String} name
     */
    public Alias(ApiCall apiCall, String name) {
        this.apiCall = apiCall;
        this.name = name;
    }

    /**
     * Retrieve an alias.
     *
     * <p>
     * Find out which collection an alias points to by fetching it
     *
     * <p>
     * HTTP: GET /aliases/{aliasName}
     *
     * @return the {@code CollectionAlias} response
     * @throws Exception if the request fails
     *
     * @see <a href="https://typesense.org/docs/latest/api/collections.html">Typesense docs</a>
     */
    public CollectionAlias retrieve() throws Exception {
        return this.apiCall.get(this.getEndpoint(), null, CollectionAlias.class);
    }

    /**
     * Delete an alias.
     *
     * <p>
     * HTTP: DELETE /aliases/{aliasName}
     *
     * @return the {@code CollectionAlias} response
     * @throws Exception if the request fails
     *
     * @see <a href="https://typesense.org/docs/latest/api/collections.html">Typesense docs</a>
     */
    public CollectionAlias delete() throws Exception {
        return this.apiCall.delete(this.getEndpoint(), null, CollectionAlias.class);
    }

    public String getEndpoint() {
        return Aliases.RESOURCE_PATH + "/" + URLEncoding.encodeURIComponent(this.name);
    }
}
