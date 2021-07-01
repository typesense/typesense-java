package org.typesense.model.exceptions;

public class ObjectUnprocessable extends TypesenseError{
    public ObjectUnprocessable(String message, int status) {
        super(message, status);
    }
}
