package com.jotformeu;

import com.jotformeu.drivermanager.DriverManager;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

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
