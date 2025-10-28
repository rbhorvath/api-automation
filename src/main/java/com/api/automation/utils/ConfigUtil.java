package com.api.automation.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


/**
 * Utility class for loading configuration properties from config.properties file.
 */
public class ConfigUtil {
    private static final String CONFIG_FILE = "src/main/resources/config.properties";
    private static Properties properties;

    /**
     * Loads properties from the configuration file at class initialization.
     */
    static {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE)) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    /**
     * Returns the property value for the given key.
     * @param key the property key
     * @return the property value, or null if not found
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
