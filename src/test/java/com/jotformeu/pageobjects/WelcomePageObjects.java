package com.jotformeu.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.jotformeu.BasePageObjects;

public class WelcomePageObjects extends BasePageObjects {

    @FindBy(css = "#header_welcomePage")
    private WebElement welcomePageHeader;

    @FindBy(css = "#jfCard-welcome-start")
    private WebElement welcomePageNextButton;

    public String getTextOfWelcomePageHeader() {
        return welcomePageHeader.getText();
    }

    public void clickOnNextButton() {
        wait.until(ExpectedConditions.visibilityOf(welcomePageHeader));
        welcomePageNextButton.click();
    }

    public WelcomePageObjects(WebDriver driver) {
        super(driver);
    }
}
