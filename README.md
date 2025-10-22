# Test Automation Framework

This project is my **graduation project from the Information Technology Institute (ITI)**, developed after completing an intensive **5-month internship in Software Testing and Test Automation**.  

It is a **comprehensive automation framework** built using **Java, Selenium, TestNG, and Allure Reports**.  
The framework supports **UI,End-to-End testing**, designed with scalability, reusability, and maintainability in mind — following industry best practices such as the **Page Object Model (POM)** and **modular test design**.


## Features

- **Multi-browser Support**: Chrome, Firefox, and Edge
- **Page Object Model (POM)**: Maintainable and scalable test architecture
- **Data-Driven Testing**: JSON-based test data management
- **Allure Reporting**: Detailed test execution reports with screenshots
- **Custom Listeners**: TestNG listeners for enhanced test monitoring
- **Wait Management**: Intelligent wait strategies for stable test execution
- **Screenshot Capture**: Automatic screenshot capture on test failures
- **Logging**: Comprehensive logging with Log4j2

## Project Structure

```
src
├── main
│   ├── java
│   │   └── com
│   │       └── graduationproject
│   │           ├── FileUtils.java
│   │           ├── apis
│   │           │   ├── LoginApi.java
│   │           │   └── POJO.java
│   │           ├── drivers
│   │           │   ├── AbstractDriver.java
│   │           │   ├── Browser.java
│   │           │   ├── ChromeFactory.java
│   │           │   ├── EdgeFactory.java
│   │           │   ├── FirefoxFactory.java
│   │           │   ├── UIDriver.java
│   │           │   └── WebDriverProvider.java
│   │           ├── listeners
│   │           │   └── TestNGListeners.java
│   │           ├── media
│   │           │   └── ScreenshotsManager.java
│   │           ├── pages
│   │           │   ├── CartPage.java
│   │           │   ├── CheckoutPage.java
│   │           │   ├── E2E.java
│   │           │   ├── LoginPage.java
│   │           │   ├── ProductDetailsPage.java
│   │           │   ├── ProductsPage.java
│   │           │   ├── SignupPage.java
│   │           │   └── components
│   │           │       └── NavigationBarComponent.java
│   │           ├── utils
│   │           │   ├── Actions
│   │           │   │   ├── AlertActions.java
│   │           │   │   ├── BrowserActions.java
│   │           │   │   ├── ElementActions.java
│   │           │   │   └── FrameActions.java
│   │           │   ├── OSUtils.java
│   │           │   ├── TerminalUtils.java
│   │           │   ├── TimeManager.java
│   │           │   ├── WaitManager.java
│   │           │   ├── dataReader
│   │           │   │   ├── ExcelReader.java
│   │           │   │   ├── JsonReader.java
│   │           │   │   └── PropertyReader.java
│   │           │   ├── logs
│   │           │   │   └── LogsManager.java
│   │           │   └── report
│   │           │       ├── AllureAttachmentManager.java
│   │           │       ├── AllureBinaryManager.java
│   │           │       ├── AllureConstants.java
│   │           │       ├── AllureEnvironmentManager.java
│   │           │       └── AllureReportGenerator.java
│   │           └── validations
│   │               ├── BaseAssertion.java
│   │               ├── Validation.java
│   │               └── Verification.java
│   └── resources
│       ├── META-INF
│       │   └── services
│       │       └── org.testng.ITestNGListener
│       ├── allure.properties
│       ├── browser.properties
│       ├── environment.properties
│       ├── log4j2.properties
│       ├── seleniumGrid.properties
│       └── waits.properties
└── test
    ├── java
    │   └── com
    │       └── graduationproject
    │           └── tests
    │               ├── BaseTest.java
    │               └── ui
    │                   ├── CartTest.java
    │                   ├── CheckoutTest.java
    │                   ├── E2ETest.java
    │                   ├── LandingTest.java
    │                   ├── LoginTest.java
    │                   ├── ProductDetailsTest.java
    │                   ├── ProductsTest.java
    │                   └── SignupTest.java
    └── resources
        └── test-data
```

## Prerequisites

- Java JDK 21
- Maven 3.6+
- Chrome/Firefox/Edge browser installed

## Installation

1. Clone the repository:
```bash
git clone https://github.com/yourusername/your-repo-name.git
cd your-repo-name
```

2. Install dependencies:
```bash
mvn clean install
```

## Configuration

Configure test execution settings in `src/main/resources/`:

- `browser.properties`        - Browser configuration
- `environment.properties`    - Application URLs and environment settings
- `waits.properties`          - Timeout and wait configurations

## Running Tests

Execute all tests:
```bash
mvn clean test
```

Run specific test class:
```bash
mvn clean test -Dtest=LoginTest
```

Run with specific browser:
```bash
mvn clean test -Dbrowser=chrome
```

## Test Data

Test data is stored in JSON format under `src/test/resources/test-data/`. Each test module has its corresponding data file for data-driven testing.

## Reporting

After test execution, generate Allure report:

```bash
mvn allure:serve
```

Or generate HTML report:
```bash
mvn allure:report
```

Reports are generated in the `src/test-output` directory.

## Test Modules

- **Login Tests**: User authentication scenarios
- **Signup Tests**: User registration flows
- **Products Tests**: Product listing and filtering
- **Product Details Tests**: Individual product page interactions
- **Cart Tests**: Shopping cart operations
- **Checkout Tests**: Purchase completion flows
- **E2E Tests**: Complete user journey scenario

## Author

**Muhammad Hossam**

- GitHub: [@Muhammad-Hossam](https://github.com/Muhammad-Hossam)
- Project: [WolFix Store Test Automation](https://github.com/Muhammad-Hossam/WolFix-Store-Test-Automation-Project)


