package com.epam.github.page;

import com.epam.github.browser.Browser;
import com.epam.github.util.ExplicitWait;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {

    public BasePage() {
        PageFactory.initElements(Browser.getInstance().getDriver(), this);
    }

    public void clickOnTheVisibleItem(WebElement webElement) {
        ExplicitWait.waitForElementVisibility(webElement).click();
    }

    public void clickOnTheClickableItem(WebElement webElement) {
        ExplicitWait.waitForElementClickability(webElement).click();
    }

    public void clickOnTheVisibleByLocatorItem(By by) {
        ExplicitWait.waitForElementVisibilityByLocated(by).click();
    }

    public String getText(WebElement webElement) {
        return ExplicitWait.waitForElementVisibility(webElement).getText();
    }

    public void sendKeys(WebElement webElement, String text) {
        ExplicitWait.waitForElementVisibility(webElement).sendKeys(text);
    }

    public boolean isElementExistsByLocator(By by) {
        try {
            ExplicitWait.waitForElementVisibilityByLocated(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }

    }
}