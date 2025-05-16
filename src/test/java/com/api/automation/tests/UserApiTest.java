package com.api.automation.tests;

import com.api.automation.config.ConfigManager;
import com.api.automation.models.*;
import com.api.automation.utils.ResponseValidator;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserApiTest extends BaseTest {
    
    @Test
    public void testListUsers() {
        int page = Integer.parseInt(ConfigManager.getInstance().getProperty("test.page"));
        
        Response response = restClient.get("/users?page={page}", page);
        ResponseValidator.validateStatusCode(response, HttpStatus.SC_OK);
        
        @SuppressWarnings("unchecked")
        ListResponse<User> listResponse = response.as(ListResponse.class);
        Assert.assertEquals(listResponse.page(), page);
        Assert.assertTrue(listResponse.data().size() > 0);
        
        ResponseValidator.validateResponse(listResponse);
    }
    
    @Test
    public void testGetSingleUser() {
        int userId = Integer.parseInt(ConfigManager.getInstance().getProperty("test.user.id"));
        
        Response response = restClient.get("/users/{userId}", userId);
        ResponseValidator.validateStatusCode(response, HttpStatus.SC_OK);
        
        @SuppressWarnings("unchecked")
        SingleResponse<User> singleResponse = response.as(SingleResponse.class);
        Assert.assertNotNull(singleResponse.data());
        
        ResponseValidator.validateResponse(singleResponse);
    }
    
    @Test
    public void testGetSingleUserNotFound() {
        int notFoundId = Integer.parseInt(ConfigManager.getInstance().getProperty("test.not.found.id"));
        
        Response response = restClient.get("/users/{userId}", notFoundId);
        ResponseValidator.validateStatusCode(response, HttpStatus.SC_NOT_FOUND);
    }
    
    @Test
    public void testCreateUser() {
        UserRequest newUser = UserRequest.builder()
                .name("morpheus")
                .job("leader")
                .build();
        
        Response response = restClient.post("/users", newUser);
        ResponseValidator.validateStatusCode(response, HttpStatus.SC_CREATED);
        
        CreatedUserResponse createdResponse = response.as(CreatedUserResponse.class);
        Assert.assertEquals(createdResponse.name(), newUser.name());
        Assert.assertEquals(createdResponse.job(), newUser.job());
        Assert.assertNotNull(createdResponse.id());
        Assert.assertNotNull(createdResponse.createdAt());
    }
    
    @Test
    public void testUpdateUserWithPut() {
        int userId = Integer.parseInt(ConfigManager.getInstance().getProperty("test.user.id"));
        UserRequest updatedUser = UserRequest.builder()
                .name("morpheus")
                .job("zion resident")
                .build();
        
        Response response = restClient.put("/users/{userId}", updatedUser, userId);
        ResponseValidator.validateStatusCode(response, HttpStatus.SC_OK);
        
        UpdatedUserResponse updatedResponse = response.as(UpdatedUserResponse.class);
        Assert.assertEquals(updatedResponse.name(), updatedUser.name());
        Assert.assertEquals(updatedResponse.job(), updatedUser.job());
        Assert.assertNotNull(updatedResponse.updatedAt());
    }
    
    @Test
    public void testDeleteUser() {
        int userId = Integer.parseInt(ConfigManager.getInstance().getProperty("test.user.id"));
        
        Response response = restClient.delete("/users/{userId}", userId);
        ResponseValidator.validateStatusCode(response, HttpStatus.SC_NO_CONTENT);
    }
} 