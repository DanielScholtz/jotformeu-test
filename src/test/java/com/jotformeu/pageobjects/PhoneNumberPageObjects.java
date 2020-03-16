package com.jotformeu.pageobjects;

import com.jotformeu.BasePageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

public class PhoneNumberPageObjects extends BasePageObjects {

    @FindBy(xpath = "//*[@id='input_4_area']")
    private WebElement areaField;

    @FindBy(xpath = "//*[@id='input_4_phone']")
    private WebElement phoneNumberField;

    @FindBys( {
            @FindBy(css = "#cid_4"),
            @FindBy(css = ".jfInput-button.forNext.u-right")
    })
    private WebElement phoneNumberNext;

    public void fillAreaField(String area) {
        areaField.sendKeys(area);
    }

    public void fillPhoneNumberField(String phoneNumber) {
        phoneNumberField.sendKeys(phoneNumber);
    }

    public void continueToSecurityQuestion() {
        phoneNumberNext.click();
    }

    public PhoneNumberPageObjects(WebDriver driver) {
        super(driver);
    }
}
