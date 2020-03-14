package com.jotformeu.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WelcomePageObjects extends SharedPageObjects {

    @FindBy(css = "#header_welcomePage")
    private WebElement welcomePageHeader;

    @FindBy(css = "#jfCard-welcome-start")
    private WebElement welcomePageNextButton;

    public WelcomePageObjects(WebDriver driver) {
        super(driver);
    }

    public void isHeaderDisplayed() {
        welcomePageHeader.isDisplayed();
    }

    public void clickOnNextButton() {
        welcomePageNextButton.click();
    }
}
