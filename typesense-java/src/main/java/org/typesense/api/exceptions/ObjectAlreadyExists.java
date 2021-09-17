package org.typesense.api.exceptions;

public class ObjectAlreadyExists extends TypesenseError{
    public ObjectAlreadyExists(String message, int status) {
        super(message, status);
    }
}
