package org.typesense.resources;

import jakarta.ws.rs.core.Response;

/**
 * Interface for the lambda functions
 */
public interface RequestHandler {
    Response handleRequest(String URI);
}
