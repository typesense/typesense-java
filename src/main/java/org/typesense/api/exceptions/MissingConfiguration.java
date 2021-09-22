package org.typesense.api.exceptions;

public class MissingConfiguration extends  TypesenseError{
    public MissingConfiguration(String message, int status) {
        super(message, status);
    }
}
