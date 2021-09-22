package org.typesense.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImportDocumentsParameters   {

    private String action = null;

    private Integer batchSize = null;

    private String dirtyValues = null;
    /**
     * Get action
     * @return action
     **/
    @JsonProperty("action")
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ImportDocumentsParameters action(String action) {
        this.action = action;
        return this;
    }

    /**
     * Get batchSize
     * @return batchSize
     **/
    @JsonProperty("batch_size")
    public Integer getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(Integer batchSize) {
        this.batchSize = batchSize;
    }

    public ImportDocumentsParameters batchSize(Integer batchSize) {
        this.batchSize = batchSize;
        return this;
    }

    /**
     * Get dirtyValues
     * @return dirtyValues
     **/
    @JsonProperty("dirty_values")
    public String getDirtyValues() {
        return dirtyValues;
    }

    public void setDirtyValues(String dirtyValues) {
        this.dirtyValues = dirtyValues;
    }

    public ImportDocumentsParameters dirtyValues(String dirtyValues) {
        this.dirtyValues = dirtyValues;
        return this;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ImportDocumentsParameters {\n");

        sb.append("    action: ").append(toIndentedString(action)).append("\n");
        sb.append("    batchSize: ").append(toIndentedString(batchSize)).append("\n");
        sb.append("    dirtyValues: ").append(toIndentedString(dirtyValues)).append("\n");
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