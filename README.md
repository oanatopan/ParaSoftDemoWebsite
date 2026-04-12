# 🏦 ParaBank UI Automation Project
UI Test Automation Framework built with **Selenium WebDriver, Java, TestNG and Page Object Model**.


![Java](https://img.shields.io/badge/Java-17-007396?logo=java)
![Selenium](https://img.shields.io/badge/Selenium-WebDriver-43B02A?logo=selenium)
![TestNG](https://img.shields.io/badge/TestNG-Test%20Framework-red)
![Maven](https://img.shields.io/badge/Maven-Build-orange?logo=apachemaven)
![Allure](https://img.shields.io/badge/Allure-Test%20Reporting-FF6A00)
![CI](https://img.shields.io/badge/CI-GitHub%20Actions-blue?logo=githubactions)
![Automation](https://img.shields.io/badge/Test-Automation-success)

## 📑 Table of Contents

- [Project Overview](#project-overview)
- [Tech Stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Automated Test Scenarios](#automated-test-scenarios)
- [Application Under Test](#application-under-test)
- [Framework Architecture](#framework-architecture)
- [Project Structure](#project-structure)
- [Automation Design Decisions](#automation-design-decisions)
- [Automation Benefits](#automation-benefits)
- [Test Reporting (Allure)](#test-reporting-allure)
- [Key Skills Demonstrated](#key-skills-demonstrated)
- [Contact](#contact--connect)
  
## 📌 Project Overview
This project represents an **end-to-end UI automation framework** developed for the ParaBank demo banking application.
The project demonstrates practical **QA automation engineering skills** by automating realistic banking workflows such as user registration, account management, fund transfers, and transaction validation.

## 🛠️ Tech Stack

| Technology | Purpose |
| :--- | :--- |
| **Java** | Programming language |
| **Selenium WebDriver** | UI automation engine |
| **TestNG** | Test execution framework & Parallel execution |
| **Maven** | Build and dependency management |
| **Page Object Model** | Design pattern for UI abstraction |
| **Jackson** | JSON test data management |
| **Git / GitHub** | Version control |


## 📋 Prerequisites

Before running the tests, ensure you have the following installed:
* **Java SDK 17** or higher
* **Apache Maven** (3.6.0+)
* **Google Chrome** (The framework is configured for Chrome by default)
* **Allure Commandline** (to view the reports locally)


---

## 🧪 Automated Test Scenarios

The automation suite contains **11 end-to-end UI tests** that simulate real banking workflows.

| Test Case | Business Scenario | Description | Validation |
|---|---|---|---|
| RegisterTest | User Registration | Creates a new banking user account | Successful account creation message |
| LoginValidTest | User Authentication | Logs in with valid credentials | User dashboard is displayed |
| LoginInvalidTest | Authentication Validation | Attempts login with invalid credentials | Error message is displayed |
| OpenAccountTest | Account Creation | Opens a new bank account | New account appears in Accounts Overview |
| TransferFundsTest | Money Transfer | Transfers funds between accounts | Transfer confirmation message |
| BillPayTest | Payment Processing | Sends a payment to a payee | Bill payment confirmation |
| RequestLoanTest | Loan Request | Requests a loan using available account balance | Loan approval or rejection message |
| FindTransactionsTest | Transaction Search | Searches transactions using filters | Filtered transaction results |
| AccountHistoryTest | Transaction Validation | Opens account activity page | Transaction history is displayed |
| AccountsOverviewTest | Account Overview | Displays all user accounts | Account table is visible |
| LogOutTest | Session Management | Logs out from the application | Login form is displayed again |

## 🌐 Application Under Test

The automated tests target the **ParaBank demo banking application**:

https://parabank.parasoft.com/parabank

The application simulates a full online banking platform that allows users to:

- register accounts
- open new bank accounts
- transfer funds
- pay bills
- request loans
- search and validate transactions


## 🧱 Framework Architecture

The automation framework follows a **layered architecture based on the Page Object Model (POM)**.

## 📂 Project Structure

```text
ParaSoftDemoWebsite
│
├── .github
│   └── workflows
│        ├── customPipeline.yml
│        ├── nightlyPipeline.yml
│        ├── regressionPipeline.yml
│
├── allure
├── allure-results
│
├── src
│   └── test
│        ├── java
│        │   ├── tests
│        │   ├── pages
│        │   ├── helpMethods
│        │   ├── shareData
│        │   ├── modelObject
│        │   └── utils
│        │
│        └── resources
│
├── pom.xml
├── testng.xml
```


## ⚙️ Automation Design Decisions

The automation framework was designed following several key engineering principles.

### Page Object Model

The **Page Object Model (POM)** was implemented to separate test logic from UI interactions.

Benefits:

- improved test readability
- reusable page actions
- easier maintenance when UI changes occur

### Reusable Helper Methods

Common UI interactions are extracted into helper classes.

Benefits:

- reduced code duplication
- simplified test scenarios
- centralized UI interaction logic

### Externalized Test Data

Test data is stored in **JSON files** and mapped into model classes.

Benefits:

- separation between test logic and test data
- easier modification of test inputs
- scalable test design
## 📊 Test Reporting (Allure)

The framework integrates **Allure Reports** to generate interactive test execution reports.

Allure provides:

- detailed step-by-step test execution
- pass / fail visualization
- execution timeline
- historical test results
---
  
## 📈 Automation Benefits
The automation framework helps validate critical banking workflows and ensures application stability.
Key benefits include:

- faster regression testing
- reduced manual testing effort
- improved test repeatability
- early detection of UI defects
- better test coverage for core banking flows

## 🧠 Key Skills Demonstrated

This project demonstrates practical QA Automation engineering skills including:

- Selenium WebDriver automation
- TestNG test execution
- Page Object Model architecture
- reusable automation utilities
- test data modeling with JSON
- parallel test execution
- structured logging and assertions
- Allure reporting integration


## 📫 Contact & Connect
* **Author:** Oana Topan
* **Role:** QA Automation Engineer
* **LinkedIn:** https://www.linkedin.com/in/oanatopan/

