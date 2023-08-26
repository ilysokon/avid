package com.avid.exception;

public class NullNodeValidationException extends ValidationException {
    public NullNodeValidationException() {
        super("Node should not be null");
    }
}
