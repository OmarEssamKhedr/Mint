package utils;

import driver.DriverManager;
import driver.ThreadLocalDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Screenshot Utility
 * Handles screenshot capture and Allure attachment
 *
 * @author Ciye Test Team
 */
public class ScreenshotUtils {

    private static final String SCREENSHOT_DIR = "target/screenshots/";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");

    /**
     * Take screenshot and attach to Allure report
     */
    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] takeScreenshot() {
        try {
            TakesScreenshot screenshot = ThreadLocalDriverManager.getDriver(); // Changed from DriverManager
            byte[] screenshotBytes = screenshot.getScreenshotAs(OutputType.BYTES);

            String deviceId = driver.ThreadLocalDriverManager.getCurrentDeviceId();
            LogUtils.info("Screenshot captured successfully for device: " + deviceId);
            return screenshotBytes;

        } catch (Exception e) {
            LogUtils.error("Failed to capture screenshot: " + e.getMessage());
            return new byte[0];
        }
    }

    /**
     * Take screenshot and save to file
     */
    public static String takeScreenshotAsFile(String testName) {
        try {
            // Create screenshot directory if not exists
            File screenshotDir = new File(SCREENSHOT_DIR);
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }

            // Take screenshot
            TakesScreenshot screenshot = DriverManager.getInstance().getDriver();
            File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);

            // Generate file name with timestamp
            String timestamp = dateFormat.format(new Date());
            String fileName = testName + "_" + timestamp + ".png";
            String filePath = SCREENSHOT_DIR + fileName;

            // Copy screenshot to destination
            File destFile = new File(filePath);
            FileUtils.copyFile(sourceFile, destFile);

            LogUtils.info("Screenshot saved to: " + filePath);
            return filePath;

        } catch (IOException e) {
            LogUtils.error("Failed to save screenshot: " + e.getMessage());
            return null;
        }
    }

    /**
     * Take screenshot on test failure and attach to Allure
     */
    public static void captureFailureScreenshot(String testName) {
        try {
            // Take screenshot as bytes for Allure
            byte[] screenshotBytes = takeScreenshot();

            // Also save as file for backup
            takeScreenshotAsFile(testName + "_FAILURE");

            // Attach to Allure report
            Allure.addAttachment(
                    "Failure Screenshot - " + testName,
                    "image/png",
                    new ByteArrayInputStream(screenshotBytes),
                    ".png"
            );

            LogUtils.info("Failure screenshot captured for test: " + testName);

        } catch (Exception e) {
            LogUtils.error("Failed to capture failure screenshot: " + e.getMessage());
        }
    }

    /**
     * Take screenshot for test step
     */
    public static void captureStepScreenshot(String stepName) {
        try {
            byte[] screenshotBytes = takeScreenshot();

            Allure.addAttachment(
                    "Step Screenshot - " + stepName,
                    "image/png",
                    new ByteArrayInputStream(screenshotBytes),
                    ".png"
            );

            LogUtils.info("Step screenshot captured: " + stepName);

        } catch (Exception e) {
            LogUtils.error("Failed to capture step screenshot: " + e.getMessage());
        }
    }
}