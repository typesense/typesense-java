package org.typesense.api;

import org.typesense.api.utils.URLEncoding;
import org.typesense.model.StopwordsSetRetrieveSchema;
import org.typesense.model.StopwordsSetSchema;

public class StopwordsSet {
    private final ApiCall apiCall;
    private final String stopwordsSetId;

    public StopwordsSet(String stopwordsSetId, ApiCall apiCall) {
        this.stopwordsSetId = stopwordsSetId;
        this.apiCall = apiCall;
    }

    public StopwordsSetRetrieveSchema retrieve() throws Exception {
        return this.apiCall.get(this.getEndpoint(), null, StopwordsSetRetrieveSchema.class);
    }

    public StopwordsSetSchema delete() throws Exception {
        return this.apiCall.delete(this.getEndpoint(), null, StopwordsSetSchema.class);
    }

    private String getEndpoint() {
        return Stopwords.RESOURCEPATH + "/" + URLEncoding.encodeURIComponent(this.stopwordsSetId);
    }

}
