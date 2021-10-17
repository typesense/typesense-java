package org.typesense.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.lang.Override;

import javax.annotation.Priority;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Provider
@Priority(Integer.MIN_VALUE)
public class ObjectMapperResolver implements ContextResolver<ObjectMapper> {
    private ObjectMapper mapper;

    public ObjectMapperResolver() {
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public ObjectMapper getContext(Class<?> cls) {
        return mapper;
    }
}