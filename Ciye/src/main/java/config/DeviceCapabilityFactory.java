package config;

import io.appium.java_client.android.options.UiAutomator2Options;

/**
 * Device Capability Factory
 * Creates standardized capabilities for identical Android 14 emulators
 *
 * @author Ciye Test Team
 */
public class DeviceCapabilityFactory {

    public static UiAutomator2Options createCapabilities(String deviceId) {
        VirtualDeviceConfig.DeviceInfo device = VirtualDeviceConfig.getDeviceInfo(deviceId);

        if (device == null) {
            throw new RuntimeException("Device configuration not found for: " + deviceId);
        }

        UiAutomator2Options options = new UiAutomator2Options();

        // Platform capabilities
        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");
        options.setPlatformVersion(device.platformVersion);
        options.setDeviceName(device.deviceName);
        options.setUdid(device.udid);

        // App capabilities
        options.setAppPackage(AppConfig.getAppPackage());
        options.setAppActivity(AppConfig.getAppActivity());
        options.setNoReset(AppConfig.getNoReset());
        options.setCapability("autoGrantPermissions", AppConfig.getAutoGrantPermissions());

        // Performance and stability capabilities
        options.setCapability("appium:newCommandTimeout", 300);
        options.setCapability("appium:androidInstallTimeout", 90000);
        options.setCapability("appium:uiautomator2ServerLaunchTimeout", 60000);
        options.setCapability("appium:uiautomator2ServerInstallTimeout", 60000);
        options.setCapability("appium:adbExecTimeout", 20000);

        // Device-specific optimizations
        options.setCapability("appium:ignoreHiddenApiPolicyError", true);
        options.setCapability("appium:disableIdLocatorAutocompletion", true);
        options.setCapability("appium:ensureWebviewsHavePages", true);

        return options;
    }
}