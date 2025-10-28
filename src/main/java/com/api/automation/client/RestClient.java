package com.api.automation.client;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import com.api.automation.utils.ConfigUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.api.automation.client.ApiException;

/**
 * RestClient provides methods to interact with the Reqres API using REST Assured.
 * Handles authentication, configuration, and logging.
 */
public class RestClient {
    private static final Logger logger = LoggerFactory.getLogger(RestClient.class);
    private static final String BASE_URL = ConfigUtil.getProperty("base.url");
    private static final String API_KEY_HEADER = "x-api-key";
    private static final String API_KEY_VALUE;

    static {
        String apiKeyFromEnv = System.getenv("REQRES_API_KEY");
        if (apiKeyFromEnv != null && !apiKeyFromEnv.isEmpty()) {
            API_KEY_VALUE = apiKeyFromEnv;
            logger.info("Loaded API key from environment variable REQRES_API_KEY.");
        } else {
            API_KEY_VALUE = ConfigUtil.getProperty("api.key");
            if (API_KEY_VALUE == null || API_KEY_VALUE.trim().isEmpty()) {
                logger.error("CRITICAL ERROR: API Key is not configured. Please set REQRES_API_KEY environment variable or api.key in config.properties.");
                throw new ApiException("API Key is not configured. Please set REQRES_API_KEY environment variable or api.key in config.properties.");
            } else {
                logger.info("Loaded API key from config.properties.");
            }
        }
    }
    
    /**
     * Sends an HTTP request to the specified endpoint with the given method and body.
     * @param method HTTP method (GET, POST, PUT, DELETE)
     * @param endpoint API endpoint path
     * @param body Request body object (can be null)
     * @return Response from the API
     * @throws ApiException if API key is missing
     */
    public Response send(String method, String endpoint, Object body) {
        logger.info("Sending {} request to endpoint: {}", method, endpoint);
        if (API_KEY_VALUE == null || API_KEY_VALUE.trim().isEmpty()) {
            throw new ApiException("API Key is missing for request.");
        }
        var request = RestAssured.given()
                .header(API_KEY_HEADER, API_KEY_VALUE)
                .contentType(ContentType.JSON);
        if (body != null) request.body(body);

        Response response = request.request(method, BASE_URL + endpoint);
        logger.debug("Received response: {}", response.asString());
        return response;
    }

    /**
     * Sends a GET request to the specified endpoint.
     * @param endpoint API endpoint path
     * @return Response from the API
     */
    public Response get(String endpoint) {
        return send("GET", endpoint, null);
    }

    /**
     * Sends a POST request to the specified endpoint with the given body.
     * @param endpoint API endpoint path
     * @param body Request body object
     * @return Response from the API
     */
    public Response post(String endpoint, Object body) {
        return send("POST", endpoint, body);
    }

    /**
     * Sends a PUT request to the specified endpoint with the given body.
     * @param endpoint API endpoint path
     * @param body Request body object
     * @return Response from the API
     */
    public Response put(String endpoint, Object body) {
        return send("PUT", endpoint, body);
    }

    /**
     * Sends a DELETE request to the specified endpoint.
     * @param endpoint API endpoint path
     * @return Response from the API
     */
    public Response delete(String endpoint) {
        return send("DELETE", endpoint, null);
    }
} 