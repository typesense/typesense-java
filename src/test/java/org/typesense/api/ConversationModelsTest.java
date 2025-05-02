package org.typesense.api;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.typesense.model.ConversationModelCreateSchema;
import org.typesense.model.ConversationModelSchema;
import org.typesense.model.ConversationModelUpdateSchema;

@Tag("excludeFromCI")
class ConversationModelsTest {

    Client client;
    private Helper helper;

    @BeforeEach
    void setUp() throws Exception {
        helper = new Helper();
        this.client = helper.getClient();
        helper.teardown();
        helper.createConversationCollection();
    }

    @AfterEach
    void tearDown() throws Exception {
        helper.teardown();
    }

    @Test
    void testCreate() throws Exception {
        ConversationModelCreateSchema model = new ConversationModelCreateSchema();

        model.setId("conv-model");
        model.setHistoryCollection("conversations");
        model.setSystemPrompt(
                "You are an assistant for question-answering. You can only make conversations based on the provided context. If a response cannot be formed strictly using the provided context, politely say you do not have knowledge about that topic.");
        model.setApiKey(System.getenv("OPENAI_API_KEY"));
        model.setConversationModelCreateSchemaModelName("openai/gpt-3.5-turbo");
        model.setConversationModelCreateSchemaMaxBytes(16384);
        model.setConversationModelCreateSchemaHistoryCollection("conversations");

        ConversationModelCreateSchema result = client.conversations().models().create(model);
        assertNotNull(result);

        assertEquals("conv-model", result.getId());
        assertEquals(16384, result.getConversationModelCreateSchemaMaxBytes());
        assertEquals("openai/gpt-3.5-turbo", result.getConversationModelCreateSchemaModelName());
        assertEquals(
                "You are an assistant for question-answering. You can only make conversations based on the provided context. If a response cannot be formed strictly using the provided context, politely say you do not have knowledge about that topic.",
                result.getSystemPrompt());
    }

    @Test
    void testRetrieveAll() throws Exception {
        helper.createTestConversationModel();

        ConversationModelSchema[] results = client.conversations().models().retrieve();

        assertNotNull(results);

        ConversationModelSchema model = results[0];

        assertEquals("conv-model", model.getConversationModelSchemaId());
        assertEquals(16384, model.getConversationModelCreateSchemaMaxBytes());
        assertEquals("openai/gpt-3.5-turbo", model.getConversationModelCreateSchemaModelName());
        assertEquals(
                "You are an assistant for question-answering. You can only make conversations based on the provided context. If a response cannot be formed strictly using the provided context, politely say you do not have knowledge about that topic.",
                model.getSystemPrompt());
    }

    @Test
    void testRetrieve() throws Exception {
        helper.createTestConversationModel();

        ConversationModelSchema model = client.conversations().models("conv-model").retrieve();

        assertNotNull(model);

        assertEquals("conv-model", model.getConversationModelSchemaId());
        assertEquals(16384, model.getConversationModelCreateSchemaMaxBytes());
        assertEquals("openai/gpt-3.5-turbo", model.getConversationModelCreateSchemaModelName());
        assertEquals(
                "You are an assistant for question-answering. You can only make conversations based on the provided context. If a response cannot be formed strictly using the provided context, politely say you do not have knowledge about that topic.",
                model.getSystemPrompt());
    }

    @Test
    void testUpdate() throws Exception {
        helper.createTestConversationModel();

        ConversationModelUpdateSchema model = new ConversationModelUpdateSchema();
        model.setSystemPrompt("New system prompt");

        ConversationModelUpdateSchema result = client.conversations().models("conv-model").update(model);

        assertNotNull(result);

        assertEquals("conv-model", result.getId());
        assertEquals(16384, result.getMaxBytes());
        assertEquals("openai/gpt-3.5-turbo", result.getModelName());
        assertEquals("New system prompt", result.getSystemPrompt());
    }

}
