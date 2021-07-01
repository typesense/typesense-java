package org.typesense.resources;

import javax.ws.rs.core.Response;

/**
 * Interface for the lambda functions
 */
public interface RequestHandler {
    Response handleRequest(String URI);
}
