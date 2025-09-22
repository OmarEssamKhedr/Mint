package tests;

import config.AppConfig;
import config.VirtualDeviceConfig;
import driver.ThreadLocalDriverManager;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Epic;
import listeners.AllureListener;
import org.testng.annotations.*;
import pages.WelcomePage;
import utils.AllureUtils;
import utils.LogUtils;

/**
 * Parallel Base Test Class
 * Contains setup and teardown methods for parallel test execution
 *
 * @author Ciye Test Team
 */
@Epic("Ciye Mobile App Parallel Testing")
@Listeners({AllureListener.class})
public class ParallelBaseTest {

    protected AndroidDriver driver;
    protected WelcomePage welcomePage;

    /**
     * Data provider for devices
     */
    @DataProvider(name = "deviceProvider", parallel = true)
    public Object[][] deviceProvider() {
        String[] deviceIds = VirtualDeviceConfig.getAllDeviceIds();
        Object[][] data = new Object[deviceIds.length][1];

        for (int i = 0; i < deviceIds.length; i++) {
            data[i][0] = deviceIds[i];
        }

        return data;
    }

    /**
     * Setup method for parallel execution
     */
    @BeforeMethod(alwaysRun = true)
    @Parameters({"deviceId"})
    public void setUp(@Optional("device1") String deviceId) {
        LogUtils.info("=== Starting Parallel Test Setup for Device: " + deviceId + " ===");

        try {
            // Initialize thread-local driver
            ThreadLocalDriverManager.initializeDriver(deviceId);
            driver = ThreadLocalDriverManager.getDriver();

            // Initialize welcome page
            welcomePage = new WelcomePage();
            LogUtils.info("Welcome page initialized for device: " + deviceId);

            // Add device info to Allure
            AllureUtils.addEnvironmentInfo();
            AllureUtils.addParameter("Device ID", deviceId);
            AllureUtils.addParameter("Thread", Thread.currentThread().getName());

            VirtualDeviceConfig.DeviceInfo deviceInfo = VirtualDeviceConfig.getDeviceInfo(deviceId);
            AllureUtils.addParameter("Device Name", deviceInfo.deviceName);
            AllureUtils.addParameter("Platform Version", deviceInfo.platformVersion);

            LogUtils.info("=== Parallel Test Setup Complete for Device: " + deviceId + " ===");

        } catch (Exception e) {
            LogUtils.error("Parallel test setup failed for device: " + deviceId + " - " + e.getMessage());
            throw new RuntimeException("Failed to setup parallel test environment for device: " + deviceId, e);
        }
    }

    /**
     * Teardown method for parallel execution
     */
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        String deviceId = ThreadLocalDriverManager.getCurrentDeviceId();
        LogUtils.info("=== Starting Parallel Test Cleanup for Device: " + deviceId + " ===");

        try {
            ThreadLocalDriverManager.quitDriver();
            LogUtils.info("=== Parallel Test Cleanup Complete for Device: " + deviceId + " ===");

        } catch (Exception e) {
            LogUtils.error("Parallel test cleanup failed for device: " + deviceId + " - " + e.getMessage());
        }
    }

    /**
     * Suite setup for parallel execution
     */
    @BeforeSuite(alwaysRun = true)
    public void parallelSuiteSetup() {
        LogUtils.info("=== Starting Parallel Test Suite ===");
        LogUtils.info("Test Environment: " + AppConfig.getTestEnvironment());
        LogUtils.info("App Package: " + AppConfig.getAppPackage());
        LogUtils.info("Available Devices: " + String.join(", ", VirtualDeviceConfig.getAllDeviceIds()));
    }

    /**
     * Suite teardown for parallel execution
     */
    @AfterSuite(alwaysRun = true)
    public void parallelSuiteTeardown() {
        LogUtils.info("=== Parallel Test Suite Complete ===");
    }
}