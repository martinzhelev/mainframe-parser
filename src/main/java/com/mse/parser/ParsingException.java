package com.mse.parser;

/**
 * Custom runtime exception thrown when the parsing process fails.
 * This occurs during critical failures, such as missing annotations or missing resource files,
 * as opposed to individual malformed lines which are simply logged.
 */
public class ParsingException extends RuntimeException {

    /**
     * Constructs a new exception with a specific detail message.
     * @param message the detail error message
     */
    public ParsingException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with a detail message and a root cause.
     * @param message the detail error message
     * @param cause the underlying cause of the failure
     */
    public ParsingException(String message, Throwable cause) {
        super(message, cause);
    }
}