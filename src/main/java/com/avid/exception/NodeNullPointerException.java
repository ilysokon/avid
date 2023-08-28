package com.avid.exception;

public class NodeNullPointerException extends ValidationException {
    public NodeNullPointerException() {
        super("Node should not be null");
    }
}
