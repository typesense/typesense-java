package org.typesense.api;

import java.util.HashMap;

public class Client {
    private Configuration configuration;

    private ApiCall apiCall;

    private Collections collections;
    private HashMap<String, Collection> individualCollections;

    private Aliases aliases;
    private HashMap<String, Alias> individualAliases;

    private Keys keys;
    private HashMap<Long, Key> individualKeys;

    public Health health;
    public Operations operations;
    public Metrics metrics;
    public Debug debug;
    public MultiSearch multiSearch;

    public Client(Configuration configuration){
        this.configuration = configuration;
        this.apiCall = new ApiCall(configuration);
        collections = new Collections(apiCall);
        this.individualCollections = new HashMap<>();
        this.aliases = new Aliases(this.apiCall);
        this.individualAliases = new HashMap<>();
        this.keys = new Keys(this.apiCall);
        this.individualKeys = new HashMap<Long, Key>();
        this.health = new Health(this.apiCall);
        this.operations = new Operations(this.apiCall);
        this.metrics = new Metrics(this.apiCall);
        this.debug = new Debug(this.apiCall);
        this.multiSearch = new MultiSearch(this.apiCall);
    }

    public Collection collections(String name){
        Collection retVal;

        if(!this.individualCollections.containsKey(name)){
            individualCollections.put(name,new Collection(name,this.apiCall,this.configuration));
        }

        retVal = individualCollections.get(name);

        return retVal;
    }

    public Collections collections(){
        return this.collections;
    }

    public Aliases aliases(){
        return this.aliases;
    }

    public Alias aliases(String name){
        Alias retVal;

        if(!this.individualAliases.containsKey(name)){
            this.individualAliases.put(name,new Alias(this.apiCall, name));
        }

        retVal = this.individualAliases.get(name);

        return retVal;
    }

    public Keys keys(){
        return this.keys;
    }

    public Key keys(Long id){
        Key retVal;

        if(!this.individualKeys.containsKey(id)){
            this.individualKeys.put(id, new Key(id, this.apiCall));
        }

        retVal = this.individualKeys.get(id);
        return retVal;
    }
}
