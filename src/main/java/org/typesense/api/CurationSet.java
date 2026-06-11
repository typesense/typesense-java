package org.typesense.api;

import org.typesense.api.utils.URLEncoding;
import org.typesense.model.CurationSetCreateSchema;
import org.typesense.model.CurationSetSchema;
import org.typesense.model.CurationSetDeleteSchema;

/**
 * Typesense curation set API wrapper.
 */
public class CurationSet {

    private String curationSetName;
    private ApiCall apiCall;

    /**
     * Creates a new CurationSet instance.
     * @param curationSetName the {@code String} path parameter
     * @param apiCall the {@code ApiCall} instance used to send requests
     */
    public CurationSet(String curationSetName, ApiCall apiCall) {
        this.curationSetName = curationSetName;
        this.apiCall = apiCall;
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
     * @return the {@code CurationSetCreateSchema} response
     * @throws Exception if the request fails
     *
     * @see <a href="https://typesense.org/docs/latest/api/curation.html">Typesense docs</a>
     */
    public CurationSetCreateSchema retrieve() throws Exception {
        return this.apiCall.get(this.getEndpoint(), null, CurationSetCreateSchema.class);
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
     * @param curationSetCreateSchema the {@code CurationSetCreateSchema} request body
     * @return the {@code CurationSetSchema} response
     * @throws Exception if the request fails
     *
     * @see <a href="https://typesense.org/docs/latest/api/curation.html">Typesense docs</a>
     */
    public CurationSetSchema upsert(CurationSetCreateSchema curationSetCreateSchema) throws Exception {
        return this.apiCall.put(this.getEndpoint(), curationSetCreateSchema, null, CurationSetSchema.class);
    }

    /**
     * Delete a curation set.
     *
     * <p>
     * Delete a specific curation set by its name
     *
     * <p>
     * HTTP: DELETE /curation_sets/{curationSetName}
     *
     * @return the {@code CurationSetDeleteSchema} response
     * @throws Exception if the request fails
     *
     * @see <a href="https://typesense.org/docs/latest/api/curation.html">Typesense docs</a>
     */
    public CurationSetDeleteSchema delete() throws Exception {
        return this.apiCall.delete(this.getEndpoint(), null, CurationSetDeleteSchema.class);
    }

    public String getEndpoint() {
        return "/curation_sets/" + URLEncoding.encodeURIComponent(this.curationSetName);
    }
}
