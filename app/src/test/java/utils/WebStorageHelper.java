package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class WebStorageHelper {

    private final JavascriptExecutor js;

    public WebStorageHelper(WebDriver driver) {
        this.js = (JavascriptExecutor) driver;
    }

    /* ===================== LOCAL STORAGE ===================== */

    public void setLocalStorageItem(String key, String value) {
        js.executeScript(
            "window.localStorage.setItem(arguments[0], arguments[1]);",
            key, value
        );
    }

    public String getLocalStorageItem(String key) {
        return (String) js.executeScript(
            "return window.localStorage.getItem(arguments[0]);",
            key
        );
    }

    public void clearLocalStorage() {
        js.executeScript("window.localStorage.clear();");
    }

    public Map<String, String> getAllLocalStorage() {
        return (Map<String, String>) js.executeScript(
            """
            let storage = {};
            for (let i = 0; i < localStorage.length; i++) {
                let key = localStorage.key(i);
                storage[key] = localStorage.getItem(key);
            }
            return storage;
            """
        );
    }

    /* ===================== SESSION STORAGE ===================== */

    public void setSessionStorageItem(String key, String value) {
        js.executeScript(
            "window.sessionStorage.setItem(arguments[0], arguments[1]);",
            key, value
        );
    }

    public String getSessionStorageItem(String key) {
        return (String) js.executeScript(
            "return window.sessionStorage.getItem(arguments[0]);",
            key
        );
    }

    public void clearSessionStorage() {
        js.executeScript("window.sessionStorage.clear();");
    }

    public Map<String, String> getAllSessionStorage() {
        return (Map<String, String>) js.executeScript(
            """
            let storage = {};
            for (let i = 0; i < sessionStorage.length; i++) {
                let key = sessionStorage.key(i);
                storage[key] = sessionStorage.getItem(key);
            }
            return storage;
            """
        );
    }
}