package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.Scenario;
import utils.utils;
import utils.ScreenshotUtil;

public class Login extends utils{
//    AndroidDriver driver;
    public Login(AndroidDriver driver){this.driver = driver;}

    public void login(String username, String password) throws InterruptedException {
//        Thread.sleep(2000);
        driver.findElement(AppiumBy.accessibilityId("test-Username")).sendKeys(username);
        driver.findElement(AppiumBy.accessibilityId("test-Password")).sendKeys(password);
        ScreenshotUtil ss = new ScreenshotUtil(driver);
        ss.takeScreenshotWithResizedHeight("Fill Username and Password");
        driver.findElement(AppiumBy.accessibilityId("test-LOGIN")).click();
    }
}
