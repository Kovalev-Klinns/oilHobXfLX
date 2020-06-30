package com.epam.github.util;

import com.epam.reportportal.service.ReportPortal;
import com.epam.github.browser.Browser;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestListener implements ITestListener {

    private Logger log = LogManager.getRootLogger();
    private static Path screenshotsPath = Paths.get("target", "screenshots");

    public void onStart(ITestContext iTestContext) {
        File screenshotsPathAsFile = screenshotsPath.toFile();
        if (screenshotsPathAsFile.exists())
        try {
            FileUtils.cleanDirectory(screenshotsPathAsFile);
        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    public void onTestFailure(ITestResult iTestResult) {
        String testName = iTestResult.getMethod().getMethodName();
        saveScreenshot(testName);
    }

    public void onTestSuccess(ITestResult iTestResult) {
    }

    private void saveScreenshot(String testName) {
        String screenshotName = String.format("%s_%s.png", testName, DateUtil.getCurrentTimeAsString());
        Path screenshotPath = Paths.get(screenshotsPath.toString(), screenshotName);
        File screenshot;
        File screenCapture = ((TakesScreenshot) Browser.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenCapture, screenshot = new File(screenshotPath.toString()));
            ReportPortal.emitLog("Screenshot of the moment of test fall", "FAIL", DateUtil.getCurrentDate(), screenshot);
        } catch (IOException e) {
            log.error("Failed to save screenshot: " + e.getLocalizedMessage());
        }
    }
}
