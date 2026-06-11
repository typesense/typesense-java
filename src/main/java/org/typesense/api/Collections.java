package org.typesense.api;

import org.typesense.model.CollectionResponse;
import org.typesense.model.CollectionSchema;

/**
 * Typesense collections API wrapper.
 */
public class Collections {

    private final ApiCall apiCall;
    public final static String RESOURCE_PATH = "/collections";

    /**
     * Creates a new Collections instance.
     * @param apiCall the {@code ApiCall} instance used to send requests
     */
    public Collections(ApiCall apiCall){
        this.apiCall = apiCall;
    }

    /**
     * Create a new collection.
     *
     * <p>
     * When a collection is created, we give it a name and describe the fields that will be indexed from the documents added to the collection.
     *
     * <p>
     * HTTP: POST /collections
     *
     * @param c the {@code CollectionSchema} request body
     * @return the {@code CollectionResponse} response
     * @throws Exception if the request fails
     *
     * @see <a href="https://typesense.org/docs/latest/api/collections.html">Typesense docs</a>
     */
    public CollectionResponse create(CollectionSchema c) throws Exception {
        return this.apiCall.post(RESOURCE_PATH, c, null, CollectionResponse.class);
    }

    /**
     * Create a new collection.
     *
     * <p>
     * When a collection is created, we give it a name and describe the fields that will be indexed from the documents added to the collection.
     *
     * <p>
     * HTTP: POST /collections
     *
     * @param schemaJson the {@code String} request body
     * @return the {@code CollectionResponse} response
     * @throws Exception if the request fails
     *
     * @see <a href="https://typesense.org/docs/latest/api/collections.html">Typesense docs</a>
     */
    public CollectionResponse create(String schemaJson) throws Exception {
        return this.apiCall.post(RESOURCE_PATH, schemaJson, null, CollectionResponse.class);
    }

    /**
     * List all collections.
     *
     * <p>
     * Returns a summary of all your collections. The collections are returned sorted by creation date, with the most recent collections appearing first.
     *
     * <p>
     * HTTP: GET /collections
     *
     * @return the {@code CollectionResponse[]} response array
     * @throws Exception if the request fails
     *
     * @see <a href="https://typesense.org/docs/latest/api/collections.html">Typesense docs</a>
     */
    public CollectionResponse[] retrieve() throws Exception {
        return this.apiCall.get(RESOURCE_PATH, null, CollectionResponse[].class);
    }

}
