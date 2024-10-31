package StepDef;

import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.DriverManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import utils.ScreenshotUtil;

import static StepDef.checkoutStepDef.appiumProcess;

public class Hooks {
    AndroidDriver driver;
    private static Scenario currentScenario;

    @Before
    public void setUp(Scenario scenario) {
        currentScenario = scenario; // Simpan Scenario di variabel statis
    }

    public static Scenario getCurrentScenario() {
        return currentScenario; // Mengambil Scenario dari variabel statis
    }


    @After
    public void takeScreenshotFinal(Scenario scenario) {
        try {
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
//                scenario.attach(resizedScreenshot, "image/png", "Hooks");
                if (scenario.isFailed()) {
                    scenario.attach(resizedScreenshot, "image/png", "Failed Screenshot");
                    System.out.println("Screenshot taken for failed scenario: " + scenario.getName());
                } else {
                    // Opsional: ambil screenshot jika skenario berhasil
                    scenario.attach(resizedScreenshot, "image/png", "Passed Screenshot");
                    System.out.println("Screenshot taken for passed scenario: " + scenario.getName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After(order = 1)
    public void tearDown() throws Exception {
        AndroidDriver driver = DriverManager.getDriver();
        if (driver != null) {
            driver.quit();
            DriverManager.quitDriver(); // Ensure to clear the driver instance.
            stopAppium();
        }
    }

    // Fungsi untuk menghentikan server Appium
    public void stopAppium() throws AWTException, InterruptedException {
        if (appiumProcess != null) {
            appiumProcess.destroy(); // Menghentikan proses Appium
            System.out.println("Appium server stopped.");
        } else {
            System.out.println("No Appium process is running.");
        }
        closeCommandPrompt();
//        Thread.sleep(2000);
    }

    // Fungsi untuk menutup Command Prompt
    public void closeCommandPrompt() throws AWTException {
        try {
            Runtime.getRuntime().exec("taskkill /IM cmd.exe /F"); // Menutup semua jendela Command Prompt
            System.out.println("Command Prompt closed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendCtrlC() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_C);
        robot.keyRelease(KeyEvent.VK_C);
        robot.keyRelease(KeyEvent.VK_CONTROL);
    }
}
