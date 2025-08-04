package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class WaitUtils {

    // Metodă statică ce așteaptă ca un element (identificat prin ID) să conțină text
    public static String waitForText(WebDriver driver, String id) {
        // Se creează un obiect FluentWait pentru așteptare personalizată
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))        // timp maxim de așteptare: 10 secunde
                .pollingEvery(Duration.ofMillis(500))       // verifică din 500 în 500 milisecunde
                .ignoring(NoSuchElementException.class);    // ignoră dacă elementul nu e găsit imediat

        // Așteaptă până când elementul există și conține text (nu este gol)
        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                // Caută elementul după ID
                WebElement el = driver.findElement(By.id(id));

                // Dacă elementul are text (e vizibil/valid), îl returnează
                if (!el.getText().isEmpty()) {
                    return el;
                }

                // Dacă nu are încă text, continuă să aștepte
                return null;
            }
        });

        // După ce elementul este găsit și conține text, se returnează acel text
        return element.getText();
    }
}