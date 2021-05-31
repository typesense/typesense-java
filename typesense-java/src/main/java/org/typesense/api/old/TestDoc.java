//Copy of org.typesense.TypesenseIntegrationBook.java created for testing purposes.

package org.typesense.api.old;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TestDoc {

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
        String authorsstr = "";

        for(String i: this.authors)
            authorsstr += i;

        return String.format("%s: %s - %s - %s", id, title, publicationYear,authorsstr);
    }

    public static TestDoc createBook(
            String title, String[] authors, String imageUrl,
            int publicationYear, int ratingsCount, float averageRating) {
        TestDoc book = new TestDoc();
        book.id = title;
        book.title = title;
        book.authors = authors;
        book.authorsFacet = book.authors;
        book.imageUrl = imageUrl;
        book.publicationYear = publicationYear;
        book.publicationYearFacet = Integer.toString(book.publicationYear);
        book.ratingsCount = ratingsCount;
        book.averageRating = averageRating;
        return  book;
    }
}