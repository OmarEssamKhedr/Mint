package utils;

import config.AppConfig;
import driver.DriverManager;
import driver.ThreadLocalDriverManager;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Wait Utility
 * Provides various wait methods for element interactions
 *
 * @author Ciye Test Team
 */
public class WaitUtils {

    // Replace the getDriver() method:
    private static AndroidDriver getDriver() {
        return ThreadLocalDriverManager.getDriver(); // Changed from DriverManager
    }


    private static WebDriverWait getWait() {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(AppConfig.getWaitTimeout()));
    }

    private static WebDriverWait getWait(int timeoutInSeconds) {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds));
    }

    /**
     * Wait for element to be present
     */
    public static WebElement waitForElementPresent(By locator) {
        try {
            LogUtils.debug("Waiting for element to be present: " + locator);
            WebElement element = getWait().until(ExpectedConditions.presenceOfElementLocated(locator));
            LogUtils.debug("Element found: " + locator);
            return element;
        } catch (Exception e) {
            LogUtils.error("Element not found within timeout: " + locator);
            throw e;
        }
    }

    /**
     * Wait for element to be present with custom timeout
     */
    public static WebElement waitForElementPresent(By locator, int timeoutInSeconds) {
        try {
            LogUtils.debug("Waiting for element to be present: " + locator + " (timeout: " + timeoutInSeconds + "s)");
            WebElement element = getWait(timeoutInSeconds).until(ExpectedConditions.presenceOfElementLocated(locator));
            LogUtils.debug("Element found: " + locator);
            return element;
        } catch (Exception e) {
            LogUtils.error("Element not found within timeout: " + locator);
            throw e;
        }
    }


    /**
     * Wait for element to be visible with custom timeout
     */
    public static WebElement waitForElementVisible(By locator, int timeoutInSeconds) {
        try {
            LogUtils.debug("Waiting for element to be visible: " + locator + " (timeout: " + timeoutInSeconds + "s)");
            WebElement element = getWait(timeoutInSeconds).until(ExpectedConditions.visibilityOfElementLocated(locator));
            LogUtils.debug("Element is visible: " + locator);
            return element;
        } catch (Exception e) {
            LogUtils.error("Element not visible within timeout: " + locator);
            throw e;
        }
    }

    /**
     * Wait for element to be clickable
     */
    public static WebElement waitForElementClickable(By locator) {
        try {
            LogUtils.debug("Waiting for element to be clickable: " + locator);
            WebElement element = getWait().until(ExpectedConditions.elementToBeClickable(locator));
            LogUtils.debug("Element is clickable: " + locator);
            return element;
        } catch (Exception e) {
            LogUtils.error("Element not clickable within timeout: " + locator);
            throw e;
        }
    }

    /**
     * Wait for element to be visible (using default timeout)
     */
    public static WebElement waitForElementVisible(By locator) {
        return waitForElementVisible(locator, AppConfig.getWaitTimeout());
    }
    /**
     * Wait for element to be clickable with custom timeout
     */
    public static WebElement waitForElementClickable(By locator, int timeoutInSeconds) {
        try {
            LogUtils.debug("Waiting for element to be clickable: " + locator + " (timeout: " + timeoutInSeconds + "s)");
            WebElement element = getWait(timeoutInSeconds).until(ExpectedConditions.elementToBeClickable(locator));
            LogUtils.debug("Element is clickable: " + locator);
            return element;
        } catch (Exception e) {
            LogUtils.error("Element not clickable within timeout: " + locator);
            throw e;
        }
    }

    /**
     * Wait for elements to be present
     */
    public static List<WebElement> waitForElementsPresent(By locator) {
        try {
            LogUtils.debug("Waiting for elements to be present: " + locator);
            List<WebElement> elements = getWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
            LogUtils.debug("Elements found: " + elements.size() + " elements for " + locator);
            return elements;
        } catch (Exception e) {
            LogUtils.error("Elements not found within timeout: " + locator);
            throw e;
        }
    }

    /**
     * Wait for text to be present in element
     */
    public static boolean waitForTextInElement(By locator, String text) {
        try {
            LogUtils.debug("Waiting for text '" + text + "' in element: " + locator);
            boolean result = getWait().until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
            LogUtils.debug("Text found in element: " + locator);
            return result;
        } catch (Exception e) {
            LogUtils.error("Text not found in element within timeout: " + locator);
            throw e;
        }
    }

    /**
     * Wait for element to disappear
     */
    public static boolean waitForElementToDisappear(By locator) {
        try {
            LogUtils.debug("Waiting for element to disappear: " + locator);
            boolean result = getWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
            LogUtils.debug("Element disappeared: " + locator);
            return result;
        } catch (Exception e) {
            LogUtils.error("Element did not disappear within timeout: " + locator);
            throw e;
        }
    }

    /**
     * Check if element is present without waiting
     */
    public static boolean isElementPresent(By locator) {
        try {
            getDriver().findElement(locator);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}