package org.typesense.api;

import org.typesense.model.CollectionAlias;
import org.typesense.model.CollectionAliasSchema;
import org.typesense.model.CollectionAliasesResponse;

public class Aliases {

    private ApiCall apiCall;
    public final static String RESOURCE_PATH = "/aliases";

    public Aliases(ApiCall apiCall){
        this.apiCall = apiCall;
    }


    public CollectionAlias upsert(String name, CollectionAliasSchema collectionAliasSchema){
        return this.apiCall.put(RESOURCE_PATH + "/" + name, collectionAliasSchema, CollectionAlias.class);
    }

    public CollectionAliasesResponse retrieve(){
        return this.apiCall.get(RESOURCE_PATH,CollectionAliasesResponse.class);
    }

}
