package com.api.automation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Response wrapper for single entity API responses
 * Using Java 17 record for immutable data
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record SingleResponse<T>(T data, Support support) implements ApiResponse {}