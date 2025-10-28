package com.api.automation.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class TestDataUtil {
    public static List<Map<String, String>> getUserTestData(String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(filePath), List.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read test data from " + filePath, e);
        }
    }
}
