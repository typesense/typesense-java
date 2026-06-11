package org.typesense.api;

import org.typesense.api.utils.URLEncoding;
import org.typesense.model.CollectionResponse;
import org.typesense.model.CollectionUpdateSchema;

import java.util.HashMap;
import java.util.Map;

/**
 * Typesense collection API wrapper.
 */
public class Collection {

    private final Configuration configuration;

    private final ApiCall apiCall;

    private final String name;

    private Documents documents;
    private Map<String, Document> individualDocuments;

    private Synonyms synonyms;
    private Map<String, Synonym> individualSynonyms;

    private final String endpoint;

    Collection(String name, ApiCall apiCall, Configuration configuration) {
        this.name = name;
        this.apiCall = apiCall;
        this.configuration = configuration;
        this.endpoint = Collections.RESOURCE_PATH + "/" + URLEncoding.encodeURIComponent(this.name);
        this.documents = new Documents(this.name, this.apiCall, this.configuration);
        this.individualDocuments = new HashMap<>();
        this.synonyms = new Synonyms(this.name, this.apiCall);
        this.individualSynonyms = new HashMap<>();
    }

    /**
     * Retrieve a single collection.
     *
     * <p>
     * Retrieve the details of a collection, given its name.
     *
     * <p>
     * HTTP: GET /collections/{collectionName}
     *
     * @return the {@code CollectionResponse} response
     * @throws Exception if the request fails
     *
     * @see <a href="https://typesense.org/docs/latest/api/collections.html">Typesense docs</a>
     */
    public CollectionResponse retrieve() throws Exception {
        return this.apiCall.get(endpoint, null, CollectionResponse.class);
    }

    /**
     * Update a collection.
     *
     * <p>
     * Update a collection's schema to modify the fields and their types.
     *
     * <p>
     * HTTP: PATCH /collections/{collectionName}
     *
     * @param c the {@code CollectionUpdateSchema} request body
     * @return the {@code CollectionUpdateSchema} response
     * @throws Exception if the request fails
     *
     * @see <a href="https://typesense.org/docs/latest/api/collections.html">Typesense docs</a>
     */
    public CollectionUpdateSchema update(CollectionUpdateSchema c) throws Exception {
        return this.apiCall.patch(endpoint, c, null, CollectionUpdateSchema.class);
    }

    /**
     * Delete a collection.
     *
     * <p>
     * Permanently drops a collection. This action cannot be undone. For large collections, this might have an impact on read latencies.
     *
     * <p>
     * HTTP: DELETE /collections/{collectionName}
     *
     * @return the {@code CollectionResponse} response
     * @throws Exception if the request fails
     *
     * @see <a href="https://typesense.org/docs/latest/api/collections.html">Typesense docs</a>
     */
    public CollectionResponse delete() throws Exception {
        return this.apiCall.delete(endpoint, null, CollectionResponse.class);
    }

    public Documents documents() {
        return this.documents;
    }

    public Document documents(String documentId) {
        Document retVal;

        if (!this.individualDocuments.containsKey(documentId)) {
            this.individualDocuments.put(documentId, new Document(this.name, documentId, this.apiCall));
        }

        retVal = this.individualDocuments.get(documentId);
        return retVal;
    }

    /**
     * @deprecated This method is deprecated and will be removed in a future version.
     * Use {@link Client#synonymSets()} instead for the new synonym sets API.
     * 
     * Note: The old synonyms API is only available on Typesense v29.0 and below.
     * For Typesense v30.0 and above, use the new synonym sets API.
     */
    @Deprecated
    public Synonyms synonyms() {
        System.err.println("DEPRECATED: Using deprecated synonyms API. This API is only available on Typesense v29.0 and below. " +
                          "For Typesense v30.0 and above, use the new synonym sets API via Client.synonymSets().");
        return this.synonyms;
    }

    /**
     * @deprecated This method is deprecated and will be removed in a future version.
     * Use {@link Client#synonymSet(String)} instead for the new synonym sets API.
     * 
     * Note: The old synonyms API is only available on Typesense v29.0 and below.
     * For Typesense v30.0 and above, use the new synonym sets API.
     */
    @Deprecated
    public Synonym synonyms(String synonymId) {
        System.err.println("DEPRECATED: Using deprecated synonyms API. This API is only available on Typesense v29.0 and below. " +
                          "For Typesense v30.0 and above, use the new synonym sets API via Client.synonymSet(String).");
        Synonym retVal;

        if (!this.individualSynonyms.containsKey(synonymId)) {
            this.individualSynonyms.put(synonymId, new Synonym(this.name, synonymId, this.apiCall));
        }

        retVal = this.individualSynonyms.get(synonymId);
        return retVal;
    }
}
