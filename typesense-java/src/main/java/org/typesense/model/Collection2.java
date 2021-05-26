package org.typesense.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Collection2 {

    public Collection2() {
        this(null);
    }

    public Collection2(String name) {
        this.name = name;
        this.fields = new ArrayList<>();
    }

    @JsonProperty("name")
    public String name;

    @JsonProperty("fields")
    public List<CollectionField> fields;

    @JsonProperty("default_sorting_field")
    public String defaultSortingField;

    @JsonProperty(value = "num_documents", required = false)
    public int numberOfDocuments = 0;

    @JsonProperty(value = "num_memory_shards", required = false)
    public int numberOfMemoryShards = 1;

    @JsonProperty("created_at")
    public long createdAt;

    public CollectionField addField(String name, String type, boolean facet, boolean optional, boolean index) {
        CollectionField field = new CollectionField();
        field.name = name;
        field.type = type;
        field.facet = facet;
        field.optional = optional;
        field.index = index;
        fields.add(field);
        return field;
    }
}
