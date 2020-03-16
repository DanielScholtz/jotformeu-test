package com.jotformeu;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.jotformeu.drivermanager.DriverManager;
import com.jotformeu.pageobjects.EmailPageObjects;
import com.jotformeu.pageobjects.FileUploadPageObjects;
import com.jotformeu.pageobjects.PhoneNumberPageObjects;
import com.jotformeu.pageobjects.SecurityQuestionPageObjects;
import com.jotformeu.pageobjects.SignaturePageObjects;
import com.jotformeu.pageobjects.ThankYouPageObjects;
import com.jotformeu.pageobjects.WelcomePageObjects;

public class E2ETest {

    public WebDriver driver;
    private WelcomePageObjects welcomePageObjects;
    private FileUploadPageObjects fileUploadPageObjects;
    private SignaturePageObjects signaturePageObjects;
    private PhoneNumberPageObjects phoneNumberPageObjects;
    private SecurityQuestionPageObjects securityQuestionPageObjects;
    private EmailPageObjects emailPageObjects;
    private ThankYouPageObjects thankYouPageObjects;
    private static final String FORM_PAGE = "https://form.jotformeu.com/92543326450353";
    private static final String TEST_FORM_EN = "Test Form";
    private static final String TEST_FORM_HU = "Teszt Űrlap";
    private static final String SIGNATURE_EN = "Signature";
    private static final String SIGNATURE_HU = "Aláírás";
    private static final String PHONE_NUMBER_EN = "Model Phone Number";
    private static final String PHONE_NUMBER_HU = "Telefonszám";
    private static final String SECURITY_QUESTION_EN = "Security question";
    private static final String SECURITY_QUESTION_HU = "Titkos kérdés";
    private static final String SECRET_QUESTION_EN = "What is your first dog's name?";
    private static final String SECRET_QUESTION_HU = "Mi az első kutyájának a neve?";
    private static final String THANK_YOU_EN = "Thank You!";
    private static final String THANK_YOU_HU = "Köszönjük!";
    private static final String SUBMISSION_RECEIVED_EN = "Your submission has been received!";
    private static final String SUBMISSION_RECEIVED_HU = "Az űrlapját megkaptuk!";

    public static Stream<Arguments> translationDataProvider() {
        return Stream.of(
            Arguments.of(TEST_FORM_EN, SIGNATURE_EN, PHONE_NUMBER_EN, SECURITY_QUESTION_EN, SECRET_QUESTION_EN,
                THANK_YOU_EN, SUBMISSION_RECEIVED_EN));
        //            Would turn on if there would be localization
        //            Arguments.of(TEST_FORM_HU, SIGNATURE_HU, PHONE_NUMBER_HU, SECURITY_QUESTION_HU, SECRET_QUESTION_HU,
        //                            THANK_YOU_HU, SUBMISSION_RECEIVED_HU));
    }

    @BeforeEach
    public void setUp() {
        driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
        welcomePageObjects = new WelcomePageObjects(driver);
        fileUploadPageObjects = new FileUploadPageObjects(driver);
        signaturePageObjects = new SignaturePageObjects(driver);
        phoneNumberPageObjects = new PhoneNumberPageObjects(driver);
        securityQuestionPageObjects = new SecurityQuestionPageObjects(driver);
        emailPageObjects = new EmailPageObjects(driver);
        thankYouPageObjects = new ThankYouPageObjects(driver);
        driver.get(FORM_PAGE);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @ParameterizedTest
    @MethodSource("translationDataProvider")
    public void testSignature(String testFormHeader, String signatureHeader, String phoneNumberHeader, String securityQuestionHeader,
        String secretQuestion, String thankYouHeader, String thankYouMessage) {
        welcomePageTest(testFormHeader);

        uploadFilePageTest();

        signaturePageTest(signatureHeader);

        phoneNumberPageTest(phoneNumberHeader);

        securityQuestionPageTest(securityQuestionHeader, secretQuestion);

        emailPageTest();

        thankYouPageObjects.isThankYouBlockDisplayed();
        assertThat(thankYouPageObjects.getTextOfThankYouHeader(), is(thankYouHeader));
        assertThat(thankYouPageObjects.getTextOfThankYouMessage(), is(thankYouMessage));
    }

    public void welcomePageTest(String testFormHeader) {
        assertThat(welcomePageObjects.getTextOfWelcomePageHeader(), is(testFormHeader));
        welcomePageObjects.clickOnNextButton();
    }

    public void uploadFilePageTest() {
        fileUploadPageObjects.uploadFile();
        assertFalse(fileUploadPageObjects.isQuestionLabelOnFileUploadDisplayed());
        assertTrue(fileUploadPageObjects.isMandatoryDisplayedOnFileUpload());
        assertTrue(fileUploadPageObjects.isDeleteFileDisplayedOnFileUpload());
        fileUploadPageObjects.continueToSignature();
    }

    public void signaturePageTest(String signatureHeader) {
        signaturePageObjects.drawSignature();
        assertThat(signaturePageObjects.getTextOfQuestionLabelOnSignature(), is(signatureHeader));
        assertTrue(signaturePageObjects.isMandatoryDisplayedOnSignature());
        assertTrue(signaturePageObjects.isClearSignatureDisplayed());
        signaturePageObjects.continueToPhoneNumber();
    }

    public void phoneNumberPageTest(String phoneNumberHeader) {
        phoneNumberPageObjects.fillAreaField("20");
        phoneNumberPageObjects.fillPhoneNumberField("1234567");
        assertThat(phoneNumberPageObjects.getTextOfQuestionLabelOnPhoneNumber(), is(phoneNumberHeader));
        assertTrue(phoneNumberPageObjects.isMandatoryDisplayedOnPhoneNumber());
        phoneNumberPageObjects.continueToSecurityQuestion();
    }

    public void securityQuestionPageTest(String securityQuestionHeader, String secretQuestion) {
        securityQuestionPageObjects.clickSecurityQuestionDropDown();
        securityQuestionPageObjects.selectQuestion(secretQuestion);
        securityQuestionPageObjects.fillSecretAnswer("test");
        assertThat(securityQuestionPageObjects.getTextOfQuestionLabelOnSecurityQuestion(), is(securityQuestionHeader));
        securityQuestionPageObjects.continueToEmail();
    }

    public void emailPageTest() {
        emailPageObjects.fillEmailField("secret@email.com");
        assertThat(emailPageObjects.getTextOfQuestionLabelOnEmail(), is("E-mail"));
        emailPageObjects.submitForm();
    }
}
