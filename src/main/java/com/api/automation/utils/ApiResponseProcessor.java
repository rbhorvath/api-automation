package com.api.automation.utils;

import com.api.automation.models.ListResponse;
import com.api.automation.models.User;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Utility class to demonstrate Java 17 stream API enhancements
 */
public class ApiResponseProcessor {

    /**
     * Extracts names from a user list response using modern Stream operations
     * @param response The list response containing users
     * @return List of user names
     */
    public static List<String> extractNames(ListResponse<User> response) {
        return response.data().stream()
                .filter(Objects::nonNull)
                .map(user -> user.first_name() + " " + user.last_name())
                .collect(Collectors.toList());
    }
    
    /**
     * Demonstrates Stream.toList() introduced in Java 16
     * (shorter than Collectors.toList())
     */
    public static List<String> extractEmails(ListResponse<User> response) {
        return response.data().stream()
                .filter(Objects::nonNull)
                .map(User::email)
                .toList(); // Java 16+ feature
    }
    
    /**
     * Demonstrates Stream.mapMulti()
     */
    public static List<String> extractNameParts(ListResponse<User> response) {
        if (response == null || response.data() == null) {
            return Collections.emptyList();
        }
        
        return response.data().stream()
                .<String>mapMulti((user, consumer) -> {
                    if (user != null) {
                        consumer.accept(user.first_name());
                        consumer.accept(user.last_name());
                    }
                })
                .toList();
    }
} 