package com.epam.esm.exception;

/**
 * The type BalanceException.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public class BalanceException  extends RuntimeException {
    public BalanceException() {
        super();
    }

    public BalanceException(String message) {
        super(message);
    }

    public BalanceException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public BalanceException(Throwable throwable) {
        super(throwable);
    }
}
