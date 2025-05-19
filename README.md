# API Automation Framework

This project is a sample **API automation framework** built with **Java**, **RestAssured**, **Cucumber (BDD)**, **TestNG**, **Maven**, and **ExtentReports**.  
It demonstrates best practices for REST API testing, including scenario-driven development, data sharing between steps, robust assertions, and detailed reporting.

---

## Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [How to Run Tests](#how-to-run-tests)
- [Test Reports](#test-reports)
- [Configuration](#configuration)
- [Adding or Editing Tests](#adding-or-editing-tests)
- [Troubleshooting](#troubleshooting)
- [Credits](#credits)

---

## Features

- BDD-style API test scenarios using Cucumber
- REST API calls using RestAssured
- Data sharing between steps via ScenarioContext
- Robust assertions with JSONPath
- Edge case and negative scenario testing
- Clean code practices and hooks for setup/cleanup
- Beautiful HTML reports with ExtentReports

---

## Tech Stack

- **Java 11+**
- **Maven**
- **RestAssured**
- **Cucumber (Gherkin)**
- **TestNG**
- **ExtentReports**

---

## Project Structure

api-automation-framework/
├── src/
│ ├── main/java/com/api/
│ │ ├── models/ # POJO classes for API payloads
│ │ └── utils/ # Utilities (APIUtils, ScenarioContext)
│ └── test/java/com/api/
│ ├── stepdefinitions/ # Step definition classes
│ ├── runners/ # Test runner classes
│ └── Hooks.java # Global hooks (setup/teardown)
│ └── test/resources/
│ ├── features/ # Cucumber feature files
│ └── extent.properties# ExtentReports configuration
├── pom.xml
└── README.md

text

---

## How to Run Tests

### Pre-requisites

- Java 11 or higher installed
- Maven installed

### Run All Tests

- From the project root, run:
- mvn clean test




