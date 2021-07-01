package org.typesense.model.exceptions;

public class RequestUnauthorized extends TypesenseError{
    public RequestUnauthorized(String message, int status) {
        super(message, status);
    }
}
