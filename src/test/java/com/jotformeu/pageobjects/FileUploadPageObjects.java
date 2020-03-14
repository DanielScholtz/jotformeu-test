package com.jotformeu.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;

public class FileUploadPageObjects extends WelcomePageObjects {

    @FindBy(name = "file")
    private WebElement fileUploadBrowseFiles;

    @FindBy(css = ".qq-upload-delete")
    private WebElement fileUploadDeleteFile;

    public FileUploadPageObjects(WebDriver driver) {
        super(driver);
    }

    public void uploadFile() {
        String filename = "src/test/resources/com/jotformeu/uploadfile/test.txt";
        File file = new File(filename);
        String path = file.getAbsolutePath();
        fileUploadBrowseFiles.sendKeys(path);
        wait.until(ExpectedConditions.visibilityOfAllElements(fileUploadDeleteFile));
    }

    public void isDeleteFileDisplayed() {
        isDisplayed(fileUploadBrowseFiles);
    }
}
