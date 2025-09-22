package utils;

import io.qameta.allure.Step;
import org.testng.Assert;

/**
 * Assert Utility with Allure Integration
 * Provides custom assertions with step logging
 *
 * @author Ciye Test Team
 */
public class AssertUtils {

    /**
     * Assert true with step logging
     */
    @Step("Assert that condition is true: {description}")
    public static void assertTrue(boolean condition, String description) {
        try {
            Assert.assertTrue(condition, description);
            LogUtils.info("✅ Assertion passed: " + description);
        } catch (AssertionError e) {
            LogUtils.error("❌ Assertion failed: " + description);
            ScreenshotUtils.takeScreenshot();
            throw e;
        }
    }

    /**
     * Assert false with step logging
     */
    @Step("Assert that condition is false: {description}")
    public static void assertFalse(boolean condition, String description) {
        try {
            Assert.assertFalse(condition, description);
            LogUtils.info("✅ Assertion passed: " + description);
        } catch (AssertionError e) {
            LogUtils.error("❌ Assertion failed: " + description);
            ScreenshotUtils.takeScreenshot();
            throw e;
        }
    }

    /**
     * Assert equals with step logging
     */
    @Step("Assert that actual equals expected: {description}")
    public static void assertEquals(Object actual, Object expected, String description) {
        try {
            Assert.assertEquals(actual, expected, description);
            LogUtils.info("✅ Assertion passed: " + description + " (Expected: " + expected + ", Actual: " + actual + ")");
        } catch (AssertionError e) {
            LogUtils.error("❌ Assertion failed: " + description + " (Expected: " + expected + ", Actual: " + actual + ")");
            ScreenshotUtils.takeScreenshot();
            throw e;
        }
    }

    /**
     * Assert not equals with step logging
     */
    @Step("Assert that actual does not equal unexpected: {description}")
    public static void assertNotEquals(Object actual, Object unexpected, String description) {
        try {
            Assert.assertNotEquals(actual, unexpected, description);
            LogUtils.info("✅ Assertion passed: " + description);
        } catch (AssertionError e) {
            LogUtils.error("❌ Assertion failed: " + description);
            ScreenshotUtils.takeScreenshot();
            throw e;
        }
    }

    /**
     * Assert element is displayed
     */
    @Step("Assert that element is displayed: {elementDescription}")
    public static void assertElementDisplayed(boolean isDisplayed, String elementDescription) {
        try {
            Assert.assertTrue(isDisplayed, elementDescription + " should be displayed");
            LogUtils.info("✅ Element is displayed: " + elementDescription);
        } catch (AssertionError e) {
            LogUtils.error("❌ Element is not displayed: " + elementDescription);
            ScreenshotUtils.takeScreenshot();
            throw e;
        }
    }

    /**
     * Assert text contains expected value
     */
    @Step("Assert that text contains expected value: {description}")
    public static void assertTextContains(String actualText, String expectedText, String description) {
        try {
            Assert.assertTrue(actualText.contains(expectedText),
                    description + " - Expected text: '" + expectedText + "' not found in actual text: '" + actualText + "'");
            LogUtils.info("✅ Text assertion passed: " + description);
        } catch (AssertionError e) {
            LogUtils.error("❌ Text assertion failed: " + description);
            ScreenshotUtils.takeScreenshot();
            throw e;
        }
    }

    /**
     * Soft assert - doesn't stop execution
     */
    public static void softAssert(boolean condition, String description) {
        if (condition) {
            LogUtils.info("✅ Soft assertion passed: " + description);
        } else {
            LogUtils.warn("⚠️ Soft assertion failed: " + description);
            ScreenshotUtils.takeScreenshot();
        }
    }
}