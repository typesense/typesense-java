package org.typesense.model;

import java.util.HashMap;

public class Client {
    private Configuration configuration;

    private Api api;

    private Collections collections;

    private HashMap<String, Collection> individualCollections;

    Client(Configuration configuration){
        this.configuration = configuration;
        this.api = new Api(configuration);
        collections = new Collections(api);
        this.individualCollections = new HashMap<>();
    }

    public Collection collections(String name){
        Collection ret_val;

        if(!this.individualCollections.containsKey(name)){
            individualCollections.put(name,new Collection(name,this.api,this.configuration));
        }

        ret_val = individualCollections.get(name);

        return ret_val;
    }

    public Collections collections(){
        return this.collections;
    }

}
