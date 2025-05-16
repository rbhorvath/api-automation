package com.api.automation.models;

/**
 * Response wrapper for single entity API responses
 * Using Java 17 record for immutable data
 */
public record SingleResponse<T>(T data, Support support) implements ApiResponse {
} 