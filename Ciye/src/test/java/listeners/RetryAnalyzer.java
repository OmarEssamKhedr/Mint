package listeners;

import config.AppConfig;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import utils.LogUtils;

/**
 * Retry Analyzer
 * Automatically retries failed tests based on configuration
 *
 * @author Ciye Test Team
 */
public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;
    private final int maxRetryCount = AppConfig.getRetryCount();

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            String testName = result.getMethod().getMethodName();
            LogUtils.warn("Retrying test: " + testName + " (Attempt " + retryCount + " of " + maxRetryCount + ")");
            return true;
        }
        return false;
    }

    /**
     * Reset retry count for new test
     */
    public void resetRetryCount() {
        retryCount = 0;
    }

    /**
     * Get current retry count
     */
    public int getCurrentRetryCount() {
        return retryCount;
    }

    /**
     * Get max retry count
     */
    public int getMaxRetryCount() {
        return maxRetryCount;
    }
}