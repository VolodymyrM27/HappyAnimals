package com.motrechko.happyAnimals.exception;

public class EmailExistException extends RuntimeException{
    public EmailExistException(String email){
        super("Email is already exist" + email);
    }
}
