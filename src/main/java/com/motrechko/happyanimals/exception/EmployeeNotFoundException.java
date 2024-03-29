package com.motrechko.happyanimals.exception;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Long id) {
        super("Could not find employee " + id);
    }

    public EmployeeNotFoundException() {
        super("Could not find employee ");
    }
}
