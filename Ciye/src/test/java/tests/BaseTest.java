package tests;

import config.AppConfig;
import config.VirtualDeviceConfig;
import driver.ThreadLocalDriverManager;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Epic;
import listeners.AllureListener;
import listeners.RetryAnalyzer;
import org.testng.annotations.*;
import pages.WelcomePage;
import utils.AllureUtils;
import utils.LogUtils;

/**
 * Enhanced Base Test Class
 * Supports both single device and multi-device testing on standardized Android 14 emulators
 *
 * @author Ciye Test Team
 */
@Epic("Ciye Mobile App Testing")
@Listeners({AllureListener.class})
public class BaseTest {

    protected AndroidDriver driver;
    protected WelcomePage welcomePage;
    protected String currentDeviceId;

    @BeforeMethod(alwaysRun = true)
    @Parameters({"deviceId"})
    public void setUp(@Optional("device1") String deviceId) {
        currentDeviceId = deviceId;
        LogUtils.info("=== Starting Test Setup for Device: " + deviceId + " ===");

        try {
            // Verify device configuration exists
            VirtualDeviceConfig.DeviceInfo deviceInfo = VirtualDeviceConfig.getDeviceInfo(deviceId);
            if (deviceInfo == null) {
                throw new RuntimeException("No configuration found for device: " + deviceId);
            }

            // Initialize thread-local driver
            ThreadLocalDriverManager.initializeDriver(deviceId);
            driver = ThreadLocalDriverManager.getDriver();

            // Initialize welcome page
            welcomePage = new WelcomePage();
            LogUtils.info("Welcome page initialized for device: " + deviceId);

            // Add comprehensive environment info to Allure
            AllureUtils.addEnvironmentInfo();
            AllureUtils.addParameter("Device ID", deviceId);
            AllureUtils.addParameter("Thread", Thread.currentThread().getName());
            AllureUtils.addParameter("Device Name", deviceInfo.deviceName);
            AllureUtils.addParameter("Platform Version", deviceInfo.platformVersion);
            AllureUtils.addParameter("Model", deviceInfo.model);
            AllureUtils.addParameter("Manufacturer", deviceInfo.manufacturer);
            AllureUtils.addParameter("Resolution", deviceInfo.resolution);
            AllureUtils.addParameter("Density", deviceInfo.density);
            AllureUtils.addParameter("AVD Name", deviceInfo.avdName);

            LogUtils.info("=== Test Setup Complete for Device: " + deviceId + " ===");

        } catch (Exception e) {
            LogUtils.error("Test setup failed for device: " + deviceId + " - " + e.getMessage());
            logDeviceDebugInfo(deviceId);
            throw new RuntimeException("Failed to setup test environment for device: " + deviceId, e);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        LogUtils.info("=== Starting Test Cleanup for Device: " + currentDeviceId + " ===");

        try {
            if (ThreadLocalDriverManager.isDriverInitialized()) {
                String deviceId = ThreadLocalDriverManager.getCurrentDeviceId();
                ThreadLocalDriverManager.quitDriver();
                LogUtils.info("Driver quit successfully for device: " + deviceId);
            } else {
                LogUtils.warn("No driver found to quit for device: " + currentDeviceId);
            }

            LogUtils.info("=== Test Cleanup Complete for Device: " + currentDeviceId + " ===");

        } catch (Exception e) {
            LogUtils.error("Test cleanup failed for device: " + currentDeviceId + " - " + e.getMessage());
        }
    }

    @BeforeSuite(alwaysRun = true)
    public void suiteSetup() {
        LogUtils.info("=== Starting Test Suite ===");
        LogUtils.info("Test Environment: " + AppConfig.getTestEnvironment());
        LogUtils.info("App Package: " + AppConfig.getAppPackage());
        LogUtils.info("App Activity: " + AppConfig.getAppActivity());
        LogUtils.info("Retry Count: " + AppConfig.getRetryCount());

        logAllDeviceConfigurations();

        // Verify all devices are available
        verifyDeviceAvailability();
    }

    @AfterSuite(alwaysRun = true)
    public void suiteTeardown() {
        LogUtils.info("=== Test Suite Complete ===");
    }

    protected RetryAnalyzer getRetryAnalyzer() {
        return new RetryAnalyzer();
    }

    private void logAllDeviceConfigurations() {
        LogUtils.info("=== Available Device Configurations ===");
        for (String deviceId : VirtualDeviceConfig.getAllDeviceIds()) {
            VirtualDeviceConfig.DeviceInfo device = VirtualDeviceConfig.getDeviceInfo(deviceId);
            LogUtils.info("Device " + deviceId + ": " + device.deviceName +
                    " (Android " + device.platformVersion + ", " +
                    device.model + ", " + device.resolution + ")");
        }
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
                if (line.contains("emulator-") && line.contains("device")) {
                    deviceCount++;
                    LogUtils.info("Available device: " + line.trim());
                }
            }

            process.waitFor();

            int expectedDevices = VirtualDeviceConfig.getAllDeviceIds().length;
            if (deviceCount < expectedDevices) {
                LogUtils.warn("Expected " + expectedDevices + " devices, but only found " + deviceCount);
            } else {
                LogUtils.info("All " + expectedDevices + " devices are available and ready");
            }

        } catch (Exception e) {
            LogUtils.warn("Failed to verify device availability: " + e.getMessage());
        }
    }

    private void logDeviceDebugInfo(String deviceId) {
        try {
            VirtualDeviceConfig.DeviceInfo device = VirtualDeviceConfig.getDeviceInfo(deviceId);
            if (device != null) {
                LogUtils.info("Debug info for " + deviceId + ": UDID=" + device.udid +
                        ", AVD=" + device.avdName + ", Port=" + device.systemPort);
            }
        } catch (Exception e) {
            LogUtils.error("Failed to log debug info: " + e.getMessage());
        }
    }
}