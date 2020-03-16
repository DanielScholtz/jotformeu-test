package com.jotformeu.pageobjects;

import com.jotformeu.BasePageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ThankYouPageObjects extends BasePageObjects {

    @FindBy(css = ".jfThankYou-header.form-header")
    private WebElement thankYouHeader;

    public void isThankYouHeaderDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(thankYouHeader));
        isDisplayed(thankYouHeader);
    }

    public ThankYouPageObjects(WebDriver driver) {
        super(driver);
    }
}
