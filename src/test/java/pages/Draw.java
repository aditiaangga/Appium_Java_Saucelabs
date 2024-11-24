package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.utils;
import utils.ScreenshotUtil;

public class Draw extends utils{
    public Draw(AndroidDriver driver){this.driver = driver;}
    public void draw(){
        waitForElementVisibleWithRetry(By.xpath("//android.widget.Image"),5,5);
        WebElement drawBox = driver.findElement(By.xpath("//android.widget.Image"));
        System.out.println("Ukuran Size Height Element"+drawBox.getSize().getHeight());
        System.out.println("Ukuran Size Width Element"+drawBox.getSize().getWidth());
        System.out.println("Ukuran Location X Element"+drawBox.getLocation().getX());
        System.out.println("Ukuran Location Y Element"+drawBox.getLocation().getY());

        swipeToDraw(By.xpath("//android.widget.Image"),0.45, 0.55, 0.55, 0.55);
        swipeToDraw(By.xpath("//android.widget.Image"),0.45, 0.35, 0.55, 0.85);
        swipeToDraw(By.xpath("//android.widget.Image"),0.2, 0.35, 0.85, 0.85);
        swipeToDraw(By.xpath("//android.widget.Image"),0.2, 0.3, 0.85, 0.55);
        swipeToDraw(By.xpath("//android.widget.Image"),0.3, 0.25, 0.55, 0.55);
        swipeToDraw(By.xpath("//android.widget.Image"),0.25, 0.25, 0.55, 0.45);
        swipeToDraw(By.xpath("//android.widget.Image"),0.25, 0.35, 0.45, 0.45);
        swipeToDraw(By.xpath("//android.widget.Image"),0.35, 0.45, 0.45, 0.15);
        swipeToDraw(By.xpath("//android.widget.Image"),0.45, 0.55, 0.15, 0.15);
        swipeToDraw(By.xpath("//android.widget.Image"),0.55, 0.65, 0.15, 0.45);
        swipeToDraw(By.xpath("//android.widget.Image"),0.65, 0.75, 0.45, 0.45);
        swipeToDraw(By.xpath("//android.widget.Image"),0.75, 0.75, 0.45, 0.55);
        swipeToDraw(By.xpath("//android.widget.Image"),0.75, 0.7, 0.55, 0.55);
        swipeToDraw(By.xpath("//android.widget.Image"),0.7, 0.8, 0.55, 0.85);
        swipeToDraw(By.xpath("//android.widget.Image"),0.8, 0.65, 0.85, 0.85);
        swipeToDraw(By.xpath("//android.widget.Image"),0.65, 0.55, 0.85, 0.55);

        swipeToDraw(By.xpath("//android.widget.Image"),0.475, 0.525, 0.45, 0.45);
        swipeToDraw(By.xpath("//android.widget.Image"),0.475, 0.5, 0.45, 0.35);
        swipeToDraw(By.xpath("//android.widget.Image"),0.5, 0.525, 0.35, 0.45);
    }
}
