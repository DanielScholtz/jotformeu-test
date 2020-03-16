package com.jotformeu.pageobjects;

import com.jotformeu.BasePageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;

public class FileUploadPageObjects extends BasePageObjects {

    @FindBy(name = "file")
    private WebElement fileUploadBrowseFiles;

    @FindBy(css = ".qq-upload-delete")
    private WebElement fileUploadDeleteFile;

    @FindBys( {
            @FindBy(css = "#cid_12"),
            @FindBy(css = ".jfInput-button.forNext.u-right")
    })
    private WebElement fileUploadNext;

    @FindBys( {
            @FindBy(css = "#cid_12"),
            @FindBy(css = ".jsQuestionLabelContainer")
    })
    private WebElement fileUploadQuestionLabel;

    @FindBys( {
            @FindBy(css = "#cid_12"),
            @FindBy(css = ".jfRequiredStar")
    })
    private WebElement fileUploadIsMandatory;

    public FileUploadPageObjects(WebDriver driver) {
        super(driver);
    }

    public void uploadFile() {
        String filename = "src/test/resources/com/jotformeu/uploadfile/test.txt";
        File file = new File(filename);
        String absolutePathOfFile = file.getAbsolutePath();
        fileUploadBrowseFiles.sendKeys(absolutePathOfFile);
        wait.until(ExpectedConditions.visibilityOfAllElements(fileUploadDeleteFile));
    }

    public void continueToSignature() {
        fileUploadNext.click();
    }

    public void isDeleteFileDisplayed() {
        isDisplayed(fileUploadBrowseFiles);
    }
}
