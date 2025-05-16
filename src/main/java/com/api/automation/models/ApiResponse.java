package com.api.automation.models;

/**
 * Sealed interface for API responses
 * This uses Java 17's sealed classes feature to restrict which types can implement this interface
 */
public sealed interface ApiResponse permits SingleResponse, ListResponse {
    /**
     * Get the support information
     * @return Support object
     */
    Support support();
} 