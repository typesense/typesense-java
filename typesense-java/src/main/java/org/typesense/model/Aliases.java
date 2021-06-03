package org.typesense.model;

import org.typesense.api.CollectionAlias;
import org.typesense.api.CollectionAliasesResponse;

public class Aliases {

    private Api api;
    public final static String RESOURCE_PATH = "/aliases";

    public Aliases(Api api){
        this.api = api;
    }


    public CollectionAlias upsert(String name, CollectionAlias collectionAlias){
        return this.api.put(RESOURCE_PATH + "/" + name, collectionAlias, CollectionAlias.class);
    }

    public CollectionAliasesResponse retrieve(){
        return this.api.get(RESOURCE_PATH,CollectionAliasesResponse.class);
    }

}
