# Projects_Automation_Testing2



# 🧪 BStackDemoProjectAutomation

> Automatizare end-to-end a site‑ului [bstackdemo.com](https://bstackdemo.com) folosind Selenium WebDriver, JUnit 5 și integrare BrowserStack.

## 📚 Descriere

Acest proiect demonstrează o automatizare completă a unei comenzi pe [bstackdemo.com] utilizând:

- Page Object Model (POM)
- Selenium WebDriver
- JUnit 5
- Integrare BrowserStack via `DriverFactory`

## 🧰 Tehnologii folosite

- Java 8+
- Maven
- Selenium WebDriver
- JUnit 5
- BrowserStack
- Page Object Model (POM)

---

## 🗂 Structura Proiectului

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

## ⚙️ Configurare inițială

1. **Clonează proiectul:**
```bash
git clone https://github.com/BrutaruMihaiGeorgian/BStackDemoProjectAutomation.git
cd BStackDemoProjectAutomation
```

2. **Setează variabilele de mediu pentru BrowserStack:**
```bash
export BROWSERSTACK_USERNAME=your_username
export BROWSERSTACK_ACCESS_KEY=your_access_key
```
> Alternativ, poți defini un fișier `config.properties` în `resources/` cu:
```properties
browser=chrome
```

3. **Build proiectul cu Maven:**
```bash
mvn clean compile
```

---

## 🚀 Rulare test

Pentru a rula testul complet de achiziție pe `bstackdemo.com`:

```bash
mvn test
```

Pentru a specifica un alt browser (suprascrie din linia de comandă):

```bash
mvn test -Dbrowser=firefox
```

---

## 📦 Test acoperit

Testul principal acoperă:

- Autentificare
- Selectare produs (iPhone 12)
- Checkout complet cu date fictive
- Confirmare mesaj & număr comandă
- Verificare preț final
- Revenire pe pagina principală
