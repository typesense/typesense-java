package org.typesense.api.exceptions;

public class RequestMalformed extends TypesenseError{
    public RequestMalformed(String message, int status) {
        super(message, status);
    }
}
