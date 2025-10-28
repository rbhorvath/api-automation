package com.api.automation.models;


/**
 * Request payload for user creation and updates
 * Using Java 17 record for immutable data
 */
public record UserRequest(String name, String job) {
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private String job;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder job(String job) {
            this.job = job;
            return this;
        }

        public UserRequest build() {
            return new UserRequest(name, job);
        }
    }
}