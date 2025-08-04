package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class LoginPage {
    WebDriver driver;

    // Selectori pentru elementele din formularul de autentificare
    private By usernameDropdown = By.xpath("//*[@id='username']/div/div[1]");
    private By userOption = By.xpath("//div[text()='demouser']");
    private By passwordDropdown = By.xpath("//*[@id='password']/div/div[1]");
    private By passwordOption = By.xpath("//div[text()='testingisfun99']");
    private By loginBtn = By.id("login-btn");

    // Constructorul clasei - primește driverul de la test
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Metodă care efectuează procesul de autentificare
    public void login() {
        // Așteaptă și face click pe fiecare element în ordine

        WebElement usernameDropdownElement = waitForElement(usernameDropdown);
        usernameDropdownElement.click();

        WebElement userOptionElement = waitForElement(userOption);
        userOptionElement.click();

        WebElement passwordDropdownElement = waitForElement(passwordDropdown);
        passwordDropdownElement.click();

        WebElement passwordOptionElement = waitForElement(passwordOption);
        passwordOptionElement.click();

        WebElement loginButton = waitForElement(loginBtn);
        loginButton.click();
    }

    // Metodă auxiliară care așteaptă fluent până când un element este vizibil și returnează acel element
    private WebElement waitForElement(By locator) {
        // Creează un obiect FluentWait care așteaptă până la 10 secunde, verificând la fiecare 500ms
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))        // maxim 10 secunde de așteptare
                .pollingEvery(Duration.ofMillis(500))       // verificare la fiecare jumătate de secundă
                .ignoring(NoSuchElementException.class);    // ignoră eroarea dacă elementul nu e găsit imediat

        // Așteaptă până când elementul devine disponibil, fără expresie lambda
        return wait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }
        });
    }
}