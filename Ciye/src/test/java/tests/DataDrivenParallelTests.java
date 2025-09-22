package tests;

import io.qameta.allure.*;
import listeners.RetryAnalyzer;
import org.testng.annotations.Test;
import pages.LoginPage;
import testdata.TestData;
import utils.AllureUtils;
import utils.AssertUtils;

/**
 * Data-Driven Parallel Test Cases
 * Tests that run on all devices using data provider
 *
 * @author Ciye Test Team
 */
@Epic("Ciye Mobile App Parallel Testing")
@Feature("Data-Driven Parallel Testing")
public class DataDrivenParallelTests extends ParallelBaseTest {

    @Test(priority = TestData.TestPriority.MEDIUM,
            description = "Data-driven parallel test: Verify empty field validations",
            retryAnalyzer = RetryAnalyzer.class,
            dataProvider = "deviceProvider")
    @Story("Cross-Device Validation Testing")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test validates empty field handling across all configured devices")
    public void testEmptyFieldValidationAcrossDevices(String deviceId) {
        AllureUtils.step("Starting empty field validation test on device: " + deviceId);

        // Navigate to login page
        LoginPage loginPage = welcomePage.navigateToLogin();
        AssertUtils.assertTrue(loginPage.isLoginPageDisplayed(),
                "Login page should be displayed on device: " + deviceId);

        // Test empty email
        loginPage.login(TestData.InvalidData.EMPTY_STRING, TestData.ValidUser.PASSWORD);
        AssertUtils.assertTrue(loginPage.isEmptyEmailErrorDisplayed(),
                "Empty email error should be displayed on device: " + deviceId);
    }
}