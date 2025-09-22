package config;

import io.appium.java_client.android.options.UiAutomator2Options;

/**
 * Device Capability Factory
 * Creates device-specific capabilities for parallel testing
 *
 * @author Ciye Test Team
 */
public class DeviceCapabilityFactory {

    /**
     * Create capabilities for specific device
     */
    public static UiAutomator2Options createCapabilities(String deviceId) {
        VirtualDeviceConfig.DeviceInfo device = VirtualDeviceConfig.getDeviceInfo(deviceId);

        if (device == null) {
            throw new RuntimeException("Device configuration not found for: " + deviceId);
        }

        UiAutomator2Options options = new UiAutomator2Options();

        // Device capabilities
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

        // Additional capabilities
        options.setCapability("appium:newCommandTimeout", 300);
        options.setCapability("appium:androidInstallTimeout", 90000);

        return options;
    }

    /**
     * Create capabilities for all devices
     */
    public static UiAutomator2Options[] createAllCapabilities() {
        String[] deviceIds = VirtualDeviceConfig.getAllDeviceIds();
        UiAutomator2Options[] allCapabilities = new UiAutomator2Options[deviceIds.length];

        for (int i = 0; i < deviceIds.length; i++) {
            allCapabilities[i] = createCapabilities(deviceIds[i]);
        }

        return allCapabilities;
    }
}