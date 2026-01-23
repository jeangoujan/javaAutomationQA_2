package ui;

import java.net.MalformedURLException;
import java.net.URI;
import java.nio.charset.MalformedInputException;
import java.time.Duration;
import java.util.Map;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

class BaseTest {
    WebDriver driver;
    protected static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/";
    
    @BeforeEach
    void setup(){
        initDriver();
        driver.get(BASE_URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    @AfterEach
    void tearDown(){
        driver.quit();
    }

    private void initDriver(){
        String remoteUrl = System.getenv("SELENIUM_REMOTE_URL");
        System.out.println("SELENIUM_REMOTE_URL = " + remoteUrl);
        if (remoteUrl != null) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.setCapability("goog:loggingPrefs", Map.of("browser", "ALL"));
            try {
                URI uri = URI.create(remoteUrl);
                driver = new RemoteWebDriver(uri.toURL(), options);
            } catch(MalformedURLException e) {
                throw new RuntimeException("Malformed URL for Selenium Remote WebDriver", e); 
            }
        } else{
            driver = new ChromeDriver();
        }
    }
}
