package org.typesense.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

class Address {
    @JsonProperty
    String city;

    @JsonProperty
    String state;

    @JsonProperty
    String country;

    public Address(String city, String state, String country) {
        this.city = city;
        this.state = state;
        this.country = country;
    }
}

class Tag {
    @JsonProperty
    String name;

    @JsonProperty
    String value;

    public Tag(String name, String value) {
        this.name = name;
        this.value = value;
    }
}

public class NestedDocument {
    @JsonProperty
    Address address;

    @JsonProperty
    List<Tag> tags;

    NestedDocument(String city, String state, String country) {
        tags = new ArrayList<>();
        address = new Address(city, state, country);
    }

    NestedDocument addTag(String name, String value) {
        tags.add(new Tag(name, value));
        return this;
    }
}
