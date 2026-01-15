package constants;

import java.time.Duration;

public final class TestConfig {
    // Urls:
    public static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/";
    public static final String INFINITE_SCROLL_ENDPOINT = "infinite-scroll.html";
    public static final String SHADOW_DOM_ENDPOINT = "shadow-dom.html";
    public static final String COOKIES_ENDPOINT = "cookies.html";
    public static final String IFRAMES_ENDPOINT = "iframes.html";
    public static final String DIALOG_BOXES_ENDPOINT = "dialog-boxes.html";
    public static final String WEB_STORAGE_ENDPOINT = "web-storage.html";
    public static final String LOADING_IMAGES_ENDPOINT = "loading-images.html";
    public static final String SLOW_CALCULATOR_ENDPOINT = "slow-calculator.html";
    public static final String LOGIN_FORM_ENDPOINT = "login-form.html";


    // Timeouts:
    public static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(10);
    public static final Duration LONG_TIMEOUT = Duration.ofSeconds(15);
    public static final Duration SHORT_TIMEOUT = Duration.ofSeconds(5);
    
    

    private TestConfig() {}
}
