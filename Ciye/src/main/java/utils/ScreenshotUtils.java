package utils;

import driver.DriverManager;
import io.qameta.allure.Allure;
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

    public static byte[] takeScreenshot() {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) DriverManager.getInstance().getDriver();
            byte[] screenshotBytes = screenshot.getScreenshotAs(OutputType.BYTES);

            LogUtils.info("Screenshot captured successfully - Size: " + screenshotBytes.length + " bytes");

            // Attach directly to Allure
            Allure.addAttachment("Screenshot", "image/png", new ByteArrayInputStream(screenshotBytes), ".png");

            return screenshotBytes;

        } catch (Exception e) {
            LogUtils.error("Failed to capture screenshot: " + e.getMessage());
            e.printStackTrace();
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

            LogUtils.info("Failure screenshot captured for test: " + testName);

        } catch (Exception e) {
            LogUtils.error("Failed to capture failure screenshot: " + e.getMessage());
        }
    }
}