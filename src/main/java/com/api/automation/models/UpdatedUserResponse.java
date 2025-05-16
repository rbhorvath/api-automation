package com.api.automation.models;

/**
 * Response from the API after updating a user
 * Using Java 17 record for immutable data
 */
public record UpdatedUserResponse(String name, String job, String updatedAt) {
} 