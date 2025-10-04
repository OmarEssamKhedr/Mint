package pages;

import driver.DriverManager;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.LogUtils;
import utils.WaitUtils;

/**
 * Base Page Class
 * Contains common methods used across all page objects
 *
 * @author Ciye Test Team
 */
public class BasePage {

    protected AndroidDriver driver;

    public BasePage() {
        this.driver = DriverManager.getInstance().getDriver();
    }

    /**
     * Find element with wait
     */
    protected WebElement findElement(By locator) {
        return WaitUtils.waitForElementPresent(locator);
    }

    /**
     * Find element and click
     */
    protected void clickElement(By locator) {
        WebElement element = WaitUtils.waitForElementClickable(locator);
        element.click();
        LogUtils.info("Clicked element: " + locator);
    }

    /**
     * Click element immediately without long wait
     */
    protected void clickElementQuick(By locator) {
        try {
            WebElement element = WaitUtils.waitForElementVisible(locator, 3); // 3 second timeout
            element.click();
            LogUtils.info("Clicked element: " + locator);
        } catch (Exception e) {
            LogUtils.error("Quick click failed: " + e.getMessage());
            throw e;
        }
    }
    /**
     * Click element immediately without long clickable wait
     */
    protected void clickElementImmediate(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            element.click();
            LogUtils.info("Clicked element immediately: " + locator);
        } catch (Exception e) {
            LogUtils.error("Immediate click failed: " + e.getMessage());
            throw new RuntimeException("Could not click element: " + locator, e);
        }
    }

    /**
     * Find element and enter text
     */
    protected void enterText(By locator, String text) {
        WebElement element = WaitUtils.waitForElementPresent(locator);
        element.clear();
        element.sendKeys(text);
        LogUtils.info("Entered text '" + text + "' in element: " + locator);
    }

    /**
     * Get element text
     */
    protected String getElementText(By locator) {
        WebElement element = WaitUtils.waitForElementPresent(locator);
        String text = element.getText();
        LogUtils.info("Got text '" + text + "' from element: " + locator);
        return text;
    }

    /**
     * Check if element is displayed
     */
    protected boolean isElementDisplayed(By locator) {
        try {
            WebElement element = WaitUtils.waitForElementVisible(locator, 10);
            boolean isDisplayed = element.isDisplayed();
            LogUtils.info("Element visibility check - " + locator + ": " + isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            LogUtils.info("Element not visible: " + locator);
            return false;
        }
    }

    /**
     * Check if element is present
     */
    protected boolean isElementPresent(By locator) {
        boolean isPresent = WaitUtils.isElementPresent(locator);
        LogUtils.info("Element presence check - " + locator + ": " + isPresent);
        return isPresent;
    }

    /**
     * Scroll until element with given text is visible
     */
    protected void scrollToText(String visibleText) {
        try {
            driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true))" +
                            ".scrollIntoView(new UiSelector().text(\"" + visibleText + "\"))"
            ));
            LogUtils.info("Scrolled to text: " + visibleText);
        } catch (Exception e) {
            LogUtils.error("Failed to scroll to text: " + visibleText);
            throw e;
        }
    }

    /**
     * Hide soft keyboard
     */
    protected void hideKeyboard() {
        try {
            driver.hideKeyboard();
            LogUtils.info("Keyboard hidden");
            Thread.sleep(500); // Brief wait for keyboard to disappear
        } catch (Exception e) {
            LogUtils.info("Keyboard was not showing or already hidden");
        }
    }

    /**
     * Scroll until element with given description is visible
     */
    protected void scrollToDescription(String description) {
        try {
            driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true))" +
                            ".scrollIntoView(new UiSelector().description(\"" + description + "\"))"
            ));
            LogUtils.info("Scrolled to description: " + description);
        } catch (Exception e) {
            LogUtils.error("Failed to scroll to description: " + description);
            throw e;
        }
    }

    /**
     * Navigate back
     */
    protected void navigateBack() {
        driver.navigate().back();
        LogUtils.info("Navigated back");
    }

    /**
     * Wait for page to load
     */
    protected void waitForPageLoad() {
        try {
            Thread.sleep(2000); // Basic wait for page transitions
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}