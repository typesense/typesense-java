package org.typesense.api;

import java.util.List;

/**
 * Typesense stemming dictionaries retrieve schema API wrapper.
 */
public class StemmingDictionariesRetrieveSchema {
    private List<String> dictionaries;

    public List<String> getDictionaries() {
        return dictionaries;
    }

    public void setDictionaries(List<String> dictionaries) {
        this.dictionaries = dictionaries;
    }
}