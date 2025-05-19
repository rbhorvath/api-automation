package com.api.automation.models;

/**
 * Response from login and register endpoints
 * Using Java 17 record for immutable data
 */
public record TokenResponse(String token, Integer id, String error) {
} 