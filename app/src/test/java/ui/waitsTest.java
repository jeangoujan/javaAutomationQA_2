package ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import utils.ConfigLoader;
import utils.OwnerConfig;
import utils.WebStorageHelper;
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

import constants.TestConfig;

public class waitsTest {
    WebDriver driver;
    Actions actions;
    

    @BeforeEach
    void setup(){
        driver = new ChromeDriver();
        driver.get(OwnerConfig.get().baseUrl());
        actions = new Actions(driver);
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown(){
        driver.quit();
    }

    @Test
    void loginFormTest(){
        driver.get(TestConfig.BASE_URL + TestConfig.LOGIN_FORM_ENDPOINT);
        driver.findElement(By.id("username")).sendKeys(ConfigLoader.getSecret("login.username"));
        driver.findElement(By.id("password")).sendKeys(ConfigLoader.getSecret("login.password"));
        driver.findElement(By.xpath("//button[@type='submit']")).click();

    }


    @Test
    void infiniteScrollTest(){
        driver.get(TestConfig.BASE_URL + TestConfig.INFINITE_SCROLL_ENDPOINT);
        WebDriverWait wait = new WebDriverWait(driver, TestConfig.DEFAULT_TIMEOUT);
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
        driver.get(TestConfig.BASE_URL + TestConfig.SHADOW_DOM_ENDPOINT);
        WebElement content = driver.findElement(By.id("content"));

        SearchContext shadowRoot = content.getShadowRoot();
        WebElement paragraph = shadowRoot.findElement(By.cssSelector("p")); 

        assertTrue(paragraph.isDisplayed());
        assertEquals("Hello Shadow DOM", paragraph.getText());
    }

    @Test
    void isCookiesDisplayedTest(){
        driver.get(TestConfig.BASE_URL + TestConfig.COOKIES_ENDPOINT);

        WebElement displayCookieButton = driver.findElement(By.id("refresh-cookies"));
        displayCookieButton.click();

        WebElement cookies = driver.findElement(By.id("cookies-list"));

        assertNotEquals("", cookies.getText());
    }

    @Test
    void isCookiesDeletedTest(){
        driver.get(TestConfig.BASE_URL + TestConfig.COOKIES_ENDPOINT);
        driver.manage().deleteAllCookies();

        WebElement displayCookieButton = driver.findElement(By.id("refresh-cookies"));
        displayCookieButton.click();

        WebElement cookies = driver.findElement(By.id("cookies-list"));
        String cookiesText = cookies.getText();

        assertTrue(cookiesText.isBlank());
    }

    @Test
    void isCookiesEditedTest(){
        driver.get(TestConfig.BASE_URL + TestConfig.COOKIES_ENDPOINT);
        Cookie cookie = driver.manage().getCookieNamed("username");
        assertEquals("John Doe", cookie.getValue());

        driver.manage().deleteCookieNamed("username");
        driver.manage().addCookie(new Cookie("username", "Test Cookie"));

        Cookie updatedCookie = driver.manage().getCookieNamed("username");

        assertNotEquals(cookie.getValue(), updatedCookie.getValue());   
    }

    @Test
    void iFramesTest(){
        driver.get(TestConfig.BASE_URL + TestConfig.IFRAMES_ENDPOINT);
        
        WebElement iframe = driver.findElement(By.id("my-iframe"));
        driver.switchTo().frame(iframe);

        WebDriverWait wait = new WebDriverWait(driver, TestConfig.DEFAULT_TIMEOUT);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("p")));

        assertTrue(driver.findElement(By.xpath("(//p)[1]")).isDisplayed());
        driver.switchTo().defaultContent();
    }

    @Test
    void launchAlertTest(){
        driver.get(TestConfig.BASE_URL + TestConfig.DIALOG_BOXES_ENDPOINT); 
        WebDriverWait wait = new WebDriverWait(driver, TestConfig.DEFAULT_TIMEOUT);
        
        driver.findElement(By.id("my-alert")).click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        alert.accept();
        assertThrows(NoAlertPresentException.class, () -> driver.switchTo().alert());
    }

    @Test
    void launchConfirmOkTest(){
        driver.get(TestConfig.BASE_URL + TestConfig.DIALOG_BOXES_ENDPOINT); 
        WebDriverWait wait = new WebDriverWait(driver, TestConfig.DEFAULT_TIMEOUT);

        driver.findElement(By.id("my-confirm")).click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        alert.accept();
        assertTrue(driver.findElement(By.xpath("//p[text()='You chose: true']")).isDisplayed());
    }

    @Test
    void launchConfirmCancelTest(){
        driver.get(TestConfig.BASE_URL + TestConfig.DIALOG_BOXES_ENDPOINT); 
        WebDriverWait wait = new WebDriverWait(driver, TestConfig.DEFAULT_TIMEOUT);

        driver.findElement(By.id("my-confirm")).click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        alert.dismiss();
        assertTrue(driver.findElement(By.xpath("//p[text()='You chose: false']")).isDisplayed());
    }

    @Test
    void launchPromptWithTextTest(){
        driver.get(TestConfig.BASE_URL + TestConfig.DIALOG_BOXES_ENDPOINT); 
        WebDriverWait wait = new WebDriverWait(driver, TestConfig.DEFAULT_TIMEOUT);

        driver.findElement(By.id("my-prompt")).click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        alert.sendKeys("Test");
        alert.accept();

        assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'You typed:')]")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Test')]")).isDisplayed());

    }

    @Test
    void launchPromptCancelTest(){
        driver.get(TestConfig.BASE_URL + TestConfig.DIALOG_BOXES_ENDPOINT); 
        WebDriverWait wait = new WebDriverWait(driver, TestConfig.DEFAULT_TIMEOUT);

        driver.findElement(By.id("my-prompt")).click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        alert.dismiss();

        assertTrue(driver.findElement(By.xpath("//p[text()='You typed: null']")).isDisplayed());
    }

    @Test
    void launchPromptEmptyStringTest(){
        driver.get(TestConfig.BASE_URL + TestConfig.DIALOG_BOXES_ENDPOINT); 
        WebDriverWait wait = new WebDriverWait(driver, TestConfig.DEFAULT_TIMEOUT);

        driver.findElement(By.id("my-prompt")).click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        alert.accept();

        assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'You typed:')]")).isDisplayed());
    }

    @Test
    void launchModalSaveChangesTest(){
        driver.get(TestConfig.BASE_URL + TestConfig.DIALOG_BOXES_ENDPOINT); 
        WebDriverWait wait = new WebDriverWait(driver, TestConfig.DEFAULT_TIMEOUT);

        driver.findElement(By.id("my-modal")).click();
        
        wait.until((ExpectedConditions.presenceOfElementLocated(By.className("modal-content"))));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space(.)='Save changes']")));
        driver.findElement(By.xpath("//button[normalize-space(.)='Save changes']")).click();
        
        assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'You chose:')]")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Save changes')]")).isDisplayed());
    }

    @Test
    void launchModalCloseTest(){
        driver.get(TestConfig.BASE_URL + TestConfig.DIALOG_BOXES_ENDPOINT); 
        WebDriverWait wait = new WebDriverWait(driver, TestConfig.DEFAULT_TIMEOUT);

        driver.findElement(By.id("my-modal")).click();
        
        wait.until((ExpectedConditions.presenceOfElementLocated(By.className("modal-content"))));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space(.)='Close']")));
        driver.findElement(By.xpath("//button[normalize-space(.)='Close']")).click();
        
        assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'You chose:')]")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Close')]")).isDisplayed());
    }

    @Test
    void localStorageTest(){ 
        driver.get(TestConfig.BASE_URL + TestConfig.WEB_STORAGE_ENDPOINT);   

        WebElement displayLocalStorageButton = driver.findElement(By.id("display-local"));
        displayLocalStorageButton.click();

        assertTrue(driver.findElement(By.xpath("//p[text()='{}']")).isDisplayed());

        WebStorageHelper storage = new WebStorageHelper(driver);
        storage.setLocalStorageItem("test", "value");

        displayLocalStorageButton.click();
        assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'test')]")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'value')]")).isDisplayed());
    }

    @Test
    void sessionStorageTest(){ 
        driver.get(TestConfig.BASE_URL + TestConfig.WEB_STORAGE_ENDPOINT);   

        WebElement displaySessionStorageButton = driver.findElement(By.id("display-session"));
        displaySessionStorageButton.click();
 
        assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'lastname')]")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Doe')]")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'name')]")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'John')]")).isDisplayed());
 
        WebStorageHelper storage = new WebStorageHelper(driver);
        storage.clearSessionStorage();

        displaySessionStorageButton.click();
        assertTrue(driver.findElement(By.xpath("//p[text()='{}']")).isDisplayed());
    }
    
    @Test
    void loadingImagesTest(){
        driver.get(TestConfig.BASE_URL + TestConfig.LOADING_IMAGES_ENDPOINT);   

        WebDriverWait wait = new WebDriverWait(driver, TestConfig.LONG_TIMEOUT);

        wait.until(ExpectedConditions.numberOfElementsToBe(By.tagName("img"), 4));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("landscape")));

        assertTrue(driver.findElement(By.id("compass")).isDisplayed());
        assertTrue(driver.findElement(By.id("calendar")).isDisplayed());
        assertTrue(driver.findElement(By.id("award")).isDisplayed());
        assertTrue(driver.findElement(By.id("landscape")).isDisplayed());
       }

    @Test
    void slowCalculatorTest(){
        driver.get(TestConfig.BASE_URL + TestConfig.SLOW_CALCULATOR_ENDPOINT);   

        WebDriverWait wait = new WebDriverWait(driver, TestConfig.SHORT_TIMEOUT);
        
        driver.findElement(By.xpath("//span[text()='2']")).click();
        driver.findElement(By.xpath("//span[text()='+']")).click();
        driver.findElement(By.xpath("//span[text()='2']")).click();
        driver.findElement(By.xpath("//span[text()='=']")).click();

        WebElement screen = driver.findElement(By.className("screen"));
        wait.until(ExpectedConditions.textToBePresentInElement(screen, "4"));

        String actualResult = screen.getText().trim();
        assertEquals("4", actualResult);
    }

}
