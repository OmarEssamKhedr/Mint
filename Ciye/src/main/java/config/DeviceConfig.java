package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Device Configuration Manager
 * Handles all device-related configuration properties
 *
 * @author Ciye Test Team
 */
public class DeviceConfig {

    private static Properties properties;
    private static final String CONFIG_FILE_PATH = "src/main/resources/config.properties";

    static {
        loadProperties();
    }

    /**
     * Load configuration properties from file
     */
    private static void loadProperties() {
        properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream(CONFIG_FILE_PATH);
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties file", e);
        }
    }

    /**
     * Get device platform name
     */
    public static String getPlatformName() {
        return properties.getProperty("device.platform");
    }

    /**
     * Get automation name
     */
    public static String getAutomationName() {
        return properties.getProperty("device.automation");
    }

    /**
     * Get device platform version
     */
    public static String getPlatformVersion() {
        return properties.getProperty("device.version");
    }

    /**
     * Get device name
     */
    public static String getDeviceName() {
        return properties.getProperty("device.name");
    }

    /**
     * Get device UDID
     */
    public static String getDeviceUDID() {
        return properties.getProperty("device.udid");
    }

    /**
     * Get Appium server URL
     */
    public static String getAppiumServerURL() {
        return properties.getProperty("appium.server.url");
    }
}