package com.kasenov.libpro.simplelibrary.ExceptionHandler;

public class NotFoundException extends Exception{

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String objectName, long id) {
        super(String.format("%s with id: not found", objectName, id));
    }
}
