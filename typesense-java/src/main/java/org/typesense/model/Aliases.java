package org.typesense.model;

import org.typesense.api.CollectionAlias;
import org.typesense.api.CollectionAliasesResponse;

public class Aliases {

    private ApiCall apiCall;
    public final static String RESOURCE_PATH = "/aliases";

    public Aliases(ApiCall apiCall){
        this.apiCall = apiCall;
    }


    public CollectionAlias upsert(String name, CollectionAlias collectionAlias){
        return this.apiCall.put(RESOURCE_PATH + "/" + name, collectionAlias, CollectionAlias.class);
    }

    public CollectionAliasesResponse retrieve(){
        return this.apiCall.get(RESOURCE_PATH,CollectionAliasesResponse.class);
    }

}
