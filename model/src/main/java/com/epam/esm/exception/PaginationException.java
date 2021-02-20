package com.epam.esm.exception;

/**
 * The type PaginationException.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
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
