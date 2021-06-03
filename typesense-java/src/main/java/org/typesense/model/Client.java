package org.typesense.model;

import java.util.HashMap;

public class Client {
    private Configuration configuration;

    private Api api;

    private Collections collections;
    private HashMap<String, Collection> individualCollections;

    private Aliases aliases;
    private HashMap<String, Alias> individualAliases;

    Client(Configuration configuration){
        this.configuration = configuration;
        this.api = new Api(configuration);
        collections = new Collections(api);
        this.individualCollections = new HashMap<>();
        this.aliases = new Aliases(this.api);
        this.individualAliases = new HashMap<>();
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

    public Aliases aliases(){
        return this.aliases;
    }

    public Alias aliases(String name){
        Alias ret_val;

        if(!this.individualAliases.containsKey(name)){
            this.individualAliases.put(name,new Alias(this.api, name));
        }

        ret_val = this.individualAliases.get(name);

        return ret_val;
    }
}
