package com.jotformeu.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.jotformeu.BasePageObjects;

public class SecurityQuestionPageObject extends BasePageObjects {

    @FindBy(className = "jfDropdown-chipContainer")
    private WebElement dropDownQuestion;

    @FindBy(css = "#jfDropdown-optionList-13-mixed-dropdown > ul > li")
    private List<WebElement> dropDownOptions;

    @FindBy(id = "input_13_field_2")
    private WebElement answerField;

    @FindBys({
        @FindBy(css = "#cid_13"),
        @FindBy(css = ".jfInput-button.forNext.u-right")
    })
    private WebElement securityQuestionNext;

    @FindBys({
        @FindBy(css = "#cid_13"),
        @FindBy(css = ".jsQuestionLabelContainer")
    })
    private WebElement securityQuestionLabel;

    public SecurityQuestionPageObject(WebDriver driver) {
        super(driver);
    }

    public void clickSecurityQuestionDropDown() {
        dropDownQuestion.click();
    }

    public void selectQuestion(String question) {
        // Used loop for searching the text instead of getting the nth index since the order of the question could change
        for (WebElement dropDownOption : dropDownOptions) {
            if (question.equals(dropDownOption.getText())) {
                dropDownOption.click();
            }
        }
    }

    public void fillSecretAnswer(String answer) {
        answerField.sendKeys(answer);
    }

    public void continueToEmail() {
        securityQuestionNext.click();
    }

    public String getTextOfQuestionLabelOnSecurityQuestion() {
        wait.until(ExpectedConditions.visibilityOfAllElements(securityQuestionLabel));
        return securityQuestionLabel.getText();
    }
}
