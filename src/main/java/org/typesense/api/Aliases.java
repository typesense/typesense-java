package org.typesense.api;

import org.typesense.api.utils.URLEncoding;
import org.typesense.model.CollectionAlias;
import org.typesense.model.CollectionAliasSchema;
import org.typesense.model.CollectionAliasesResponse;

/**
 * Typesense aliases API wrapper.
 */
public class Aliases {

    private ApiCall apiCall;
    public final static String RESOURCE_PATH = "/aliases";

    /**
     * Creates a new Aliases instance.
     * @param apiCall the {@code ApiCall} instance used to send requests
     */
    public Aliases(ApiCall apiCall) {
        this.apiCall = apiCall;
    }

    /**
     * Create or update a collection alias.
     *
     * <p>
     * Create or update a collection alias. An alias is a virtual collection name that points to a real collection. If you're familiar with symbolic links on Linux, it's very similar to that. Aliases are useful when you want to reindex your data in the background on a new collection and switch your application to it without any changes to your code.
     *
     * <p>
     * HTTP: PUT /aliases/{aliasName}
     *
     * @param name the {@code String} path parameter
     * @param collectionAliasSchema the {@code CollectionAliasSchema} request body
     * @return the {@code CollectionAlias} response
     * @throws Exception if the request fails
     *
     * @see <a href="https://typesense.org/docs/latest/api/collections.html">Typesense docs</a>
     */
    public CollectionAlias upsert(String name, CollectionAliasSchema collectionAliasSchema) throws Exception {
        return this.apiCall.put(RESOURCE_PATH + "/" + URLEncoding.encodeURIComponent(name), collectionAliasSchema, null,
                CollectionAlias.class);
    }

    /**
     * List all aliases.
     *
     * <p>
     * List all aliases and the corresponding collections that they map to.
     *
     * <p>
     * HTTP: GET /aliases
     *
     * @return the {@code CollectionAliasesResponse} response
     * @throws Exception if the request fails
     *
     * @see <a href="https://typesense.org/docs/latest/api/collections.html">Typesense docs</a>
     */
    public CollectionAliasesResponse retrieve() throws Exception {
        return this.apiCall.get(RESOURCE_PATH, null, CollectionAliasesResponse.class);
    }

}
