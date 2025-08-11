package org.typesense.api;

import org.typesense.model.MultiSearchResult;
import org.typesense.model.SearchResult;
import org.typesense.model.MultiSearchSearchesParameter;

import java.util.Map;

public class MultiSearch {

    private ApiCall apiCall;
    public static final String RESOURCEPATH = "/multi_search";

    public MultiSearch(ApiCall apiCall) {
        this.apiCall = apiCall;
    }

    /**
     * Performs a federated multi-search, returning individual result sets for each
     * query.
     * <p>
     * This method is used for running multiple search queries in a single API call,
     * where
     * the results for each query are returned as separate and distinct sets. This
     * is
     * also known as a
     * <a href=
     * "https://typesense.org/docs/latest/api/federated-multi-search.html#federated-search"
     * target="_blank">Federated Search</a>.
     * <p>
     * This method strictly handles non-union searches. It will validate that the
     * {@code union} flag is set to {@code false}. If the flag is {@code true}, it
     * will
     * throw an {@link IllegalArgumentException} to prevent unexpected behavior. For
     * union
     * searches, use the {@link #performUnion(MultiSearchSearchesParameter, Map)}
     * method instead.
     *
     * @param multiSearchParameters The object containing the list of search queries
     *                              to perform.
     *                              The {@code union} flag must be {@code false} or
     *                              unset.
     * @param common_params         A map of common parameters that will be applied
     *                              to every
     *                              search query in the request. Can be null or
     *                              empty.
     * @return A {@link MultiSearchResult} object containing a list of individual
     *         search
     *         results. The order of results in this list is guaranteed to match the
     *         order of the queries sent in the request.
     * @throws IllegalArgumentException if the {@code union} flag in
     *                                  {@code multiSearchParameters}
     *                                  is set to {@code true}, as this method is
     *                                  strictly for
     *                                  non-union federated searches.
     * @throws Exception                if there is an issue with the API call, such
     *                                  as a network
     *                                  problem or an error response from the
     *                                  server.
     */
    public MultiSearchResult perform(MultiSearchSearchesParameter multiSearchParameters,
            Map<String, String> common_params) throws Exception {
        if (multiSearchParameters.isUnion()) {
            throw new IllegalArgumentException(
                    "The 'perform()' method is for non-union searches. For a union search, please use the 'performUnion()' method.");
        }
        return this.apiCall.post(MultiSearch.RESOURCEPATH, multiSearchParameters, common_params,
                MultiSearchResult.class);
    }

    /**
     * Performs a <a href=
     * "https://typesense.org/docs/latest/api/federated-multi-search.html#union-search"
     * target="_blank">Union Search</a>
     * and returns a single, combined search result.
     * <p>
     * This method offers a convenient way to perform a union search. It
     * automatically
     * enforces {@code union=true}, merging results from all queries into a single
     * ordered set of hits without requiring you to set the flag manually.
     * <p>
     * This method is guaranteed to not modify the provided
     * {@code multiSearchParameters}
     * object, making it safe to reuse the same parameter object across multiple API
     * calls.
     *
     */
    public SearchResult performUnion(MultiSearchSearchesParameter multiSearchParameters,
            Map<String, String> common_params) throws Exception {
        // Create a shallow copy to safely enforce union=true without modifying the
        // caller's original parameters.
        MultiSearchSearchesParameter copiedParams = new MultiSearchSearchesParameter();
        copiedParams.setSearches(multiSearchParameters.getSearches());
        copiedParams.setUnion(true);

        return this.apiCall.post(MultiSearch.RESOURCEPATH, copiedParams, common_params, SearchResult.class);
    }
}
