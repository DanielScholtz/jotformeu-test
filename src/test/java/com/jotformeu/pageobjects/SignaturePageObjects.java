package com.jotformeu.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.jotformeu.BasePageObjects;

public class SignaturePageObjects extends BasePageObjects {

    @FindBy(css = "canvas.jSignature")
    private WebElement signatureField;

    @FindBy(css = "#signature_pad_14 > span")
    private WebElement clearSignatureBtn;

    @FindBys({
        @FindBy(css = "#cid_14"),
        @FindBy(css = ".jfInput-button.forNext.u-right")
    })
    private WebElement signatureNext;

    @FindBys({
        @FindBy(css = "#cid_14"),
        @FindBy(css = ".jsQuestionLabelContainer")
    })
    private WebElement signatureQuestionLabel;

    @FindBys({
        @FindBy(css = "#cid_14"),
        @FindBy(css = ".jfRequiredStar")
    })
    private WebElement signatureIsMandatory;

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

    public String getTextOfQuestionLabelOnSignature() {
        wait.until(ExpectedConditions.visibilityOfAllElements(signatureQuestionLabel));
        return signatureQuestionLabel.getText();
    }

    public boolean isMandatoryDisplayedOnSignature() {
        return isDisplayed(signatureIsMandatory);
    }

    public boolean isClearSignatureDisplayed() {
        return isDisplayed(clearSignatureBtn);
    }

    public SignaturePageObjects(WebDriver driver) {
        super(driver);
    }
}
