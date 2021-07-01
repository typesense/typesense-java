package org.typesense.model.exceptions;

public class RequestMalformed extends TypesenseError{
    public RequestMalformed(String message, int status) {
        super(message, status);
    }
}
