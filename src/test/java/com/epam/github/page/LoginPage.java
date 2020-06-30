package com.epam.github.page;

import com.epam.github.browser.Browser;
import com.epam.github.models.User;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    public static final String ERROR_MESSAGE_INCORRECT_USERNAME_OR_PASSWORD = "Incorrect username or password";

    @FindBy(id = "login_field")
    private WebElement usernameButton;

    @FindBy(id = "password")
    private WebElement passwordButton;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement signInButton;

    public MainPage login(User user) throws InterruptedException {
        Thread.sleep(500);
        Browser.getInstance().getDriver().navigate().refresh();
        sendKeys(usernameButton, user.getUserName());
        sendKeys(passwordButton, user.getUserPassword());
        clickOnTheVisibleItem(signInButton);
        return new MainPage();
    }
}
