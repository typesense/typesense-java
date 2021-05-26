package org.typesense.resources;

/**
 * Interface for the lambda functions
 */
public interface RequestHandler<T> {
    T handleRequest(String URI);
}
