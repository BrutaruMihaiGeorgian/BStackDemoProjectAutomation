package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class ConfirmationPage {
    // Variabilă pentru driver
    WebDriver driver;

    // Selectori pentru elementele de pe pagina de confirmare
    private By confirmationMsg = By.id("confirmation-message");
    private By orderText = By.xpath("//*[@id='checkout-app']/div/div/div/ol/li/div/div/div[2]");
    private By totalPrice = By.xpath("//*[@id='checkout-app']/div/div/aside/article/section[2]/div/div/span[2]/span");
    private By backBtn = By.xpath("//*[@id='checkout-app']/div/div/div/div/a/button");

    // Constructor – primește driverul și îl reține pentru a fi folosit în metodele de mai jos
    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }

    // Metodă care returnează mesajul de confirmare
    public String getConfirmationMessage() {
        WebElement messageElement = waitForElement(confirmationMsg);
        return messageElement.getText();
    }

    // Metodă care returnează textul care conține numărul comenzii
    public String getOrderText() {
        WebElement orderTextElement = waitForElement(orderText);
        return orderTextElement.getText();
    }

    // Metodă care returnează prețul total din dreapta (ex: "$799.00")
    public String getTotalPrice() {
        WebElement priceElement = waitForElement(totalPrice);
        return priceElement.getText();
    }

    // Metodă care apasă pe butonul de întoarcere înapoi pe homepage
    public void goBackHome() {
        WebElement backButton = waitForElement(backBtn);
        backButton.click();
    }

    // Metodă care așteaptă fluent până când un element este disponibil în pagină
    private WebElement waitForElement(By locator) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);

        return wait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }
        });
    }
}