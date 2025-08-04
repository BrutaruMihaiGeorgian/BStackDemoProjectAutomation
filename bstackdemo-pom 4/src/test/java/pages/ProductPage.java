package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class ProductPage {
    WebDriver driver;

    // Selectori pentru elementele relevante de pe pagina produsului
    private By iphone = By.xpath("//*[@id='1']/div[4]");
    private By iphoneTitle = By.xpath("//p[text()='iPhone 12']");
    private By buyButton = By.className("buy-btn");

    // Constructorul clasei – primește driverul
    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    // Metodă care selectează produsul (iPhone)
    public void selectProduct() {
        WebElement iphoneElement = waitForElement(iphone);
        iphoneElement.click();
    }

    // Metodă care verifică dacă titlul produsului (iPhone 12) este vizibil
    public boolean isProductVisible() {
        WebElement titleElement = waitForElement(iphoneTitle);
        return titleElement.isDisplayed();
    }

    // Metodă care apasă pe butonul "Buy"
    public void clickBuy() {
        WebElement buyBtnElement = waitForElement(buyButton);
        buyBtnElement.click();
    }

    // Metodă auxiliară care așteaptă ca un element să fie vizibil în pagină și îl returnează
    private WebElement waitForElement(By locator) {
        // Se creează un FluentWait care așteaptă până la 10 secunde și verifică la fiecare 500ms
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))         // timp maxim de așteptare
                .pollingEvery(Duration.ofMillis(500))        // verificare periodică
                .ignoring(NoSuchElementException.class);     // ignoră dacă elementul nu e imediat disponibil

        // Așteaptă și returnează elementul căutat, folosind o clasă anonimă (fără lambda)
        return wait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }
        });
    }
}