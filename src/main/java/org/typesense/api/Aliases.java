package org.typesense.api;

import org.typesense.api.utils.URLEncoding;
import org.typesense.model.CollectionAlias;
import org.typesense.model.CollectionAliasSchema;
import org.typesense.model.CollectionAliasesResponse;

public class Aliases {

    private ApiCall apiCall;
    public final static String RESOURCE_PATH = "/aliases";

    public Aliases(ApiCall apiCall) {
        this.apiCall = apiCall;
    }

    public CollectionAlias upsert(String name, CollectionAliasSchema collectionAliasSchema) throws Exception {
        return this.apiCall.put(RESOURCE_PATH + "/" + URLEncoding.encodeURIComponent(name), collectionAliasSchema, null,
                CollectionAlias.class);
    }

    public CollectionAliasesResponse retrieve() throws Exception {
        return this.apiCall.get(RESOURCE_PATH, null, CollectionAliasesResponse.class);
    }

}
