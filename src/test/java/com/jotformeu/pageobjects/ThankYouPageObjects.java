package com.jotformeu.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.jotformeu.BasePageObjects;

public class ThankYouPageObjects extends BasePageObjects {

    @FindBy(css = ".jfThankYou-imageWrapper")
    private WebElement thankYouBlock;

    @FindBy(css = ".jfThankYou-header.form-header")
    private WebElement thankYouHeader;

    @FindBy(css = ".jfThankYou-description.form-subHeader")
    private WebElement thankYouMsg;

    public void isThankYouBlockDisplayed() {
        isDisplayed(thankYouBlock);
    }

    public String getTextOfThankYouHeader() {
        return thankYouHeader.getText();
    }

    public String getTextOfThankYouMessage() {
        return thankYouMsg.getText();
    }

    public ThankYouPageObjects(WebDriver driver) {
        super(driver);
    }
}
