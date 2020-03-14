package com.jotformeu.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class SignaturePageObjects extends SharedPageObjects {

    @FindBy(css = "canvas.jSignature")
    private WebElement signatureField;

    @FindBy(css = ".clear-pad-btn.clear-pad")
    private WebElement clearSignature;

    @FindBy(css = "#cid_14 > div > div.jfCard-actions > button.jfInput-button.forNext.u-right")
    private WebElement signatureNext;


    public SignaturePageObjects(WebDriver driver) {
        super(driver);
    }

    public void drawSignature() {
        Actions builder = new Actions(driver);
         builder.moveToElement(signatureField)
                .clickAndHold(signatureField)
                .moveByOffset(100, 60)
                .release(signatureField)
                .build()
                .perform();
    }

    public void clickOnNext() {
        signatureNext.click();
    }

    public void clearSignature() {
        clearSignature.click();
    }
}
