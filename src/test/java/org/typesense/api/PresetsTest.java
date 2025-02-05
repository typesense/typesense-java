package org.typesense.api;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.typesense.model.OneOfPresetUpsertSchemaValue;
import org.typesense.model.PresetDeleteSchema;
import org.typesense.model.PresetSchema;
import org.typesense.model.PresetUpsertSchema;
import org.typesense.model.PresetsRetrieveSchema;
import org.typesense.model.SearchParameters;

public class PresetsTest {

    private Client client;
    private Helper helper;

    @BeforeEach
    void setUp() throws Exception {
        helper = new Helper();
        client = helper.getClient();
        helper.teardown();
        helper.createTestCollection();
    }

    @AfterEach
    void tearDown() throws Exception {
        helper.teardown();
    }

    @Test
    void testUpsert() throws Exception {
        SearchParameters params = new SearchParameters()
                .q("Romeo")
                .queryBy("title");

        PresetUpsertSchema preset = new PresetUpsertSchema()
                .value(params);

        PresetSchema result = client.presets().upsert("listing_view", preset);

        assertNotNull(result);
        assertEquals("listing_view", result.getName());

        OneOfPresetUpsertSchemaValue value = result.getValue();
        assertNotNull(value);
    }

    @Test
    void testRetrieve() throws Exception {
        helper.createTestPreset();

        PresetSchema result = this.client.presets("listing_view").retrieve();

        assertNotNull(result);
        assertEquals("listing_view", result.getName());
        assertNotNull(result.getValue());
    }

    @Test
    void testRetrieveAll() throws Exception {
        helper.createTestPreset();

         PresetsRetrieveSchema result = this.client.presets().retrieve();

        assertNotNull(result);

        assertEquals(1, result.getPresets().size());

        PresetSchema preset = result.getPresets().get(0);
        assertEquals("listing_view", preset.getName());
        assertNotNull(preset.getValue());
    }

    @Test
    void testDelete() throws Exception {
        helper.createTestPreset();

        PresetDeleteSchema result = this.client.presets("listing_view").delete();

        assertNotNull(result);

        assertEquals("listing_view", result.getName());

   }
}
