package org.typesense.model;

import java.util.HashMap;

public class Debug {

    private Api api;
    public static final String RESOURCEPATH = "/debug";

    public Debug(Api api) {
        this.api = api;
    }

    public HashMap<String, String> retrieve(){
        return this.api.get(RESOURCEPATH);
    }
}
