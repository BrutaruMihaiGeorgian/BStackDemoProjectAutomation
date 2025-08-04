package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class HomePage {
    WebDriver driver;

    // Selector pentru butonul "Sign In"
    private By signInBtn = By.id("signin");

    // Constructor – primește driverul și îl reține pentru folosire în metode
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Metodă care apasă pe butonul de autentificare
    public void clickSignIn() {
        WebElement signInButton = waitForElement(signInBtn);
        signInButton.click();
    }

    // Metodă care așteaptă ca un element să apară în pagină (fără expresie lambda)
    private WebElement waitForElement(By locator) {
        // Se creează un obiect FluentWait pentru așteptare controlată
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))        // timp maxim de așteptare: 10 secunde
                .pollingEvery(Duration.ofMillis(500))       // verifică la fiecare 500ms
                .ignoring(NoSuchElementException.class);    // ignoră eroarea dacă elementul nu e găsit imediat

        // Se folosește o funcție explicită care caută elementul și îl returnează
        return wait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }
        });
    }
}