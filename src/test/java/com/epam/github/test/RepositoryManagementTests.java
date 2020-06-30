package com.epam.github.test;

import com.epam.github.models.Issue;
import com.epam.github.models.Repository;
import com.epam.github.page.IssuePage;
import com.epam.github.page.MainPage;
import com.epam.github.service.ApiService;
import com.epam.github.service.PropertyReader;
import com.epam.github.models.User;
import com.epam.github.util.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class RepositoryManagementTests extends BaseTest {

    @Test
    public void oneCanCreateNewRepository() {
        final String randomName = RandomStringUtils.getRandomName();
        User testUser = PropertyReader.getUserWithCredentialsFromProperty();
        String actualRepositoryName = new MainPage()
                .clickSignIn()
                .login(testUser)
                .clickNewRepository()
                .createNewRepository(randomName)
                .getNewRepositoryName();

        assertThat("Name repository isn't correct", actualRepositoryName, is(equalTo(randomName)));
    }

    @Test
    public void OneCanCreateCommentToIssue() {
        Repository repository = new Repository(RandomStringUtils.getRandomName());
        Issue issue = new Issue(RandomStringUtils.getRandomName());
        ApiService apiService = new ApiService();
        final String issueComment = "This is a comment for issue";
        User testUser = PropertyReader.getUserWithCredentialsFromProperty();
        apiService.createEmptyRepository(repository);
        apiService.createIssueInRepository(repository, issue);
        MainPage mainPage = new MainPage();
        IssuePage issuePage = new IssuePage();
        mainPage
                .clickSignIn()
                .login(testUser)
                .openCreatedIssue(issue)
                .createComment(issueComment);
        try {
            Assert.assertTrue(issuePage.isCommentCreated(issueComment));
        } finally {
            apiService.deleteRepository(repository);
        }
    }
}