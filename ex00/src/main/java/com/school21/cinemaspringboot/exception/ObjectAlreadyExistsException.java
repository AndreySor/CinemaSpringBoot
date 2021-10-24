package com.school21.cinemaspringboot.exception;

public class ObjectAlreadyExistsException extends RuntimeException{

    public ObjectAlreadyExistsException(String message) {
        super(message);
    }
}
