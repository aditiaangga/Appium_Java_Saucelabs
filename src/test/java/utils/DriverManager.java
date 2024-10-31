package utils;

import io.appium.java_client.android.AndroidDriver;

public class DriverManager {
    private static AndroidDriver driver;

    public static AndroidDriver getDriver() {
        return driver;
    }

    public static void setDriver(AndroidDriver driverInstance) {
        driver = driverInstance;
    }

    public static void quitDriver() {
        driver = null; // Set the driver to null after quitting
    }
}
