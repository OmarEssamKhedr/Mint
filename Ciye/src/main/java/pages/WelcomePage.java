package pages;

import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utils.AllureUtils;
import utils.LogUtils;

/**
 * Welcome Page Object
 * Contains elements and methods for the Welcome/Landing screen
 *
 * @author Ciye Test Team
 */
public class WelcomePage extends BasePage {

    // Page Elements
    private final By loginButton = AppiumBy.accessibilityId("Have an account?,  Log in!");

    /**
     * Check if welcome page is displayed
     */
    @Step("Verify welcome page is displayed")
    public boolean isWelcomePageDisplayed() {
        AllureUtils.step("Checking if welcome page is displayed");
        boolean isDisplayed = isElementDisplayed(loginButton);
        LogUtils.info("Welcome page displayed: " + isDisplayed);
        return isDisplayed;
    }

    /**
     * Navigate to login page
     */
    @Step("Navigate to login page from welcome screen")
    public LoginPage navigateToLogin() {
        AllureUtils.step("Tapping 'Have an account? Log in!' button");
        clickElement(loginButton);
        LogUtils.info("Navigated from welcome to login page");
        waitForPageLoad();
        return new LoginPage();
    }

    /**
     * Check if login button is visible
     */
    @Step("Verify login navigation button is visible")
    public boolean isLoginButtonVisible() {
        AllureUtils.step("Checking if login button is visible");
        return isElementDisplayed(loginButton);
    }
}