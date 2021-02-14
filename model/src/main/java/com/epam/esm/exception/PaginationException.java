package com.epam.esm.exception;

public class PaginationException extends RuntimeException {
    public PaginationException() {
        super();
    }

    public PaginationException(String message) {
        super(message);
    }

    public PaginationException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public PaginationException(Throwable throwable) {
        super(throwable);
    }
}
