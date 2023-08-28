package com.avid.exception;

public class NodeNotFoundException extends ValidationException {
    public NodeNotFoundException() {
        super("Node is not fount in the tree.");
    }
}
