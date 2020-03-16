package com.jotformeu.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.jotformeu.BasePageObjects;

public class PhoneNumberPageObjects extends BasePageObjects {

    @FindBy(xpath = "//*[@id='input_4_area']")
    private WebElement areaField;

    @FindBy(xpath = "//*[@id='input_4_phone']")
    private WebElement phoneNumberField;

    @FindBys({
        @FindBy(css = "#cid_4"),
        @FindBy(css = ".jfInput-button.forNext.u-right")
    })
    private WebElement phoneNumberNext;

    @FindBys({
        @FindBy(css = "#cid_4"),
        @FindBy(css = ".jsQuestionLabelContainer")
    })
    private WebElement phoneNumberQuestionLabel;

    @FindBys({
        @FindBy(css = "#cid_4"),
        @FindBy(css = ".jfRequiredStar")
    })
    private WebElement phoneNumberIsMandatory;

    public void fillAreaField(String area) {
        areaField.sendKeys(area);
    }

    public void fillPhoneNumberField(String phoneNumber) {
        phoneNumberField.sendKeys(phoneNumber);
    }

    public void continueToSecurityQuestion() {
        phoneNumberNext.click();
    }

    public String getTextOfQuestionLabelOnPhoneNumber() {
        wait.until(ExpectedConditions.visibilityOfAllElements(phoneNumberQuestionLabel));
        return phoneNumberQuestionLabel.getText();
    }

    public boolean isMandatoryDisplayedOnPhoneNumber() {
        return isDisplayed(phoneNumberIsMandatory);
    }

    public PhoneNumberPageObjects(WebDriver driver) {
        super(driver);
    }
}
