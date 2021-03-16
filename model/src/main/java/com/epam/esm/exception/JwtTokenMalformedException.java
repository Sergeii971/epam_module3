package com.epam.esm.exception;

public class JwtTokenMalformedException extends RuntimeException {
    public JwtTokenMalformedException() {
        super();
    }

    public JwtTokenMalformedException(String message) {
        super(message);
    }
}
