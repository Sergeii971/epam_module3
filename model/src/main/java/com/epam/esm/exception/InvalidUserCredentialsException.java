package com.epam.esm.exception;

public class InvalidUserCredentialsException extends RuntimeException {
    public InvalidUserCredentialsException(String msg) {
        super(msg);
    }
}
