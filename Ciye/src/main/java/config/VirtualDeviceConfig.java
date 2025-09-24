package config;

import java.util.HashMap;
import java.util.Map;

/**
 * Virtual Device Configuration
 * Manages identical Android 14 emulators for consistent parallel testing
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
        public String model;
        public String manufacturer;
        public String density;
        public String resolution;

        public DeviceInfo(String deviceName, String platformVersion, String avdName,
                          String systemPort, String udid, String model, String manufacturer,
                          String density, String resolution) {
            this.deviceName = deviceName;
            this.platformVersion = platformVersion;
            this.avdName = avdName;
            this.systemPort = systemPort;
            this.udid = udid;
            this.model = model;
            this.manufacturer = manufacturer;
            this.density = density;
            this.resolution = resolution;
        }
    }

    private static final Map<String, DeviceInfo> DEVICES = new HashMap<>();

    static {
        // Device 1: emulator-5554
        DEVICES.put("device1", new DeviceInfo(
                "emulator-5554",
                "16",  // Correct Android version from your devices
                "Pixel7Pro",
                "4723",
                "emulator-5554",
                "sdk_gphone64_x86_64",
                "Google",
                "560",
                "1440x3120"
        ));

        // Device 2: emulator-5556
        DEVICES.put("device2", new DeviceInfo(
                "emulator-5556",
                "16",  // Correct Android version from your devices
                "Pixel8",
                "4724",
                "emulator-5556",
                "sdk_gphone64_x86_64",
                "Google",
                "420",
                "1080x2400"
        ));

        // Device 3: emulator-5558
        DEVICES.put("device3", new DeviceInfo(
                "emulator-5558",
                "16",  // Correct Android version from your devices
                "Pixel9",
                "4725",
                "emulator-5558",
                "sdk_gphone64_x86_64",
                "Google",
                "420",
                "1080x2424"
        ));
    }

    public static DeviceInfo getDeviceInfo(String deviceId) {
        return DEVICES.get(deviceId);
    }

    public static String[] getAllDeviceIds() {
        return DEVICES.keySet().toArray(new String[0]);
    }

    public static String getAppiumServerUrl(String deviceId) {
        DeviceInfo device = getDeviceInfo(deviceId);
        // For Appium 2.x, use root endpoint
        return "http://127.0.0.1:" + device.systemPort + "/";
    }

    public static void addDevice(String deviceId, DeviceInfo deviceInfo) {
        DEVICES.put(deviceId, deviceInfo);
    }

    public static Map<String, DeviceInfo> getAllDevices() {
        return new HashMap<>(DEVICES);
    }
}