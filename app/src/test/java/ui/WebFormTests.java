package ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

class WebFormTests extends BaseTest {
    
    @Test
    void openWebFormTest(){
        String webFormUrl = "web-form.html";
        driver.findElement(By.linkText("Web form")).click();

        String currentUrl = driver.getCurrentUrl();
        WebElement title = driver.findElement(By.className("display-6"));

        assertEquals(BASE_URL + webFormUrl, currentUrl);
        assertEquals("Web form", title.getText()); // title сам по себе не сохраняет в переменной текст, поэтому требуется добавить метод getTest() чтобы можно было сравнить
    } 

    @Test
    void inputToFieldsTest() {
        driver.findElement(By.linkText("Web form")).click();
        driver.findElement(By.id("my-text-id")).sendKeys("Test_input");
        driver.findElement(By.name("my-password")).sendKeys("Test_password");
        driver.findElement(By.name("my-textarea")).sendKeys("Test_text");
        driver.findElement(By.name("my-datalist")).sendKeys("San Francisco");
 

        driver.findElement(By.cssSelector("button[type='submit']")).click();
        String url = driver.getCurrentUrl();

        assertTrue(url.contains("my-text=Test_input")); // Testing Text input
        assertTrue(url.contains("my-password=Test_password")); // Testing Password input
        assertTrue(url.contains("my-textarea=Test_text")); // Testing Textarea input
        assertTrue(url.contains("my-datalist=San+Francisco")); // Testing Dropdown (datalist)
    }
}
