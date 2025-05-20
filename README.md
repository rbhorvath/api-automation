# API Test Automation Framework

A minimal and efficient API test automation framework using Java, TestNG, and REST Assured. This framework is optimized for running in GitHub Actions.

## Features

- REST API testing using REST Assured
- Parallel test execution
- Minimal and maintainable code structure
- GitHub Actions integration
- TestNG for test execution and reporting

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

## Project Structure

```
src/
├── main/java/com/api/automation/
│   ├── client/
│   │   └── RestClient.java         # HTTP client for API requests
│   └── models/                     # API request/response models
└── test/
    ├── java/com/api/automation/tests/
    │   ├── BaseTest.java          # Base test class
    │   ├── UserApiTest.java       # User API tests
    │   ├── ResourceApiTest.java   # Resource API tests
    │   ├── AuthApiTest.java       # Authentication tests
    │   └── DelayedResponseTest.java # Delayed response tests
    └── resources/
        ├── config.properties       # Test configuration
        └── testng.xml             # TestNG configuration
```

## Test Categories

1. **User API Tests**
   - List users
   - Get single user
   - Create user
   - Update user
   - Delete user

2. **Resource API Tests**
   - List resources
   - Get single resource
   - Get non-existent resource

3. **Auth API Tests**
   - Successful login
   - Unsuccessful login
   - Successful register
   - Unsuccessful register

4. **Delayed Response Tests**
   - Test delayed user list

## Running Tests

### Local Execution

```bash
mvn clean test
```

### GitHub Actions

Tests run automatically on:
- Push to main/master branch
- Pull requests to main/master branch
- Daily at 6 AM Brazil time (UTC-3)

## Configuration

The framework uses minimal configuration in `src/test/resources/config.properties`:

```properties
# API Key for reqres.in (signup at https://reqres.in/signup)
api.key=your_api_key_here 
# Example id (if still used, or remove if obsolete)
# id=2 
```

Make sure to replace `your_api_key_here` with your actual API key from reqres.in. 
For running in GitHub Actions, it's recommended to store the API key as a repository secret (e.g., `REQRES_API_KEY`) and pass it to your tests as an environment variable. You would then modify `RestClient.java` to read this environment variable.

## Dependencies

- REST Assured 5.3.2
- TestNG 7.8.0
- Jackson 2.15.2
- Lombok 1.18.30

## GitHub Actions Workflow

The framework includes a GitHub Actions workflow (`.github/workflows/api-tests.yml`) that:
- Sets up JDK 17
- Caches Maven dependencies
- Runs tests in parallel
- Publishes test results
- Uploads test reports as artifacts

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request 