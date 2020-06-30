package com.epam.github.page;

import com.epam.github.browser.Browser;
import com.epam.github.models.Issue;
import com.epam.github.util.ExplicitWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {

    @FindBy(xpath = "//a[@href='/login']")
    private WebElement signInButton;

    @FindBy(xpath = "//img[@class='avatar avatar-user ']")
    private WebElement avatarUserButton;

    @FindBy(xpath = "//strong[@class='css-truncate-target']")
    private WebElement linkLoggedInUserLocator;

    @FindBy(xpath = "//div[@class='container-lg px-2']")
    private WebElement errorMessage;

    @FindBy(xpath = "//body//div[6]/details//span")
    private WebElement signPlusButton;

    @FindBy(xpath = "//a[@data-ga-click='Header, create new repository']")
    private WebElement newRepositoryButton;

    private final String createdIssueLocator = "//a[@title='%s']";

    public LoginPage clickSignIn() {
        clickOnTheVisibleItem(signInButton);
        return new LoginPage();
    }

    public String getLoggedInUserName() {
        clickOnTheVisibleItem(avatarUserButton);
        return getText(linkLoggedInUserLocator);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public NewRepositoryPage clickNewRepository() {
        Browser.getInstance().getDriver().navigate().refresh();
        clickOnTheVisibleItem(signPlusButton);
        clickOnTheVisibleItem(newRepositoryButton);
        return new NewRepositoryPage();
    }

    public IssuePage openCreatedIssue(Issue issue) {
        //clickOnTheVisibleItem(Browser.getInstance().getDriver().findElement(By.xpath(String.format(createdIssueLocator, issue.getTitle()))));
        clickOnTheVisibleByLocatorItem(By.xpath(String.format(createdIssueLocator, issue.getTitle())));
        return new IssuePage();
    }
}
