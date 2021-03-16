package com.epam.esm.exception;

import java.util.List;

/**
 * The type IncorrectParameterValueException.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public class IncorrectParameterValueException extends RuntimeException {
    private List<String> errorMessages;

    public IncorrectParameterValueException() {
        super();
    }

    public IncorrectParameterValueException(String message) {
        super(message);
    }

    public IncorrectParameterValueException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public IncorrectParameterValueException(Throwable throwable) {
        super(throwable);
    }

    public IncorrectParameterValueException(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }


    public List<String> getErrorMessages() {
        return errorMessages;
    }
}
