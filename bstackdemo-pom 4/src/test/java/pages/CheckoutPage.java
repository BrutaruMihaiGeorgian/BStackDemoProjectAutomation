package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class CheckoutPage {
    // Variabilă pentru driverul WebDriver
    WebDriver driver;

    // Selectoarele (locatorii) pentru câmpurile din formular
    private By firstName = By.id("firstNameInput");
    private By lastName = By.id("lastNameInput");
    private By address = By.id("addressLine1Input");
    private By province = By.id("provinceInput");
    private By postalCode = By.id("postCodeInput");
    private By continueBtn = By.id("checkout-shipping-continue");

    // Constructorul clasei – primește driverul de la test
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    // Metodă care completează formularul de livrare
    public void fillShippingForm(String fname, String lname, String addr, String prov, String code) {
        // Așteaptă până când fiecare element apare și apoi introduce textul

        WebElement firstNameField = waitForElement(firstName);
        firstNameField.sendKeys(fname);

        WebElement lastNameField = waitForElement(lastName);
        lastNameField.sendKeys(lname);

        WebElement addressField = waitForElement(address);
        addressField.sendKeys(addr);

        WebElement provinceField = waitForElement(province);
        provinceField.sendKeys(prov);

        WebElement postalCodeField = waitForElement(postalCode);
        postalCodeField.sendKeys(code);
    }

    // Metodă care apasă pe butonul "Continue"
    public void continueToConfirmation() {
        WebElement continueButton = waitForElement(continueBtn);
        continueButton.click();
    }

    // Metodă auxiliară care așteaptă ca un element să apară în pagină (fără expresii lambda)
    private WebElement waitForElement(By locator) {
        // Creează un obiect FluentWait
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))        // timp maxim de așteptare: 10 secunde
                .pollingEvery(Duration.ofMillis(500))       // verifică la fiecare 500 milisecunde
                .ignoring(NoSuchElementException.class);    // ignoră eroarea dacă elementul nu este imediat disponibil

        // Așteaptă și returnează elementul folosind o clasă anonimă
        return wait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }
        });
    }
}