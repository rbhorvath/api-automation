package com.api.automation.tests;

import com.api.automation.models.LoginRequest;
import com.api.automation.models.TokenResponse;
import com.api.automation.utils.ResponseValidator;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthApiTest extends BaseTest {
    
    @Test
    public void testSuccessfulRegister() {
        LoginRequest registerRequest = LoginRequest.builder()
                .email("eve.holt@reqres.in")
                .password("pistol")
                .build();
        
        Response response = restClient.post("/register", registerRequest);
        ResponseValidator.validateStatusCode(response, HttpStatus.SC_OK);
        
        TokenResponse tokenResponse = response.as(TokenResponse.class);
        Assert.assertNotNull(tokenResponse.token());
        Assert.assertNotNull(tokenResponse.id());
    }
    
    @Test
    public void testUnsuccessfulRegister() {
        LoginRequest registerRequest = LoginRequest.builder()
                .email("sydney@fife")
                .build();
        
        Response response = restClient.post("/register", registerRequest);
        ResponseValidator.validateStatusCode(response, HttpStatus.SC_BAD_REQUEST);
        
        TokenResponse tokenResponse = response.as(TokenResponse.class);
        Assert.assertEquals(tokenResponse.error(), "Missing password");
    }
    
    @Test
    public void testSuccessfulLogin() {
        LoginRequest loginRequest = LoginRequest.builder()
                .email("eve.holt@reqres.in")
                .password("cityslicka")
                .build();
        
        Response response = restClient.post("/login", loginRequest);
        ResponseValidator.validateStatusCode(response, HttpStatus.SC_OK);
        
        TokenResponse tokenResponse = response.as(TokenResponse.class);
        Assert.assertNotNull(tokenResponse.token());
    }
    
    @Test
    public void testUnsuccessfulLogin() {
        LoginRequest loginRequest = LoginRequest.builder()
                .email("peter@klaven")
                .build();
        
        Response response = restClient.post("/login", loginRequest);
        ResponseValidator.validateStatusCode(response, HttpStatus.SC_BAD_REQUEST);
        
        TokenResponse tokenResponse = response.as(TokenResponse.class);
        Assert.assertEquals(tokenResponse.error(), "Missing password");
    }
} 