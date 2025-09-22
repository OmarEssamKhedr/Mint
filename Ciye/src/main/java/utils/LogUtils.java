package utils;

import io.qameta.allure.Allure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Logging Utility
 * Provides centralized logging with Allure integration
 *
 * @author Ciye Test Team
 */
public class LogUtils {

    private static final Logger logger = LoggerFactory.getLogger(LogUtils.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Log info message
     */
    public static void info(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        String logMessage = String.format("[%s] [INFO] %s", timestamp, message);
        logger.info(logMessage);
        Allure.step(message);
    }

    /**
     * Log error message
     */
    public static void error(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        String logMessage = String.format("[%s] [ERROR] %s", timestamp, message);
        logger.error(logMessage);
        Allure.step("❌ ERROR: " + message);
    }

    /**
     * Log warning message
     */
    public static void warn(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        String logMessage = String.format("[%s] [WARN] %s", timestamp, message);
        logger.warn(logMessage);
        Allure.step("⚠️ WARNING: " + message);
    }

    /**
     * Log debug message
     */
    public static void debug(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        String logMessage = String.format("[%s] [DEBUG] %s", timestamp, message);
        logger.debug(logMessage);
    }

    /**
     * Log test step
     */
    public static void step(String stepDescription) {
        info("STEP: " + stepDescription);
    }

    /**
     * Log test start
     */
    public static void testStart(String testName) {
        String message = "🚀 Starting test: " + testName;
        info(message);
        Allure.step(message);
    }

    /**
     * Log test end
     */
    public static void testEnd(String testName) {
        String message = "✅ Completed test: " + testName;
        info(message);
        Allure.step(message);
    }
}