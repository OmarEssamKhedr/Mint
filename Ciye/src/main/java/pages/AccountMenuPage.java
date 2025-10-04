package pages;

import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.AllureUtils;
import utils.LogUtils;
import utils.WaitUtils;

/**
 * Profile Page Object
 * Contains elements and methods for the Profile/Menu screen
 *
 * @author Ciye Test Team
 */
public class AccountMenuPage extends BasePage {

    // ==================== PROFILE BUTTON LOCATORS ====================

    // Profile button - 3rd clickable ViewGroup (index 2) - works with or without photo
    private final By profileButton = AppiumBy.androidUIAutomator(
            "new UiSelector().className(\"android.view.ViewGroup\").clickable(true).instance(2)"
    );

    // Fallback: Profile button with initials (when no photo)
    private final By profileButtonWithInitials = AppiumBy.androidUIAutomator(
            "new UiSelector().className(\"android.widget.TextView\").textMatches(\"[A-Z]{2}\")"
    );

    private final By deleteUserProfileButton = AppiumBy.androidUIAutomator(
            "new UiSelector().className(\"android.widget.TextView\").text(\"MT\")"
    );
    private final By personalInformationButton = AppiumBy.androidUIAutomator(
            "new UiSelector().className(\"android.view.ViewGroup\").description(\"Personal Information\").clickable(true)"
    );    // Menu Elements
    private final By logoutButton = AppiumBy.xpath(
            "//android.widget.TextView[@text='Log out']"
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
     * Click profile button (works for both photo and initials)
     */
    @Step("Click profile button for valid user")
    public AccountMenuPage clickValidUserProfile() {
        AllureUtils.step("Tapping profile button");

        try {
            // Wait for home screen to appear
            WaitUtils.waitForElementPresent(homeRoot, 10);

            // Wait a bit for initial UI to settle
            Thread.sleep(500);

            // Find ALL clickable ViewGroups
            java.util.List<org.openqa.selenium.WebElement> clickableElements =
                    driver.findElements(AppiumBy.androidUIAutomator(
                            "new UiSelector().className(\"android.view.ViewGroup\").clickable(true)"
                    ));

            LogUtils.info("Found " + clickableElements.size() + " clickable ViewGroups");

            // Find the element in TOP-RIGHT corner (x > 1200, y < 400)
            for (org.openqa.selenium.WebElement element : clickableElements) {
                org.openqa.selenium.Rectangle rect = element.getRect();
                int x = rect.getX();
                int y = rect.getY();

                // Profile button is in top-right: x > 1200, y between 150-350
                if (x > 1200 && y > 150 && y < 400) {
                    LogUtils.info("Found profile button at position: x=" + x + ", y=" + y);
                    element.click();
                    waitForPageLoad();
                    return this;
                }
            }

            throw new RuntimeException("Profile button not found in top-right corner");

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted while waiting", e);
        } catch (Exception e) {
            LogUtils.error("Failed to click profile button: " + e.getMessage());
            throw new RuntimeException("Could not find profile button", e);
        }
    }
    /**
     * Debug method - call before clickValidUserProfile()
     */
    @Step("Debug: Find all clickable elements")
    public void debugFindProfileButton() {
        try {
            // Wait for home screen
            WaitUtils.waitForElementPresent(homeRoot, 10);
            Thread.sleep(500);

            // Find all clickable ViewGroups
            java.util.List<org.openqa.selenium.WebElement> clickableElements =
                    driver.findElements(AppiumBy.androidUIAutomator(
                            "new UiSelector().className(\"android.view.ViewGroup\").clickable(true)"
                    ));

            LogUtils.info("===== DEBUG: Found " + clickableElements.size() + " clickable ViewGroups =====");

            for (int i = 0; i < clickableElements.size(); i++) {
                org.openqa.selenium.WebElement el = clickableElements.get(i);
                org.openqa.selenium.Rectangle rect = el.getRect();
                String desc = el.getAttribute("content-desc");

                LogUtils.info(String.format("Element %d: x=%d, y=%d, width=%d, height=%d, desc='%s'",
                        i, rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight(), desc));
            }

        } catch (Exception e) {
            LogUtils.error("Debug failed: " + e.getMessage());
        }
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

        // Personal Information is always at same position (no scroll needed)
        // Bounds: [0,1159][1440,1327]
        // Center: x = 720, y = 1243

        driver.executeScript("mobile: clickGesture", java.util.Map.of(
                "x", 720,   // (0 + 1440) / 2
                "y", 1243   // (1159 + 1327) / 2
        ));

        LogUtils.info("Tapped Personal Information at coordinates (720, 1243)");

        waitForPageLoad();
        return new Personal_InformationPage();
    }

    /**
     * Click logout button
     */
    @Step("Click logout button")
    public WelcomePage logout() {
        AllureUtils.step("Scrolling to and tapping logout button");

        scrollToBottom();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Tap logout button at fixed coordinates (center of bounds [614,2754][826,2831])
        driver.executeScript("mobile: clickGesture", java.util.Map.of(
                "x", 720,  // (614 + 826) / 2
                "y", 2792  // (2754 + 2831) / 2
        ));

        LogUtils.info("Tapped logout button at coordinates (720, 2792)");

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
     * Scroll profile menu to bottom
     */
    @Step("Scroll to bottom of profile menu")
    private void scrollToBottom() {
        AllureUtils.step("Scrolling profile menu to bottom");
        try {
            int width = driver.manage().window().getSize().getWidth();
            int height = driver.manage().window().getSize().getHeight();

            int startX = width / 2;
            int startY = (int)(height * 0.8);
            int endY = (int)(height * 0.2);

            // Perform 3 drag gestures to scroll down
            for (int i = 0; i < 3; i++) {
                driver.executeScript("mobile: dragGesture", java.util.Map.of(
                        "startX", startX,
                        "startY", startY,
                        "endX", startX,
                        "endY", endY
                ));
                Thread.sleep(300);
            }

            LogUtils.info("Scrolled to bottom of profile menu");

        } catch (Exception e) {
            LogUtils.warn("Scroll failed: " + e.getMessage());
        }
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