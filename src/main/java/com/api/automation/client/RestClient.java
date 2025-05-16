package com.api.automation.client;

import com.api.automation.config.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class RestClient {
    private final String baseUrl;
    
    public RestClient() {
        this.baseUrl = ConfigManager.getInstance().getBaseUrl();
    }
    
    public Response get(String endpoint, Object... pathParams) {
        log.info("Making GET request to: {}", endpoint);
        RequestSpecification request = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("x-api-key", "reqres-free-v1");
        return request.get(baseUrl + endpoint, pathParams);
    }
    
    public Response post(String endpoint, Object body) {
        log.info("Making POST request to: {}", endpoint);
        RequestSpecification request = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("x-api-key", "reqres-free-v1")
                .body(body);
        return request.post(baseUrl + endpoint);
    }
    
    public Response put(String endpoint, Object body, Object... pathParams) {
        log.info("Making PUT request to: {}", endpoint);
        RequestSpecification request = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("x-api-key", "reqres-free-v1")
                .body(body);
        return request.put(baseUrl + endpoint, pathParams);
    }
    
    public Response patch(String endpoint, Object body, Object... pathParams) {
        log.info("Making PATCH request to: {}", endpoint);
        RequestSpecification request = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("x-api-key", "reqres-free-v1")
                .body(body);
        return request.patch(baseUrl + endpoint, pathParams);
    }
    
    public Response delete(String endpoint, Object... pathParams) {
        log.info("Making DELETE request to: {}", endpoint);
        RequestSpecification request = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("x-api-key", "reqres-free-v1");
        return request.delete(baseUrl + endpoint, pathParams);
    }
} 