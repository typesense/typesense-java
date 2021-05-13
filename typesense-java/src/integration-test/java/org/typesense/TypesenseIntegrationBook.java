package org.typesense;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TypesenseIntegrationBook {

    @JsonProperty("id")
    public String id;

    @JsonProperty("title")
    public String title;

    @JsonProperty("authors")
    public String[] authors;

    @JsonProperty("authors_facet")
    public String[] authorsFacet;

    @JsonProperty("publication_year")
    public int publicationYear;

    @JsonProperty("publication_year_facet")
    public String publicationYearFacet;

    @JsonProperty("image_url")
    public String imageUrl = "imageUrl";

    @JsonProperty("ratings_count")
    public int ratingsCount;

    @JsonProperty("average_rating")
    public float averageRating;

    public String toString() {
        return String.format("%s: %s - %s", id, title, publicationYear);
    }
}