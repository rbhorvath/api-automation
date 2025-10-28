src/
src/

# API Test Automation Framework

This is an API test automation project using Java 25, Maven, TestNG, and REST Assured.

## Prerequisites

- Java 25 or higher
- Maven 3.6 or higher

## Project Structure

```
src/
  main/java/com/api/automation/
    client/
    models/
    utils/
  main/resources/
    config.properties
  test/java/com/api/automation/tests/
  test/resources/
    user_test_data.json
    testng.xml
.github/workflows/ci.yml
```

## How to Run

1. Install Java and Maven
2. Run tests with:
   ```
   mvn clean test
   ```

## Description

This project contains basic API tests for reqres.in endpoints. All tests are in the `src/test/java/com/api/automation/tests/` folder.

## CI/CD

- Automated build, test, and report upload via GitHub Actions (`.github/workflows/ci.yml`)

## Code Quality

- Checkstyle and SpotBugs can be added for static analysis and code style enforcement

## Documentation

- Main classes and methods include Javadoc comments for clarity

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
