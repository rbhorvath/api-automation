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
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Name must not be null or empty");
            }
            if (job == null || job.trim().isEmpty()) {
                throw new IllegalArgumentException("Job must not be null or empty");
            }
            return new UserRequest(name, job);
        }
    }
}