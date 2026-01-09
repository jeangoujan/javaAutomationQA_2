package ui;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;

public class HomePageTests {
    WebDriver driver;
    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/";


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
    void chapterThreeTest(){
        List<String> expectedPagePaths = List.of(
        "web-form.html",
        "navigation1.html",
        "dropdown-menu.html",
        "mouse-over.html",
        "drag-and-drop.html",
        "draw-in-canvas.html",
        "loading-images.html",
        "slow-calculator.html"
    );

        List<String> expectedH1 = List.of(
        "Web form",
        "Navigation example",
        "Dropdown menu",
        "Mouse over",
        "Drag and drop",
        "Drawing in canvas",
        "Loading images",
        "Slow calculator"
    );
        WebElement chapterThreeCardBody = driver.findElement(By.xpath("//h5[contains(text(), 'Chapter 3')]/ancestor::div[contains(@class, 'card-body')]"));

        List<WebElement> links = chapterThreeCardBody.findElements(By.tagName("a"));

        assertEquals(expectedPagePaths.size(), links.size());

        for (int i = 0; i < links.size(); i++){
            chapterThreeCardBody = driver.findElement(By.xpath("//h5[contains(text(), 'Chapter 3')]/ancestor::div[contains(@class, 'card-body')]"));
            links = chapterThreeCardBody.findElements(By.tagName("a"));

            WebElement link = links.get(i);

            link.click();
            String actualUrl = driver.getCurrentUrl();
            String actualH1 = driver.findElement(By.cssSelector("h1.display-6")).getText();

            assertTrue(actualUrl.endsWith(expectedPagePaths.get(i)));
            assertEquals(expectedH1.get(i), actualH1);
 
            driver.get(BASE_URL);
        }
    }

    @Test
    void chapterFourTest(){
        List<String> expectedPagePaths = List.of(
        "long-page.html",
        "infinite-scroll.html",
        "shadow-dom.html",
        "cookies.html",
        "frames.html",
        "iframes.html",
        "dialog-boxes.html",
        "web-storage.html"
    );

        List<String> expectedH1 = Arrays.asList(
        "This is a long page",
        "Infinite scroll",
        "Shadow DOM",
        "Cookies",
        null,
        "IFrame",
        "Dialog boxes",
        "Web storage"
    );
    By chapterFourLocator = By.xpath("//h5[contains(text(), 'Chapter 4')]/ancestor::div[contains(@class, 'card-body')]");

    WebElement chapterFourCardBody = driver.findElement(chapterFourLocator);
    List<WebElement> links = chapterFourCardBody.findElements(By.tagName("a"));

    assertEquals(expectedPagePaths.size(), links.size(), "Количество ссылок не совпадает с ожиданиями");

    for (int i = 0; i < links.size(); i++) {
        chapterFourCardBody = driver.findElement(chapterFourLocator);
        links = chapterFourCardBody.findElements(By.tagName("a"));

        WebElement link = links.get(i);
        String expectedPath = expectedPagePaths.get(i);

        link.click();

        String actualUrl = driver.getCurrentUrl();
        assertTrue(actualUrl.endsWith(expectedPath));

       
        if (expectedH1.get(i) != null) {
            String actualH1 = driver
                    .findElement(By.cssSelector("h1.display-6"))
                    .getText();
            assertEquals(expectedH1.get(i), actualH1);
        }

        driver.get(BASE_URL);
        }
    }

    @Test
    void chapterFiveTest(){
        List<String> expectedPaths = List.of(
            "geolocation.html",
            "notifications.html",
            "get-user-media.html",
            "multilanguage.html",
            "console-logs.html"
        );

        List<String> expectedH1 = Arrays.asList(
            "Geolocation",
            "Notifications",
            "Get user media",
            null,
            "Console logs"
        );

        By chapterFiveLocator = By.xpath("//h5[contains(text(), 'Chapter 5')]/ancestor::div[contains(@class, 'card-body')]");
        WebElement chapterFiveCardBody = driver.findElement(chapterFiveLocator);

        List<WebElement> links = chapterFiveCardBody.findElements(By.tagName("a"));

        assertEquals(expectedPaths.size(), links.size(), "Количество ссылок не совпадает с ожиданиями");

        for (int i = 0; i < links.size(); i++) {
            chapterFiveCardBody = driver.findElement(chapterFiveLocator);
            links = chapterFiveCardBody.findElements(By.tagName("a"));

            WebElement link = links.get(i);
            String expectedPath = expectedPaths.get(i);

            link.click();

            String actualUrl = driver.getCurrentUrl();
            assertTrue(actualUrl.endsWith(expectedPath));

        
            if (expectedH1.get(i) != null) {
                String actualH1 = driver
                        .findElement(By.cssSelector("h1.display-6"))
                        .getText();
                assertEquals(expectedH1.get(i), actualH1);
            }

            driver.get(BASE_URL);
            }
        }

        @Test
        void chapterSevenTest(){
            List<String> expectedPaths = List.of(
                "login-form.html",
                "login-slow.html"
            );

            List<String> expectedH1 = List.of(
                "Login form",
                "Slow login form"
            );

            By chapterSevenLocator = By.xpath("//h5[contains(text(), 'Chapter 7')]/ancestor::div[contains(@class, 'card-body')]");
            WebElement chapterSevenCardBody = driver.findElement(chapterSevenLocator);
            List<WebElement> links = chapterSevenCardBody.findElements(By.tagName("a"));

            assertEquals(expectedPaths.size(), links.size(), "Количество ссылок не совпадает с ожиданиями");

            for (int i = 0; i < links.size(); i++) {
            chapterSevenCardBody = driver.findElement(chapterSevenLocator);
            links = chapterSevenCardBody.findElements(By.tagName("a"));

            WebElement link = links.get(i);
            String expectedPath = expectedPaths.get(i);

            link.click();

            String actualUrl = driver.getCurrentUrl();
            assertTrue(actualUrl.endsWith(expectedPath));

        
            if (expectedH1.get(i) != null) {
                String actualH1 = driver
                        .findElement(By.cssSelector("h1.display-6"))
                        .getText();
                assertEquals(expectedH1.get(i), actualH1);
            }

            driver.get(BASE_URL);
            }
        }

        @Test
        void chapterEightTest(){
            List<String> expectedPaths = List.of(
                "random-calculator.html"
            );

            List<String> expectedH1 = List.of(
                "Random calculator"
            );

            By chapterEightLocator = By.xpath("//h5[contains(text(), 'Chapter 8')]/ancestor::div[contains(@class, 'card-body')]");
            WebElement chapterEightCardBody = driver.findElement(chapterEightLocator);
            List<WebElement> links = chapterEightCardBody.findElements(By.tagName("a"));

            assertEquals(expectedPaths.size(), links.size(), "Количество ссылок не совпадает с ожиданиями");

            for (int i = 0; i < links.size(); i++) {
            chapterEightCardBody = driver.findElement(chapterEightLocator);
            links = chapterEightCardBody.findElements(By.tagName("a"));

            WebElement link = links.get(i);
            String expectedPath = expectedPaths.get(i);

            link.click();

            String actualUrl = driver.getCurrentUrl();
            assertTrue(actualUrl.endsWith(expectedPath));

        
            if (expectedH1.get(i) != null) {
                String actualH1 = driver
                        .findElement(By.cssSelector("h1.display-6"))
                        .getText();
                assertEquals(expectedH1.get(i), actualH1);
            }

            driver.get(BASE_URL);
            }
        }

        @Test
        void chapterNineTest(){
            List<String> expectedPaths = List.of(
                "download.html",
                "ab-testing.html",
                "data-types.html"
            );

            List<String> expectedH1 = List.of(
                "Download files",
                "A/B Testing",
                "Data types"
            );

            By chapterNineLocator = By.xpath("//h5[contains(text(), 'Chapter 9')]/ancestor::div[contains(@class, 'card-body')]");
            WebElement chapterNineCardBody = driver.findElement(chapterNineLocator);
            List<WebElement> links = chapterNineCardBody.findElements(By.tagName("a"));

            assertEquals(expectedPaths.size(), links.size(), "Количество ссылок не совпадает с ожиданиями");

            for (int i = 0; i < links.size(); i++) {
            chapterNineCardBody = driver.findElement(chapterNineLocator);
            links = chapterNineCardBody.findElements(By.tagName("a"));

            WebElement link = links.get(i);
            String expectedPath = expectedPaths.get(i);

            link.click();

            String actualUrl = driver.getCurrentUrl();
            assertTrue(actualUrl.endsWith(expectedPath));

        
            if (expectedH1.get(i) != null) {
                String actualH1 = driver
                        .findElement(By.cssSelector("h1.display-6"))
                        .getText();
                assertEquals(expectedH1.get(i), actualH1);
            }

            driver.get(BASE_URL);
            }
        }


        @Test
        void openAllLinks() throws InterruptedException {
            int qtyLinks = 0;
            List<WebElement> chapters = driver.findElements(By.cssSelector("h5.card-title"));
            for (WebElement chapter: chapters){
                List<WebElement> links = chapter.findElements(By.xpath("./../a"));
                qtyLinks += links.size();
                System.out.println(chapter.getText());
                for (WebElement link: links){
                    System.out.println(link.getText());
                    link.click();
                    // Thread.sleep(1000);
                    driver.navigate().back();
                }
            }
            assertEquals(6, chapters.size());
            assertEquals(27, qtyLinks);
        }

        @Test
        void classPlusClassTest(){
            List<WebElement> links = driver.findElements(By.cssSelector(".btn.btn-outline-primary.mb-2"));
            assertEquals(27, links.size());
        }

        // Home Work
        @Test
        void allTheLocatorsTest(){
            String wf = "web-form.html";
            driver.get(BASE_URL+wf);

            WebElement h1 = driver.findElement(By.className("display-4")); // By Class Name
            System.out.println(h1.getText()); 

            WebElement h5 = driver.findElement(By.tagName("h5")); // By Tag name
            System.out.println(h5.getText());

            WebElement logo = driver.findElement(By.cssSelector("img.img-fluid")); // CSS Selector (Tag + class)
            System.out.println(logo);

            WebElement webForm = driver.findElement(By.xpath("//h1[text() = 'Web form']")); // XPath By text
            System.out.println(webForm.getText());

            WebElement textLabel1 = driver.findElement(By.xpath("(//label)[1]")); // XPath + First Element
            System.out.println(textLabel1.getText());

            WebElement textInput1 = driver.findElement(By.id("my-text-id")); // By ID
            System.out.println(textInput1.getText());

            WebElement textLabel2 = driver.findElement(By.xpath("//label/input[@type='password']")); // XPath + Type
            System.out.println(textLabel2.getText());

            WebElement textInput2 = driver.findElement(By.name("my-password")); // By Name
            System.out.println(textInput2.getText());

            WebElement textAreaLabel = driver.findElement(By.xpath("//label/textarea[@class = 'form-control']")); // Xpath + Potomok
            System.out.println(textAreaLabel);

            WebElement textAreaInput = driver.findElement(By.cssSelector("textarea.form-control")); // CSS Selector: Tag + Class
            System.out.println(textAreaInput);

            WebElement disabledLabel = driver.findElement(By.xpath("//label[contains(text(),'Disabled input')]"));
            System.out.println(disabledLabel);

            WebElement disabledInput = driver.findElement(By.cssSelector("input[name='my-disabled']")); // CSS Selector: Tag + Attribute
            System.out.println(disabledInput);

        }
    }

    // @Test
    // void openHomePageTest(){
    //     String actualtitle = driver.getTitle();
    //     assertEquals("Hands-On Selenium WebDriver with Java", actualtitle);
    // }    

    // @Test
    // void openWebFormTest(){
    //     // WebElement webFormLink = driver.findElement(By.xpath("//a[@href='web-form.html']"));
    //     //webFormLink.click();
    //     // driver.findElement(By.xpath("//h5[text() = 'Chapter 3. Webdriver Fundamentals']/../a[contains(@href, 'web-form')]")).click();

    //     String webFormUrl = "web-form.html";
    //     driver.findElement(By.linkText("Web form")).click();

    //     String currentUrl = driver.getCurrentUrl();
    //     WebElement title = driver.findElement(By.className("display-6"));

    //     assertEquals(BASE_URL + webFormUrl, currentUrl);
    //     assertEquals("Web form", title.getText()); // title сам по себе не сохраняет в переменной текст, поэтому требуется добавить метод getTest() чтобы можно было сравнить
    // } 

