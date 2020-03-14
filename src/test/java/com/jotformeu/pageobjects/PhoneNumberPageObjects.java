package com.jotformeu.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PhoneNumberPageObjects {

    @FindBy(xpath = "//*[@id=\"input_4_area\"]")
    private WebElement AreaField;

    @FindBy(xpath = "//*[@id=\"input_4_phone\"]")
    private WebElement phoneNumberField;
}
