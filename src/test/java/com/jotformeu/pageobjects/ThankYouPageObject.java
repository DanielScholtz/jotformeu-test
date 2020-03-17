package com.jotformeu.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.jotformeu.BasePageObjects;

public class ThankYouPageObject extends BasePageObjects {

    @FindBy(className = "jfThankYou-imageWrapper")
    private WebElement thankYouBlock;

    @FindBy(css = ".jfThankYou-header.form-header")
    private WebElement thankYouHeader;

    @FindBy(css = ".jfThankYou-description.form-subHeader")
    private WebElement thankYouMsg;

    public ThankYouPageObject(WebDriver driver) {
        super(driver);
    }

    public void isThankYouBlockDisplayed() {
        isDisplayed(thankYouBlock);
    }

    public String getTextOfThankYouHeader() {
        return thankYouHeader.getText();
    }

    public String getTextOfThankYouMessage() {
        return thankYouMsg.getText();
    }
}
