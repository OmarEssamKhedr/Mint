package driver;

import config.AppConfig;
import config.DeviceConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import utils.LogUtils;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

/**
 * Driver Manager - Singleton Pattern
 * Manages AndroidDriver instance creation and configuration
 *
 * @author Ciye Test Team
 */
public class DriverManager {

    private static DriverManager instance;
    private AndroidDriver driver;
    private static final ThreadLocal<AndroidDriver> driverThreadLocal = new ThreadLocal<>();

    // Private constructor for singleton
    private DriverManager() {}

    /**
     * Get singleton instance of DriverManager
     */
    public static DriverManager getInstance() {
        if (instance == null) {
            synchronized (DriverManager.class) {
                if (instance == null) {
                    instance = new DriverManager();
                }
            }
        }
        return instance;
    }

    /**
     * Initialize and return AndroidDriver
     */
    public AndroidDriver getDriver() {
        if (driverThreadLocal.get() == null) {
            createDriver();
        }
        return driverThreadLocal.get();
    }

    /**
     * Create new AndroidDriver instance
     */
    private void createDriver() {
        try {
            LogUtils.info("Initializing Ciye app driver...");

            UiAutomator2Options options = new UiAutomator2Options();

            // Set device capabilities from config
            options.setPlatformName(DeviceConfig.getPlatformName());
            options.setAutomationName(DeviceConfig.getAutomationName());
            options.setPlatformVersion(DeviceConfig.getPlatformVersion());
            options.setDeviceName(DeviceConfig.getDeviceName());
            options.setUdid(DeviceConfig.getDeviceUDID());

            // Set app capabilities from config
            options.setAppPackage(AppConfig.getAppPackage());
            options.setAppActivity(AppConfig.getAppActivity());
            options.setNoReset(AppConfig.getNoReset());
            options.setCapability("autoGrantPermissions", AppConfig.getAutoGrantPermissions());

            // Additional capabilities for stability
            options.setCapability("appium:newCommandTimeout", 300);
            options.setCapability("appium:androidInstallTimeout", 90000);

            // Create driver instance
            AndroidDriver newDriver = new AndroidDriver(
                    new URI(DeviceConfig.getAppiumServerURL()).toURL(),
                    options
            );

            // Set implicit wait
            newDriver.manage().timeouts()
                    .implicitlyWait(Duration.ofSeconds(AppConfig.getImplicitWait()));

            driverThreadLocal.set(newDriver);
            LogUtils.info("Ciye app driver initialized successfully");

        } catch (MalformedURLException | URISyntaxException e) {
            LogUtils.error("Failed to create driver: " + e.getMessage());
            throw new RuntimeException("Driver initialization failed", e);
        }
    }

    /**
     * Quit driver and clean up
     */
    public void quitDriver() {
        AndroidDriver currentDriver = driverThreadLocal.get();
        if (currentDriver != null) {
            LogUtils.info("Quitting driver...");
            currentDriver.quit();
            driverThreadLocal.remove();
            LogUtils.info("Driver quit successfully");
        }
    }

    /**
     * Check if driver is active
     */
    public boolean isDriverActive() {
        return driverThreadLocal.get() != null;
    }
}