package org.typesense.model.exceptions;

public class ServerError extends TypesenseError{
    public ServerError(String message, int status) {
        super(message, status);
    }
}
