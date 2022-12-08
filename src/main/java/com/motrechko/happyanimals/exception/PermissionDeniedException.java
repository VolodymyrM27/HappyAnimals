package com.motrechko.happyanimals.exception;

public class PermissionDeniedException extends RuntimeException {
    public PermissionDeniedException(String action) {
        super("Permission denied for this action: " + action);
    }
}
