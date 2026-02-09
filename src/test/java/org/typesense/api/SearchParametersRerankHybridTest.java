package org.typesense.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.typesense.model.SearchParameters;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Bug: rerank_hybrid_matches was only available on MultiSearchCollectionParameters,
 * forcing users to use multi_search even for single collection hybrid search.
 * The Typesense server accepts this parameter for all search endpoints.
 */
class SearchParametersRerankHybridTest {

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void testRerankHybridMatchesDefaultsToFalseAndSerializes() {
        SearchParameters params = new SearchParameters();
        assertFalse(params.isRerankHybridMatches());

        Map<String, Object> map = mapper.convertValue(params, Map.class);
        assertEquals(false, map.get("rerank_hybrid_matches"));
    }

    @Test
    void testRerankHybridMatchesTrueSerializesToCorrectJsonKey() {
        SearchParameters params = new SearchParameters()
                .q("search term")
                .queryBy("title")
                .rerankHybridMatches(true);

        assertTrue(params.isRerankHybridMatches());

        Map<String, Object> map = mapper.convertValue(params, Map.class);
        assertTrue(map.containsKey("rerank_hybrid_matches"),
                "Serialized map must contain 'rerank_hybrid_matches' key for Typesense server compatibility");
        assertTrue((Boolean) map.get("rerank_hybrid_matches"));
    }

    @Test
    void testRerankHybridMatchesFalseIsIncludedInSerialization() {
        SearchParameters params = new SearchParameters()
                .q("*")
                .rerankHybridMatches(false);

        Map<String, Object> map = mapper.convertValue(params, Map.class);
        assertFalse((Boolean) map.get("rerank_hybrid_matches"));
    }
}
