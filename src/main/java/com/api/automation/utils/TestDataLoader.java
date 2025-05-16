package com.api.automation.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;

@Log4j2
public class TestDataLoader {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    public static <T> T loadTestData(String filePath, Class<T> valueType) {
        try {
            return objectMapper.readValue(new File(filePath), valueType);
        } catch (IOException e) {
            log.error("Failed to load test data from {}", filePath, e);
            throw new RuntimeException("Failed to load test data", e);
        }
    }
} 