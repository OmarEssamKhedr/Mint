package tests;

import config.AppConfig;
import driver.DriverManager;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import listeners.AllureListener;
import listeners.RetryAnalyzer;
import org.testng.annotations.*;
import pages.WelcomePage;
import utils.AllureUtils;
import utils.LogUtils;

/**
 * Base Test Class
 * Contains setup and teardown methods for all test classes
 *
 * @author Ciye Test Team
 */
@Epic("Ciye Mobile App Testing")
@Listeners({AllureListener.class})
public class BaseTest {

    protected AndroidDriver driver;
    protected WelcomePage welcomePage;

    /**
     * Setup method - runs before each test method
     */
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        LogUtils.info("=== Starting Test Setup ===");

        try {
            // Initialize driver
            driver = DriverManager.getInstance().getDriver();
            LogUtils.info("Driver initialized successfully");

            // Initialize welcome page
            welcomePage = new WelcomePage();
            LogUtils.info("Welcome page initialized");

            // Add environment info to Allure
            AllureUtils.addEnvironmentInfo();

            LogUtils.info("=== Test Setup Complete ===");

        } catch (Exception e) {
            LogUtils.error("Test setup failed: " + e.getMessage());
            throw new RuntimeException("Failed to setup test environment", e);
        }
    }

    /**
     * Teardown method - runs after each test method
     */
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        LogUtils.info("=== Starting Test Cleanup ===");

        try {
            // Quit driver
            DriverManager.getInstance().quitDriver();
            LogUtils.info("Driver quit successfully");

            LogUtils.info("=== Test Cleanup Complete ===");

        } catch (Exception e) {
            LogUtils.error("Test cleanup failed: " + e.getMessage());
        }
    }

    /**
     * Suite setup - runs once before all tests in the suite
     */
    @BeforeSuite(alwaysRun = true)
    public void suiteSetup() {
        LogUtils.info("=== Starting Test Suite ===");
        LogUtils.info("Test Environment: " + AppConfig.getTestEnvironment());
        LogUtils.info("App Package: " + AppConfig.getAppPackage());
        LogUtils.info("Retry Count: " + AppConfig.getRetryCount());
    }

    /**
     * Suite teardown - runs once after all tests in the suite
     */
    @AfterSuite(alwaysRun = true)
    public void suiteTeardown() {
        LogUtils.info("=== Test Suite Complete ===");
    }

    /**
     * Get retry analyzer for test methods
     */
    protected RetryAnalyzer getRetryAnalyzer() {
        return new RetryAnalyzer();
    }
}