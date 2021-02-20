package com.epam.esm.exception;

/**
 * The type UserException.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public class UserException extends RuntimeException {
    public UserException() {
        super();
    }

    public UserException(String message) {
        super(message);
    }

    public UserException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public UserException(Throwable throwable) {
        super(throwable);
    }
}
