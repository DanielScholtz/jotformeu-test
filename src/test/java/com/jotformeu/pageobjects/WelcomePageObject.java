package com.jotformeu.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.jotformeu.BasePageObjects;
import org.springframework.stereotype.Component;

@Component
public class WelcomePageObject extends BasePageObjects {

    @FindBy(id = "header_welcomePage")
    private WebElement welcomePageHeader;

    @FindBy(id = "jfCard-welcome-start")
    private WebElement welcomePageNextButton;

    public WelcomePageObject(WebDriver driver) {
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
