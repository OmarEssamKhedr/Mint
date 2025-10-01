package pages;

import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import testdata.TestData;
import utils.AllureUtils;
import utils.LogUtils;
import utils.WaitUtils;

/**
 * Create Account Page Object
 * Contains elements and methods for the Create Account screen
 *
 * @author Ciye Test Team
 */
public class CreateAccountPage extends BasePage {

    // Page Elements
    private final By emailField = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").text(\"Email\")");
    private final By passwordField = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").text(\"Password\")");
    private final By createAccountButton = AppiumBy.xpath("//android.widget.TextView[@text='Create Account']/parent::android.view.ViewGroup");
    private final By signUpWithGoogleButton = AppiumBy.xpath("//android.widget.TextView[@text='Sign up with Google']/parent::android.view.ViewGroup");
    private final By privacyPolicyLink = AppiumBy.xpath("//android.widget.TextView[@text='Privacy Policy']/parent::android.view.ViewGroup");
    private final By backButton = AppiumBy.accessibilityId("Back");

    // Error Message Elements
    private final By emailValidationError = AppiumBy.androidUIAutomator("new UiSelector().text(\"Please enter a valid email address\")");
    private final By passwordValidationError = AppiumBy.androidUIAutomator("new UiSelector().text(\"Password has to be at least 6 characters\")");

    // Success Message Elements
    private final By registrationSuccessMessage = AppiumBy.id("android:id/alertTitle");

    // Dialog Elements - replace with actual locators when found
    private final By confirmDialogOkButton = AppiumBy.androidUIAutomator("new UiSelector().text(\"OK\")"); // Replace with actual locator
    private final By termsAndConditionsCheckbox = AppiumBy.androidUIAutomator("new UiSelector().checkable(true)"); // Replace with actual locator

    /**
     * Check if create account page is displayed
     */
    @Step("Verify create account page is displayed")
    public boolean isCreateAccountPageDisplayed() {
        AllureUtils.step("Checking if create account page is displayed");
        boolean isDisplayed = isElementDisplayed(emailField) && isElementDisplayed(passwordField) && isElementDisplayed(createAccountButton);
        LogUtils.info("Create account page displayed: " + isDisplayed);
        return isDisplayed;
    }

    /**
     * Enter email address
     */
    @Step("Enter email: {email}")
    public CreateAccountPage enterEmail(String email) {
        AllureUtils.step("Entering email: " + (email.isEmpty() ? "[EMPTY]" : email));
        enterText(emailField, email);
        return this;
    }

    /**
     * Enter password
     */
    @Step("Enter password: {password}")
    public CreateAccountPage enterPassword(String password) {
        String displayPassword = password.isEmpty() ? "[EMPTY]" : password;
        AllureUtils.step("Entering password: " + displayPassword);
        enterText(passwordField, password);
        return this;
    }

    /**
     * Click create account button
     */
    @Step("Click create account button")
    public CreateAccountPage clickCreateAccount() {
        AllureUtils.step("Tapping create account button");
        clickElement(createAccountButton);
        waitForPageLoad();
        return this;
    }

    /**
     * Click sign up with Google button
     */
    @Step("Click sign up with Google")
    public CreateAccountPage clickSignUpWithGoogle() {
        AllureUtils.step("Tapping sign up with Google button");
        clickElement(signUpWithGoogleButton);
        waitForPageLoad();
        return this;
    }

    /**
     * Click privacy policy link
     */
    @Step("Click privacy policy link")
    public CreateAccountPage clickPrivacyPolicy() {
        AllureUtils.step("Tapping privacy policy link");
        clickElement(privacyPolicyLink);
        waitForPageLoad();
        return this;
    }

    /**
     * Click back button
     */
    @Step("Navigate back to previous page")
    public WelcomePage clickBack() {
        AllureUtils.step("Tapping back button");
        clickElement(backButton);
        waitForPageLoad();
        return new WelcomePage();
    }

    /**
     * Perform account creation with credentials
     */
    @Step("Create account with email: {email}")
    public CreateAccountPage createAccount(String email, String password) {
        String displayPassword = password.isEmpty() ? "[EMPTY]" : password;
        AllureUtils.step("Creating account - Email: " + (email.isEmpty() ? "[EMPTY]" : email) + ", Password: " + displayPassword);

        enterEmail(email);
        enterPassword(password);
        clickCreateAccount();
        return this;
    }

    /**
     * Perform account creation with valid credentials
     */
    @Step("Create account with valid credentials")
    public CreateAccountPage createAccountWithValidCredentials() {
        AllureUtils.step("Creating account with valid test credentials");
        return createAccount(TestData.ValidUser.EMAIL, TestData.ValidUser.PASSWORD);
    }

    /**
     * Create account with new user credentials
     */
    @Step("Create account with new user credentials")
    public CreateAccountPage createAccountWithNewUser() {
        // Generate unique email for testing
        String uniqueEmail = "test" + System.currentTimeMillis() + "@test.com";
        AllureUtils.step("Creating account with new user credentials");
        return createAccount(uniqueEmail, "123456789");
    }

    /**
     * Check if email validation error is displayed
     */
    @Step("Verify email validation error is displayed")
    public boolean isEmailValidationErrorDisplayed() {
        AllureUtils.step("Checking for email validation error");
        try {
            return WaitUtils.waitForElementVisible(emailValidationError, 5).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if password validation error is displayed
     */
    @Step("Verify password validation error is displayed")
    public boolean isPasswordValidationErrorDisplayed() {
        AllureUtils.step("Checking for password validation error");
        try {
            return WaitUtils.waitForElementVisible(passwordValidationError, 5).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if registration success message is displayed
     */
    @Step("Verify registration success message is displayed")
    public boolean isRegistrationSuccessMessageDisplayed() {
        AllureUtils.step("Checking for registration success message");
        try {
            return WaitUtils.waitForElementVisible(registrationSuccessMessage, 10).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Dismiss success dialog
     */
    @Step("Dismiss registration success dialog")
    public WelcomePage dismissSuccessDialog() {
        AllureUtils.step("Tapping OK to dismiss success dialog");
        try {
            clickElement(confirmDialogOkButton);
            waitForPageLoad();
        } catch (Exception e) {
            LogUtils.warn("Success dialog dismiss button not found, continuing...");
        }
        return new WelcomePage();
    }

    /**
     * Check if terms and conditions checkbox is present
     */
    @Step("Verify terms and conditions checkbox is present")
    public boolean isTermsCheckboxPresent() {
        AllureUtils.step("Checking if terms and conditions checkbox is present");
        return isElementPresent(termsAndConditionsCheckbox);
    }

    /**
     * Accept terms and conditions
     */
    @Step("Accept terms and conditions")
    public CreateAccountPage acceptTermsAndConditions() {
        AllureUtils.step("Clicking terms and conditions checkbox");
        if (isElementPresent(termsAndConditionsCheckbox)) {
            clickElement(termsAndConditionsCheckbox);
        }
        return this;
    }

    /**
     * Get email validation error text
     */
    @Step("Get email validation error text")
    public String getEmailValidationErrorText() {
        AllureUtils.step("Getting email validation error text");
        try {
            return getElementText(emailValidationError);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Get password validation error text
     */
    @Step("Get password validation error text")
    public String getPasswordValidationErrorText() {
        AllureUtils.step("Getting password validation error text");
        try {
            return getElementText(passwordValidationError);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Complete account creation process and handle success dialog
     */
    @Step("Complete account creation with new user and handle success")
    public WelcomePage completeAccountCreationWithNewUser() {
        AllureUtils.step("Completing full account creation flow with new user");

        createAccountWithNewUser();

        // Wait for and verify success message
        if (isRegistrationSuccessMessageDisplayed()) {
            AllureUtils.step("Registration successful - dismissing dialog");
            return dismissSuccessDialog();
        } else {
            LogUtils.warn("Success message not displayed after account creation");
            return new WelcomePage();
        }
    }

    /**
     * Navigate back to welcome page
     */
    @Step("Navigate back to welcome page")
    public WelcomePage navigateBackToWelcome() {
        AllureUtils.step("Navigating back to welcome page");
        return clickBack();
    }
}