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
 * Manages AndroidDriver instances for parallel test execution across identical devices
 *
 * @author Ciye Test Team
 */
public class ThreadLocalDriverManager {

    private static final ThreadLocal<AndroidDriver> driverThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<String> deviceIdThreadLocal = new ThreadLocal<>();

    public static void initializeDriver(String deviceId) {
        try {
            LogUtils.info("Initializing driver for device: " + deviceId + " on thread: " + Thread.currentThread().getName());

            deviceIdThreadLocal.set(deviceId);

            UiAutomator2Options options = DeviceCapabilityFactory.createCapabilities(deviceId);
            String serverUrl = VirtualDeviceConfig.getAppiumServerUrl(deviceId);

            AndroidDriver driver = new AndroidDriver(
                    new URI(serverUrl).toURL(),
                    options
            );

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driverThreadLocal.set(driver);

            VirtualDeviceConfig.DeviceInfo deviceInfo = VirtualDeviceConfig.getDeviceInfo(deviceId);
            LogUtils.info("Driver initialized successfully for device: " + deviceId +
                    " (Model: " + deviceInfo.model + ", Resolution: " + deviceInfo.resolution + ")");

        } catch (MalformedURLException | URISyntaxException e) {
            LogUtils.error("Failed to initialize driver for device: " + deviceId + " - " + e.getMessage());
            throw new RuntimeException("Driver initialization failed for device: " + deviceId, e);
        }
    }

    public static AndroidDriver getDriver() {
        AndroidDriver driver = driverThreadLocal.get();
        if (driver == null) {
            throw new RuntimeException("Driver not initialized for current thread: " + Thread.currentThread().getName());
        }
        return driver;
    }

    public static String getCurrentDeviceId() {
        String deviceId = deviceIdThreadLocal.get();
        if (deviceId == null) {
            throw new RuntimeException("Device ID not set for current thread: " + Thread.currentThread().getName());
        }
        return deviceId;
    }

    public static void quitDriver() {
        try {
            AndroidDriver driver = driverThreadLocal.get();
            String deviceId = getCurrentDeviceId();

            if (driver != null) {
                driver.quit();
                LogUtils.info("Driver quit successfully for device: " + deviceId);
            } else {
                LogUtils.warn("No driver found to quit for device: " + deviceId);
            }
        } catch (Exception e) {
            LogUtils.error("Failed to quit driver: " + e.getMessage());
        } finally {
            driverThreadLocal.remove();
            deviceIdThreadLocal.remove();
        }
    }

    public static boolean isDriverInitialized() {
        return driverThreadLocal.get() != null;
    }

    public static VirtualDeviceConfig.DeviceInfo getCurrentDeviceInfo() {
        String deviceId = getCurrentDeviceId();
        return VirtualDeviceConfig.getDeviceInfo(deviceId);
    }
}