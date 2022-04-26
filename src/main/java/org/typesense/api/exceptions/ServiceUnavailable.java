package org.typesense.api.exceptions;

public class ServiceUnavailable extends TypesenseError {
    public ServiceUnavailable(String message, int status) {
        super(message, status);
    }
}