package com.jotformeu.pageobjects;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.jotformeu.BasePageObjects;

public class FileUploadPageObject extends BasePageObjects {

    private static final String FILE_NAME = "src/test/resources/com/jotformeu/uploadfile/test.txt";

    @FindBy(name = "file")
    private WebElement fileUploadBrowseFiles;

    @FindBy(className = "qq-upload-delete")
    private WebElement fileUploadDeleteFile;

    @FindBys({
        @FindBy(css = "#cid_12"),
        @FindBy(css = ".jfInput-button.forNext.u-right")
    })
    private WebElement fileUploadNext;

    @FindBys({
        @FindBy(css = "#cid_12"),
        @FindBy(css = ".jsQuestionLabelContainer")
    })
    private WebElement fileUploadQuestionLabel;

    @FindBys({
        @FindBy(css = "#cid_12"),
        @FindBy(css = ".jfRequiredStar")
    })
    private WebElement fileUploadIsMandatory;

    public FileUploadPageObject(WebDriver driver) {
        super(driver);
    }

    public void uploadFile() {
        File file = new File(FILE_NAME);
        String absolutePathOfFile = file.getAbsolutePath();
        fileUploadBrowseFiles.sendKeys(absolutePathOfFile);
        wait.until(ExpectedConditions.visibilityOfAllElements(fileUploadDeleteFile));
    }

    public void continueToSignature() {
        fileUploadNext.click();
    }

    public String getTextOfQuestionLabelOnFileUpload() {
        wait.until(ExpectedConditions.visibilityOfAllElements(fileUploadQuestionLabel));
        return fileUploadQuestionLabel.getText();
    }

    public boolean isQuestionLabelOnFileUploadDisplayed() {
        return isDisplayed(fileUploadQuestionLabel);
    }

    public boolean isMandatoryDisplayedOnFileUpload() {
        return isDisplayed(fileUploadIsMandatory);
    }

    public boolean isDeleteFileDisplayedOnFileUpload() {
        return isDisplayed(fileUploadDeleteFile);
    }
}
