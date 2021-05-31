package org.typesense;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.HttpUrlConnectorProvider;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.typesense.interceptor.LoggingInterceptor;
import org.typesense.model.Collection;
import org.typesense.api.SearchParameters;
import org.typesense.api.old.SearchResult;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * A client for the Typesense (https://typesense.org) service.
 */
public class TypesenseClient {

    private static final String API_KEY_HEADER = "X-TYPESENSE-API-KEY";

    private static final Logger logger = LoggerFactory.getLogger(TypesenseClient.class);

    private String apiKey;
    private WebTarget typeSenseTarget;

    /**
     * Creates an instance of the TypesenseClient.
     *
     * @param baseUrl the base URL of the Typesense service e.g. http://localhost:8108
     * @param apiKey the API key to use for the Typesense session
     */
    public TypesenseClient(String baseUrl, String apiKey) {
        Objects.requireNonNull(baseUrl, "baseUrl can't be null");
        Objects.requireNonNull(apiKey, "apiKey can't be null");
        this.apiKey = apiKey;
        ClientConfig clientConfig = new ClientConfig();
        if (logger.isTraceEnabled()) {
            clientConfig = clientConfig.register(new LoggingInterceptor());
        }
        // TODO: SET_METHOD_WORKAROUND Generates an illegal reflective access operation for the patch op
        Client client = ClientBuilder.newClient(clientConfig)
                .register(JacksonFeature.class)
                .property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true);
        typeSenseTarget = client.target(baseUrl);
    }

    /**
     * Creates a new collection based on the schema provided.
     *
     * @param collection a Collection instance containing the relevant schema information.
     * @return a Collection instance with metadata about the collection if successfully created.
     */
    public Collection createCollection(Collection collection) {
        Objects.requireNonNull(collection, "collection can't be null");
        WebTarget collectionsTarget = typeSenseTarget.path("collections");
        return collectionsTarget.request(MediaType.APPLICATION_JSON)
                .header(API_KEY_HEADER, apiKey)
                .post(Entity.json(collection), Collection.class);
    }

    /**
     * Creates a new collection based on the schema provided.
     *
     * @param collectionName name of the collection to retrieve.
     * @return a Collection instance with metadata about the collection if successfully created.
     */
    public Collection getCollection(String collectionName) {
        Objects.requireNonNull(collectionName, "collectionName can't be null");
        WebTarget collectionsTarget = typeSenseTarget.path("collections/" + collectionName);
        return collectionsTarget.request(MediaType.APPLICATION_JSON)
                .header(API_KEY_HEADER, apiKey)
                .get(Collection.class);
    }

    /**
     * Returns a list of all collections.
     *
     * @return a List of Collection instances.
     */
    public List<Collection> getCollections() {
        WebTarget collectionsTarget = typeSenseTarget.path("collections");
        return collectionsTarget.request(MediaType.APPLICATION_JSON)
                .header(API_KEY_HEADER, apiKey)
                .get(new GenericType<List<Collection>>(){});
    }

    /**
     * Deletes a collection.
     *
     * @param collectionName name of the collection to delete.
     * @return a Collection instance with metadata about the collection if successfully created.
     */
    public Collection deleteCollection(String collectionName) {
        Objects.requireNonNull(collectionName, "collectionName can't be null");
        WebTarget collectionsTarget = typeSenseTarget.path("collections/" + collectionName);
        return collectionsTarget.request(MediaType.APPLICATION_JSON)
                .header(API_KEY_HEADER, apiKey)
                .delete(Collection.class);
    }

    /**
     * Retrieves a document from a collection.
     *
     * @param collectionName name of the collection.
     * @param id the ID of the document.
     * @param documentClass the document object class.
     * @return an instance of the document object
     */
    public <T> T getDocument(String collectionName, String id, Class<T> documentClass) {
        Objects.requireNonNull(collectionName, "collectionName can't be null");
        Objects.requireNonNull(id, "id can't be null");
        Objects.requireNonNull(documentClass, "documentClass can't be null");
        WebTarget collectionsTarget = typeSenseTarget.path(String.format("collections/%s/documents/%s", collectionName, id));
        return collectionsTarget.request(MediaType.APPLICATION_JSON)
                .header(API_KEY_HEADER, apiKey)
                .get(documentClass);
    }

    /**
     * Creates a document for the specified collection.
     *
     * @param collectionName name of the collection.
     * @param document a document object (must be JSON-serializable)
     * @param documentClass the document object class
     * @return an instance of the document object
     */
    public <T> T createDocument(String collectionName, T document, Class<T> documentClass) {
        return createDocument(collectionName, document, documentClass, false);
    }

    /**
     * Creates or updates a document for the specified collection.
     *
     * @param collectionName name of the collection.
     * @param document a document object (must be JSON-serializable).
     * @param documentClass the document object class.
     * @return an instance of the document object.
     */
    public <T> T upsertDocument(String collectionName, T document, Class<T> documentClass) {
        return createDocument(collectionName, document, documentClass, true);
    }

    /**
     * Updates a document in a collection.
     *
     * @param collectionName name of the collection.
     * @param id the ID of the document.
     * @param document a document object (must be JSON-serializable).
     * @param documentClass the document object class.
     * @return an instance of the document object
     */
    public <T> T updateDocument(String collectionName, String id, T document, Class<T> documentClass) {
        Objects.requireNonNull(collectionName, "collectionName can't be null");
        Objects.requireNonNull(id, "id can't be null");
        Objects.requireNonNull(document, "document can't be null");
        Objects.requireNonNull(documentClass, "documentClass can't be null");
        WebTarget collectionsTarget = typeSenseTarget.path(String.format("collections/%s/documents/%s", collectionName, id));
        return collectionsTarget.request(MediaType.APPLICATION_JSON)
                .header(API_KEY_HEADER, apiKey)
                .method("PATCH", Entity.json(document), documentClass);
    }

    /**
     * Deletes a document from a collection.
     *
     * @param collectionName name of the collection.
     * @param id the ID of the document.
     * @param documentClass the document object class.
     * @return an instance of the document object
     */
    public <T> T deleteDocument(String collectionName, String id, Class<T> documentClass) {
        Objects.requireNonNull(collectionName, "collectionName can't be null");
        Objects.requireNonNull(id, "id can't be null");
        Objects.requireNonNull(documentClass, "documentClass can't be null");
        WebTarget collectionsTarget = typeSenseTarget.path(String.format("collections/%s/documents/%s", collectionName, id));
        return collectionsTarget.request(MediaType.APPLICATION_JSON)
                .header(API_KEY_HEADER, apiKey)
                .delete(documentClass);
    }

    /**
     * Search a collection using the provided search parameters.
     *
     * @param collectionName name of the collection.
     * @param searchParameters the search parameters.
     * @return a SearchResult instance containing search metadata and any documents matching the search.
     */
    public SearchResult searchCollection(String collectionName, SearchParameters searchParameters) {
        Objects.requireNonNull(collectionName, "collectionName can't be null");
        Objects.requireNonNull(searchParameters, "searchParameters can't be null");
        WebTarget collectionsTarget = typeSenseTarget.path("collections/" + collectionName + "/documents/search");
        collectionsTarget = populateSearchParameters(collectionsTarget, searchParameters);
        return collectionsTarget.request(MediaType.APPLICATION_JSON)
            .header(API_KEY_HEADER, apiKey)
            .get(SearchResult.class);
    }

    /**
     * Appends search query parameters to the request URL.
     *
     * @param client a WebTarget target instance.
     * @param parameters a SearchParameters instance.
     * @return a WebTarget with the relevant search parameters added.
     */
    private WebTarget populateSearchParameters(WebTarget client, SearchParameters parameters) {
        for (Map.Entry<String, String> entry : parameters.getParameters().entrySet()) {
            client = client.queryParam(entry.getKey(), entry.getValue());
        }
        return client;
    }

    /**
     * Creates a document for the specified collection.
     *
     * @param collectionName name of the collection.
     * @param document a document object (must be JSON-serializable)
     * @param documentClass the document object class
     * @param upsert if true an upsert operation will be used instead of insert
     * @return an instance of the document object
     */
    private <T> T createDocument(String collectionName, T document, Class<T> documentClass, boolean upsert) {
        Objects.requireNonNull(collectionName, "collectionName can't be null");
        Objects.requireNonNull(document, "document can't be null");
        Objects.requireNonNull(documentClass, "documentClass can't be null");
        WebTarget collectionsTarget = typeSenseTarget.path("collections/" + collectionName + "/documents");
        if (upsert) {
            collectionsTarget = collectionsTarget.queryParam("action", "upsert");
        }
        return collectionsTarget.request(MediaType.APPLICATION_JSON)
                .header(API_KEY_HEADER, apiKey)
                .post(Entity.json(document), documentClass);
    }
}
