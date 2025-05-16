package com.api.automation.models;

/**
 * Login and register request payload
 * Using Java 17 record for immutable data
 */
public record LoginRequest(String email, String password) {
    /**
     * Builder pattern for LoginRequest
     */
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
            return new LoginRequest(email, password);
        }
    }
    
    /**
     * Static builder factory method to maintain Lombok-like builder API
     */
    public static Builder builder() {
        return new Builder();
    }
} 