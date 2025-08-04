package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.UUID;

public class DriverFactory {

    public static WebDriver createDriver(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();

            // Generăm un profil temporar unic pentru a evita erorile din CI
            String uniqueProfile = "/tmp/chrome-profile-" + UUID.randomUUID();
            options.addArguments("--user-data-dir=" + uniqueProfile);

            // Opțiuni recomandate pentru rulare în medii CI/CD (ex. GitHub Actions)
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--incognito");

            return new ChromeDriver(options);
        }

        throw new IllegalArgumentException("Browser not supported: " + browser);
    }
}
