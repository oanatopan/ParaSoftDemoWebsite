# **🏦 ParaBank Automation Testing Project**

** *** **

## **🌟 Overview**
This repository showcases a comprehensive and complex testing project for the **ParaBank** online banking application. The project integrates **100+ manual test cases** with a custom-built **Automation Framework** using **Selenium & Java**, ensuring the reliability, security, and performance of critical banking operations.

** // **

---

## **🏗️ Project Structure**

** *** **

### **1. Manual Testing Documentation (`/docs`)**
* **`TestPlan.md`**: Strategic approach, test objectives, risk analysis, and exit criteria.
* **`01_User_Authentication.md`**: Registration flows, credential security, and session management.
* **`05_Field_Validation.md`**: **Negative Testing**, Sanitization, and Boundary Value Analysis (BVA).
* **`Bugs.md`**: Detailed defect log correlated with automated scripts and Test Case IDs.
* **`07_Cross_Browser_UI.md`**: Visual integrity and DOM stability verification across different browsers.

** // **

### **2. Automation Framework (`/src/test/java`)**
Built with **Java 17** and **Selenium WebDriver**, following the **Page Object Model (POM)** design pattern for maximum maintainability:



* **`helpMethods`**: Reusable utility classes for element manipulation, JS executors, and **Explicit Waits**.
* **`pages`**: Page Object classes encapsulating WebElements via **`@FindBy`** and business logic methods.
* **`shareData`**: WebDriver lifecycle management, Hooks, and cross-browser setup.
* **`tests`**: Automated test scripts covering Registration, Login, Fund Transfers, and Loan Requests.

** // **

---

## **🛠️ Technology Stack**

** *** **

| Category | Tool / Technology |
| :--- | :--- |
| **Language** | Java 17 |
| **Automation Library** | Selenium WebDriver |
| **Test Runner** | TestNG |
| **Build Tool** | Maven |
| **Management** | Qase.io |
| **Design Pattern** | Page Object Model (POM) |

** // **

---

## **🚀 Execution & Reporting**

** *** **

1. **Prerequisites:**
    * **JDK 17** or higher installed.
    * **Maven** installed and configured in system PATH.
    * **Chrome** and **Firefox** browsers installed for cross-browser testing.

2. **Clone and Run:**
   ```bash
   git clone [https://github.com/oanatopan/ParaSoftDemoWebsite.git](https://github.com/oanatopan/ParaSoftDemoWebsite.git)
   cd ParaSoftDemoWebsite
   mvn clean test