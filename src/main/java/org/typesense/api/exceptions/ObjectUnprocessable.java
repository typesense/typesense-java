package org.typesense.api.exceptions;

public class ObjectUnprocessable extends TypesenseError{
    public ObjectUnprocessable(String message, int status) {
        super(message, status);
    }
}
