package pages;

import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utils.AllureUtils;
import utils.LogUtils;

/**
 * Profile Page Object
 * Contains elements and methods for the Profile/Menu screen
 *
 * @author Ciye Test Team
 */
public class AccountMenuPage extends BasePage {

    // Profile Button Elements
    private final By validUserProfileButton = AppiumBy.androidUIAutomator(
            "new UiSelector().className(\"android.widget.TextView\").text(\"MM\")"
    );
    private final By deleteUserProfileButton = AppiumBy.androidUIAutomator(
            "new UiSelector().className(\"android.widget.TextView\").text(\"MT\")"
    );
    private final By personalInformationButton = AppiumBy.androidUIAutomator(
            "new UiSelector().className(\"android.view.ViewGroup\").description(\"Personal Information\").clickable(true)"
    );    // Menu Elements
    private final By logoutButton = AppiumBy.androidUIAutomator(
            "new UiSelector().className(\"android.widget.TextView\").text(\"Log out\")"
    );
    private final By deleteAccountButton = AppiumBy.androidUIAutomator(
            "new UiSelector().className(\"android.widget.TextView\").text(\"Delete account\")"
    );
    private final By deleteEmailField = AppiumBy.androidUIAutomator(
            "new UiSelector().className(\"android.widget.EditText\").text(\"Email\")"
    );
    private final By confirmDeleteButton = AppiumBy.accessibilityId("Confirm & Delete");

    // Home/Progress Page Elements
    private final By homeRoot = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.TextView\").text(\"Latest Swim\")");
    /**
     * Check if user is on home/progress page
     */
    @Step("Verify user is on home page")
    public boolean isOnHomePage() {
        AllureUtils.step("Checking if user is on home page");
        boolean isOnHome = isElementDisplayed(homeRoot);
        LogUtils.info("User is on home page: " + isOnHome);
        return isOnHome;
    }

    /**
     * Click profile button for valid user
     */
    @Step("Click profile button for valid user")
    public AccountMenuPage clickValidUserProfile() {
        AllureUtils.step("Tapping profile button for valid user (MM)");
        clickElement(validUserProfileButton);
        waitForPageLoad();
        return this;
    }

    /**
     * Click profile button for delete test user
     */
    @Step("Click profile button for delete test user")
    public AccountMenuPage clickDeleteUserProfile() {
        AllureUtils.step("Tapping profile button for delete user (MT)");
        clickElement(deleteUserProfileButton);
        waitForPageLoad();
        return this;
    }
    /**
     * Click profile button for delete test user
     */
    @Step("Click profile button for Personal Information")
    public Personal_InformationPage clickPersonalInformation() {
        AllureUtils.step("Tapping Personal Information");
        clickElement(personalInformationButton);
        waitForPageLoad();
        return new Personal_InformationPage();
    }

    /**
     * Click logout button
     */
    @Step("Click logout button")
    public WelcomePage logout() {
        AllureUtils.step("Scrolling to and tapping logout button");
        scrollToText("Log out");
        clickElement(logoutButton);
        waitForPageLoad();
        LogUtils.info("User logged out successfully");
        return new WelcomePage();
    }

    /**
     * Click delete account button
     */
    @Step("Click delete account button")
    public AccountMenuPage clickDeleteAccount() {
        AllureUtils.step("Scrolling to and tapping delete account button");
        scrollToText("Delete account");
        clickElement(deleteAccountButton);
        waitForPageLoad();
        return this;
    }

    /**
     * Enter email for account deletion confirmation
     */
    @Step("Enter email for account deletion confirmation")
    public AccountMenuPage enterDeleteConfirmationEmail(String email) {
        AllureUtils.step("Entering email for deletion confirmation");
        enterText(deleteEmailField, email);
        return this;
    }

    /**
     * Confirm account deletion
     */
    @Step("Confirm account deletion")
    public WelcomePage confirmAccountDeletion() {
        AllureUtils.step("Tapping confirm delete button");
        clickElement(confirmDeleteButton);
        waitForPageLoad();
        LogUtils.info("Account deletion confirmed");
        return new WelcomePage();
    }

    /**
     * Complete account deletion process
     */
    @Step("Delete account with email confirmation")
    public WelcomePage deleteAccountWithConfirmation(String email) {
        AllureUtils.step("Performing complete account deletion process");
        clickDeleteAccount();
        enterDeleteConfirmationEmail(email);
        return confirmAccountDeletion();
    }

    /**
     * Check if logout button is visible
     */
    @Step("Verify logout button is visible")
    public boolean isLogoutButtonVisible() {
        AllureUtils.step("Checking if logout button is visible");
        scrollToText("Log out");
        return isElementDisplayed(logoutButton);
    }

    /**
     * Check if delete account button is visible
     */
    @Step("Verify delete account button is visible")
    public boolean isDeleteAccountButtonVisible() {
        AllureUtils.step("Checking if delete account button is visible");
        scrollToText("Delete account");
        return isElementDisplayed(deleteAccountButton);
    }
}