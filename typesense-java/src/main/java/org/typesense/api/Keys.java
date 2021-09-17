package org.typesense.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.typesense.model.ApiKey;
import org.typesense.model.ApiKeySchema;
import org.typesense.model.ApiKeysResponse;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;

public class Keys {

    public static final String RESOURCEPATH = "/keys";
    private ApiCall apiCall;

    public Keys(ApiCall apiCall) {
        this.apiCall = apiCall;
    }

    public ApiKey create(ApiKeySchema apiKeySchema){
        return this.apiCall.post(Keys.RESOURCEPATH, apiKeySchema, ApiKey.class);
    }

    public ApiKeysResponse retrieve(){
        return this.apiCall.get(Keys.RESOURCEPATH, ApiKeysResponse.class);
    }

    public String generateScopedSearchKey(String searchKey, HashMap<String, Object> parameters){
        ObjectMapper mapper = new ObjectMapper();
        String params = "";
        try {
            params = mapper.writeValueAsString(parameters);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        byte[] hmac256 = null;
        try{
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec sks = new SecretKeySpec(searchKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            mac.init(sks);
            hmac256 = mac.doFinal(params.getBytes(StandardCharsets.UTF_8));
        }catch(Exception e){
            e.printStackTrace();
        }
        String digest = Base64.getEncoder().encodeToString(hmac256);
        String keyPrefix = searchKey.substring(0,4);
        String rawScopedKey = digest + keyPrefix + params;
        return  Base64.getEncoder().encodeToString(rawScopedKey.getBytes(StandardCharsets.UTF_8));
    }
}
