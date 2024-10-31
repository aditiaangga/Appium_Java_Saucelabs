package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import utils.utils;
import utils.ScreenshotUtil;



public class Checkout extends utils{
//    static AndroidDriver driver;
    public Checkout(AndroidDriver driver){this.driver = driver;}

    public void checkout() throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.widget.ImageView")).click();
        Thread.sleep(3000);

        findingElementAndClick("test-CHECKOUT", "Cart List");
//        swipeUp();
//        driver.findElement(AppiumBy.accessibilityId("test-CHECKOUT")).click();
        Thread.sleep(2000);
        WebElement firstname = driver.findElement(AppiumBy.accessibilityId("test-First Name"));
        firstname.sendKeys("Aditia");
        String textFirstName = firstname.getText();
        Assert.assertEquals("Aditia",textFirstName);

        WebElement lastname = driver.findElement(AppiumBy.accessibilityId("test-Last Name"));
        lastname.sendKeys("Perdana");
        String textLastName = lastname.getText();
        Assert.assertEquals("Perdana",textLastName);

        WebElement zipcode = driver.findElement(AppiumBy.accessibilityId("test-Zip/Postal Code"));
        zipcode.sendKeys("12130");
        String textZipCode = zipcode.getText();
        Assert.assertEquals("12130", textZipCode);

        Assert.assertEquals("Aditia",firstname.getText());
        Assert.assertEquals("Perdana",lastname.getText());
        Assert.assertEquals("12130",zipcode.getText());

        Thread.sleep(2000);
        ScreenshotUtil ss = new ScreenshotUtil(driver);
        ss.takeScreenshotWithResizedHeight("Data Information");
        driver.findElement(AppiumBy.accessibilityId("test-CONTINUE")).click();
        Thread.sleep(2000);

        findingElementAndClick("test-FINISH","Checkout Overview");
//        swipeUp();
//        Thread.sleep(5000);
//        driver.findElement(AppiumBy.accessibilityId("test-FINISH")).click();
        Thread.sleep(7000);
        ss.takeScreenshotWithResizedHeight("Checkout Complete");
    }


}
