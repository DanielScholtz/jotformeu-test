package com.jotformeu.pageobjects;

import com.jotformeu.BasePageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class SignaturePageObjects extends BasePageObjects {

    @FindBy(css = "canvas.jSignature")
    private WebElement signatureField;

    @FindBys({
            @FindBy(css = "#cid_14"),
            @FindBy(css = ".jfInput-button.forNext.u-right")
    })
    private WebElement signatureNext;


    public SignaturePageObjects(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void drawSignature() {
        // A simple click would have been enough on the element but wanted to draw something
        Actions builder = new Actions(driver);
        builder.moveToElement(signatureField)
                .clickAndHold(signatureField)
                .moveByOffset(100, 60)
                .release(signatureField)
                .build()
                .perform();
    }

    public void continueToPhoneNumber() {
        signatureNext.click();
    }
}
