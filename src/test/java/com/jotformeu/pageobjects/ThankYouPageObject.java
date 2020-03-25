package com.jotformeu.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.jotformeu.BasePageObjects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ThankYouPageObject extends BasePageObjects {

    @FindBy(className = "jfThankYou-imageWrapper")
    private WebElement thankYouBlock;

    @FindBy(css = ".jfThankYou-header.form-header")
    private WebElement thankYouHeader;

    @FindBy(css = ".jfThankYou-description.form-subHeader")
    private WebElement thankYouMsg;

    @Autowired
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
