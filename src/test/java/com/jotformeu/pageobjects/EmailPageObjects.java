package com.jotformeu.pageobjects;

import com.jotformeu.BasePageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class EmailPageObjects extends BasePageObjects {

    @FindBy(name = "q5_email")
    private WebElement emailField;

    @FindBys( {
            @FindBy(css = "#cid_5"),
            @FindBy(css = ".jfInput-button.forSubmit.form-submit-button.u-right")
    })
    private WebElement submitForm;

    public void fillEmailField(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailField));
        emailField.sendKeys(email);
    }

    public void submitForm() {
        submitForm.click();
    }

    public EmailPageObjects(WebDriver driver) {
        super(driver);
    }
}
