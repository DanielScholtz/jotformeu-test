package com.jotformeu;

import com.jotformeu.drivermanager.DriverManager;
import com.jotformeu.pageobjects.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class E2ETest {

    public WebDriver driver;
    public WebDriverWait wait;
    public WelcomePageObjects welcomePageObjects;
    public FileUploadPageObjects fileUploadPageObjects;
    public SignaturePageObjects signaturePageObjects;
    private PhoneNumberPageObjects phoneNumberPageObjects;
    private SecurityQuestionPageObjects securityQuestionPageObjects;
    private EmailPageObjects emailPageObjects;
    private ThankYouPageObjects thankYouPageObjects;
    public static final String FORM_PAGE = "https://form.jotformeu.com/92543326450353";


    @BeforeEach
    public void setUp() {
        driver = DriverManager.getDriver(driver);
        PageFactory.initElements(driver, this);
        welcomePageObjects = new WelcomePageObjects(driver);
        fileUploadPageObjects = new FileUploadPageObjects(driver);
        signaturePageObjects = new SignaturePageObjects(driver);
        phoneNumberPageObjects = new PhoneNumberPageObjects(driver);
        securityQuestionPageObjects = new SecurityQuestionPageObjects(driver);
        emailPageObjects = new EmailPageObjects(driver);
        thankYouPageObjects = new ThankYouPageObjects(driver);
        driver.manage().window().maximize();
        driver.get(FORM_PAGE);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Happy Path")
    public void testSignature() {
        welcomePageTest();

        uploadFilePageTest();

        signaturePageObjects.drawSignature();
        signaturePageObjects.continueToPhoneNumber();

        phoneNumberPageObjects.fillAreaField("20");
        phoneNumberPageObjects.fillPhoneNumberField("1234567");
        phoneNumberPageObjects.continueToSecurityQuestion();

        securityQuestionPageObjects.clickSecurityQuestionDropDown();
        securityQuestionPageObjects.selectQuestion("What is your first dog's name?");
        securityQuestionPageObjects.fillSecretAnswer("test");
        securityQuestionPageObjects.continueToEmail();

        emailPageObjects.fillEmailField("secret@email.com");
        emailPageObjects.submitForm();

        thankYouPageObjects.isThankYouHeaderDisplayed();
    }

    public void welcomePageTest() {
        assertThat(welcomePageObjects.getTextOfWelcomePageHeader(), is("Test Form"));
        welcomePageObjects.clickOnNextButton();
    }

    public void uploadFilePageTest() {
        fileUploadPageObjects.uploadFile();
        fileUploadPageObjects.continueToSignature();
    }
}
