package org.typesense.api;

import org.typesense.model.CurationSetCreateSchema;
import org.typesense.model.CurationSetSchema;

/**
 * Typesense curation sets API wrapper.
 */
public class CurationSets {

    private ApiCall apiCall;
    public final static String RESOURCEPATH = "/curation_sets";

    /**
     * Creates a new CurationSets instance.
     * @param apiCall the {@code ApiCall} instance used to send requests
     */
    public CurationSets(ApiCall apiCall) {
        this.apiCall = apiCall;
    }

    /**
     * Create or update a curation set.
     *
     * <p>
     * Create or update a curation set with the given name
     *
     * <p>
     * HTTP: PUT /curation_sets/{curationSetName}
     *
     * @param curationSetName the {@code String} path parameter
     * @param curationSetCreateSchema the {@code CurationSetCreateSchema} request body
     * @return the {@code CurationSetSchema} response
     * @throws Exception if the request fails
     *
     * @see <a href="https://typesense.org/docs/latest/api/curation.html">Typesense docs</a>
     */
    public CurationSetSchema upsert(String curationSetName, CurationSetCreateSchema curationSetCreateSchema) throws Exception {
        return this.apiCall.put(getEndpoint(curationSetName), curationSetCreateSchema, null, CurationSetSchema.class);
    }

    /**
     * Retrieve a curation set.
     *
     * <p>
     * Retrieve a specific curation set by its name
     *
     * <p>
     * HTTP: GET /curation_sets/{curationSetName}
     *
     * @return the {@code CurationSetSchema[]} response array
     * @throws Exception if the request fails
     *
     * @see <a href="https://typesense.org/docs/latest/api/curation.html">Typesense docs</a>
     */
    public CurationSetSchema[] retrieve() throws Exception {
        return this.apiCall.get(this.getEndpoint(null), null, CurationSetSchema[].class);
    }

    public String getEndpoint(String operation) {
        return RESOURCEPATH + "/" + (operation == null ? "" : operation);
    }
}
