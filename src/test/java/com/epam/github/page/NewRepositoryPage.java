package com.epam.github.page;

import com.epam.github.browser.Browser;
import com.epam.github.util.ExplicitWait;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewRepositoryPage extends BasePage {

    @FindBy(id = "repository_name")
    private WebElement repositoryName;

    @FindBy(xpath = "//*[@id='new_repository']/div[3]/button")
    private WebElement createRepositoryButton;

    @FindBy(xpath = "//a[@data-pjax='#js-repo-pjax-container']")
    private WebElement repositoryNameField;

    public NewRepositoryPage createNewRepository(String randomName){
        Browser.getInstance().getDriver().navigate().refresh();
        sendKeys(repositoryName, randomName);
        //ExplicitWait.waitForElementClickability(createRepositoryButton);
        //clickOnTheVisibleItem(createRepositoryButton);
        clickOnTheClickableItem(createRepositoryButton);
        return new NewRepositoryPage();
    }

    public String getNewRepositoryName() {
        return getText(repositoryNameField);
    }
}
