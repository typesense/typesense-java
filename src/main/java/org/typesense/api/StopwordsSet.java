package org.typesense.api;

import org.typesense.api.utils.URLEncoding;
import org.typesense.model.StopwordsSetRetrieveSchema;
import org.typesense.model.StopwordsSetSchema;

/**
 * Typesense stopwords set API wrapper.
 */
public class StopwordsSet {
    private final ApiCall apiCall;
    private final String stopwordsSetId;

    /**
     * Creates a new StopwordsSet instance.
     * @param stopwordsSetId the {@code String} path parameter
     * @param apiCall the {@code ApiCall} instance used to send requests
     */
    public StopwordsSet(String stopwordsSetId, ApiCall apiCall) {
        this.stopwordsSetId = stopwordsSetId;
        this.apiCall = apiCall;
    }

    /**
     * Retrieves a stopwords set.
     *
     * <p>
     * Retrieve the details of a stopwords set, given it's name.
     *
     * <p>
     * HTTP: GET /stopwords/{setId}
     *
     * @return the {@code StopwordsSetRetrieveSchema} response
     * @throws Exception if the request fails
     *
     * @see <a href="https://typesense.org/docs/latest/api/stopwords.html">Typesense docs</a>
     */
    public StopwordsSetRetrieveSchema retrieve() throws Exception {
        return this.apiCall.get(this.getEndpoint(), null, StopwordsSetRetrieveSchema.class);
    }

    /**
     * Delete a stopwords set.
     *
     * <p>
     * Permanently deletes a stopwords set, given it's name.
     *
     * <p>
     * HTTP: DELETE /stopwords/{setId}
     *
     * @return the {@code StopwordsSetSchema} response
     * @throws Exception if the request fails
     *
     * @see <a href="https://typesense.org/docs/latest/api/stopwords.html">Typesense docs</a>
     */
    public StopwordsSetSchema delete() throws Exception {
        return this.apiCall.delete(this.getEndpoint(), null, StopwordsSetSchema.class);
    }

    private String getEndpoint() {
        return Stopwords.RESOURCEPATH + "/" + URLEncoding.encodeURIComponent(this.stopwordsSetId);
    }

}
