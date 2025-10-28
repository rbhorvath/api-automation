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
.github/workflows/
  api-tests.yml
  ci.yml
```

## How to Run

1. Install Java and Maven
2. Run tests with:
   ```
   mvn clean test
   ```

## Description

This project contains basic API tests for reqres.in endpoints. All tests are in the `src/test/java/com/api/automation/tests/` folder.

## Code Quality

- Javadoc comments are provided for main classes and methods
- SLF4J logging is used for diagnostics
- Input validation is implemented in model builders
- Consistent code style is enforced (see Checkstyle below)

## Documentation

- Main classes and methods include Javadoc comments for clarity

### Local Execution

```bash
mvn clean test
```

## Configuration

The framework uses minimal configuration in `src/test/resources/config.properties`:

```properties
# API Key for reqres.in (signup at https://reqres.in/signup)
api.key=your_api_key_here
# Example id (if still used, or remove if obsolete)
# id=2
```

Make sure to replace `your_api_key_here` with your actual API key from reqres.in.
For running in GitHub Actions, it's recommended to store the API key as a repository secret (e.g., `REQRES_API_KEY`) and pass it to your tests as an environment variable. The framework reads this variable automatically.

## Dependencies

- REST Assured 5.3.2
- TestNG 7.8.0
- Jackson 2.15.2
- Lombok 1.18.30
