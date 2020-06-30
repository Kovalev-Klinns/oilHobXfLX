package com.epam.github.test;

import com.epam.github.page.MainPage;
import com.epam.github.service.DataFromProperty;
import com.epam.github.service.PropertyReader;
import com.epam.github.models.User;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.epam.github.page.LoginPage.ERROR_MESSAGE_INCORRECT_USERNAME_OR_PASSWORD;
import static com.epam.github.service.PropertyReader.getTestData;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class LoginPageTest extends BaseTest {

    @Test
    public void oneCanLoginGithub() {
        User testUser = PropertyReader.getUserWithCredentialsFromProperty();
        String loggedInUserName = new MainPage()
                .clickSignIn()
                .login(testUser)
                .getLoggedInUserName();

        assertThat("Logged in username isn't correct", loggedInUserName, is(equalTo(testUser.getUserName())));
    }

    @Test
    public void loginGithubWithIncorrectPassword() {
        User testUser = new User(getTestData(DataFromProperty.TEST_USER_NAME.getKey()),
                getTestData(DataFromProperty.TEST_USER_PASSWORD.getKey()) + "Qzki!@");
        String actualErrorMessage = new MainPage()
                .clickSignIn()
                .login(testUser)
                .getErrorMessage();

        Assert.assertTrue(actualErrorMessage.contains(ERROR_MESSAGE_INCORRECT_USERNAME_OR_PASSWORD));
    }
}