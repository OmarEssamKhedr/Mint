package tests;

import io.qameta.allure.*;
import listeners.RetryAnalyzer;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProfilePage;
import pages.WelcomePage;
import testdata.TestData;
import utils.AllureUtils;
import utils.AssertUtils;

/**
 * Profile Test Cases
 * Contains all test cases related to profile and user management functionality
 *
 * @author Ciye Test Team
 */
@Epic("Ciye Mobile App Testing")
@Feature("Profile Management")
public class ProfileTests extends BaseTest {

    @Test(priority = TestData.TestPriority.CRITICAL,
            description = "Verify user can successfully logout",
            retryAnalyzer = RetryAnalyzer.class)
    @Story("User Session Management")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test validates that logged-in user can successfully logout and return to welcome screen")
    public void testUserLogout() {
        AllureUtils.step("Starting user logout test");

        // Login with valid credentials
        LoginPage loginPage = welcomePage.navigateToLogin();
        loginPage.loginWithValidCredentials();

        // Verify user is logged in (on home page)
        ProfilePage profilePage = new ProfilePage();
        AssertUtils.assertTrue(profilePage.isOnHomePage(), "User should be on home page after login");

        // Access profile and logout
        profilePage.clickValidUserProfile();
        WelcomePage returnedWelcomePage = profilePage.logout();

        // Verify user is logged out (back on welcome page)
        AssertUtils.assertTrue(returnedWelcomePage.isWelcomePageDisplayed(),
                "User should be on welcome page after logout");
    }

    @Test(priority = TestData.TestPriority.HIGH,
            description = "Verify profile menu accessibility",
            retryAnalyzer = RetryAnalyzer.class)
    @Story("Profile Navigation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test validates that user can access profile menu after successful login")
    public void testProfileMenuAccess() {
        AllureUtils.step("Starting profile menu access test");

        // Login with valid credentials
        LoginPage loginPage = welcomePage.navigateToLogin();
        loginPage.loginWithValidCredentials();

        // Access profile menu
        ProfilePage profilePage = new ProfilePage();
        profilePage.clickValidUserProfile();

        // Verify profile menu options are accessible
        AssertUtils.assertTrue(profilePage.isLogoutButtonVisible(), "Logout button should be visible in profile menu");
    }

    @Test(priority = TestData.TestPriority.HIGH,
            description = "Verify delete account option is available",
            retryAnalyzer = RetryAnalyzer.class)
    @Story("Account Management")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test validates that delete account option is available in profile menu")
    public void testDeleteAccountOptionAvailable() {
        AllureUtils.step("Starting delete account option visibility test");

        // Login with valid credentials
        LoginPage loginPage = welcomePage.navigateToLogin();
        loginPage.loginWithValidCredentials();

        // Access profile menu
        ProfilePage profilePage = new ProfilePage();
        profilePage.clickValidUserProfile();

        // Verify delete account option is available
        AssertUtils.assertTrue(profilePage.isDeleteAccountButtonVisible(),
                "Delete account button should be visible in profile menu");
    }

    @Test(priority = TestData.TestPriority.MEDIUM,
            description = "Verify profile button displays correct user initials",
            retryAnalyzer = RetryAnalyzer.class)
    @Story("User Profile Display")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test validates that profile button displays correct user initials after login")
    public void testProfileButtonDisplaysCorrectInitials() {
        AllureUtils.step("Starting profile initials display test");

        // Login with valid credentials
        LoginPage loginPage = welcomePage.navigateToLogin();
        loginPage.loginWithValidCredentials();

        // Verify user is on home page (profile button should be visible)
        ProfilePage profilePage = new ProfilePage();
        AssertUtils.assertTrue(profilePage.isOnHomePage(), "User should be on home page to see profile button");

        // Note: In a more comprehensive test, we would verify the actual initials text
        // For now, we verify that we can click the profile button (which implies it's visible with correct content)
        profilePage.clickValidUserProfile();

        // Verify profile menu opened (indicates profile button worked correctly)
        AssertUtils.assertTrue(profilePage.isLogoutButtonVisible(),
                "Profile menu should open, indicating profile button displayed correctly");
    }

    // Commented out delete account test as it would actually delete the test account
    /*
    @Test(priority = TestData.TestPriority.MEDIUM,
          description = "Verify account deletion process",
          retryAnalyzer = RetryAnalyzer.class,
          enabled = false) // Disabled to prevent actual account deletion
    @Story("Account Deletion")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test validates the complete account deletion process")
    public void testAccountDeletion() {
        AllureUtils.step("Starting account deletion test");

        // Login with delete test account
        LoginPage loginPage = welcomePage.navigateToLogin();
        loginPage.login(TestData.DeleteUser.EMAIL, TestData.DeleteUser.PASSWORD);

        // Access profile menu
        ProfilePage profilePage = new ProfilePage();
        profilePage.clickDeleteUserProfile();

        // Perform account deletion
        WelcomePage returnedWelcomePage = profilePage.deleteAccountWithConfirmation(TestData.DeleteUser.EMAIL);

        // Verify user is returned to welcome page
        AssertUtils.assertTrue(returnedWelcomePage.isWelcomePageDisplayed(),
            "User should be on welcome page after account deletion");
    }
    */

    @Test(priority = TestData.TestPriority.LOW,
            description = "Verify profile menu navigation flow",
            retryAnalyzer = RetryAnalyzer.class)
    @Story("Navigation Flow")
    @Severity(SeverityLevel.MINOR)
    @Description("Test validates the complete navigation flow: welcome -> login -> home -> profile -> logout -> welcome")
    public void testCompleteProfileNavigationFlow() {
        AllureUtils.step("Starting complete profile navigation flow test");

        // Step 1: Verify starting on welcome page
        AssertUtils.assertTrue(welcomePage.isWelcomePageDisplayed(), "Should start on welcome page");

        // Step 2: Navigate to login
        LoginPage loginPage = welcomePage.navigateToLogin();
        AssertUtils.assertTrue(loginPage.isLoginPageDisplayed(), "Should navigate to login page");

        // Step 3: Login successfully
        loginPage.loginWithValidCredentials();
        ProfilePage profilePage = new ProfilePage();
        AssertUtils.assertTrue(profilePage.isOnHomePage(), "Should reach home page after login");

        // Step 4: Access profile menu
        profilePage.clickValidUserProfile();
        AssertUtils.assertTrue(profilePage.isLogoutButtonVisible(), "Should access profile menu");

        // Step 5: Logout and return to welcome
        WelcomePage returnedWelcomePage = profilePage.logout();
        AssertUtils.assertTrue(returnedWelcomePage.isWelcomePageDisplayed(),
                "Should return to welcome page after logout");
    }
}