
package com.api.automation.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Utility class for loading user test data from JSON files.
 */
public class TestDataUtil {
    /**
     * Reads user test data from the specified JSON file path.
     * @param filePath the path to the JSON file
     * @return a list of user test data as maps
     * @throws RuntimeException if reading the file fails
     */
    public static List<Map<String, String>> getUserTestData(String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(filePath), List.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read test data from " + filePath, e);
        }
    }
}
