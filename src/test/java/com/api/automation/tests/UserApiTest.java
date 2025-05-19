package com.api.automation.tests;

import com.api.automation.models.*;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserApiTest extends BaseTest {
    
    @Test
    public void testListUsers() {
        Response response = client.get("/users?page=2");
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        
        @SuppressWarnings("unchecked")
        ListResponse<User> listResponse = response.as(ListResponse.class);
        Assert.assertEquals(listResponse.page(), 2);
        Assert.assertTrue(listResponse.data().size() > 0);
    }
    
    @Test
    public void testGetSingleUser() {
        Response response = client.get("/users/2");
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        
        @SuppressWarnings("unchecked")
        SingleResponse<User> singleResponse = response.as(SingleResponse.class);
        Assert.assertNotNull(singleResponse.data());
    }
    
    @Test
    public void testGetSingleUserNotFound() {
        Response response = client.get("/users/23");
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_NOT_FOUND);
    }
    
    @Test
    public void testCreateUser() {
        UserRequest newUser = UserRequest.builder()
                .name("morpheus")
                .job("leader")
                .build();
        
        Response response = client.post("/users", newUser);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED);
        
        CreatedUserResponse createdResponse = response.as(CreatedUserResponse.class);
        Assert.assertEquals(createdResponse.name(), newUser.name());
        Assert.assertEquals(createdResponse.job(), newUser.job());
        Assert.assertNotNull(createdResponse.id());
        Assert.assertNotNull(createdResponse.createdAt());
    }
    
    @Test
    public void testUpdateUserWithPut() {
        UserRequest updatedUser = UserRequest.builder()
                .name("morpheus")
                .job("zion resident")
                .build();
        
        Response response = client.put("/users/2", updatedUser);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        
        UpdatedUserResponse updatedResponse = response.as(UpdatedUserResponse.class);
        Assert.assertEquals(updatedResponse.name(), updatedUser.name());
        Assert.assertEquals(updatedResponse.job(), updatedUser.job());
        Assert.assertNotNull(updatedResponse.updatedAt());
    }
    
    @Test
    public void testDeleteUser() {
        Response response = client.delete("/users/2");
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_NO_CONTENT);
    }
} 