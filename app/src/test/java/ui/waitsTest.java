package ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class waitsTest {
    WebDriver driver;
    Actions actions;
    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/";

    @BeforeEach
    void setup(){
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        actions = new Actions(driver);
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown(){
        driver.quit();
    }

    @Test
    void infiniteScrollTest() throws InterruptedException{
        String endpoint = "infinite-scroll.html";
        driver.get(BASE_URL + endpoint);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("p")));
        
        List<WebElement> paragraphsBefore = driver.findElements(By.xpath("//div[@id='content']//p"));
        int numBeforeScroll = paragraphsBefore.size();

        actions.scrollToElement(driver.findElement(By.tagName("footer"))).perform();

        List<WebElement> paragraphsAfter = driver.findElements(By.xpath("//div[@id='content']//p"));
        int numAfterScrool = paragraphsAfter.size();

        assertTrue(numAfterScrool > numBeforeScroll);
    }

    @Test
    void shadowDomTest(){
        String endpoint = "shadow-dom.html";
        driver.get(BASE_URL + endpoint);
        WebElement content = driver.findElement(By.id("content"));

        SearchContext shadowRoot = content.getShadowRoot();
        WebElement paragraph = shadowRoot.findElement(By.cssSelector("p")); 

        assertTrue(paragraph.isDisplayed());
        assertEquals("Hello Shadow DOM", paragraph.getText());
    }

    @Test
    void isCookiesDisplayedTest(){
        String endpoint = "cookies.html";
        driver.get(BASE_URL + endpoint);

        WebElement displayCookieButton = driver.findElement(By.id("refresh-cookies"));
        displayCookieButton.click();

        WebElement cookies = driver.findElement(By.id("cookies-list"));

        assertNotEquals("", cookies.getText());
    }

    @Test
    void isCookiesDeletedTest(){
        String endpoint = "cookies.html";
        driver.get(BASE_URL + endpoint);
        driver.manage().deleteAllCookies();

        WebElement displayCookieButton = driver.findElement(By.id("refresh-cookies"));
        displayCookieButton.click();

        WebElement cookies = driver.findElement(By.id("cookies-list"));
        String cookiesText = cookies.getText();

        assertTrue(cookiesText.isBlank());
    }

    @Test
    void isCookiesEditedTest(){
        String endpoint = "cookies.html";
        driver.get(BASE_URL + endpoint);
        Cookie cookie = driver.manage().getCookieNamed("username");
        assertEquals("John Doe", cookie.getValue());

        driver.manage().deleteCookieNamed("username");
        driver.manage().addCookie(new Cookie("username", "Test Cookie"));

        Cookie updatedCookie = driver.manage().getCookieNamed("username");

        assertNotEquals(cookie.getValue(), updatedCookie.getValue());   
    }

    @Test
    void iFramesTest(){
        String endpoint = "iframes.html";
        driver.get(BASE_URL + endpoint);
        
        WebElement iframe = driver.findElement(By.id("my-iframe"));
        driver.switchTo().frame(iframe);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("p")));

        assertTrue(driver.findElement(By.xpath("(//p)[1]")).isDisplayed());
        driver.switchTo().defaultContent();
    }

    @Test
    void launchAlertTest(){
        String endpoint = "dialog-boxes.html";
        driver.get(BASE_URL + endpoint); 
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        driver.findElement(By.id("my-alert")).click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        alert.accept();
        assertThrows(NoAlertPresentException.class, () -> driver.switchTo().alert());
    }

    @Test
    void launchConfirmOkTest(){
        String endpoint = "dialog-boxes.html";
        driver.get(BASE_URL + endpoint); 
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(By.id("my-confirm")).click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        alert.accept();
        assertTrue(driver.findElement(By.xpath("//p[text()='You chose: true']")).isDisplayed());
    }

    @Test
    void launchConfirmCancelTest(){
        String endpoint = "dialog-boxes.html";
        driver.get(BASE_URL + endpoint); 
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(By.id("my-confirm")).click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        alert.dismiss();
        assertTrue(driver.findElement(By.xpath("//p[text()='You chose: false']")).isDisplayed());
    }

    @Test
    void launchPromptWithTextTest(){
        String endpoint = "dialog-boxes.html";
        driver.get(BASE_URL + endpoint); 
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(By.id("my-prompt")).click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        alert.sendKeys("Test");
        alert.accept();

        assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'You typed:')]")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Test')]")).isDisplayed());

    }

    @Test
    void launchPromptCancelTest(){
        String endpoint = "dialog-boxes.html";
        driver.get(BASE_URL + endpoint); 
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(By.id("my-prompt")).click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        alert.dismiss();

        assertTrue(driver.findElement(By.xpath("//p[text()='You typed: null']")).isDisplayed());
    }

    @Test
    void launchPromptEmptyStringTest(){
        String endpoint = "dialog-boxes.html";
        driver.get(BASE_URL + endpoint); 
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(By.id("my-prompt")).click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        alert.accept();

        assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'You typed:')]")).isDisplayed());
    }

    @Test
    void launchModalSaveChangesTest() throws InterruptedException{
        String endpoint = "dialog-boxes.html";
        driver.get(BASE_URL + endpoint); 
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(By.id("my-modal")).click();
        
        wait.until((ExpectedConditions.presenceOfElementLocated(By.className("modal-content"))));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space(.)='Save changes']")));
        driver.findElement(By.xpath("//button[normalize-space(.)='Save changes']")).click();
        
        assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'You chose:')]")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Save changes')]")).isDisplayed());
    }

    @Test
    void launchModalCloseTest() throws InterruptedException{
        String endpoint = "dialog-boxes.html";
        driver.get(BASE_URL + endpoint); 
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(By.id("my-modal")).click();
        
        wait.until((ExpectedConditions.presenceOfElementLocated(By.className("modal-content"))));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space(.)='Close']")));
        driver.findElement(By.xpath("//button[normalize-space(.)='Close']")).click();
        
        assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'You chose:')]")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Close')]")).isDisplayed());
    }
}
