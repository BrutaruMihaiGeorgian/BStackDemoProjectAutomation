
# 🧪 BStackDemoProjectAutomation

> End-to-end automation of the [bstackdemo.com](https://bstackdemo.com) website using Selenium WebDriver, JUnit 5, and BrowserStack integration.

## 📚 Description

This project demonstrates a complete automation flow for placing an order on [bstackdemo.com] using:

- Page Object Model (POM)
- Selenium WebDriver
- JUnit 5
- BrowserStack integration via `DriverFactory`

## 🧰 Technologies Used

- Java 8+
- Maven
- Selenium WebDriver
- JUnit 5
- BrowserStack
- Page Object Model (POM)

---

## 🗂 Project Structure

```
src/
├── main/
│   └── java/
│       └── utils/
│           ├── DriverFactory.java
│           └── WaitUtils.java
├── test/
│   └── java/
│       └── tests/
│           └── BStackDemoTest.java
│       └── pages/
│           ├── HomePage.java
│           ├── LoginPage.java
│           ├── ProductPage.java
│           ├── CheckoutPage.java
│           └── ConfirmationPage.java
resources/
└── config.properties
```

---

## ⚙️ Initial Setup

1. **Clone the repository:**
```bash
git clone https://github.com/BrutaruMihaiGeorgian/BStackDemoProjectAutomation.git
cd BStackDemoProjectAutomation
```

2. **Set BrowserStack environment variables:**
```bash
export BROWSERSTACK_USERNAME=your_username
export BROWSERSTACK_ACCESS_KEY=your_access_key
```
> Alternatively, define a `config.properties` file in `resources/` with:
```properties
browser=chrome
```

3. **Build the project using Maven:**
```bash
mvn clean compile
```

---

## 🚀 Running Tests

To run the full purchase flow test on `bstackdemo.com`:

```bash
mvn test
```

To specify a different browser (override from command line):

```bash
mvn test -Dbrowser=firefox
```

---

## 📦 Covered Test

The main test covers:

- Login
- Product selection (iPhone 12)
- Complete checkout with sample data
- Confirmation message & order number validation
- Final price verification
- Return to homepage
