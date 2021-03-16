package com.epam.esm.exception;

/**
 * The type ResourceNotFoundException.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ResourceNotFoundException(Throwable throwable) {
        super(throwable);
    }
}
