package com.epam.github.test;

import com.epam.github.browser.Browser;
import com.epam.github.util.TestListener;
import com.epam.reportportal.testng.ReportPortalTestNGListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners({TestListener.class})
public abstract class BaseTest {

    @BeforeMethod()
    public void setUp() {
        Browser.getInstance().openStartPage();
    }

    @AfterMethod(alwaysRun = true)
    public void stopBrowser() {
        Browser.getInstance().quitDriver();
    }
}