package com.api.automation.models;


/**
 * Login and register request payload
 * Using Java 17 record for immutable data
 */
public record LoginRequest(String email, String password) {
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String email;
        private String password;

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public LoginRequest build() {
            if (email == null || email.trim().isEmpty()) {
                throw new IllegalArgumentException("Email must not be null or empty");
            }
            // Password can be null or empty to allow API error scenarios
            return new LoginRequest(email, password);
        }
    }
}