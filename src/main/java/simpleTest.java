import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import java.net.URL;
import java.time.Duration;
import java.util.Arrays;


import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.By.xpath;


public class simpleTest {
    static AndroidDriver driver;
    static Process appiumProcess;

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

    @Test
    public void androidLaunchTest() throws InterruptedException, MalformedURLException {
        openCommandPromptWithAppium();

        //Pilih device yang diinginkan dengan cara uncomment
        UiAutomator2Options options = emulator();
//        UiAutomator2Options options = oppoF9();

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
//        new WebDriverWait(driver, Duration.ofSeconds(100)).until(e->e.findElement(By.xpath("//android.widget.EditText[@content-desc=\"test-Username\"]"))).sendKeys("aaaa");

        //login
        login("standard_user", "secret_sauce");

        //add to cart
        addToCart("Sauce Labs Fleece Jacket");
        addToCart("Sauce Labs Onesie");
        addToCart("Sauce Labs Backpack");
        addToCart("Sauce Labs Bolt T-Shirt");
        addToCart("Sauce Labs Bike Light");
        addToCart("Test.allTheThings() T-Shirt (Red)");

        //checkout
        checkout();

        driver.quit();
        stopAppium();
    }

    // Fungsi untuk membuka Command Prompt dan menjalankan perintah Appium
    public static void openCommandPromptWithAppium() throws InterruptedException {
        try {
            // Menggunakan Runtime untuk membuka Command Prompt dengan perintah Appium
            Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k appium --allow-cors");
            System.out.println("Command Prompt opened and Appium server started with --allow-cors.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread.sleep(5000);
    }

    // Fungsi untuk menghentikan server Appium
    public void stopAppium() {
        if (appiumProcess != null) {
            appiumProcess.destroy(); // Menghentikan proses Appium
            System.out.println("Appium server stopped.");
        } else {
            System.out.println("No Appium process is running.");
        }
        closeCommandPrompt();
    }

    // Fungsi untuk menutup Command Prompt
    public void closeCommandPrompt() {
        try {
            Runtime.getRuntime().exec("taskkill /IM cmd.exe /F"); // Menutup semua jendela Command Prompt
            System.out.println("Command Prompt closed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void swipeUp() {
        // Mendapatkan ukuran layar
        int height = driver.manage().window().getSize().getHeight();
        int width = driver.manage().window().getSize().getWidth();

        // Koordinat untuk swipe
        int startX = width / 2;
        int startY = (int) (height * 0.85); // Mulai dari 85% dari tinggi layar
        int endY = (int) (height * 0.25);   // Berhenti di 25% dari tinggi layar

        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        var start = new Point(startX, startY);
        var end = new Point(startX, endY);
        var swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), start.getX(), start.getY()));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(500),
                PointerInput.Origin.viewport(), end.getX(), end.getY()));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(swipe));
    }

    public static void swipeDown() {
        // Mendapatkan ukuran layar
        int height = driver.manage().window().getSize().getHeight();
        int width = driver.manage().window().getSize().getWidth();

        // Koordinat untuk swipe
        int startX = width / 2;
        int startY = (int) (height * 0.25);  // Mulai dari 25% dari tinggi layar
        int endY = (int) (height * 0.85);    // Berhenti di 85% dari tinggi layar

        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        var start = new Point(startX, startY);
        var end = new Point(startX, endY);
        var swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), start.getX(), start.getY()));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(500),
                PointerInput.Origin.viewport(), end.getX(), end.getY()));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(swipe));
    }

    public void login(String username, String password) throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(AppiumBy.accessibilityId("test-Username")).sendKeys(username);
        driver.findElement(AppiumBy.accessibilityId("test-Password")).sendKeys(password);
        driver.findElement(AppiumBy.accessibilityId("test-LOGIN")).click();
    }

    public void addToCart(String Product) throws InterruptedException {
        Thread.sleep(2000);

        findElementWithSwipe(AppiumBy.androidUIAutomator("new UiSelector().text(\"" + Product + "\")"));

//        waitToPresence(driver, (AppiumBy) AppiumBy.androidUIAutomator("new UiSelector().text(\"" + Product + "\")"));
        Thread.sleep(2000);
        swipeUp();
        driver.findElement(AppiumBy.accessibilityId("test-ADD TO CART")).click();
        driver.findElement(AppiumBy.accessibilityId("test-BACK TO PRODUCTS")).click();
    }

    public void checkout() throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.widget.ImageView")).click();
        Thread.sleep(3000);

        findingElementAndClick("test-CHECKOUT");
//        swipeUp();
//        driver.findElement(AppiumBy.accessibilityId("test-CHECKOUT")).click();
        Thread.sleep(2000);
        driver.findElement(AppiumBy.accessibilityId("test-First Name")).sendKeys("Aditia");
        driver.findElement(AppiumBy.accessibilityId("test-Last Name")).sendKeys("Perdana");
        driver.findElement(AppiumBy.accessibilityId("test-Zip/Postal Code")).sendKeys("12130");
        Thread.sleep(2000);
        driver.findElement(AppiumBy.accessibilityId("test-CONTINUE")).click();
        Thread.sleep(2000);

        findingElementAndClick("test-FINISH");
//        swipeUp();
//        Thread.sleep(5000);
//        driver.findElement(AppiumBy.accessibilityId("test-FINISH")).click();
        Thread.sleep(7000);
        WebElement complete = driver.findElement(xpath("//android.widget.TextView[@text=\"CHECKOUT: COMPLETE!\"]"));
        complete.isDisplayed();
        System.out.println(complete.getText());
        driver.findElement(AppiumBy.accessibilityId("test-BACK HOME")).click();
        Thread.sleep(2000);
    }

    public void findingElementAndClick(String accessibilityId) throws InterruptedException {
        // Variabel untuk elemen yang akan dicari
        WebElement findElement = null;
        boolean find = false;

        // Loop untuk klik tombol Next hingga elemen ditemukan atau batas trial tercapai
        int maxTrial = 10; // Maksimal trial swipeUp
        int trial = 0;
        while (trial < maxTrial && !find) {
            try {
                // Coba temukan elemen yang dicari
                findElement = driver.findElement(AppiumBy.accessibilityId(accessibilityId));
                find = true; // Jika elemen ditemukan, set find menjadi true
            } catch (NoSuchElementException e) {
                // Jika elemen belum ditemukan, melakukan swipeUp
                swipeUp();
                // Tambahkan penundaan agar tampilan aplikasi sempat berganti sebelum pengecekan berikutnya
                Thread.sleep(2000);  // Tunggu 2 detik sebelum mencoba menemukan elemen lagi

            }
            trial++; // Tingkatkan hitungan trial
        }
        findElement.click();
    }

    public static WebElement waitToVisible(AndroidDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        return (WebElement) wait.until(ExpectedConditions.visibilityOf(element));

    }

    public static WebElement waitToClickable(AndroidDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        return (WebElement) wait.until(ExpectedConditions.elementToBeClickable(element));

    }

    public static WebElement waitToPresence(AndroidDriver driver, AppiumBy element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        return (WebElement) wait.until(ExpectedConditions.presenceOfElementLocated(element));

    }

    // Fungsi untuk mengecek apakah layar di bagian paling atas (ini asumsi saja, bisa disesuaikan)
    public static boolean isAtTop() {
        // Contoh logika: Jika kita swipe down dari atas layar dan tidak ada pergerakan,
        // maka asumsikan layar sudah di atas.
        try {
            swipeDown();
            Thread.sleep(1000);
            // Coba swipe down dan lihat apakah ada perubahan (asumsi ini sudah di atas jika tidak ada perubahan)
            return false; // Jika berhasil swipe down, berarti tidak di atas.
        } catch (Exception e) {
            return true; // Jika ada error, berarti layar sudah di paling atas.
        }
    }

    // Fungsi untuk mengecek apakah layar di bagian paling bawah
    public static boolean isAtBottom() {
        // Coba swipe up dan lihat apakah ada perubahan (asumsi ini sudah di bawah jika tidak ada perubahan)
        try {
            swipeUp();
            Thread.sleep(1000);
            return false; // Jika berhasil swipe up, berarti tidak di bawah.
        } catch (Exception e) {
            return true; // Jika ada error, berarti layar sudah di paling bawah.
        }
    }

    // Fungsi untuk mencari elemen dengan swipe up dan swipe down berdasarkan posisi layar
    public static void findElementWithSwipe(By locator) throws InterruptedException {
        int swipeUpTrials = 3;    // Jumlah swipe up maksimal
        int swipeDownTrials = 3;  // Jumlah swipe down maksimal
        boolean elementFound = false;
        int totalTrials = 0;      // Jumlah total percobaan swipe up dan down

        // Tentukan apakah layar berada di atas atau di bawah
        boolean startAtTop = isAtTop();     // Jika di atas, prioritaskan swipe up
        boolean startAtBottom = isAtBottom(); // Jika di bawah, prioritaskan swipe down

        // Loop pencarian elemen
        while (!elementFound && totalTrials < (swipeUpTrials + swipeDownTrials)) {
            try {
                // Coba temukan elemen
                driver.findElement(locator).click();
                elementFound = true; // Elemen ditemukan, keluar dari loop
            } catch (NoSuchElementException e) {
                if (startAtTop && totalTrials < swipeUpTrials) {
                    // Jika layar ada di atas, swipe up lebih dulu
                    swipeUp();
                } else if (startAtBottom && totalTrials < swipeDownTrials) {
                    // Jika layar ada di bawah, swipe down lebih dulu
                    swipeDown();
                } else if (totalTrials < swipeUpTrials) {
                    // Jika tidak tahu posisinya, lakukan swipe up dulu
                    swipeUp();
                } else {
                    // Jika tidak tahu posisinya, lakukan swipe down setelah swipe up selesai
                    swipeDown();
                }
                // Beri waktu tunggu sebelum mencoba lagi
                Thread.sleep(2000);
            }
            totalTrials++;  // Hitung jumlah percobaan swipe
        }

        // Jika elemen tidak ditemukan setelah percobaan maksimal
        if (!elementFound) {
            System.out.println("Elemen tidak ditemukan setelah 6 kali swipe (3 up dan 3 down).");
        }
    }
}
