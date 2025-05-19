package com.api.automation.client;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestClient {
    private static final String BASE_URL = "https://reqres.in/api";
    
    public Response send(String method, String endpoint, Object body) {
        var request = RestAssured.given().contentType(ContentType.JSON);
        if (body != null) request.body(body);
        return request.request(method, BASE_URL + endpoint);
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