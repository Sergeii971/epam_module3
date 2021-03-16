package com.epam.esm.exception;

/**
 * The type TagException.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public class TagException extends RuntimeException {
    public TagException() {
        super();
    }

    public TagException(String message) {
        super(message);
    }

    public TagException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public TagException(Throwable throwable) {
        super(throwable);
    }
}
