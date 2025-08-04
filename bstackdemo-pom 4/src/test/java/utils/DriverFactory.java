package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverFactory {

    public static WebDriver createDriver(String browser) {
        if (browser == null || browser.isEmpty()) {
            browser = "chrome"; // default
        }

        switch (browser.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();

            case "edge":
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();

            case "safari":
                // SafariDriver nu are nevoie de WebDriverManager
                // Asigură-te că "Allow Remote Automation" e activat în Safari -> Develop menu
                return new SafariDriver();

            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--incognito");
                return new ChromeDriver(options);
        }
    }
}