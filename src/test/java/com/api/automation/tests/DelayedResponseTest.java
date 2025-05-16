package com.api.automation.tests;

import com.api.automation.models.ListResponse;
import com.api.automation.models.User;
import com.api.automation.utils.ResponseValidator;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DelayedResponseTest extends BaseTest {
    
    @Test
    public void testDelayedResponse() {
        Response response = restClient.get("/users?delay=3");
        ResponseValidator.validateStatusCode(response, HttpStatus.SC_OK);
        
        @SuppressWarnings("unchecked")
        ListResponse<User> listResponse = response.as(ListResponse.class);
        Assert.assertTrue(listResponse.data().size() > 0);
        
        ResponseValidator.validateResponse(listResponse);
    }
} 