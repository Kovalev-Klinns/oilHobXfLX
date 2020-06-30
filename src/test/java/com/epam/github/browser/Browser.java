package com.epam.github.browser;

import com.epam.github.service.PropertyReader;
import org.openqa.selenium.WebDriver;

public class Browser {

    private static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();
    private static Browser instance;

    private Browser() {
    }

    public static Browser getInstance() {
        if (instance == null) {
            instance = new Browser();
        }
        return instance;
    }

    public WebDriver getDriver() {
        if (driverThread.get() == null) {
            WebDriver driver = BrowserFactory.getDriver(PropertyReader.getBaseBrowser());
            driverThread.set(driver);
        }
        return driverThread.get();
    }

    public void openStartPage() {
        getDriver().manage().window().maximize();
        getDriver().navigate().to(PropertyReader.getBaseUrl());
    }

    public void quitDriver() {
        if (driverThread.get() != null) {
            driverThread.get().quit();
            driverThread.remove();
        }
    }
}