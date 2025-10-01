package pages;

import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import testdata.TestData;
import utils.AllureUtils;
import utils.LogUtils;
import utils.WaitUtils;

/**
 * Login Page Object
 * Contains elements and methods for the Login screen
 *
 * @author Ciye Test Team
 */
public class LoginPage extends BasePage {

    // Page Elements
    private final By emailField = AppiumBy.androidUIAutomator("new UiSelector().text(\"Email\")");
    private final By passwordField = AppiumBy.androidUIAutomator("new UiSelector().text(\"Password\")");
    private final By loginButton = By.xpath("//android.view.ViewGroup[@content-desc='Log in']/android.view.ViewGroup");

    // Error Message Elements
    private final By emptyEmailError = By.xpath("//android.widget.TextView[@text='Please enter a valid email address']");
    private final By emptyFieldsError = AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Please fill in all fields!\")");
    private final By wrongCredentialsError = AppiumBy.androidUIAutomator(
            "new UiSelector().className(\"android.widget.TextView\").text(\"Could not login. Wrong password or username.\")"
    );

    /**
     * Check if login page is displayed
     */
    @Step("Verify login page is displayed")
    public boolean isLoginPageDisplayed() {
        AllureUtils.step("Checking if login page is displayed");
        boolean isDisplayed = isElementDisplayed(emailField) && isElementDisplayed(passwordField);
        LogUtils.info("Login page displayed: " + isDisplayed);
        return isDisplayed;
    }

    /**
     * Enter email address
     */
    @Step("Enter email: {email}")
    public LoginPage enterEmail(String email) {
        AllureUtils.step("Entering email: " + (email.isEmpty() ? "[EMPTY]" : email));
        enterText(emailField, email);
        return this;
    }

    /**
     * Enter password
     */
    @Step("Enter password")
    public LoginPage enterPassword(String password) {
        String displayPassword = password.isEmpty() ? "[EMPTY]" : password;
        AllureUtils.step("Entering password: " + displayPassword);
        enterText(passwordField, password);
        return this;
    }

    /**
     * Click login button
     */
    @Step("Click login button")
    public void clickLogin() {
        AllureUtils.step("Tapping login button");
        clickElement(loginButton);
        waitForPageLoad();
    }

    /**
     * Perform login with credentials
     */
    @Step("Login with email: {email}")
    public void login(String email, String password) {
        String displayPassword = password.isEmpty() ? "[EMPTY]" : password;
        AllureUtils.step("Performing login - Email: " + (email.isEmpty() ? "[EMPTY]" : email) + ", Password: " + displayPassword);

        enterEmail(email);
        enterPassword(password);
        clickLogin();
    }

    /**
     * Perform login with valid credentials
     */
    @Step("Login with valid credentials")
    public void loginWithValidCredentials() {
        AllureUtils.step("Performing login with valid test credentials");
        login(TestData.ValidUser.EMAIL, TestData.ValidUser.PASSWORD);
    }

    /**
     * Check if empty email error is displayed
     */
    @Step("Verify empty email error is displayed")
    public boolean isEmptyEmailErrorDisplayed() {
        AllureUtils.step("Checking for empty email validation error");
        try {
            return WaitUtils.waitForElementVisible(emptyEmailError).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if empty fields error is displayed
     */
    @Step("Verify empty fields error is displayed")
    public boolean isEmptyFieldsErrorDisplayed() {
        AllureUtils.step("Checking for empty fields validation error");
        try {
            return WaitUtils.waitForElementVisible(emptyFieldsError, 5).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if wrong credentials error is displayed
     */
    @Step("Verify wrong credentials error is displayed")
    public boolean isWrongCredentialsErrorDisplayed() {
        AllureUtils.step("Checking for wrong credentials error");
        try {
            return WaitUtils.waitForElementVisible(wrongCredentialsError, 5).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Navigate back to welcome page
     */
    @Step("Navigate back to welcome page")
    public WelcomePage navigateBackToWelcome() {
        AllureUtils.step("Navigating back to welcome page");
        navigateBack();
        waitForPageLoad();
        return new WelcomePage();
    }
}