# Automation Test Framework (Web UI + API)

## Tech Stack
- Java 17
- Gradle
- Selenium
- Cucumber (Gherkin)
- JUnit
- WebDriverManager
- GitHub Actions (CI/CD)

## Test Type
- Web UI
- API 

## Target Website
https://www.saucedemo.com/

## Test Scenarios WeB UI
- Login (valid & invalid)
- Add backpack to cart
- Filter product (Price & Name)
- Logout
- End to end checkout

## Authentication
Header required:
- app-id: 63a804408eb0cb069b57e43a

## Endpoint API
- Get User by ID
- Create User
- Update User
- Delete User
- Get List of Tags
- Get user with invalid ID

## How to Run Web Test / Api Test
- ./gradlew webTest
- ./gradlew apiTest

## Reports
HTML : reports/web-cucumber-report.html
JSON : reports/web-cucumber-report.json

