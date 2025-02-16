package org.typesense.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.typesense.model.StemmingDictionaryWords;

import com.fasterxml.jackson.databind.ObjectMapper;

public class StemmingDictionaries {
    private final ApiCall apiCall;
    public final static String RESOURCE_PATH = "/stemming/dictionaries";

    public StemmingDictionaries(ApiCall apiCall) {
        this.apiCall = apiCall;
    }

    public String upsert(String dictionaryId, String wordRootCombinations) throws Exception {
        Map<String, String> params = Collections.singletonMap("id", dictionaryId);

        return this.apiCall.post(this.getEndPoint("import"), wordRootCombinations, params, String.class);
    }

    public List<StemmingDictionaryWords> upsert(String dictionaryId, List<StemmingDictionaryWords> wordRootCombinations)
            throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<String> jsonLines = new ArrayList<>();
        List<StemmingDictionaryWords> objectList = new ArrayList<>();

        for (StemmingDictionaryWords word : wordRootCombinations) {
            jsonLines.add(mapper.writeValueAsString(word));
        }

        String reqBody = String.join("\n", jsonLines);

        Map<String, String> params = Collections.singletonMap("id", dictionaryId);

        String resInJsonLineFormat = this.apiCall.post(this.getEndPoint("import"), reqBody, params, String.class);

        for (String line : resInJsonLineFormat.split("\n")) {
            objectList.add(mapper.readValue(line, StemmingDictionaryWords.class));
        }

        return objectList;
    }

    public StemmingDictionariesRetrieveSchema retrieve() throws Exception {
        StemmingDictionariesRetrieveSchema response = this.apiCall.get(RESOURCE_PATH, null,
                StemmingDictionariesRetrieveSchema.class);
        return response != null ? response : new StemmingDictionariesRetrieveSchema();
    }

    public String getEndPoint(String target) {
        return RESOURCE_PATH + "/" + target;
    }

}