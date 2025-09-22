package tests;

import io.qameta.allure.*;
import listeners.RetryAnalyzer;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProfilePage;
import testdata.TestData;
import utils.AllureUtils;
import utils.AssertUtils;

/**
 * Parallel Login Test Cases
 * Login tests designed for parallel execution across multiple devices
 *
 * @author Ciye Test Team
 */
@Epic("Ciye Mobile App Parallel Testing")
@Feature("Parallel Login Functionality")
public class ParallelLoginTests extends ParallelBaseTest {

    @Test(priority = TestData.TestPriority.CRITICAL,
            description = "Parallel test: Verify successful login with valid credentials",
            retryAnalyzer = RetryAnalyzer.class,
            dataProvider = "deviceProvider",
            dataProviderClass = ParallelBaseTest.class)
    @Story("Parallel User Authentication")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test validates successful login across multiple devices simultaneously")
    public void testParallelValidLogin(String deviceId) {
        AllureUtils.step("Starting parallel valid login test on device: " + deviceId);

        // Navigate to login page
        LoginPage loginPage = welcomePage.navigateToLogin();
        AssertUtils.assertTrue(loginPage.isLoginPageDisplayed(), "Login page should be displayed on " + deviceId);

        // Perform login
        loginPage.loginWithValidCredentials();

        // Verify successful login
        ProfilePage profilePage = new ProfilePage();
        AssertUtils.assertTrue(profilePage.isOnHomePage(), "User should be on home page after successful login on " + deviceId);
    }

    @Test(priority = TestData.TestPriority.HIGH,
            description = "Parallel test: Verify login fails with invalid email",
            retryAnalyzer = RetryAnalyzer.class,
            dataProvider = "deviceProvider",
            dataProviderClass = ParallelBaseTest.class)
    @Story("Parallel Input Validation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test validates login failure with invalid email across multiple devices")
    public void testParallelInvalidEmail(String deviceId) {
        AllureUtils.step("Starting parallel invalid email test on device: " + deviceId);

        // Navigate to login page
        LoginPage loginPage = welcomePage.navigateToLogin();
        AssertUtils.assertTrue(loginPage.isLoginPageDisplayed(), "Login page should be displayed on " + deviceId);

        // Attempt login with invalid email
        loginPage.login(TestData.InvalidData.INVALID_EMAIL, TestData.ValidUser.PASSWORD);

        // Verify error message
        AssertUtils.assertTrue(loginPage.isWrongCredentialsErrorDisplayed(),
                "Wrong credentials error should be displayed for invalid email on " + deviceId);
    }
}