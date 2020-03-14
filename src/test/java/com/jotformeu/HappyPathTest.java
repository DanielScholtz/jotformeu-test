package com.jotformeu;

import com.jotformeu.drivermanager.DriverManager;
import com.jotformeu.pageobjects.FileUploadPageObjects;
import com.jotformeu.pageobjects.SharedPageObjects;
import com.jotformeu.pageobjects.SignaturePageObjects;
import com.jotformeu.pageobjects.WelcomePageObjects;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HappyPathTest {

    public WebDriver driver;
    public WebDriverWait wait;
    public SignaturePageObjects signaturePageObjects;
    public WelcomePageObjects welcomePageObjects;
    public FileUploadPageObjects fileUploadPageObjects;
    private SharedPageObjects sharedPageObjects;
    public static final String FORM_PAGE = "https://form.jotformeu.com/92543326450353";


    @BeforeEach
    public void setUp() {
        driver = DriverManager.getDriver(driver, "Chrome");
        PageFactory.initElements(driver, this);
        signaturePageObjects = new SignaturePageObjects(driver);
        welcomePageObjects = new WelcomePageObjects(driver);
        sharedPageObjects = new SharedPageObjects(driver);
        fileUploadPageObjects = new FileUploadPageObjects(driver);
        driver.manage().window().maximize();
        driver.get(FORM_PAGE);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testSignature() throws InterruptedException {
        welcomePageObjects.clickOnNextButton();
        fileUploadPageObjects.uploadFile();
        sharedPageObjects.clickNext();
        signaturePageObjects.drawSignature();
        signaturePageObjects.clickOnNext();
    }
}
