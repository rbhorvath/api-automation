package com.api.automation;

/**
 * Simple test class to verify Java 17 features
 */
public class Java17Test {
    
    // Test record
    public record TestData(String name, int value) {}
    
    // Test enhanced switch expression
    public static String testSwitch(String input) {
        return switch(input) {
            case "hello" -> "world";
            case "test" -> "success";
            default -> "unknown";
        };
    }
    
    public static void main(String[] args) {
        // Test record
        TestData data = new TestData("Test", 17);
        System.out.println("Record test: " + data.name() + ", " + data.value());
        
        // Test switch
        System.out.println("Switch test: " + testSwitch("test"));
        
        // Test text block
        String textBlock = """
            This is a Java 17 text block.
            It preserves formatting
            and is more readable.
            """;
        System.out.println("Text block test: \n" + textBlock);
        
        System.out.println("Java 17 features are working correctly!");
    }
} 