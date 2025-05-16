package com.api.automation.tests;

import com.api.automation.client.RestClient;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.BeforeClass;

@Log4j2
public abstract class BaseTest {
    protected RestClient restClient;
    
    @BeforeClass
    public void setup() {
        log.info("Initializing test setup");
        restClient = new RestClient();
    }
} 