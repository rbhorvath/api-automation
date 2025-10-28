package com.api.automation.tests;

import com.api.automation.models.ListResponse;
import com.api.automation.models.User;
import io.restassured.response.Response;
import io.restassured.common.mapper.TypeRef;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DelayedResponseTest extends BaseTest {
    
    @Test(retryAnalyzer = com.api.automation.utils.RetryAnalyzer.class)
    public void testDelayedResponse() {
        Response response = client.get("/users?delay=3");
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        
        ListResponse<User> listResponse = response.as(new TypeRef<ListResponse<User>>() {});
        Assert.assertTrue(listResponse.data().size() > 0);
    }
} 