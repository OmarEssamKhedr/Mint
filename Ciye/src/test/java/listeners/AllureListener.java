package listeners;

import io.qameta.allure.Attachment;
import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.TestResult;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.AllureUtils;
import utils.LogUtils;
import utils.ScreenshotUtils;

/**
 * Allure Test Listener
 * Handles test lifecycle events and attaches screenshots, logs to Allure report
 *
 * @author Ciye Test Team
 */
public class AllureListener implements ITestListener, TestLifecycleListener {

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String className = result.getTestClass().getName();

        LogUtils.testStart(className + "." + testName);
        AllureUtils.addEnvironmentInfo();

        // Add test metadata
        AllureUtils.addEpic("Ciye Mobile App Testing");
        AllureUtils.addFeature(getFeatureFromClassName(className));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        LogUtils.testEnd(testName + " - PASSED");

        // Take success screenshot
        attachScreenshot("Success Screenshot");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String errorMessage = result.getThrowable().getMessage();

        LogUtils.error("Test failed: " + testName + " - " + errorMessage);

        // Attach failure screenshot
        ScreenshotUtils.captureFailureScreenshot(testName);

        // Attach error details
        attachErrorDetails(errorMessage, result.getThrowable());

        LogUtils.testEnd(testName + " - FAILED");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String skipReason = result.getThrowable() != null ?
                result.getThrowable().getMessage() : "Test skipped";

        LogUtils.warn("Test skipped: " + testName + " - " + skipReason);
        attachSkipReason(skipReason);
    }

    /**
     * Attach screenshot to Allure report
     */
    @Attachment(value = "{attachmentName}", type = "image/png")
    public byte[] attachScreenshot(String attachmentName) {
        return ScreenshotUtils.takeScreenshot();
    }

    /**
     * Attach error details to Allure report
     */
    @Attachment(value = "Error Details", type = "text/plain")
    public String attachErrorDetails(String message, Throwable throwable) {
        StringBuilder errorDetails = new StringBuilder();
        errorDetails.append("Error Message: ").append(message).append("\n\n");
        errorDetails.append("Stack Trace:\n");

        for (StackTraceElement element : throwable.getStackTrace()) {
            errorDetails.append(element.toString()).append("\n");
        }

        return errorDetails.toString();
    }

    /**
     * Attach skip reason to Allure report
     */
    @Attachment(value = "Skip Reason", type = "text/plain")
    public String attachSkipReason(String reason) {
        return "Test was skipped because: " + reason;
    }

    /**
     * Extract feature name from class name
     */
    private String getFeatureFromClassName(String className) {
        if (className.contains("Login")) {
            return "Login Functionality";
        } else if (className.contains("Profile")) {
            return "Profile Management";
        } else if (className.contains("Navigation")) {
            return "App Navigation";
        } else {
            return "General Functionality";
        }
    }

    @Override
    public void beforeTestStop(TestResult result) {
        // Add any cleanup or additional attachments before test completes
        LogUtils.info("Completing test execution...");
    }

    @Override
    public void afterTestStop(TestResult result) {
        // Final cleanup after test stops
        LogUtils.info("Test execution completed");
    }
}