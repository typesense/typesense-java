package org.typesense.model.exceptions;

public class MissingConfiguration extends  TypesenseError{
    public MissingConfiguration(String message, int status) {
        super(message, status);
    }
}
