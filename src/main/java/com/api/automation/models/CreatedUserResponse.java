package com.api.automation.models;

/**
 * Response from the API after creating a user
 * Using Java 17 record for immutable data
 */
public record CreatedUserResponse(String name, String job, String id, String createdAt) {
} 