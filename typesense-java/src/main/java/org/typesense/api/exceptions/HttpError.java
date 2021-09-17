package org.typesense.api.exceptions;

public class HttpError extends TypesenseError{
    public HttpError(String message, int status) {
        super(message, status);
    }
}
