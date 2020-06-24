package com.jotformeu;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jotformeu.drivermanager.WebFactory;
import com.jotformeu.pageobjects.EmailPageObject;
import com.jotformeu.pageobjects.FileUploadPageObject;
import com.jotformeu.pageobjects.PhoneNumberPageObject;
import com.jotformeu.pageobjects.SecurityQuestionPageObject;
import com.jotformeu.pageobjects.SignaturePageObject;
import com.jotformeu.pageobjects.ThankYouPageObject;
import com.jotformeu.pageobjects.WelcomePageObject;

public class E2ETest {

    private static final String TEST_FORM_HEADER = "test_form_header";
    private static final String SIGNATURE_HEADER = "signature_header";
    private static final String PHONE_NUMBER_HEADER = "phone_number_header";
    private static final String SECURITY_QUESTION_HEADER = "security_question_header";
    private static final String SECURITY_QUESTION = "security_question_dog";
    private static final String THANK_YOU_HEADER = "thank_you_header";
    private static final String SUBMISSION_RECEIVED_MESSAGE = "submission_received_message";
    private static final String FORM_PAGE = "https://form.jotformeu.com/92543326450353";
    private static WebDriver driver;
    private static WelcomePageObject welcomePageObject;
    private static FileUploadPageObject fileUploadPageObject;
    private static SignaturePageObject signaturePageObject;
    private static PhoneNumberPageObject phoneNumberPageObject;
    private static SecurityQuestionPageObject securityQuestionPageObject;
    private static EmailPageObject emailPageObject;
    private static ThankYouPageObject thankYouPageObject;
    private Map<String, String> localization_texts = new HashMap<>();

    private static final Logger LOGGER = LoggerFactory.getLogger(E2ETest.class);

    public static Stream<Arguments> translationDataProvider() {
        return Stream.of(
            Arguments.of("en", "GB"),
            // It will fail since no localization on the site
            Arguments.of("hu", "HU"));
    }

    @BeforeAll
    static void beforeAll() {
        driver = WebFactory.getDriver();
        welcomePageObject = new WelcomePageObject(driver);
        fileUploadPageObject = new FileUploadPageObject(driver);
        signaturePageObject = new SignaturePageObject(driver);
        phoneNumberPageObject = new PhoneNumberPageObject(driver);
        securityQuestionPageObject = new SecurityQuestionPageObject(driver);
        emailPageObject = new EmailPageObject(driver);
        thankYouPageObject = new ThankYouPageObject(driver);
    }

    @BeforeEach
    public void setUp() {
        driver.manage().deleteAllCookies();
        driver.get(FORM_PAGE);
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }

    @ParameterizedTest
    @MethodSource("translationDataProvider")
    public void testSignature(String pos, String locale) {
        // GIVEN
        setLocalizationKeys();
        Locale.setDefault(new Locale(pos, locale));
        LOGGER.info("Current Locale: {}", Locale.getDefault());
        ResourceBundle mybundle = ResourceBundle.getBundle("form_text");

        // WHEN
        verifyWelcomePage(mybundle.getString(localization_texts.get(TEST_FORM_HEADER)));
        continueToFileUploadPage();

        uploadFile();
        verifyUploadFilePage();
        continueToSignaturePage();

        drawSignature();
        verifySignaturePage(mybundle.getString(localization_texts.get(SIGNATURE_HEADER)));
        continueToPhoneNumberPage();

        fillPhoneNumberPage();
        verifyPhoneNumberPage(mybundle.getString(localization_texts.get(PHONE_NUMBER_HEADER)));
        continueToSecurityQuestionPage();

        fillSecurityQuestionPage(mybundle.getString(localization_texts.get(SECURITY_QUESTION)));
        verifySecurityQuestionPage(mybundle.getString(localization_texts.get(SECURITY_QUESTION_HEADER)));
        continueToEmailPage();

        fillEmailPage();
        verifyEmailPage();
        submitForm();

        // THEN
        thankYouPageObject.isThankYouBlockDisplayed();
        assertThat(thankYouPageObject.getTextOfThankYouHeader(), is(mybundle.getString(localization_texts.get(THANK_YOU_HEADER))));
        assertThat(thankYouPageObject.getTextOfThankYouMessage(), is(mybundle.getString(localization_texts.get(SUBMISSION_RECEIVED_MESSAGE))));
    }

    public void verifyWelcomePage(String testFormHeader) {
        assertThat(welcomePageObject.getTextOfWelcomePageHeader(), is(testFormHeader));

    }

    public void continueToFileUploadPage() {
        welcomePageObject.clickOnNextButton();
    }

    public void uploadFile() {
        fileUploadPageObject.uploadFile();
    }

    public void verifyUploadFilePage() {
        assertFalse(fileUploadPageObject.isQuestionLabelOnFileUploadDisplayed());
        assertTrue(fileUploadPageObject.isMandatoryDisplayedOnFileUpload());
        assertTrue(fileUploadPageObject.isDeleteFileDisplayedOnFileUpload());
    }

    public void continueToSignaturePage() {
        fileUploadPageObject.continueToSignature();
    }

    public void drawSignature() {
        signaturePageObject.drawSignature();
    }

    public void verifySignaturePage(String signatureHeader) {
        assertThat(signaturePageObject.getTextOfQuestionLabelOnSignature(), is(signatureHeader));
        assertTrue(signaturePageObject.isMandatoryDisplayedOnSignature());
        assertTrue(signaturePageObject.isClearSignatureDisplayed());
    }

    public void continueToPhoneNumberPage() {
        signaturePageObject.continueToPhoneNumber();
    }

    public void fillPhoneNumberPage() {
        phoneNumberPageObject.fillAreaField("20");
        phoneNumberPageObject.fillPhoneNumberField("1234567");
    }

    public void verifyPhoneNumberPage(String phoneNumberHeader) {
        assertThat(phoneNumberPageObject.getTextOfQuestionLabelOnPhoneNumber(), is(phoneNumberHeader));
        assertTrue(phoneNumberPageObject.isMandatoryDisplayedOnPhoneNumber());
    }

    public void continueToSecurityQuestionPage() {
        phoneNumberPageObject.continueToSecurityQuestion();
    }

    public void fillSecurityQuestionPage(String secretQuestion) {
        securityQuestionPageObject.clickSecurityQuestionDropDown();
        securityQuestionPageObject.selectQuestion(secretQuestion);
        securityQuestionPageObject.fillSecretAnswer("test");
    }

    public void verifySecurityQuestionPage(String securityQuestionHeader) {
        assertThat(securityQuestionPageObject.getTextOfQuestionLabelOnSecurityQuestion(), is(securityQuestionHeader));
    }

    public void continueToEmailPage() {
        securityQuestionPageObject.continueToEmail();
    }

    public void fillEmailPage() {
        emailPageObject.fillEmailField("secret@email.com");
    }

    public void verifyEmailPage() {
        assertThat(emailPageObject.getTextOfQuestionLabelOnEmail(), is("E-mail"));
    }

    public void submitForm() {
        emailPageObject.submitForm();
    }

    public Map<String, String> setLocalizationKeys() {
        localization_texts.put(TEST_FORM_HEADER, TEST_FORM_HEADER);
        localization_texts.put(SIGNATURE_HEADER, SIGNATURE_HEADER);
        localization_texts.put(PHONE_NUMBER_HEADER, PHONE_NUMBER_HEADER);
        localization_texts.put(SECURITY_QUESTION_HEADER, SECURITY_QUESTION_HEADER);
        localization_texts.put(SECURITY_QUESTION, SECURITY_QUESTION);
        localization_texts.put(THANK_YOU_HEADER, THANK_YOU_HEADER);
        localization_texts.put(SUBMISSION_RECEIVED_MESSAGE, SUBMISSION_RECEIVED_MESSAGE);
        return localization_texts;
    }
}
