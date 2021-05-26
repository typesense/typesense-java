package org.typesense.api;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.typesense.api.CollectionSchema;

public class CollectionResponse extends CollectionSchema {

   /**
     * Number of documents in the collection
     **/
    private Long numDocuments = null;
    /**
     * Number of documents in the collection
     * @return numDocuments
     **/
    @JsonProperty("num_documents")
    public Long getNumDocuments() {
        return numDocuments;
    }

    @JsonProperty("created_at")
    public String created_at;

    @JsonProperty("num_memory_shards")
    public long num_memory_shards;


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Collection {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("    numDocuments: ").append(toIndentedString(numDocuments)).append("\n");
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
