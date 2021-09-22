package org.typesense.api.exceptions;

public class ObjectNotFound extends TypesenseError{
    public ObjectNotFound(String message, int status) {
        super(message, status);
    }
}
