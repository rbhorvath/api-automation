# API Test Automation Framework

A Java-based framework for API test automation using REST Assured and TestNG.

## Project Structure

```
api-automation/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── api/
│   │               └── automation/
│   │                   ├── client/       # REST API client
│   │                   ├── config/       # Configuration management
│   │                   ├── models/       # Data models/POJOs
│   │                   └── utils/        # Utility classes
│   └── test/
│       ├── java/
│       │   └── com/
│       │       └── api/
│       │           └── automation/
│       │               └── tests/        # Test classes
│       └── resources/
│           ├── config.properties        # Configuration properties
│           ├── log4j2.xml              # Logging configuration
│           └── testng.xml              # TestNG configuration
├── logs/                               # Test execution logs
├── pom.xml                            # Maven project file
└── README.md                          # Project documentation
```

## Features

- REST Assured for API testing
- TestNG for test execution and reporting
- Log4j2 for logging
- Jackson for JSON processing
- Lombok to reduce boilerplate code
- Configuration management
- Modular and extensible design

## Prerequisites

- Java 11 or higher
- Maven 3.6 or higher

## Running Tests

To run all tests:

```bash
mvn clean test
```

To run a specific test class:

```bash
mvn clean test -Dtest=UserApiTest
```

## Reporting

TestNG reports are generated in the `target/surefire-reports` directory after test execution.

## Configuration

Edit the `src/test/resources/config.properties` file to configure the API base URL and other settings. 