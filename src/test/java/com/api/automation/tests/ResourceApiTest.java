package com.api.automation.tests;

import com.api.automation.models.ListResponse;
import com.api.automation.models.Resource;
import com.api.automation.models.SingleResponse;
import io.restassured.response.Response;
import io.restassured.common.mapper.TypeRef;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ResourceApiTest extends BaseTest {
    
    @Test
    public void testListResources() {
        Response response = client.get("/unknown");
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        
        ListResponse<Resource> listResponse = response.as(new TypeRef<ListResponse<Resource>>() {});
        Assert.assertTrue(listResponse.data().size() > 0);
    }
    
    @Test
    public void testGetSingleResource() {
        Response response = client.get("/unknown/2");
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        
        SingleResponse<Resource> singleResponse = response.as(new TypeRef<SingleResponse<Resource>>() {});
        Assert.assertNotNull(singleResponse.data());
    }
    
    @Test
    public void testGetSingleResourceNotFound() {
        Response response = client.get("/unknown/23");
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_NOT_FOUND);
    }
} 