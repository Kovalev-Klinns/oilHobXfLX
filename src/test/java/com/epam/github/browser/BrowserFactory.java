package com.epam.github.browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

public class BrowserFactory {

    public static WebDriver getDriver(String browser) {
        switch (browser) {
            case "opera": {
                WebDriverManager.operadriver().setup();
                return new OperaDriver();
            }
            case "firefox": {
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            }
            default: {
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            }
        }
    }
}
