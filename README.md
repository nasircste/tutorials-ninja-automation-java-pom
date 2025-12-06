# TutorialsNinja Test Automation (Selenium + Java, POM)

Professional-grade test automation suite for the TutorialsNinja demo site, focused on authentication and core e‑commerce flows. Built with Selenium WebDriver, Java, TestNG, and the Page Object Model (POM). Includes cross‑browser execution, Dockerized Selenium Grid, reporting, screenshots, and data-driven testing support.

- Repository: [nasircste/tutorials-ninja-automation-java-pom](https://github.com/nasircste/tutorials-ninja-automation-java-pom)
- Demo site: https://tutorialsninja.com/demo/

## Table of Contents
- [Key Highlights](#key-highlights)
- [Tech Stack](#tech-stack)
- [Repository Structure](#repository-structure)
- [What This Project Covers](#what-this-project-covers)
  - [User Registration](#user-registration)
  - [User Login](#user-login)
  - [Navigation & Basic E‑commerce Checks](#navigation--basic-e-commerce-checks)
  - [Test Reliability Features](#test-reliability-features)
- [How It Works](#how-it-works)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Configuration](#configuration)
- [Running Tests](#running-tests)
  - [Option 1: One‑click (Windows)](#option-1-one-click-windows)
  - [Option 2: Maven + TestNG Suite](#option-2-maven--testng-suite)
  - [Option 3: Selenium Grid (Docker)](#option-3-selenium-grid-docker)
- [Reports & Artifacts](#reports--artifacts)
- [Continuous Testing Ideas (Optional)](#continuous-testing-ideas-optional)
- [Coding Approach & Good Practices](#coding-approach--good-practices)
- [Example Scenarios (Conceptual)](#example-scenarios-conceptual)
  - [Registration](#registration)
  - [Login](#login)
  - [Navigation](#navigation)
- [Maintenance & Extensibility](#maintenance--extensibility)
- [Troubleshooting](#troubleshooting)
- [License](#license)

## Key Highlights
- Selenium WebDriver + Java with TestNG suite orchestration
- Page Object Model (POM) for maintainable, scalable tests
- Cross‑browser runs (Chrome/Firefox/Edge) via TestNG suites
- Selenium Grid via Docker Compose for distributed/parallel runs
- Centralized test data and environment configuration
- HTML reports and artifacts (reports, logs, screenshots)
- Simple one‑click run script for local execution (Windows)

## Tech Stack
- Language: Java
- Test Framework: TestNG
- Automation: Selenium WebDriver
- Build Tool: Maven (`pom.xml`)
- Grid/Parallel: Selenium Grid (Docker Compose)
- Reporting & Artifacts: TestNG HTML, custom `reports/`, `logs/`, `screenshots/`
- Patterns: Page Object Model (POM)
- IDE: Eclipse/IntelliJ project files present (`.project`, `.classpath`, `.settings/`)

## Repository Structure
```
.
├── pom.xml                     # Maven dependencies & plugins
├── master.xml                  # Aggregated TestNG suite
├── CrossBrowserTesting.xml     # TestNG suite for cross-browser runs
├── grouping.xml                # TestNG suite with TestNG groups
├── grid-docker.xml             # TestNG suite targeting Selenium Grid
├── docker-compose.yaml         # Selenium Grid infra (hub & nodes)
├── run.bat                     # One-click local execution (Windows)
├── src/                        # Source code (pages, base, tests, utils)
├── testData/                   # Externalized test data (e.g., CSV/JSON)
├── reports/                    # Test reports output
├── logs/                       # Execution logs
├── screenshots/                # Failure screenshots
├── test-output/                # Default TestNG report output
├── .project, .classpath, .settings/  # IDE metadata
└── testing.txt                 # Repo placeholder/info
```

## What This Project Covers
- User Registration
  - New account creation with valid data
  - Validation for existing email
  - Client- and server-side error messages
- User Login
  - Positive login with valid credentials
  - Negative login (invalid email/password combinations)
  - Locked/unknown user scenarios (if applicable)
- Navigation & Basic E‑commerce Checks
  - Home → Login/Registration flow navigation
  - Header/footer links smoke checks (optional)
- Test Reliability Features
  - Explicit waits and reusable wait utilities
  - Clean driver lifecycle management
  - Page objects encapsulate locators and actions
  - Data-driven testing from `testData/`

## How It Works
1. TestNG orchestrates execution through suite files:
   - `master.xml` runs the full suite end‑to‑end.
   - `CrossBrowserTesting.xml` configures the same tests against multiple browsers.
   - `grouping.xml` targets specific TestNG groups (e.g., `smoke`, `regression`, `auth`).
   - `grid-docker.xml` points tests to Selenium Grid when running in Docker.
2. Selenium WebDriver is initialized in a base layer (Driver Factory / Test Base).
3. Each page of the demo site has a dedicated Page Object class (POM) that:
   - Holds the locators and business actions (e.g., `LoginPage.login(email, password)`).
   - Provides clear separation of concerns and reusability.
4. Tests call page methods to perform actions, assert results, and capture artifacts.
5. Failures automatically capture screenshots and logs for fast debugging.
6. Reports are generated to `reports/` and `test-output/`.

## Getting Started

### Prerequisites
- Java 11+ (17 recommended)
- Maven 3.8+
- Chrome/Firefox/Edge installed (for local runs)
- Docker Desktop (for Grid runs)

### Installation
```bash
git clone https://github.com/nasircste/tutorials-ninja-automation-java-pom.git
cd tutorials-ninja-automation-java-pom
mvn -v
java -version
```

### Configuration
- Base URL: https://tutorialsninja.com/demo/
- Browser selection is controlled by suite parameters or environment/system properties.
- Test data resides under `testData/`.
- If you maintain a `config.properties`, set values like:
  ```
  baseUrl=https://tutorialsninja.com/demo/
  browser=chrome
  headless=false
  timeout=10
  ```

## Running Tests

### Option 1: One‑click (Windows)
```bash
run.bat
```
By default, this triggers a standard local execution (Chrome, non‑headless).

### Option 2: Maven + TestNG Suite
Run the master suite:
```bash
mvn clean test -DsuiteXmlFile=master.xml
```

Run cross‑browser suite:
```bash
mvn clean test -DsuiteXmlFile=CrossBrowserTesting.xml
```

Run with groups (e.g., smoke):
```bash
mvn clean test -DsuiteXmlFile=grouping.xml -Dgroups=smoke
```

### Option 3: Selenium Grid (Docker)
Start Selenium Grid:
```bash
docker compose up -d
```
Run tests pointing to Grid:
```bash
mvn clean test -DsuiteXmlFile=grid-docker.xml
```
Tear down Grid:
```bash
docker compose down
```

## Reports & Artifacts
- Default TestNG HTML report: `test-output/index.html`
- Custom report/artifact folder: `reports/`
- Logs: `logs/`
- Failure screenshots: `screenshots/`

To open default TestNG report:
- Navigate to `test-output` and open `index.html` in a browser.

## Continuous Testing Ideas (Optional)
- GitHub Actions CI: run `mvn clean test` on every push/PR
- Matrix build: run across multiple Java versions and browsers
- Publish artifacts (reports, screenshots) as workflow outputs

## Coding Approach & Good Practices
- Page Object Model (POM) with clear separation of pages vs tests
- Reusable utilities for waits, config, and common actions
- Stable selectors and explicit waits to reduce flakiness
- Parameterized runs via TestNG/Maven properties
- Consistent naming and package structure for readability
- Artifacts preserved for investigation (reports/logs/screenshots)

## Example Scenarios (Conceptual)
- Registration:
  - Navigate to Register page → Fill form with unique data → Submit → Verify success alert.
  - Attempt register with existing email → Verify error message.
- Login:
  - Navigate to Login page → Enter valid credentials → Verify account dashboard.
  - Enter wrong password → Verify warning message and remain on login.
- Navigation:
  - Validate header links (My Account, Login, Register) presence and clickability.
  - Ensure URLs and page titles match expectations.

## Maintenance & Extensibility
- Add new Page Objects under `src/main/java/.../pages/`
- Add tests under `src/test/java/.../tests/`
- Add more data sets in `testData/` for data-driven tests
- Extend suites (`master.xml`, `grouping.xml`) to include new modules
- Scale parallelism with Grid; add more nodes in `docker-compose.yaml`

## Troubleshooting
- Drivers/browsers mismatch:
  - Update WebDriverManager or driver binaries via Maven dependencies (if configured).
- Grid connection issues:
  - Ensure hub and nodes are healthy (`docker compose ps`).
  - Check that the suite points to the hub URL (e.g., `http://localhost:4444/wd/hub`).
- Flaky tests:
  - Review waits and page load strategies.
  - Stabilize locators and use retry logic if appropriate.

## License
- This project is for demonstration/portfolio purposes and uses the public demo site. No license specified.

---

If you are an HR or interviewer, this repository demonstrates hands‑on experience with:
- Designing a maintainable test framework (POM)
- Writing robust TestNG suites and grouping strategies
- Running locally and at scale on Selenium Grid with Docker
- Managing test data, logs, screenshots, and reports
- Applying best practices to reduce flakiness and improve reliability
