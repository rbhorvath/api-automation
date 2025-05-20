package com.api.automation.models;

import lombok.Builder;

/**
 * Login and register request payload
 * Using Java 17 record for immutable data
 */
@Builder
public record LoginRequest(String email, String password) {
    // Manual builder removed
} 