package org.typesense.api.old;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CollectionField {

    public CollectionField() {
        this(null, null, true, false, false);
    }

    public CollectionField(String name, String type, boolean optional, boolean facet, boolean index) {
        this.name = name;
        this.type = type;
        this.optional = optional;
        this.facet = facet;
        this.index = index;
    }

    @JsonProperty("name")
    public String name;

    @JsonProperty("type")
    public String type;

    @JsonProperty("optional")
    public boolean optional;

    @JsonProperty("facet")
    public boolean facet;

    @JsonProperty("index")
    public boolean index;
}
