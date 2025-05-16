package com.api.automation.config;

import lombok.extern.log4j.Log4j2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Log4j2
public class ConfigManager {
    private static ConfigManager instance;
    private final Properties properties;

    private ConfigManager() {
        properties = new Properties();
        try (var fileInputStream = new FileInputStream("src/test/resources/config.properties")) {
            properties.load(fileInputStream);
            log.info("Configuration loaded successfully");
        } catch (IOException e) {
            log.error("""
                Failed to load configuration properties.
                Please ensure that config.properties exists in the correct location
                and has the proper permissions.
                """, e);
        }
    }

    public static synchronized ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public String getBaseUrl() {
        return getProperty("base.url");
    }
} 