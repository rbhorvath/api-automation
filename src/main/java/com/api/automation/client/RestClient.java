package com.api.automation.client;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class RestClient {
    private static final String BASE_URL = "https://reqres.in/api";
    private static final String API_KEY_HEADER = "x-api-key";
    private static final String API_KEY_VALUE;

    static {
        String apiKeyFromEnv = System.getenv("REQRES_API_KEY");
        if (apiKeyFromEnv != null && !apiKeyFromEnv.isEmpty()) {
            API_KEY_VALUE = apiKeyFromEnv;
            System.out.println("Loaded API key from environment variable REQRES_API_KEY.");
        } else {
            Properties props = new Properties();
            try (InputStream input = RestClient.class.getClassLoader().getResourceAsStream("config.properties")) {
                if (input == null) {
                    System.err.println("ERROR: config.properties not found and REQRES_API_KEY env variable is not set.");
                    API_KEY_VALUE = ""; // Or throw an exception to fail fast
                } else {
                    props.load(input);
                    API_KEY_VALUE = props.getProperty("api.key");
                    if (API_KEY_VALUE == null || API_KEY_VALUE.trim().isEmpty()) {
                        System.err.println("ERROR: api.key not found or empty in config.properties and REQRES_API_KEY env variable is not set.");
                        // throw new RuntimeException("API Key not configured.");
                    }
                    System.out.println("Loaded API key from config.properties.");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                throw new RuntimeException("Error loading config.properties", ex);
            }
        }

        if (API_KEY_VALUE == null || API_KEY_VALUE.trim().isEmpty()) {
            System.err.println("CRITICAL ERROR: API Key is not configured. Please set REQRES_API_KEY environment variable or api.key in config.properties.");
            // Consider throwing a hard exception here if an API key is absolutely mandatory for the app to function
            // throw new RuntimeException("API Key is not configured correctly.");
        }
    }
    
    public Response send(String method, String endpoint, Object body) {
        var request = RestAssured.given()
                .header(API_KEY_HEADER, API_KEY_VALUE)
                .contentType(ContentType.JSON);
        if (body != null) request.body(body);
        
        Response response = request.request(method, BASE_URL + endpoint);
        return response;
    }
    
    public Response get(String endpoint) {
        return send("GET", endpoint, null);
    }
    
    public Response post(String endpoint, Object body) {
        return send("POST", endpoint, body);
    }
    
    public Response put(String endpoint, Object body) {
        return send("PUT", endpoint, body);
    }
    
    public Response delete(String endpoint) {
        return send("DELETE", endpoint, null);
    }
} 