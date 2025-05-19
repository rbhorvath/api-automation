package com.api.automation.models;

/**
 * Resource data model returned by the API
 * Using Java 17 record for immutable data
 */
public record Resource(int id, String name, int year, String pantone_value) {
} 