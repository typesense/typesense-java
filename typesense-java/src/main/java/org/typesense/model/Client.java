package org.typesense.model;

import java.util.HashMap;

public class Client {
    private Configuration configuration;

    private Api api;

    private Collections collections;
    private HashMap<String, Collection> individualCollections;

    private Aliases aliases;
    private HashMap<String, Alias> individualAliases;

    private Keys keys;
    private HashMap<String, Key> individualKeys;

    public Health health;
    public Operations operations;
    public Metrics metrics;
    public Debug debug;
    public MultiSearch multiSearch;

    Client(Configuration configuration){
        this.configuration = configuration;
        this.api = new Api(configuration);
        collections = new Collections(api);
        this.individualCollections = new HashMap<>();
        this.aliases = new Aliases(this.api);
        this.individualAliases = new HashMap<>();
        this.keys = new Keys(this.api);
        this.individualKeys = new HashMap<>();
        this.health = new Health(this.api);
        this.operations = new Operations(this.api);
        this.metrics = new Metrics(this.api);
        this.debug = new Debug(this.api);
        this.multiSearch = new MultiSearch(this.api);
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

    public Keys keys(){
        return this.keys;
    }

    public Key keys(String id){
        Key ret_val;

        if(!this.individualKeys.containsKey(id)){
            this.individualKeys.put(id, new Key(id, this.api));
        }

        ret_val = this.individualKeys.get(id);
        return ret_val;
    }
}
