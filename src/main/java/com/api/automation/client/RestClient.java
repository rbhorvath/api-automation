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
        Properties props = new Properties();
        try (InputStream input = RestClient.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                API_KEY_VALUE = ""; // Or throw an exception
            } else {
                props.load(input);
                API_KEY_VALUE = props.getProperty("api.key");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error loading config.properties", ex);
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