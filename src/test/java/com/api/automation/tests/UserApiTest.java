package com.api.automation.tests;

import com.api.automation.models.*;
import com.api.automation.utils.TestDataUtil;
import io.restassured.response.Response;
import io.restassured.common.mapper.TypeRef;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserApiTest extends BaseTest {
    
    @Test(retryAnalyzer = com.api.automation.utils.RetryAnalyzer.class)
    public void testListUsers() {
        Response response = client.get("/users?page=2");
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        
        ListResponse<User> listResponse = response.as(new TypeRef<ListResponse<User>>() {});
        Assert.assertEquals(listResponse.page(), 2);
        Assert.assertTrue(listResponse.data().size() > 0);
    }
    
    @Test(retryAnalyzer = com.api.automation.utils.RetryAnalyzer.class)
    public void testGetSingleUser() {
        Response response = client.get("/users/2");
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        
        SingleResponse<User> singleResponse = response.as(new TypeRef<SingleResponse<User>>() {});
        Assert.assertNotNull(singleResponse.data(), "User data should not be null");
        Assert.assertEquals(singleResponse.data().id(), 2, "User ID should match the requested ID");
        Assert.assertNotNull(singleResponse.data().email(), "User email should not be null");
    }
    
    @Test(retryAnalyzer = com.api.automation.utils.RetryAnalyzer.class)
    public void testGetSingleUserNotFound() {
        Response response = client.get("/users/23");
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_NOT_FOUND);
    }
    
    @Test(retryAnalyzer = com.api.automation.utils.RetryAnalyzer.class)
    public void testCreateUser() {
        String testDataPath = "src/test/resources/user_test_data.json";
        var userDataList = TestDataUtil.getUserTestData(testDataPath);
        for (var userData : userDataList) {
            UserRequest newUser = UserRequest.builder()
                    .name(userData.get("name"))
                    .job(userData.get("job"))
                    .build();

            Response response = client.post("/users", newUser);
            Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED);

            CreatedUserResponse createdResponse = response.as(CreatedUserResponse.class);
            Assert.assertEquals(createdResponse.name(), newUser.name());
            Assert.assertEquals(createdResponse.job(), newUser.job());
            Assert.assertNotNull(createdResponse.id());
            Assert.assertNotNull(createdResponse.createdAt());
        }
    }
    
    @Test(retryAnalyzer = com.api.automation.utils.RetryAnalyzer.class)
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
    
    @Test(retryAnalyzer = com.api.automation.utils.RetryAnalyzer.class)
    public void testDeleteUser() {
        Response response = client.delete("/users/2");
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_NO_CONTENT);
    }
} 