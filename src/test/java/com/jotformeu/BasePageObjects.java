package com.jotformeu;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasePageObjects {

    protected WebDriverWait wait;
    protected WebDriver driver;

    private static final Logger LOGGER = LoggerFactory.getLogger(BasePageObjects.class);

    public BasePageObjects(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 10);
    }

    public boolean isDisplayed(WebElement webElement) {
        boolean isDisplayed;
        try {
            isDisplayed = webElement.isDisplayed();
        } catch (NoSuchElementException | NullPointerException exception) {
            isDisplayed = false;
            LOGGER.error(webElement + "is not found");

        }
        return isDisplayed;
    }
}
