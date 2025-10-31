package org.typesense.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import org.typesense.model.AnalyticsRule;
import org.typesense.model.AnalyticsRuleCreate;
import org.typesense.model.AnalyticsRuleCreateParams;
import java.util.List;
import java.util.ArrayList;

/**
 * Serializer for AnalyticsRule objects to avoid Jackson type discriminator issues
 */
public class AnalyticsRuleSerializer {
    
    private final ObjectMapper objectMapper;
    
    public AnalyticsRuleSerializer() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
    }
    
    /**
     * Parse a single AnalyticsRule from JsonNode
     */
    public AnalyticsRule parseFromJsonNode(JsonNode node) {
        AnalyticsRule rule = new AnalyticsRule();
        
        if (node.has("name")) {
            rule.name(node.get("name").asText());
        }
        
        if (node.has("type")) {
            String typeStr = node.get("type").asText();
            if ("counter".equals(typeStr)) {
                rule.type(AnalyticsRuleCreate.TypeEnum.COUNTER);
            } else if ("popular_queries".equals(typeStr)) {
                rule.type(AnalyticsRuleCreate.TypeEnum.POPULAR_QUERIES);
            } else if ("nohits_queries".equals(typeStr)) {
                rule.type(AnalyticsRuleCreate.TypeEnum.NOHITS_QUERIES);
            } else if ("log".equals(typeStr)) {
                rule.type(AnalyticsRuleCreate.TypeEnum.LOG);
            }
        }
        
        if (node.has("collection")) {
            rule.collection(node.get("collection").asText());
        }
        
        if (node.has("event_type")) {
            rule.eventType(node.get("event_type").asText());
        }
        
        if (node.has("rule_tag")) {
            rule.ruleTag(node.get("rule_tag").asText());
        }
        
        if (node.has("params")) {
            JsonNode paramsNode = node.get("params");
            AnalyticsRuleCreateParams params = new AnalyticsRuleCreateParams();
            
            if (paramsNode.has("counter_field")) {
                params.counterField(paramsNode.get("counter_field").asText());
            }
            
            if (paramsNode.has("weight")) {
                params.weight(paramsNode.get("weight").asInt());
            }
            
            if (paramsNode.has("destination_collection")) {
                params.destinationCollection(paramsNode.get("destination_collection").asText());
            }
            
            if (paramsNode.has("limit")) {
                params.limit(paramsNode.get("limit").asInt());
            }
            
            if (paramsNode.has("capture_search_requests")) {
                params.captureSearchRequests(paramsNode.get("capture_search_requests").asBoolean());
            }
            
            if (paramsNode.has("meta_fields")) {
                List<String> metaFields = new ArrayList<>();
                JsonNode metaFieldsNode = paramsNode.get("meta_fields");
                if (metaFieldsNode.isArray()) {
                    for (JsonNode metaField : metaFieldsNode) {
                        metaFields.add(metaField.asText());
                    }
                }
                params.metaFields(metaFields);
            }
            
            if (paramsNode.has("expand_query")) {
                params.expandQuery(paramsNode.get("expand_query").asBoolean());
            }
            
            rule.params(params);
        }
        
        return rule;
    }
    
    /**
     * Parse AnalyticsRule from JSON string
     */
    public AnalyticsRule parseFromJson(String jsonResponse) throws Exception {
        JsonNode rootNode = objectMapper.readTree(jsonResponse);
        return parseFromJsonNode(rootNode);
    }
    
    /**
     * Parse a list of AnalyticsRule objects from JSON string
     */
    public List<AnalyticsRule> parseListFromJson(String jsonResponse) throws Exception {
        JsonNode rootNode = objectMapper.readTree(jsonResponse);
        List<AnalyticsRule> rules = new ArrayList<>();
        
        if (rootNode.isArray()) {
            for (JsonNode item : rootNode) {
                if (item.has("error")) {
                    String error = item.get("error").asText();
                    throw new RuntimeException("Analytics rule parsing failed: " + error);
                } else {
                    AnalyticsRule rule = parseFromJsonNode(item);
                    rules.add(rule);
                }
            }
        } else {
            // Single object response
            AnalyticsRule rule = parseFromJsonNode(rootNode);
            rules.add(rule);
        }
        
        return rules;
    }
} 