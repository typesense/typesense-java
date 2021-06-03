package org.typesense.api;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonCreator;

public class CollectionAlias   {

    /**
     * Name of the collection alias
     **/
    private String name = null;

    /**
     * Name of the collection the alias mapped to
     **/
    private String collectionName = null;
    /**
     * Name of the collection alias
     * @return name
     **/
    @JsonProperty("name")
    public String getName() {
        return name;
    }


    /**
     * Name of the collection the alias mapped to
     * @return collectionName
     **/
    @JsonProperty("collection_name")
    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public CollectionAlias collectionName(String collectionName) {
        this.collectionName = collectionName;
        return this;
    }

    public CollectionAlias name(String name) {
        this.name = name;
        return this;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CollectionAlias {\n");

        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    collectionName: ").append(toIndentedString(collectionName)).append("\n");
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
