package org.typesense.api;


import okhttp3.OkHttpClient;

import java.util.HashMap;
import java.util.Map;

public class Client {
    private Configuration configuration;

    private ApiCall apiCall;

    private Collections collections;
    private Map<String, Collection> individualCollections;

    private Aliases aliases;
    private Map<String, Alias> individualAliases;

    private Keys keys;
    private Map<Long, Key> individualKeys;


    private Analytics analytics;

    private Stemming stemming;

    private Stopwords stopwords;
    private Map<String, StopwordsSet> individualStopwordsSets;

    private SynonymSets synonymSets;
    private Map<String, SynonymSet> individualSynonymSets;

    private CurationSets curationSets;
    private Map<String, CurationSet> individualCurationSets;

    public Health health;
    public Operations operations;
    public Metrics metrics;
    public Debug debug;
    public MultiSearch multiSearch;

    public Client(Configuration configuration, OkHttpClient okHttpClient){
        this.configuration = configuration;
        this.apiCall = new ApiCall(configuration, okHttpClient);
        collections = new Collections(apiCall);
        this.individualCollections = new HashMap<>();
        this.aliases = new Aliases(this.apiCall);
        this.individualAliases = new HashMap<>();
        this.keys = new Keys(this.apiCall);
        this.individualKeys = new HashMap<>();
        this.health = new Health(this.apiCall);
        this.operations = new Operations(this.apiCall);
        this.metrics = new Metrics(this.apiCall);
        this.debug = new Debug(this.apiCall);
        this.multiSearch = new MultiSearch(this.apiCall);
        this.analytics = new Analytics(this.apiCall);
        this.stemming = new Stemming(this.apiCall);
        this.stopwords = new Stopwords(this.apiCall);
        this.individualStopwordsSets = new HashMap<>();
    }

    public Client(Configuration configuration){
        this.configuration = configuration;
        this.apiCall = new ApiCall(configuration);
        collections = new Collections(apiCall);
        this.individualCollections = new HashMap<>();
        this.aliases = new Aliases(this.apiCall);
        this.individualAliases = new HashMap<>();
        this.keys = new Keys(this.apiCall);
        this.individualKeys = new HashMap<>();
        this.health = new Health(this.apiCall);
        this.operations = new Operations(this.apiCall);
        this.metrics = new Metrics(this.apiCall);
        this.debug = new Debug(this.apiCall);
        this.multiSearch = new MultiSearch(this.apiCall);
        this.analytics = new Analytics(this.apiCall);
        this.stemming = new Stemming(this.apiCall);
        this.stopwords = new Stopwords(this.apiCall);
        this.individualStopwordsSets = new HashMap<>();
        this.synonymSets = new SynonymSets(this.apiCall);
        this.individualSynonymSets = new HashMap<>();
        this.curationSets = new CurationSets(this.apiCall);
        this.individualCurationSets = new HashMap<>();
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

    public Analytics analytics(){
        return this.analytics;
    }

    public Stemming stemming(){
        return this.stemming;
    }

    public Stopwords stopwords() {
        return this.stopwords;
    }

    public StopwordsSet stopwords(String stopwordsSetId) {
        StopwordsSet retVal;

        if (!this.individualStopwordsSets.containsKey(stopwordsSetId)) {
            this.individualStopwordsSets.put(stopwordsSetId, new StopwordsSet(stopwordsSetId, this.apiCall));
        }

        retVal = this.individualStopwordsSets.get(stopwordsSetId);
        return retVal;
    }

    public SynonymSets synonymSets() {
        return this.synonymSets;
    }

    public SynonymSet synonymSet(String synonymSetName) {
        SynonymSet retVal;

        if (!this.individualSynonymSets.containsKey(synonymSetName)) {
            this.individualSynonymSets.put(synonymSetName, new SynonymSet(synonymSetName, this.apiCall));
        }

        retVal = this.individualSynonymSets.get(synonymSetName);
        return retVal;
    }

    public CurationSets curationSets() {
        return this.curationSets;
    }

    public CurationSet curationSet(String curationSetName) {
        CurationSet retVal;

        if (!this.individualCurationSets.containsKey(curationSetName)) {
            this.individualCurationSets.put(curationSetName, new CurationSet(curationSetName, this.apiCall));
        }

        retVal = this.individualCurationSets.get(curationSetName);
        return retVal;
    }
}
