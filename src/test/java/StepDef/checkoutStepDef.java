package StepDef;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import pages.Cart;
import pages.Checkout;
import pages.Login;
import utils.DriverManager;
import utils.ScreenshotUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static org.openqa.selenium.By.xpath;

public class checkoutStepDef {
    AndroidDriver driver;
    static Process appiumProcess;


    @Given("User use Device {string} and Open the Apps Saucelabs")
    public void userUseDeviceAndOpenTheAppsSaucelabs(String device) throws InterruptedException, MalformedURLException {
        openCommandPromptWithAppium();

        UiAutomator2Options options = getDeviceOptions(device);
        System.out.println("Selected device options: " + options.toString());

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        DriverManager.setDriver(driver);
        Thread.sleep(2000);

        ScreenshotUtil ss = new ScreenshotUtil(driver);
        ss.takeScreenshotWithResizedHeight("Open Apps");
    }

    @And("User input username {string}, password {string} and Click Login")
    public void userInputUsernamePasswordAndClickLogin(String user, String pass) throws InterruptedException {
        Login login = new Login(driver);
        login.login(user, pass);
    }

    @When("User add cart product {string}")
    public void userAddCartProduct(String product) throws InterruptedException {
        Cart cart = new Cart(driver);
        cart.addToCart(product);
    }

    @And("User checkout the cart")
    public void userCheckoutTheCart() throws InterruptedException {
        Checkout co = new Checkout(driver);
        co.checkout();
    }

    @Then("Checkout Complete")
    public void checkoutComplete() throws InterruptedException {
        WebElement complete = driver.findElement(xpath("//android.widget.TextView[@text=\"CHECKOUT: COMPLETE!\"]"));
        complete.isDisplayed();
        System.out.println(complete.getText());
        driver.findElement(AppiumBy.accessibilityId("test-BACK HOME")).click();
        Thread.sleep(2000);
    }


    public static UiAutomator2Options emulator(){
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");
        options.setDeviceName("emulator-5554");
//        options.setApp(System.getProperty("user.dir")+"/src/test/resources/apps/AndroidSauceLabsMobile.apk");
        options.setAppPackage("com.swaglabsmobileapp");
        options.setAppActivity("com.swaglabsmobileapp.SplashActivity");
        options.setNewCommandTimeout(Duration.ofMillis(300));

        return options;
    }

    public static UiAutomator2Options oppoF9(){
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");
        options.setDeviceName("RSFUNNSGMBW4U8GE");
//        options.setApp(System.getProperty("user.dir")+"/src/test/resources/apps/AndroidSauceLabsMobile.apk");
        options.setAppPackage("com.swaglabsmobileapp");
        options.setAppActivity("com.swaglabsmobileapp.SplashActivity");
        options.setNewCommandTimeout(Duration.ofMillis(300));

        return options;
    }

    public static UiAutomator2Options getDeviceOptions(String deviceName) {
        switch (deviceName.toLowerCase()) {
            case "emulator":
                return emulator();  // Memanggil konfigurasi emulator
            case "oppof9":
                return oppoF9();    // Memanggil konfigurasi Oppo F9
            default:
                throw new IllegalArgumentException("Device name not recognized: " + deviceName);
        }
    }

    // Fungsi untuk membuka Command Prompt dan menjalankan perintah Appium
    public static void openCommandPromptWithAppium() throws InterruptedException {
        try {
            // Menggunakan Runtime untuk membuka Command Prompt dengan perintah Appium
            // Cek apakah cmd.exe sudah berjalan
            if (isCommandPromptRunning()) {
                // Jika cmd.exe sudah ada, jalankan appium --allow-cors di cmd yang sudah terbuka
                Runtime.getRuntime().exec("cmd.exe /c appium --allow-cors");
                System.out.println("Appium --allow-cors command executed in existing Command Prompt.");
            } else {
                // Jika belum ada cmd.exe, buka baru dan jalankan appium --allow-cors
                Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k appium --allow-cors");
                System.out.println("Command Prompt opened and Appium server started with --allow-cors.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread.sleep(5000);
    }

    private static boolean isCommandPromptRunning() {
        try {
            // Gunakan tasklist untuk memeriksa apakah cmd.exe sedang berjalan
            Process process = Runtime.getRuntime().exec("tasklist");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("cmd.exe")) {
                    return true; // Jika cmd.exe ditemukan, return true
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // Jika cmd.exe tidak ditemukan, return false
    }

}
