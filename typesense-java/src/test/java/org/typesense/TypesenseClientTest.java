package org.typesense;

import org.junit.Assert;
import org.junit.Test;

public class TypesenseClientTest {

    @Test
    public void shouldValidateConstructorParameters() {
        new TypesenseClient("baseUrl", "apiKey");
        try {
            new TypesenseClient(null, "apiKey");
            Assert.fail("Failed to validate baseUrl parameter");
        } catch (NullPointerException ex) {}
        try {
            new TypesenseClient("baseUrl", null);
            Assert.fail("Failed to validate apiKey parameter");
        } catch (NullPointerException ex) {}
    }
}
