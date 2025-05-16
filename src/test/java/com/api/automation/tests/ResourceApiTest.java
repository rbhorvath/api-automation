package com.api.automation.tests;

import com.api.automation.config.ConfigManager;
import com.api.automation.models.ListResponse;
import com.api.automation.models.Resource;
import com.api.automation.models.SingleResponse;
import com.api.automation.utils.ResponseValidator;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ResourceApiTest extends BaseTest {
    
    @Test
    public void testListResources() {
        Response response = restClient.get("/unknown");
        ResponseValidator.validateStatusCode(response, HttpStatus.SC_OK);
        
        @SuppressWarnings("unchecked")
        ListResponse<Resource> listResponse = response.as(ListResponse.class);
        Assert.assertTrue(listResponse.data().size() > 0);
        
        ResponseValidator.validateResponse(listResponse);
    }
    
    @Test
    public void testGetSingleResource() {
        int resourceId = Integer.parseInt(ConfigManager.getInstance().getProperty("test.resource.id"));
        
        Response response = restClient.get("/unknown/{resourceId}", resourceId);
        ResponseValidator.validateStatusCode(response, HttpStatus.SC_OK);
        
        @SuppressWarnings("unchecked")
        SingleResponse<Resource> singleResponse = response.as(SingleResponse.class);
        Assert.assertNotNull(singleResponse.data());
        
        ResponseValidator.validateResponse(singleResponse);
    }
    
    @Test
    public void testGetSingleResourceNotFound() {
        int notFoundId = Integer.parseInt(ConfigManager.getInstance().getProperty("test.not.found.id"));
        
        Response response = restClient.get("/unknown/{resourceId}", notFoundId);
        ResponseValidator.validateStatusCode(response, HttpStatus.SC_NOT_FOUND);
    }
} 