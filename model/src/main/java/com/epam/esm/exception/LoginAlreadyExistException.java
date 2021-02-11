package com.epam.esm.exception;

public class LoginAlreadyExistException extends RuntimeException {
    public LoginAlreadyExistException() {
        super();
    }

    public LoginAlreadyExistException(String message) {
        super(message);
    }

    public LoginAlreadyExistException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public LoginAlreadyExistException(Throwable throwable) {
        super(throwable);
    }
}
