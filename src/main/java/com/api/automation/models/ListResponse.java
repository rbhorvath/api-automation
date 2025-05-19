package com.api.automation.models;

import java.util.List;

/**
 * Response wrapper for paginated list API responses
 * Using Java 17 record for immutable data
 */
public record ListResponse<T>(int page, int per_page, int total, int total_pages, List<T> data, Support support) implements ApiResponse {
} 