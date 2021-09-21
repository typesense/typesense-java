package org.typesense.api.exceptions;

public class RequestUnauthorized extends TypesenseError{
    public RequestUnauthorized(String message, int status) {
        super(message, status);
    }
}
