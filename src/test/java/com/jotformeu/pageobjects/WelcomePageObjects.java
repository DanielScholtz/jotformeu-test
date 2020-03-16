package com.jotformeu.pageobjects;

import com.jotformeu.BasePageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class WelcomePageObjects extends BasePageObjects {

    @FindBy(css = "#header_welcomePage")
    private WebElement welcomePageHeader;

    @FindBy(css = "#jfCard-welcome-start")
    private WebElement welcomePageNextButton;

    public WelcomePageObjects(WebDriver driver) {
        super(driver);
    }

    public String getTextOfWelcomePageHeader() {
        return welcomePageHeader.getText();
    }

    public void clickOnNextButton() {
        wait.until(ExpectedConditions.visibilityOf(welcomePageHeader));
        welcomePageNextButton.click();
    }
}
