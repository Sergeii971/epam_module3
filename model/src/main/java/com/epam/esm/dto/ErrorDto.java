package com.epam.esm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The type ErrorDto.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public class ErrorDto implements BaseDto {
    @JsonProperty("error message")
    private String errorMessage;
    @JsonProperty("error code")
    private int errorCode;

    public ErrorDto() {
        super();
    }

    public ErrorDto(String errorMessage, int errorCode) {
        super();
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    /**
     * Sets error message.
     *
     * @param errorMessage the error message
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Sets error code.
     *
     * @param errorCode the error code
     */
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Gets error message.
     *
     * @return the error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Gets error code.
     *
     * @return the error code
     */
    public int getErrorCode() {
        return errorCode;
    }
}
