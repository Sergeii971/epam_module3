package com.epam.esm.exception;

/**
 * The type OrderException.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public class OrderException extends RuntimeException {
    public OrderException() {
        super();
    }

    public OrderException(String message) {
        super(message);
    }

    public OrderException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public OrderException(Throwable throwable) {
        super(throwable);
    }
}
