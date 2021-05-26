package org.typesense.api;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CollectionSchema   {

     /**
     * Name of the collection
     **/
    private String name = null;

    /**
     * A list of fields for querying, filtering and faceting
     **/
    private List<Field> fields = new ArrayList<Field>();

    /**
     * The name of an int32 / float field that determines the order in which the search results are ranked when a sort_by clause is not provided during searching. This field must indicate some kind of popularity.
     **/
    private String defaultSortingField = null;
    /**
     * Name of the collection
     * @return name
     **/
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CollectionSchema name(String name) {
        this.name = name;
        return this;
    }

    /**
     * A list of fields for querying, filtering and faceting
     * @return fields
     **/
    @JsonProperty("fields")
    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public CollectionSchema fields(List<Field> fields) {
        this.fields = fields;
        return this;
    }

    public CollectionSchema addFieldsItem(Field fieldsItem) {
        this.fields.add(fieldsItem);
        return this;
    }

    /**
     * The name of an int32 / float field that determines the order in which the search results are ranked when a sort_by clause is not provided during searching. This field must indicate some kind of popularity.
     * @return defaultSortingField
     **/
    @JsonProperty("default_sorting_field")
    public String getDefaultSortingField() {
        return defaultSortingField;
    }

    public void setDefaultSortingField(String defaultSortingField) {
        this.defaultSortingField = defaultSortingField;
    }

    public CollectionSchema defaultSortingField(String defaultSortingField) {
        this.defaultSortingField = defaultSortingField;
        return this;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CollectionSchema {\n");

        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    fields: ").append(toIndentedString(fields)).append("\n");
        sb.append("    defaultSortingField: ").append(toIndentedString(defaultSortingField)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private static String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
