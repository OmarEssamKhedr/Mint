package driver;

import config.DeviceCapabilityFactory;
import config.VirtualDeviceConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import utils.LogUtils;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

/**
 * Thread Local Driver Manager
 * Manages AndroidDriver instances for parallel test execution
 *
 * @author Ciye Test Team
 */
public class ThreadLocalDriverManager {

    private static final ThreadLocal<AndroidDriver> driverThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<String> deviceIdThreadLocal = new ThreadLocal<>();

    /**
     * Initialize driver for specific device
     */
    public static void initializeDriver(String deviceId) {
        try {
            LogUtils.info("Initializing driver for device: " + deviceId + " on thread: " + Thread.currentThread().getName());

            // Set device ID for current thread
            deviceIdThreadLocal.set(deviceId);

            // Create capabilities for device
            UiAutomator2Options options = DeviceCapabilityFactory.createCapabilities(deviceId);

            // Get Appium server URL for device
            String serverUrl = VirtualDeviceConfig.getAppiumServerUrl(deviceId);

            // Create driver instance
            AndroidDriver driver = new AndroidDriver(
                    new URI(serverUrl).toURL(),
                    options
            );

            // Set implicit wait
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            // Store driver in thread local
            driverThreadLocal.set(driver);

            LogUtils.info("Driver initialized successfully for device: " + deviceId);

        } catch (MalformedURLException | URISyntaxException e) {
            LogUtils.error("Failed to initialize driver for device: " + deviceId + " - " + e.getMessage());
            throw new RuntimeException("Driver initialization failed for device: " + deviceId, e);
        }
    }

    /**
     * Get current thread's driver
     */
    public static AndroidDriver getDriver() {
        AndroidDriver driver = driverThreadLocal.get();
        if (driver == null) {
            throw new RuntimeException("Driver not initialized for current thread: " + Thread.currentThread().getName());
        }
        return driver;
    }

    /**
     * Get current thread's device ID
     */
    public static String getCurrentDeviceId() {
        return deviceIdThreadLocal.get();
    }

    /**
     * Quit driver for current thread
     */
    public static void quitDriver() {
        AndroidDriver driver = driverThreadLocal.get();
        String deviceId = deviceIdThreadLocal.get();

        if (driver != null) {
            LogUtils.info("Quitting driver for device: " + deviceId + " on thread: " + Thread.currentThread().getName());
            driver.quit();
            driverThreadLocal.remove();
            deviceIdThreadLocal.remove();
            LogUtils.info("Driver quit successfully for device: " + deviceId);
        }
    }

    /**
     * Check if driver is active for current thread
     */
    public static boolean isDriverActive() {
        return driverThreadLocal.get() != null;
    }
}