package com.api.automation.models;

import lombok.Builder;

/**
 * Request payload for user creation and updates
 * Using Java 17 record for immutable data
 */
@Builder
public record UserRequest(String name, String job) {
    // Manual builder removed
} 