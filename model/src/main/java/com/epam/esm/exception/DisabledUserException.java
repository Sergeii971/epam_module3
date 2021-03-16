package com.epam.esm.exception;

public class DisabledUserException extends RuntimeException {
    public DisabledUserException(String msg) {
        super(msg);
    }

}
