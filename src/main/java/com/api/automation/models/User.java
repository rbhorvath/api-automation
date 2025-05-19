package com.api.automation.models;

/**
 * User data model returned by the API
 * Using Java 17 record for immutable data
 */
public record User(int id, String email, String first_name, String last_name, String avatar) {
} 