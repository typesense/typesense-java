package org.typesense.api;

import org.typesense.api.utils.URLEncoding;

import org.typesense.model.SynonymSetCreateSchema;
import org.typesense.model.SynonymSetSchema;
import org.typesense.model.SynonymSetDeleteSchema;

/**
 * Typesense synonym set API wrapper.
 */
public class SynonymSet {

    private String synonymSetName;
    private ApiCall apiCall;

    /**
     * Creates a new SynonymSet instance.
     * @param synonymSetName the {@code String} path parameter
     * @param apiCall the {@code ApiCall} instance used to send requests
     */
    public SynonymSet(String synonymSetName, ApiCall apiCall) {
        this.synonymSetName = synonymSetName;
        this.apiCall = apiCall;
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
     * @return the {@code SynonymSetCreateSchema} response
     * @throws Exception if the request fails
     *
     * @see <a href="https://typesense.org/docs/latest/api/synonyms.html">Typesense docs</a>
     */
    public SynonymSetCreateSchema retrieve() throws Exception {
        return this.apiCall.get(this.getEndpoint(), null, SynonymSetCreateSchema.class);
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
     * @param synonymSetCreateSchema the {@code SynonymSetCreateSchema} request body
     * @return the {@code SynonymSetSchema} response
     * @throws Exception if the request fails
     *
     * @see <a href="https://typesense.org/docs/latest/api/synonyms.html">Typesense docs</a>
     */
    public SynonymSetSchema upsert(SynonymSetCreateSchema synonymSetCreateSchema) throws Exception {
        return this.apiCall.put(this.getEndpoint(), synonymSetCreateSchema, null, SynonymSetSchema.class);
    }

    /**
     * Delete a synonym set.
     *
     * <p>
     * Delete a specific synonym set by its name
     *
     * <p>
     * HTTP: DELETE /synonym_sets/{synonymSetName}
     *
     * @return the {@code SynonymSetDeleteSchema} response
     * @throws Exception if the request fails
     *
     * @see <a href="https://typesense.org/docs/latest/api/synonyms.html">Typesense docs</a>
     */
    public SynonymSetDeleteSchema delete() throws Exception {
        return this.apiCall.delete(this.getEndpoint(), null, SynonymSetDeleteSchema.class);
    }

    public String getEndpoint() {
        return "/synonym_sets/" + URLEncoding.encodeURIComponent(this.synonymSetName);
    }
} 