package com.epam.github.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IssuePage extends BasePage {

    @FindBy(id = "new_comment_field")
    private WebElement newCommentField;

    @FindBy(xpath = "//*[@id='partial-new-comment-form-actions']//div[2]/button")
    private WebElement commentButton;

    private final String createdCommentLocator = "//*/p[text()='%s']";

    public IssuePage createComment(String comment) {
        sendKeys(newCommentField, comment);
        clickOnTheClickableItem(commentButton);
        return this;
    }

    public boolean isCommentCreated(String comment) {
        return isElementExistsByLocator(By.xpath(String.format(createdCommentLocator, comment)));
    }
}
