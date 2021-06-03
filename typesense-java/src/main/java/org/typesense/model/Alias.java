package org.typesense.model;

import org.typesense.api.CollectionAlias;

public class Alias {

    public Api api;
    public String name;

    public Alias(Api api, String name) {
        this.api = api;
        this.name = name;
    }

    public CollectionAlias retrieve(){
        return this.api.get(this.getEndpoint(),CollectionAlias.class);
    }

    public CollectionAlias delete(){
        return this.api.delete(this.getEndpoint(),CollectionAlias.class);
    }

    public String getEndpoint(){
        return Aliases.RESOURCE_PATH + "/" + this.name;
    }
}
