package com.epam.esm.exception;

public class JwtTokenMissingException extends RuntimeException {
    public JwtTokenMissingException(String msg) {
        super(msg);
    }
}
