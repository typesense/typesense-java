package org.typesense.api.exceptions;

public class RequestForbidden extends TypesenseError {
    public RequestForbidden(String message, int status) {
        super(message, status);
    }
}
