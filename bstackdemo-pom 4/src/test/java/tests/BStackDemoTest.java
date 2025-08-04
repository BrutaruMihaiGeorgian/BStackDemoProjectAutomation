package tests;

// Importuri pentru JUnit (testare)
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

// Importuri pentru clasele Page Object
import pages.*;

// Importuri pentru utilitare
import utils.DriverFactory;
import utils.WaitUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

// Specifică faptul că se folosește o singură instanță a clasei pentru toate metodele de test
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BStackDemoTest {

    // Variabilă pentru driverul WebDriver (controlul browserului)
    WebDriver driver;

    // Obiect pentru a încărca setările din fișierul de configurare
    Properties props;

    // Obiecte pentru paginile aplicației (Page Object Model)
    HomePage homePage;
    LoginPage loginPage;
    ProductPage productPage;
    CheckoutPage checkoutPage;
    ConfirmationPage confirmationPage;

    // Se execută o singură dată înainte de toate testele – încarcă fișierul config.properties
    @BeforeAll
    void loadConfig() throws IOException {
        props = new Properties();
        InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");
        props.load(input); // încarcă proprietățile în memorie (ex: browser=chrome)
    }

    // Se execută înainte de fiecare test – deschide browserul și inițializează paginile
    @BeforeEach
    void init() {
        // Citește din linia de comandă sau din config.properties tipul de browser
        String browser = System.getProperty("browser", props.getProperty("browser"));

        // Creează driverul corespunzător (Chrome, Firefox etc.)
        driver = DriverFactory.createDriver(browser);

        // Maximizează fereastra
        driver.manage().window().maximize();

        // Setează un timeout implicit de 5 secunde pentru toate căutările de elemente
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Accesează pagina principală a aplicației
        driver.get("https://bstackdemo.com/");

        // Inițializează toate paginile (folosind Page Object Model)
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
        checkoutPage = new CheckoutPage(driver);
        confirmationPage = new ConfirmationPage(driver);
    }

    // Test complet care simulează o comandă: login → selectare produs → checkout
    @Test
    void completePurchaseFlow() {
        // Apasă pe butonul de login
        homePage.clickSignIn();

        // Se autentifică folosind user + parolă prestabilite
        loginPage.login();

        // Selectează produsul iPhone 12
        productPage.selectProduct();

        // Verifică dacă produsul este afișat
        assertTrue(productPage.isProductVisible());

        // Apasă pe butonul "Buy"
        productPage.clickBuy();

        // Completează formularul de livrare cu date fictive
        checkoutPage.fillShippingForm("alex", "mihai", "Calea Martirilor", "Timisoara", "307220");

        // Continuă spre confirmarea comenzii
        checkoutPage.continueToConfirmation();

        // Verifică dacă mesajul de confirmare este afișat corect
        assertEquals(
                "Your Order has been successfully placed.",
                WaitUtils.waitForText(driver, "confirmation-message")
        );

        // Verifică dacă textul despre numărul comenzii apare
        assertTrue(confirmationPage.getOrderText().contains("Your order number is"));


        // Verifică dacă prețul total este cel așteptat
        assertEquals("$799.00", confirmationPage.getTotalPrice());

        // Revine înapoi pe pagina principală
        confirmationPage.goBackHome();
    }

    // Se execută după fiecare test – închide browserul
    @AfterEach
    void close() {
        if (driver != null) {
            driver.quit(); // închide complet browserul
        }
    }
}