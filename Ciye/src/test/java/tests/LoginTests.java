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
 * Login Test Cases
 * Contains all test cases related to login functionality
 *
 * @author Ciye Test Team
 */
@Epic("Ciye Mobile App Testing")
@Feature("Login Functionality")
public class LoginTests extends BaseTest {

    @Test(priority = TestData.TestPriority.CRITICAL,
            description = "Verify successful login with valid credentials",
            retryAnalyzer = RetryAnalyzer.class)
    @Story("User Authentication")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test validates that user can successfully login with valid email and password")
    public void testValidLogin() {
        AllureUtils.step("Starting valid login test");

        // Navigate to login page
        LoginPage loginPage = welcomePage.navigateToLogin();
        AssertUtils.assertTrue(loginPage.isLoginPageDisplayed(), "Login page should be displayed");

        // Perform login
        loginPage.loginWithValidCredentials();

        // Verify successful login
        ProfilePage profilePage = new ProfilePage();
        AssertUtils.assertTrue(profilePage.isOnHomePage(), "User should be on home page after successful login");
    }

    @Test(priority = TestData.TestPriority.HIGH,
            description = "Verify login fails with invalid email",
            retryAnalyzer = RetryAnalyzer.class)
    @Story("Input Validation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test validates that login fails with invalid email address")
    public void testInvalidEmail() {
        AllureUtils.step("Starting invalid email test");

        // Navigate to login page
        LoginPage loginPage = welcomePage.navigateToLogin();
        AssertUtils.assertTrue(loginPage.isLoginPageDisplayed(), "Login page should be displayed");

        // Attempt login with invalid email
        loginPage.login(TestData.InvalidData.INVALID_EMAIL, TestData.ValidUser.PASSWORD);

        // Verify error message
        AssertUtils.assertTrue(loginPage.isWrongCredentialsErrorDisplayed(),
                "Wrong credentials error should be displayed for invalid email");
    }

    @Test(priority = TestData.TestPriority.HIGH,
            description = "Verify login fails with invalid password",
            retryAnalyzer = RetryAnalyzer.class)
    @Story("Input Validation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test validates that login fails with invalid password")
    public void testInvalidPassword() {
        AllureUtils.step("Starting invalid password test");

        // Navigate to login page
        LoginPage loginPage = welcomePage.navigateToLogin();
        AssertUtils.assertTrue(loginPage.isLoginPageDisplayed(), "Login page should be displayed");

        // Attempt login with invalid password
        loginPage.login(TestData.ValidUser.EMAIL, TestData.InvalidData.INVALID_PASSWORD);

        // Verify error message
        AssertUtils.assertTrue(loginPage.isWrongCredentialsErrorDisplayed(),
                "Wrong credentials error should be displayed for invalid password");
    }

    @Test(priority = TestData.TestPriority.MEDIUM,
            description = "Verify validation error for empty email",
            retryAnalyzer = RetryAnalyzer.class)
    @Story("Field Validation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test validates that appropriate error is shown when email field is empty")
    public void testEmptyEmail() {
        AllureUtils.step("Starting empty email validation test");

        // Navigate to login page
        LoginPage loginPage = welcomePage.navigateToLogin();
        AssertUtils.assertTrue(loginPage.isLoginPageDisplayed(), "Login page should be displayed");

        // Attempt login with empty email
        loginPage.login(TestData.InvalidData.EMPTY_STRING, TestData.ValidUser.PASSWORD);

        // Verify validation error
        AssertUtils.assertTrue(loginPage.isEmptyEmailErrorDisplayed(),
                "Empty email validation error should be displayed");
    }

    @Test(priority = TestData.TestPriority.MEDIUM,
            description = "Verify validation error for empty password",
            retryAnalyzer = RetryAnalyzer.class)
    @Story("Field Validation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test validates that appropriate error is shown when password field is empty")
    public void testEmptyPassword() {
        AllureUtils.step("Starting empty password validation test");

        // Navigate to login page
        LoginPage loginPage = welcomePage.navigateToLogin();
        AssertUtils.assertTrue(loginPage.isLoginPageDisplayed(), "Login page should be displayed");

        // Attempt login with empty password
        loginPage.login(TestData.ValidUser.EMAIL, TestData.InvalidData.EMPTY_STRING);

        // Verify validation error
        AssertUtils.assertTrue(loginPage.isEmptyFieldsErrorDisplayed(),
                "Empty password validation error should be displayed");
    }

    @Test(priority = TestData.TestPriority.MEDIUM,
            description = "Verify validation error for both empty fields",
            retryAnalyzer = RetryAnalyzer.class)
    @Story("Field Validation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test validates that appropriate error is shown when both email and password fields are empty")
    public void testEmptyEmailAndPassword() {
        AllureUtils.step("Starting empty fields validation test");

        // Navigate to login page
        LoginPage loginPage = welcomePage.navigateToLogin();
        AssertUtils.assertTrue(loginPage.isLoginPageDisplayed(), "Login page should be displayed");

        // Attempt login with empty fields
        loginPage.login(TestData.InvalidData.EMPTY_STRING, TestData.InvalidData.EMPTY_STRING);

        // Verify validation error
        AssertUtils.assertTrue(loginPage.isEmptyEmailErrorDisplayed(),
                "Empty fields validation error should be displayed");
    }

    @Test(priority = TestData.TestPriority.MEDIUM,
            description = "Verify login fails with password less than 8 characters",
            retryAnalyzer = RetryAnalyzer.class)
    @Story("Password Validation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test validates that login fails when password is less than 8 characters")
    public void testPasswordLessThan8Characters() {
        AllureUtils.step("Starting short password validation test");

        // Navigate to login page
        LoginPage loginPage = welcomePage.navigateToLogin();
        AssertUtils.assertTrue(loginPage.isLoginPageDisplayed(), "Login page should be displayed");

        // Attempt login with short password
        loginPage.login(TestData.ValidUser.EMAIL, TestData.InvalidData.SHORT_PASSWORD);

        // Verify error message
        AssertUtils.assertTrue(loginPage.isWrongCredentialsErrorDisplayed(),
                "Wrong credentials error should be displayed for short password");
    }

    @Test(priority = TestData.TestPriority.MEDIUM,
            description = "Verify navigation from welcome to login page",
            retryAnalyzer = RetryAnalyzer.class)
    @Story("Navigation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test validates that user can navigate from welcome screen to login screen")
    public void testWelcomeToLoginNavigation() {
        AllureUtils.step("Starting welcome to login navigation test");

        // Verify welcome page is displayed
        AssertUtils.assertTrue(welcomePage.isWelcomePageDisplayed(), "Welcome page should be displayed");

        // Navigate to login page
        LoginPage loginPage = welcomePage.navigateToLogin();

        // Verify login page is displayed
        AssertUtils.assertTrue(loginPage.isLoginPageDisplayed(), "Login page should be displayed after navigation");
    }

    @Test(priority = TestData.TestPriority.LOW,
            description = "Verify navigation from login back to welcome page",
            retryAnalyzer = RetryAnalyzer.class)
    @Story("Navigation")
    @Severity(SeverityLevel.MINOR)
    @Description("Test validates that user can navigate back from login screen to welcome screen")
    public void testLoginToWelcomeNavigation() {
        AllureUtils.step("Starting login to welcome navigation test");

        // Navigate to login page
        LoginPage loginPage = welcomePage.navigateToLogin();
        AssertUtils.assertTrue(loginPage.isLoginPageDisplayed(), "Login page should be displayed");

        // Navigate back to welcome page
        WelcomePage returnedWelcomePage = loginPage.navigateBackToWelcome();

        // Verify welcome page is displayed
        AssertUtils.assertTrue(returnedWelcomePage.isWelcomePageDisplayed(),
                "Welcome page should be displayed after navigating back");
    }
}