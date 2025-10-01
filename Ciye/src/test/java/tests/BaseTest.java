package tests;

import config.AppConfig;
import driver.DriverManager;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Epic;
import listeners.AllureListener;
import listeners.RetryAnalyzer;
import org.testng.annotations.*;
import pages.WelcomePage;
import utils.AllureUtils;
import utils.LogUtils;

/**
 * Base Test Class - Single Device Testing
 * Contains setup and teardown methods for single device test execution
 *
 * @author Ciye Test Team
 */
@Epic("Ciye Mobile App Testing")
@Listeners({AllureListener.class})
public class BaseTest {

    protected AndroidDriver driver;
    protected WelcomePage welcomePage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        LogUtils.info("=== Starting Test Setup ===");

        try {
            // Initialize regular driver (not ThreadLocal)
            driver = DriverManager.getInstance().getDriver();

            // Initialize welcome page
            welcomePage = new WelcomePage();
            LogUtils.info("Welcome page initialized");

            // Add environment info to Allure
            AllureUtils.addEnvironmentInfo();
            AllureUtils.addParameter("Device", "emulator-5554");
            AllureUtils.addParameter("Platform", "Android 16");

            LogUtils.info("=== Test Setup Complete ===");

        } catch (Exception e) {
            LogUtils.error("Test setup failed: " + e.getMessage());
            throw new RuntimeException("Failed to setup test environment", e);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        LogUtils.info("=== Starting Test Cleanup ===");

        try {
            DriverManager.getInstance().quitDriver();
            LogUtils.info("=== Test Cleanup Complete ===");
        } catch (Exception e) {
            LogUtils.error("Test cleanup failed: " + e.getMessage());
        }
    }

    @BeforeSuite(alwaysRun = true)
    public void suiteSetup() {
        LogUtils.info("=== Starting Test Suite ===");
        LogUtils.info("Test Environment: " + AppConfig.getTestEnvironment());
        LogUtils.info("App Package: " + AppConfig.getAppPackage());
        LogUtils.info("App Activity: " + AppConfig.getAppActivity());
        LogUtils.info("Retry Count: " + AppConfig.getRetryCount());

        // Verify device is available
        verifyDeviceAvailability();
    }

    @AfterSuite(alwaysRun = true)
    public void suiteTeardown() {
        LogUtils.info("=== Test Suite Complete ===");
    }

    protected RetryAnalyzer getRetryAnalyzer() {
        return new RetryAnalyzer();
    }

    private void verifyDeviceAvailability() {
        LogUtils.info("=== Verifying Device Availability ===");
        try {
            ProcessBuilder pb = new ProcessBuilder("adb", "devices");
            Process process = pb.start();

            java.io.BufferedReader reader = new java.io.BufferedReader(
                    new java.io.InputStreamReader(process.getInputStream())
            );

            String line;
            int deviceCount = 0;
            while ((line = reader.readLine()) != null) {
                if (line.contains("emulator-5554") && line.contains("device")) {
                    deviceCount++;
                    LogUtils.info("Available device: " + line.trim());
                }
            }

            process.waitFor();

            if (deviceCount == 0) {
                LogUtils.warn("emulator-5554 not found! Make sure Pixel7Pro emulator is running");
            } else {
                LogUtils.info("emulator-5554 is available and ready");
            }

        } catch (Exception e) {
            LogUtils.warn("Failed to verify device availability: " + e.getMessage());
        }
    }
}