package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Application Configuration Manager
 * Handles all app-related configuration properties
 *
 * @author Ciye Test Team
 */
public class AppConfig {

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
     * Get app package name
     */
    public static String getAppPackage() {
        return properties.getProperty("app.package");
    }

    /**
     * Get app main activity
     */
    public static String getAppActivity() {
        return properties.getProperty("app.activity");
    }

    /**
     * Get no reset flag
     */
    public static boolean getNoReset() {
        return Boolean.parseBoolean(properties.getProperty("app.no.reset"));
    }

    /**
     * Get auto grant permissions flag
     */
    public static boolean getAutoGrantPermissions() {
        return Boolean.parseBoolean(properties.getProperty("app.auto.grant.permissions"));
    }

    /**
     * Get wait timeout
     */
    public static int getWaitTimeout() {
        return Integer.parseInt(properties.getProperty("wait.timeout"));
    }

    /**
     * Get implicit wait timeout
     */
    public static int getImplicitWait() {
        return Integer.parseInt(properties.getProperty("implicit.wait"));
    }

    /**
     * Get test environment
     */
    public static String getTestEnvironment() {
        return properties.getProperty("test.env");
    }

    /**
     * Get retry count
     */
    public static int getRetryCount() {
        return Integer.parseInt(properties.getProperty("test.retries"));
    }
}