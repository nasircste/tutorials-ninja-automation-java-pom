# Tutorials Ninja Automation (Java + Selenium + POM)

A professional-grade Test Automation Framework for the Tutorials Ninja demo application, built with Java, Selenium WebDriver, TestNG, and the Page Object Model (POM) design pattern. The framework supports cross-browser execution, grouping and suite management via TestNG XMLs, Selenium Grid (local and Dockerized), reporting, screenshots, and logs.
- **Purpose:** To automate testing of web applications.  
- **Why it was created:** To achieve faster, repeatable, and maintenance-friendly testing compared to manual testing.   

## Table of Contents
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)

- [Running Tests](#running-tests)
- [Cross-Browser Testing](#cross-browser-testing)
- [Selenium Grid with Docker](#selenium-grid-with-docker)
- [Data, Logs, Screenshots, and Reports](#data-logs-screenshots-and-reports)
- [Test Suite Files](#test-suite-files)
- [Best Practices](#best-practices)
- [Troubleshooting](#troubleshooting)
- [Contributing](#contributing)
- [License](#license)

---

## Features
- Page Object Model (POM) structure for clean, maintainable tests
- TestNG for test orchestration (suites, groups, parallelism)
- Cross-browser execution (Chrome, Firefox, Edge, configured via TestNG XML)
- Selenium Grid support (local hub/node and Dockerized grid)
- Centralized test data directory for data-driven testing
- Automatic screenshots for failures
- Logs and HTML reports output after execution
- Simple one-click/run script via `run.bat`

## Tech Stack
- Java (JDK 8+ recommended)
- Selenium WebDriver
- TestNG
- Maven (build, dependency management)
- Docker (for Selenium Grid via docker-compose)
- Optional: Allure or Extent Reports (depending on project setup)

## Project Structure
High-level directories and files:

- `.classpath`, `.project`, `.settings/` — Eclipse project configuration
- `pom.xml` — Maven dependencies and build configuration
- `src/` — Source code (page objects, test classes, utilities)
- `testData/` — Input datasets for data-driven tests
- `logs/` — Runtime logs generated during test runs
- `screenshots/` — Captured images (e.g., on test failure)
- `reports/` — Test execution reports
- `test-output/` — TestNG output (HTML reports, etc.)
- `master.xml` — Main TestNG suite file
- `grouping.xml` — Group-based TestNG execution file
- `CrossBrowserTesting.xml` — Cross-browser TestNG suite
- `grid-docker.xml` — TestNG suite configured for Selenium Grid (Docker)
- `docker-compose.yaml` — Selenium Grid setup via Docker
- `run.bat` — Convenience script to run tests locally
- `grid-docker.xml`, `CrossBrowserTesting.xml`, `grouping.xml`, `master.xml` — Different suites for varied execution strategies

## Getting Started

### Prerequisites
- Java JDK installed and `JAVA_HOME` configured
- Maven installed (`mvn -v` should work)
- A modern web browser (Chrome/Firefox/Edge)
- Docker Desktop (optional, for Grid)
- IDE (IntelliJ IDEA or Eclipse)

### Install Dependencies
In the project root:
```bash
mvn clean install -DskipTests
```

## Running Tests

### Option 1: Using the Batch Script
Windows:
```bash
run.bat
```

### Option 2: Using Maven + TestNG Suites
Run the main suite:
```bash
mvn clean test -DsuiteXmlFile=master.xml
```

Run group-based suite:
```bash
mvn clean test -DsuiteXmlFile=grouping.xml
```

## Cross-Browser Testing
Execute cross-browser suite (parallel or sequential as defined in the XML):
```bash
mvn clean test -DsuiteXmlFile=CrossBrowserTesting.xml
```
You can parameterize browser via TestNG parameters or system properties (e.g., `-Dbrowser=chrome`), depending on how the framework is wired in `pom.xml` and the test initialization code.

## Selenium Grid with Docker

### Start Selenium Grid via Docker Compose
From the project root:
```bash
docker compose up -d
```
This brings up the Selenium Hub and browser nodes as defined in `docker-compose.yaml`.

### Run Tests on the Grid
```bash
mvn clean test -DsuiteXmlFile=grid-docker.xml
```
Ensure your TestNG suite and WebDriver setup point to the Grid hub URL (e.g., `http://localhost:4444/wd/hub`).

## Data, Logs, Screenshots, and Reports

- `testData/` — Store CSV/JSON/Excel files for data-driven tests. Reference them in your tests via utility loaders.
- `logs/` — Runtime logs for debugging; cleared/recreated per run as configured.
- `screenshots/` — Failure screenshots saved with test names and timestamps.
- `reports/` and `test-output/` — TestNG HTML reports and any extended reporting artifacts.

After execution, open the TestNG report:
- `test-output/index.html`

## Test Suite Files

- `master.xml` — Primary suite covering core flows (login, search, cart, checkout, etc.)
- `grouping.xml` — Enables execution by TestNG group annotations (e.g., `smoke`, `regression`)
- `CrossBrowserTesting.xml` — Runs same tests across multiple browsers
- `grid-docker.xml` — Routes tests to Selenium Grid (useful for parallel execution at scale)


---

### Contact
Maintainer: nasircste  
GitHub: [nasircste](https://github.com/nasircste)
