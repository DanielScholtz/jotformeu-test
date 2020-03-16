package com.jotformeu;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.jotformeu.drivermanager.DriverManager;

public class BasePageObjects {

    public WebDriverWait wait;
    public DriverManager drivermanager = new DriverManager();
    public WebDriver driver;

    public BasePageObjects(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(this.driver, 10);
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
