package com.motrechko.happyanimals.exception;

public class AnimalNotFoundException extends RuntimeException{
    public AnimalNotFoundException(Long id) {
        super("Could not find animal " + id);
    }
}
