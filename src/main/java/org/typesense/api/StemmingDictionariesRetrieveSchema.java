package org.typesense.api;

import java.util.List;

public class StemmingDictionariesRetrieveSchema {
    private List<String> dictionaries;

    public List<String> getDictionaries() {
        return dictionaries;
    }

    public void setDictionaries(List<String> dictionaries) {
        this.dictionaries = dictionaries;
    }
}