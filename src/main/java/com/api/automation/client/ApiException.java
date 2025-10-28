

package com.api.automation.client;

/**
 * Custom exception for API errors in the automation client.
 */
public class ApiException extends RuntimeException {
    /**
     * Constructs a new ApiException with the specified detail message.
     * @param message the detail message
     */
    public ApiException(String message) {
        super(message);
    }

    /**
     * Constructs a new ApiException with the specified detail message and cause.
     * @param message the detail message
     * @param cause the cause of the exception
     */
    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
