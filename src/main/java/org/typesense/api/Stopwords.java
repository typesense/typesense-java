package org.typesense.api;

import org.typesense.api.utils.URLEncoding;
import org.typesense.model.StopwordsSetSchema;
import org.typesense.model.StopwordsSetUpsertSchema;
import org.typesense.model.StopwordsSetsRetrieveAllSchema;

public class Stopwords {
    public final static String RESOURCEPATH = "/stopwords";

    private final ApiCall apiCall;

    public Stopwords(ApiCall apiCall) {
        this.apiCall = apiCall;
    }

    public StopwordsSetSchema upsert(String stopwordSetId, StopwordsSetUpsertSchema stopwordSet) throws Exception {
        return this.apiCall.put(getEndpoint(stopwordSetId), stopwordSet, null, StopwordsSetSchema.class);
    }

    public StopwordsSetsRetrieveAllSchema retrieve() throws Exception {
        return this.apiCall.get(Stopwords.RESOURCEPATH, null, StopwordsSetsRetrieveAllSchema.class);
    }

    private String getEndpoint(String stopwordSetId) {
        return RESOURCEPATH + "/" + URLEncoding.encodeURIComponent(stopwordSetId);
    }

}
