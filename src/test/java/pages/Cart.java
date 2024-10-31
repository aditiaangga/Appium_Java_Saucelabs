package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Arrays;

import utils.utils;
import utils.ScreenshotUtil;




public class Cart extends utils{
//    static AndroidDriver driver;
    public Cart(AndroidDriver driver){this.driver = driver;}

    public void addToCart(String Product) throws InterruptedException {
        Thread.sleep(2000);
        ScreenshotUtil ss = new ScreenshotUtil(driver);

        By locator = AppiumBy.androidUIAutomator("new UiSelector().text(\"" + Product + "\")");
        findElementWithSwipe(locator);

//        waitToPresence(driver, (AppiumBy) AppiumBy.androidUIAutomator("new UiSelector().text(\"" + Product + "\")"));
        Thread.sleep(2000);
        driver.findElement(locator).isDisplayed();
        ss.takeScreenshotWithResizedHeight("Add to Cart "+Product);
        swipeUp();
        driver.findElement(AppiumBy.accessibilityId("test-ADD TO CART")).click();
        driver.findElement(AppiumBy.accessibilityId("test-BACK TO PRODUCTS")).click();
    }




}
