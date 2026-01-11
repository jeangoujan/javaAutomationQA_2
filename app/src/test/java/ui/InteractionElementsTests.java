package ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class InteractionElementsTests {
    WebDriver driver;
    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/web-form.html";

    @BeforeEach
    void setup(){
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown(){
        driver.quit();
    }
    
    @Test
    void submitForm() throws InterruptedException{ 
        //driver.findElement(By.xpath("//form")).submit();
        // driver.findElement(By.xpath("//button")).click(); --- Test CLick
        // Thread.sleep(3000);

        WebElement selectElement = driver.findElement(By.name("my-select")); // --- Test Select
        Select select = new Select(selectElement);
        String actualText = select.getFirstSelectedOption().getText();
        assertEquals("Open this select menu", actualText);
        
        select.selectByIndex(2);
        assertEquals("Two",select.getFirstSelectedOption().getText());
        select.selectByIndex(0);    
        assertEquals("Open this select menu", select.getFirstSelectedOption().getText());    
        Thread.sleep(3000);
    }


    @Test
    void inputToFieldsTest() throws InterruptedException{
        driver.findElement(By.id("my-text-id")).sendKeys("Test_input");
        driver.findElement(By.name("my-password")).sendKeys("Test_password");
        driver.findElement(By.name("my-textarea")).sendKeys("Test_text");
        driver.findElement(By.name("my-datalist")).sendKeys("San Francisco");
 

        driver.findElement(By.cssSelector("button[type='submit']")).click();
        String url = driver.getCurrentUrl();
        Thread.sleep(2000);

        assertTrue(url.contains("my-text=Test_input")); // Testing Text input
        assertTrue(url.contains("my-password=Test_password")); // Testing Password input
        assertTrue(url.contains("my-textarea=Test_text")); // Testing Textarea input
        assertTrue(url.contains("my-datalist=San+Francisco")); // Testing Dropdown (datalist)
    }

    @Test
    void inputToDisabledField(){
        WebElement disabledInput = driver.findElement(By.cssSelector("input[name='my-disabled']"));
        // assertThrows(ElementNotInteractableException.class, () -> {disabledInput.sendKeys("Test");});  - одно и тоже что и вторая проверка
        assertFalse(disabledInput.isEnabled());
    }   

    @Test
    void dropdownSelectTest(){
        WebElement selectElement = driver.findElement(By.name("my-select"));
        Select select = new Select(selectElement);

        select.selectByVisibleText("Three");

        assertEquals("Three", select.getFirstSelectedOption().getText());
        assertEquals(4, select.getOptions().size());
        }

    @Test
    void fileInputTest(){
        WebElement fileInput = driver.findElement(By.name("my-file"));
        String path = Paths.get("test-files", "testfile.jpg").toAbsolutePath().toString();
        fileInput.sendKeys(path);
        
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        String url = driver.getCurrentUrl();

        assertTrue(url.contains("my-file=testfile.jpg"));
    }

    @Test
    void checkBoxTest(){
        WebElement checkBox1 = driver.findElement(By.id("my-check-1"));
        WebElement checkBox2 = driver.findElement(By.id("my-check-2"));

        assertTrue(checkBox1.isSelected());
        assertFalse(checkBox2.isSelected());

        checkBox1.click();
        checkBox2.click();

        assertFalse(checkBox1.isSelected());
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        String url = driver.getCurrentUrl();
        assertTrue(url.contains("my-check=on"));
    }

    @Test
    void radioButtonTest(){
        WebElement radio1 = driver.findElement(By.id("my-radio-1"));
        WebElement radio2 = driver.findElement(By.id("my-radio-2"));

        assertTrue(radio1.isSelected());

        radio2.click();
        assertFalse(radio1.isSelected());
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        String url = driver.getCurrentUrl();
        assertTrue(url.contains("my-radio=on"));
    }

    @Test
    void datepickerTest(){
        driver.findElement(By.name("my-date")).sendKeys("01/11/2026");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        String url = driver.getCurrentUrl();
        assertTrue(url.contains("my-date=01%2F11%2F2026"));
    }

    @Test
    void navigationTest(){
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/navigation1.html");
        WebElement secondPage = driver.findElement(By.xpath("//a[text()= '2']"));
        WebElement thirdPage = driver.findElement(By.xpath("//a[text()= '3']"));
        WebElement NextPage = driver.findElement(By.xpath("//a[text()= 'Next']"));
        WebElement PreviousPage = driver.findElement(By.xpath("//a[text()= 'Previous']"));
                
        List<WebElement> pages = List.of(secondPage, thirdPage);

        for(WebElement p: pages){
            p.click();
            String url = driver.getCurrentUrl();
            assertTrue(url.contains(p.getText()));
        }
    }
}