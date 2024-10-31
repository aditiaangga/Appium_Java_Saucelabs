package utils;

import StepDef.Hooks;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class ScreenshotUtil {
    public AndroidDriver driver;

    public ScreenshotUtil(AndroidDriver driver) {
        this.driver = driver;
    }

    public void takeScreenshotWithResizedHeight(String name) {
        try {
            Scenario scenario = Hooks.getCurrentScenario(); // Ambil Scenario dari Hooks
            AndroidDriver driver = DriverManager.getDriver();
            if (driver != null) {
                File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

                // Baca screenshot sebagai BufferedImage
                BufferedImage originalImage = ImageIO.read(screenshotFile);

                // Ambil ukuran asli gambar
                int originalWidth = originalImage.getWidth();
                int originalHeight = originalImage.getHeight();

                // Hitung lebar baru berdasarkan rasio aspek
                int newHeight = 700;
                int newWidth = (int) ((double) originalWidth / originalHeight * newHeight);

                // Buat image dengan ukuran yang diinginkan
                BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, originalImage.getType());
                Graphics2D g2d = resizedImage.createGraphics();
                g2d.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
                g2d.dispose();

                // Konversi resized image ke byte array
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(resizedImage, "png", baos);
                byte[] resizedScreenshot = baos.toByteArray();

                // Lampirkan screenshot yang sudah diubah ukurannya ke skenario
                scenario.attach(resizedScreenshot, "image/png", name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
