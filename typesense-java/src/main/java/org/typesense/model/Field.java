package org.typesense.model;

import com.fasterxml.jackson.annotation.*;

public class Field   {

    private String name = null;
    public enum TypeEnum {
        STRING("string"),
        INT32("int32"),
        INT64("int64"),
        FLOAT("float"),
        BOOL("bool"),
        STRING_("string[]"),
        INT32_("int32[]"),
        INT64_("int64[]"),
        FLOAT_("float[]"),
        BOOL_("bool[]");

        private String value;

        TypeEnum(String value) {
            this.value = value;
        }
        @JsonValue
        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
        @JsonCreator
        public static TypeEnum fromValue(String text) {
            for (TypeEnum b : TypeEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }
    }

    private String type = null;

    private Boolean optional = false;

    private Boolean facet = false;
    /**
     * Get name
     * @return name
     **/
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Field name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get type
     * @return type
     **/
    @JsonProperty("type")
    public String getType() {
        if (type == null) {
            return null;
        }
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Field type(String type) {
        this.type = type;
        return this;
    }

    /**
     * Get optional
     * @return optional
     **/
    @JsonProperty("optional")
    @JsonIgnoreProperties(ignoreUnknown = true)
    public Boolean isOptional() {
        return optional;
    }

    public void setOptional(Boolean optional) {
        this.optional = optional;
    }

    public Field optional(Boolean optional) {
        this.optional = optional;
        return this;
    }

    /**
     * Get facet
     * @return facet
     **/
    @JsonProperty("facet")
    public Boolean isFacet() {
        return facet;
    }

    public void setFacet(Boolean facet) {
        this.facet = facet;
    }

    public Field facet(Boolean facet) {
        this.facet = facet;
        return this;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Field {\n");

        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    optional: ").append(toIndentedString(optional)).append("\n");
        sb.append("    facet: ").append(toIndentedString(facet)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    @JsonProperty("index")
    @JsonIgnore
    public boolean index;

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
