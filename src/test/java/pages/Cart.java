package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Pause;
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
//        findElementWithSwipe(locator);
        try {
            driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+Product+"\"));"));
        }
        catch (NoSuchElementException e){
            driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).flingBackward().scrollIntoView(text(\""+Product+"\"));"));
        }
        ss.takeScreenshotWithResizedHeight("Find Product");
        waitForElementVisibleWithRetry(locator, 5, 5);
        driver.findElement(locator).click();

//        waitToPresence(driver, (AppiumBy) AppiumBy.androidUIAutomator("new UiSelector().text(\"" + Product + "\")"));
//        Thread.sleep(2000);
        waitForElementVisibleWithRetry(locator,5,5);
        driver.findElement(locator).isDisplayed();
        ss.takeScreenshotWithResizedHeight("Add to Cart "+Product);
        swipeUp();
        driver.findElement(AppiumBy.accessibilityId("test-ADD TO CART")).click();
        driver.findElement(AppiumBy.accessibilityId("test-BACK TO PRODUCTS")).click();
    }

    public void dragAndDropAddToCart(String Product) throws InterruptedException {
        waitForElementVisibleWithRetry(AppiumBy.androidUIAutomator("new UiSelector().text(\"PRODUCTS\")"),5,5);
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"PRODUCTS\")")).isDisplayed();
        int attempts = 0;
        while (attempts < 5) {
            try {
                driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + Product + "\"));"));
                try {
                    driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"test-Item title\" and @text=\"" + Product + "\"]/..//android.view.ViewGroup[@content-desc=\"test-Drag Handle\"]")).isDisplayed();
                } catch (NoSuchElementException e) {
                    swipeUpALitte();
                }
            } catch (NoSuchElementException e) {
                driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).flingBackward().scrollIntoView(text(\"" + Product + "\"));"));
            }
            attempts++;
        }
        By locator = AppiumBy.xpath("//android.widget.TextView[@content-desc=\"test-Item title\" and @text=\"" + Product + "\"]/..//android.view.ViewGroup[@content-desc=\"test-Drag Handle\"]");
        ss.takeScreenshotWithResizedHeight("Find Product");

//        Thread.sleep(2000);
        waitForElementVisibleWithRetry(locator, 5, 5);
        driver.findElement(locator).isDisplayed();
        //From
        //Get Coordinate Element
        int startX = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"test-Item title\" and @text=\"" + Product + "\"]/..//android.view.ViewGroup[@content-desc=\"test-Drag Handle\"]")).getLocation().getX();
        int startY = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"test-Item title\" and @text=\"" + Product + "\"]/..//android.view.ViewGroup[@content-desc=\"test-Drag Handle\"]")).getLocation().getY();

        //To
        //Get Coordinate Element
        int endX = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"PRODUCTS\"]")).getLocation().getX();
        int endY = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"PRODUCTS\"]")).getLocation().getY();


        //drag and drop
        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        var start = new Point(startX, startY);
        var end = new Point(endX, endY);

        Sequence dragAndDrop = new Sequence(finger, 1);
        dragAndDrop.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), start.getX(), start.getY()));
        dragAndDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        dragAndDrop.addAction(new Pause(finger, Duration.ofMillis(500)));
        dragAndDrop.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), end.getX(), end.getY()));
        dragAndDrop.addAction(new Pause(finger, Duration.ofMillis(500)));
        dragAndDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

// Menjalankan sequence
        driver.perform(Arrays.asList(dragAndDrop));
        ss.takeScreenshotWithResizedHeight("Find Product");

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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        ss.takeScreenshotWithResizedHeight("Before Sort");
        driver.findElement(AppiumBy.accessibilityId("test-Modal Selector Button")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        ss.takeScreenshotWithResizedHeight("Sort list");
        switch (sort.toLowerCase()) {
            case "atz":
                driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Name (A to Z)\")")).click();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
                ss.takeScreenshotWithResizedHeight("After Sort Name (A to Z)");
                break;
            case "zta":
                driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Name (Z to A)\")")).click();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
                ss.takeScreenshotWithResizedHeight("After Sort Name (Z to A)");
                break;
            case "lth":
                driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Price (low to high)\")")).click();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
                ss.takeScreenshotWithResizedHeight("After Sort Price (Low to High)");
                break;
            case "htl":
                driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Price (high to low)\")")).click();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
                ss.takeScreenshotWithResizedHeight("After Sort Price (High to Low)");
                break;
            default:
                System.out.println("Sort is not valid");
        }
    }




}
