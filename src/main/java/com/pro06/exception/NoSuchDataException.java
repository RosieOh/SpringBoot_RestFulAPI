package com.pro06.exception;

public class NoSuchDataException extends RuntimeException{
    public NoSuchDataException (String message) {
        super(message);
    }
}
