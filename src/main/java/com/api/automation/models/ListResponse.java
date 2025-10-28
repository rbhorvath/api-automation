package com.api.automation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

/**
 * Response wrapper for paginated list API responses
 * Using Java 17 record for immutable data
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record ListResponse<T>(int page, int per_page, int total, int total_pages, List<T> data, Support support) implements ApiResponse {}