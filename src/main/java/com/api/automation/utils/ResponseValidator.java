package com.api.automation.utils;

import com.api.automation.models.ApiResponse;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpStatus;
import org.testng.Assert;

import java.util.Optional;

@Log4j2
public class ResponseValidator {
    
    public static void validateStatusCode(Response response, int expectedStatusCode) {
        log.info("Validating status code: expected={}, actual={}", expectedStatusCode, response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode, "Status code validation failed");
        
        // Using Java 17 enhanced switch expression to log more descriptive messages based on status code
        String statusMessage = switch (expectedStatusCode) {
            case HttpStatus.SC_OK -> "Request successful";
            case HttpStatus.SC_CREATED -> "Resource created successfully";
            case HttpStatus.SC_NO_CONTENT -> "Resource deleted successfully";
            case HttpStatus.SC_BAD_REQUEST -> "Bad request - validation failed";
            case HttpStatus.SC_NOT_FOUND -> "Resource not found";
            default -> "Status code: " + expectedStatusCode;
        };
        
        log.info(statusMessage);
    }
    
    public static void validateField(Response response, String jsonPath, Object expectedValue) {
        Object actualValue = response.jsonPath().get(jsonPath);
        log.info("Validating field {}: expected={}, actual={}", jsonPath, expectedValue, actualValue);
        Assert.assertEquals(actualValue, expectedValue, "Field validation failed for " + jsonPath);
    }
    
    public static void validateFieldExists(Response response, String jsonPath) {
        // Using Optional for cleaner null handling
        Optional.ofNullable(response.jsonPath().get(jsonPath))
            .ifPresentOrElse(
                value -> log.info("Field {} exists with value: {}", jsonPath, value),
                () -> {
                    log.error("Field {} does not exist", jsonPath);
                    Assert.fail("Field " + jsonPath + " does not exist");
                }
            );
    }
    
    /**
     * Validates a response object using pattern matching for instanceof (Java 17 feature)
     * @param obj The object to validate
     */
    public static void validateResponse(Object obj) {
        // Using pattern matching for instanceof (Java 17 feature)
        if (obj instanceof ApiResponse response) {
            Assert.assertNotNull(response.support(), "Support information should not be null");
            log.info("Response validated successfully: {}", response.getClass().getSimpleName());
        } else if (obj instanceof Response restResponse) {
            // For RestAssured Response objects
            Assert.assertTrue(restResponse.getStatusCode() < 500, "Server error detected");
            log.info("REST response validated successfully with status code: {}", restResponse.getStatusCode());
        } else {
            log.warn("Unknown response type: {}", obj.getClass().getName());
        }
    }
} 