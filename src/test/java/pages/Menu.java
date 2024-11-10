package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import utils.utils;
import utils.ScreenshotUtil;

public class Menu extends utils{

    public Menu(AndroidDriver driver){this.driver = driver;}

    public void clickMenu() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(AppiumBy.accessibilityId("test-Menu")).click();
        Thread.sleep(1000);
        ScreenshotUtil ss = new ScreenshotUtil(driver);
        ss.takeScreenshotWithResizedHeight("Menu");
    }

    public void clickClose(){
        driver.findElement(AppiumBy.accessibilityId("test-Close")).click();
    }

    public void clickAllItem(){
        driver.findElement(AppiumBy.accessibilityId("test-ALL ITEMS")).click();
    }

    public void clickWebView(){
        driver.findElement(AppiumBy.accessibilityId("test-WEBVIEW")).click();
    }

    public void clickQRCode(){
        driver.findElement(AppiumBy.accessibilityId("test-QR CODE SCANNER")).click();
    }

    public void clickGeoLocation(){
        driver.findElement(AppiumBy.accessibilityId("test-GEO LOCATION")).click();
    }

    public void clickDrawing(){
        driver.findElement(AppiumBy.accessibilityId("test-DRAWING")).click();
    }

    public void clickAbout(){
        driver.findElement(AppiumBy.accessibilityId("test-ABOUT")).click();
    }

    public void clickLogout(){
        driver.findElement(AppiumBy.accessibilityId("test-LOGOUT")).click();
    }

    public void clickResetAppState(){
        driver.findElement(AppiumBy.accessibilityId("test-RESET APP STATE")).click();
    }
}
