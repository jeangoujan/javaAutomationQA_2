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

import io.qameta.allure.Allure;

class BaseTest {
    WebDriver driver;
    protected static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/";
    
    @BeforeEach
    void setup() throws MalformedURLException{
        initDriver();
        driver.get(BASE_URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    @AfterEach
    void tearDown(){
        driver.quit();
    }

    private void initDriver() throws MalformedURLException {
        String remoteUrl = System.getenv("SELENIUM_REMOTE_URL");

        if (remoteUrl == null || remoteUrl.isEmpty()) {
            throw new RuntimeException("SELENIUM_REMOTE_URL is NOT set");
        }

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new RemoteWebDriver(URI.create(remoteUrl).toURL(), options);
    }
}
