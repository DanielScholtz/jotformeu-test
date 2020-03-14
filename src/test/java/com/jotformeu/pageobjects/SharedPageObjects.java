package com.jotformeu.pageobjects;

import com.jotformeu.drivermanager.DriverManager;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SharedPageObjects {

    @FindBy(css = "#cardProgressToggle")
    private WebElement progressButton;

    @FindBy(css = "#cid_12 > div > div.jfCard-actions > button.jfInput-button.forNext.u-right")
    private WebElement nextButton;

    @FindBy(css = ".jfCard-actions > button.jfInput-button.forPrev.u-left")
    private WebElement prevButton;

    public WebDriverWait wait;
    public DriverManager drivermanager = new DriverManager();
    public WebDriver driver;

    public SharedPageObjects(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(this.driver, 10);
    }

    public void clickNext() {
        nextButton.click();
    }

    public void clickPrev() {
        prevButton.click();
    }

    public void clickProgressButton() {
        progressButton.click();
    }

    public boolean isDisplayed(WebElement webElement) {
        boolean isDisplayed;
        try {
            isDisplayed = webElement.isDisplayed();
        } catch (NoSuchElementException | NullPointerException exception) {
            isDisplayed = false;
        }
        return isDisplayed;
    }
}
