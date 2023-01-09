package com.kasenov.libpro.simplelibrary.exceptionHandler;

public class NotFoundException extends Exception{

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(long id) {
        super(String.format("Object with id: %d not found", id));
    }
}
