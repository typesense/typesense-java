package org.typesense.api;

import java.util.List;
import org.typesense.model.SynonymSetCreateSchema;
import org.typesense.model.SynonymSetSchema;

/**
 * Typesense synonym sets API wrapper.
 */
public class SynonymSets {

    private ApiCall apiCall;
    public final static String RESOURCEPATH = "/synonym_sets";

    /**
     * Creates a new SynonymSets instance.
     * @param apiCall the {@code ApiCall} instance used to send requests
     */
    public SynonymSets(ApiCall apiCall) {
        this.apiCall = apiCall;
    }

    /**
     * Create or update a synonym set.
     *
     * <p>
     * Create or update a synonym set with the given name
     *
     * <p>
     * HTTP: PUT /synonym_sets/{synonymSetName}
     *
     * @param synonymSetName the {@code String} path parameter
     * @param synonymSetCreateSchema the {@code SynonymSetCreateSchema} request body
     * @return the {@code SynonymSetSchema} response
     * @throws Exception if the request fails
     *
     * @see <a href="https://typesense.org/docs/latest/api/synonyms.html">Typesense docs</a>
     */
    public SynonymSetSchema upsert(String synonymSetName, SynonymSetCreateSchema synonymSetCreateSchema) throws Exception {
        return this.apiCall.put(getEndpoint(synonymSetName), synonymSetCreateSchema, null, SynonymSetSchema.class);
    }

    /**
     * Retrieve a synonym set.
     *
     * <p>
     * Retrieve a specific synonym set by its name
     *
     * <p>
     * HTTP: GET /synonym_sets/{synonymSetName}
     *
     * @return the {@code SynonymSetSchema[]} response array
     * @throws Exception if the request fails
     *
     * @see <a href="https://typesense.org/docs/latest/api/synonyms.html">Typesense docs</a>
     */
    public SynonymSetSchema[] retrieve() throws Exception {
        return this.apiCall.get(this.getEndpoint(null), null, SynonymSetSchema[].class);
    }

    public String getEndpoint(String operation) {
        return RESOURCEPATH + "/" + (operation == null ? "" : operation);
    }
} 