package com.api.automation.tests;

import com.api.automation.models.LoginRequest;
import com.api.automation.models.TokenResponse;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AuthApiTest extends BaseTest {
    
    @DataProvider(name = "registerData")
    public Object[][] registerDataProvider() {
        return new Object[][]{
                {"eve.holt@reqres.in", "pistol", HttpStatus.SC_OK, true, null},
                {"sydney@fife", null, HttpStatus.SC_BAD_REQUEST, false, "Missing password"}
        };
    }

    @Test(dataProvider = "registerData", retryAnalyzer = com.api.automation.utils.RetryAnalyzer.class)
    public void testRegisterScenarios(String email, String password, int expectedStatusCode, boolean expectTokenAndId, String expectedErrorMessage) {
        var registerBuilder = LoginRequest.builder().email(email);
        if (password != null) {
            registerBuilder.password(password);
        }
        LoginRequest registerRequest = registerBuilder.build();

        Response response = client.post("/register", registerRequest);
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode);

        TokenResponse tokenResponse = response.as(TokenResponse.class);
        if (expectTokenAndId) {
            Assert.assertNotNull(tokenResponse.token(), "Token should not be null for successful registration");
            Assert.assertNotNull(tokenResponse.id(), "ID should not be null for successful registration");
        } else {
            Assert.assertNotNull(tokenResponse.error(), "Error message should not be null for unsuccessful registration");
            if (expectedErrorMessage != null) {
                 // Similar to login, actual error messages from reqres.in for bad registration data (non-API key error) might vary.
                Assert.assertEquals(tokenResponse.error().toLowerCase(), expectedErrorMessage.toLowerCase());
            }
        }
    }

    @DataProvider(name = "loginData")
    public Object[][] loginDataProvider() {
        return new Object[][]{
                {"eve.holt@reqres.in", "cityslicka", HttpStatus.SC_OK, true, null}, // Successful login
                {"peter@klaven", null, HttpStatus.SC_BAD_REQUEST, false, "Missing password"}, // Unsuccessful login - missing password
                {"eve.holt@reqres.in", "wrongpassword", HttpStatus.SC_OK, true, null} 
        };
    }

    @Test(dataProvider = "loginData", retryAnalyzer = com.api.automation.utils.RetryAnalyzer.class)
    public void testLoginScenarios(String email, String password, int expectedStatusCode, boolean expectToken, String expectedErrorMessage) {
        var loginBuilder = LoginRequest.builder().email(email);
        if (password != null) {
            loginBuilder.password(password);
        }
        LoginRequest loginRequest = loginBuilder.build();

        Response response = client.post("/login", loginRequest);
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode);

        TokenResponse tokenResponse = response.as(TokenResponse.class);
        if (expectToken) {
            Assert.assertNotNull(tokenResponse.token(), "Token should not be null for successful login");
            Assert.assertNull(tokenResponse.error(), "Error should be null for successful login");
        } else {
            // Handle cases where login fails
            if (expectedErrorMessage != null) {
                Assert.assertNotNull(tokenResponse.error(), "Error message should not be null when an error message is expected");
                Assert.assertEquals(tokenResponse.error().toLowerCase(), expectedErrorMessage.toLowerCase());
                Assert.assertNull(tokenResponse.token(), "Token should be null for unsuccessful login with specific error message");
            } else {
                // This case implies login failed, but no specific error message string is expected in the 'error' field (e.g. wrong password returning 200 OK)
                Assert.assertNull(tokenResponse.error(), "Error field should be null for this failure scenario");
                Assert.assertNull(tokenResponse.token(), "Token should be null for this failure scenario");
            }
        }
    }
} 