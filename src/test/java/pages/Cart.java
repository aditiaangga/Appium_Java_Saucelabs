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
    ScreenshotUtil ss = new ScreenshotUtil(driver);

    public Cart(AndroidDriver driver){this.driver = driver;}


    public void addToCart(String Product) throws InterruptedException {
//        Thread.sleep(2000);
        waitForElementVisibleWithRetry(AppiumBy.androidUIAutomator("new UiSelector().text(\"PRODUCTS\")"),5,5);
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"PRODUCTS\")")).isDisplayed();


        By locator = AppiumBy.androidUIAutomator("new UiSelector().text(\"" + Product + "\")");
        findElementWithSwipe(locator);

//        waitToPresence(driver, (AppiumBy) AppiumBy.androidUIAutomator("new UiSelector().text(\"" + Product + "\")"));
//        Thread.sleep(2000);
        waitForElementVisibleWithRetry(locator,5,5);
        driver.findElement(locator).isDisplayed();
        ss.takeScreenshotWithResizedHeight("Add to Cart "+Product);
        swipeUp();
        driver.findElement(AppiumBy.accessibilityId("test-ADD TO CART")).click();
        driver.findElement(AppiumBy.accessibilityId("test-BACK TO PRODUCTS")).click();
    }

    public void toggle() throws InterruptedException {
//        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        ss.takeScreenshotWithResizedHeight("Before Click Toggle");
        driver.findElement(AppiumBy.accessibilityId("test-Toggle")).click();
//        Thread.sleep(1000);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        ss.takeScreenshotWithResizedHeight("After Click Toggle");
    }

    public void sort(String sort) throws InterruptedException {
//        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        ss.takeScreenshotWithResizedHeight("Before Sort");
        driver.findElement(AppiumBy.accessibilityId("test-Modal Selector Button")).click();
//        Thread.sleep(1000);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        ss.takeScreenshotWithResizedHeight("Sort list");
        switch (sort.toLowerCase()) {
            case "atz":
                driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Name (A to Z)\")")).click();
//                Thread.sleep(1000);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
                ss.takeScreenshotWithResizedHeight("After Sort Name (A to Z)");
                break;
            case "zta":
                driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Name (Z to A)\")")).click();
//                Thread.sleep(1000);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
                ss.takeScreenshotWithResizedHeight("After Sort Name (Z to A)");
                break;
            case "lth":
                driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Price (low to high)\")")).click();
//                Thread.sleep(1000);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
                ss.takeScreenshotWithResizedHeight("After Sort Price (Low to High)");
                break;
            case "htl":
                driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Price (high to low)\")")).click();
//                Thread.sleep(1000);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
                ss.takeScreenshotWithResizedHeight("After Sort Price (High to Low)");
                break;
            default:
                System.out.println("Sort is not valid");
        }
    }




}
