package ui;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.qameta.allure.Allure;

class BaseTest {

    protected WebDriver driver;
    protected static final String BASE_URL =
            "https://bonigarcia.dev/selenium-webdriver-java/";

    @BeforeEach
    void setup() throws MalformedURLException {
        initDriver();
        driver.get(BASE_URL);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception ignored) {
            }
        }
    }

    private void initDriver() throws MalformedURLException {
        String remoteUrl = System.getenv("SELENIUM_REMOTE_URL");
        Allure.addAttachment("SELENIUM_REMOTE_URL", String.valueOf(remoteUrl));

        if (remoteUrl != null && !remoteUrl.isBlank()) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");

            driver = new RemoteWebDriver(
                    URI.create(remoteUrl).toURL(),
                    options
            );
        } else {
            driver = new ChromeDriver();
        }
    }
}