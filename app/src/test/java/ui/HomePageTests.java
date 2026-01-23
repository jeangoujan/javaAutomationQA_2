package ui;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


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
            System.out.println(textAreaLabel.getText());

            WebElement textAreaInput = driver.findElement(By.cssSelector("textarea.form-control")); // CSS Selector: Tag + Class
            System.out.println(textAreaInput.getText());

            WebElement disabledLabel = driver.findElement(By.xpath("//label[contains(text(),'Disabled input')]"));
            System.out.println(disabledLabel.getText());

            WebElement disabledInput = driver.findElement(By.cssSelector("input[name='my-disabled']")); // CSS Selector: Tag + Attribute
            System.out.println(disabledInput.getText());

            WebElement readonlyLabel = driver.findElement(By.xpath("//label[normalize-space(.)='Readonly input']")); // XPath 
            System.out.println(readonlyLabel.getText());

            WebElement readonlyInput = driver.findElement(By.cssSelector("[value='Readonly input']")); // CSS Selector: Attribute
            System.out.println(readonlyInput.getText());

            WebElement returnToIndexButton = driver.findElement(By.linkText("Return to index")); // BASE: By link text
            System.out.println(returnToIndexButton.getText());

            WebElement dropDownSelectLabel = driver.findElement(By.xpath("//select[@class='form-select']/parent::label")); // XPath Search by Parent
            System.out.println(dropDownSelectLabel.getText());

            WebElement dropDownSelect = driver.findElement(By.xpath("//select[@class='form-select']"));
            System.out.println(dropDownSelect.getText());

            WebElement dropDownDatalistLabel = driver.findElement(By.xpath("//input[@name='my-datalist']/parent::label"));
            System.out.println(dropDownDatalistLabel.getText());

            WebElement dropDownDatalist = driver.findElement(By.xpath("//input[@placeholder='Type to search...']"));
            System.out.println(dropDownDatalist.getText());

            WebElement fileInputLabel = driver.findElement(By.xpath("//input[@type='file']/parent::label"));
            System.out.println(fileInputLabel.getText());

            WebElement fileInput = driver.findElement(By.xpath("//input[@class='form-control' and @type='file']")); // XPath + AND
            System.out.println(fileInput.getText());

            WebElement checkBox = driver.findElement(By.xpath("//input[@type='checkbox']"));
            System.out.println(checkBox.getText());

            WebElement checkedBoxLabel = driver.findElement(By.xpath("//input[@type='checkbox' and @id = 'my-check-1']/parent::label"));
            System.out.println(checkedBoxLabel.getText());

            WebElement checkBox2 = driver.findElement(By.xpath("//input[@type='checkbox' and @id = 'my-check-2']"));
            System.out.println(checkBox2.getText());

            WebElement checkBox2Label = driver.findElement(By.xpath("//input[@type='checkbox' and @id = 'my-check-2']/parent::label"));
            System.out.println(checkBox2Label.getText());

            WebElement radio1 = driver.findElement(By.xpath("//input[@id = 'my-radio-1']"));
            System.out.println(radio1.getText());

            WebElement radio1Label = driver.findElement(By.xpath("//input[@id = 'my-radio-1']/parent::label"));
            System.out.println(radio1Label.getText()); 

            WebElement radio2 = driver.findElement(By.xpath("//input[@id = 'my-radio-2']"));
            System.out.println(radio2.getText());

            WebElement radio2Label = driver.findElement(By.xpath("//input[@id = 'my-radio-2']/parent::label"));
            System.out.println(radio2Label.getText()); 

            WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']")); // CSS Selector: attribute + tag
            System.out.println(submitButton.getText());

            WebElement line = driver.findElement(By.className("my-4"));
            System.out.println(line);

            WebElement colorsLabel = driver.findElement(By.xpath("//input[@name='my-colors']/parent::label"));
            System.out.println(colorsLabel.getText());

            WebElement colors = driver.findElement(By.xpath("//input[@name='my-colors']"));
            System.out.println(colors.getText());

            WebElement dateColorLabel = driver.findElement(By.xpath("//input[@name='my-date']/parent::label"));
            System.out.println(dateColorLabel.getText());

            WebElement dateColor = driver.findElement(By.xpath("//input[@name='my-date']"));
            System.out.println(dateColor.getText());

            WebElement myRangeLabel = driver.findElement(By.xpath("//input[@name='my-range']/parent::label"));
            System.out.println(myRangeLabel.getText());

            WebElement myRange = driver.findElement(By.xpath("//input[@name='my-range']"));
            System.out.println(myRange.getText());

            WebElement footerSpan = driver.findElement(By.xpath("//span[@class='text-muted']"));
            System.out.println(footerSpan.getText());

            WebElement footerLink = driver.findElement(By.cssSelector("a[href='./index.html']"));
            System.out.println(footerLink.getText());
        }

    }

    // @Test
    // void openHomePageTest(){
    //     String actualtitle = driver.getTitle();
    //     assertEquals("Hands-On Selenium WebDriver with Java", actualtitle);
    // }    



