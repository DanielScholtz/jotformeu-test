package com.jotformeu.pageobjects;

import com.jotformeu.BasePageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

public class SecurityQuestionPageObjects  extends BasePageObjects {

    @FindBy(css = ".jfDropdown-chipContainer")
    private WebElement dropDownQuestion;

    @FindBy(css = "#jfDropdown-optionList-13-mixed-dropdown > ul > li")
    private List<WebElement> dropDownOptions;

    @FindBy(css = "#input_13_field_2")
    private WebElement answerField;

    @FindBys( {
            @FindBy(css = "#cid_13"),
            @FindBy(css = ".jfInput-button.forNext.u-right")
    })
    private WebElement securityQuestionNext;

    public void clickSecurityQuestionDropDown() {
        dropDownQuestion.click();
    }

    public void selectQuestion(String question) {
        for (WebElement dropDownOption : dropDownOptions) {
            if (dropDownOption.getText().equals(question)) {
                dropDownOption.click();
            }
        }
    }

    public void fillSecretAnswer(String answer) {
        answerField.sendKeys(answer);
    }

    public void continueToEmail() {
        securityQuestionNext.click();
    }

    public SecurityQuestionPageObjects(WebDriver driver) {
        super(driver);
    }
}
