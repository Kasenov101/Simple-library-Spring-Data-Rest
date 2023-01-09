package com.kasenov.libpro.simplelibrary.exceptionHandler;

public class CannotSaveException extends Exception{

    public CannotSaveException(String message) {
        super(message);
    }
}
