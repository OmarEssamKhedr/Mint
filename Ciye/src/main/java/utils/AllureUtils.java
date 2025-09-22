package utils;

import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;

/**
 * Allure Utility
 * Provides helper methods for Allure reporting
 *
 * @author Ciye Test Team
 */
public class AllureUtils {

    /**
     * Add test step to Allure report
     */
    public static void step(String stepName) {
        Allure.step(stepName);
        LogUtils.step(stepName);
    }

    /**
     * Add test step with status
     */
    public static void step(String stepName, Status status) {
        Allure.step(stepName, status);
        if (status == Status.PASSED) {
            LogUtils.info("✅ " + stepName);
        } else if (status == Status.FAILED) {
            LogUtils.error("❌ " + stepName);
        } else {
            LogUtils.warn("⚠️ " + stepName);
        }
    }

    /**
     * Add environment information
     */
    public static void addEnvironmentInfo() {
        Allure.addAttachment("Test Environment", "text/plain",
                "App Package: co.ciye.swim\n" +
                        "Device: samsung SM-S928B\n" +
                        "Platform: Android 15.0\n" +
                        "Automation: UiAutomator2"
        );
    }

    /**
     * Add parameter to Allure report
     */
    public static void addParameter(String name, String value) {
        Allure.parameter(name, value);
    }
    /**
     * Add test description
     */
    public static void addDescription(String description) {
        Allure.description(description);
    }

    /**
     * Add issue link
     */
    public static void addTestCaseLink(String testCaseKey) {
        Allure.tms(testCaseKey, testCaseKey);
    }

    public static void addIssueLink(String issueKey) {
        Allure.issue(issueKey, issueKey);
    }

    /**
     * Add severity level (Note: Severity is typically set via @Severity annotation on test methods)
     */
    public static void addSeverity(String severityLevel) {
        // Severity in Allure is typically set via @Severity annotation on test methods
        // This method can be used to log severity information
        LogUtils.info("Test severity level: " + severityLevel);
        Allure.parameter("Severity", severityLevel);
    }

    /**
     * Add epic
     */
    public static void addEpic(String epic) {
        Allure.epic(epic);
    }

    /**
     * Add feature
     */
    public static void addFeature(String feature) {
        Allure.feature(feature);
    }

    /**
     * Add story
     */
    public static void addStory(String story) {
        Allure.story(story);
    }

    /**
     * Add attachment from text
     */
    public static void addTextAttachment(String name, String content) {
        Allure.addAttachment(name, "text/plain", content);
    }

    /**
     * Add JSON attachment
     */
    public static void addJsonAttachment(String name, String jsonContent) {
        Allure.addAttachment(name, "application/json", jsonContent);
    }
}