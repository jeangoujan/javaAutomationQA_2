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
        if (!(driver instanceof RemoteWebDriver)) {
            driver.manage().window().maximize();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    @AfterEach
    void tearDown(){
            if (driver != null) {
        try {
            driver.quit();
        } catch (Exception ignored) {
        }
    }
    }

    private void initDriver() {
        String remoteUrl = System.getenv("SELENIUM_REMOTE_URL");
        Allure.addAttachment("remote", String.valueOf(remoteUrl));

        if (remoteUrl != null && !remoteUrl.isEmpty()) {
            try {
                ChromeOptions options = new ChromeOptions();
                options.setCapability("browserName", "chrome");
                options.setCapability("platformName", "LINUX");

                driver = new RemoteWebDriver(
                    URI.create(remoteUrl).toURL(),
                    options
                );
            } catch (MalformedURLException e) {
                throw new RuntimeException("Bad Selenium URL", e);
            }
        } else {
            driver = new ChromeDriver();
        }
    }
}
