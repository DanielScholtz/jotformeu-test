package com.jotformeu.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.jotformeu.BasePageObjects;

public class EmailPageObject extends BasePageObjects {

    @FindBy(name = "q5_email")
    private WebElement emailField;

    @FindBys({
        @FindBy(css = "#id_5"),
        @FindBy(css = "button[type=submit]")
    })
    private WebElement submitForm;

    @FindBy(css = "#label_5 > span")
    private WebElement emailQuestionLabel;

    public EmailPageObject(WebDriver driver) {
        super(driver);
    }

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
}
