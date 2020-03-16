package com.jotformeu.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.jotformeu.BasePageObjects;

public class EmailPageObjects extends BasePageObjects {

    @FindBy(name = "q5_email")
    private WebElement emailField;

    @FindBys({
        @FindBy(css = "#cid_5"),
        @FindBy(css = ".jfInput-button.forSubmit.form-submit-button.u-right")
    })
    private WebElement submitForm;

    @FindBys({
        @FindBy(css = "#cid_5"),
        @FindBy(css = ".jsQuestionLabelContainer")
    })
    private WebElement emailQuestionLabel;

    public void fillEmailField(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailField));
        emailField.sendKeys(email);
    }

    public void submitForm() {
        submitForm.click();
    }

    public String getTextOfQuestionLabelOnEmail() {
        wait.until(ExpectedConditions.visibilityOfAllElements(emailQuestionLabel));
        return emailQuestionLabel.getText();
    }

    public EmailPageObjects(WebDriver driver) {
        super(driver);
    }
}
