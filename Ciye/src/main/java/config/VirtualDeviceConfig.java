package config;

import java.util.HashMap;
import java.util.Map;

/**
 * Virtual Device Configuration
 * Manages multiple virtual device configurations for parallel testing
 *
 * @author Ciye Test Team
 */
public class VirtualDeviceConfig {

    public static class DeviceInfo {
        public String deviceName;
        public String platformVersion;
        public String avdName;
        public String systemPort;
        public String udid;

        public DeviceInfo(String deviceName, String platformVersion, String avdName, String systemPort, String udid) {
            this.deviceName = deviceName;
            this.platformVersion = platformVersion;
            this.avdName = avdName;
            this.systemPort = systemPort;
            this.udid = udid;
        }
    }

    private static final Map<String, DeviceInfo> DEVICES = new HashMap<>();

    static {
        // Device 1: Pixel 7 Pro Emulator
        DEVICES.put("device1", new DeviceInfo(
                "emulator-5554",
                "14",
                "pixel7pro_api34",
                "4723",
                "emulator-5554"
        ));

        // Device 2: Galaxy S21 Emulator
        DEVICES.put("device2", new DeviceInfo(
                "emulator-5556",
                "11",
                "galaxyS21_api30",
                "4725",
                "emulator-5556"
        ));

        // Device 3: Nexus 5X Emulator
        DEVICES.put("device3", new DeviceInfo(
                "emulator-5558",
                "9",
                "nexus5x_api28",
                "4727",
                "emulator-5558"
        ));
    }

    /**
     * Get device configuration by device ID
     */
    public static DeviceInfo getDeviceInfo(String deviceId) {
        return DEVICES.get(deviceId);
    }

    /**
     * Get all available device IDs
     */
    public static String[] getAllDeviceIds() {
        return DEVICES.keySet().toArray(new String[0]);
    }

    /**
     * Get Appium server URL for device
     */
    public static String getAppiumServerUrl(String deviceId) {
        DeviceInfo device = getDeviceInfo(deviceId);
        return "http://127.0.0.1:" + device.systemPort;
    }
}