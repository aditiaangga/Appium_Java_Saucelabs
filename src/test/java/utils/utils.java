package utils;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import io.cucumber.java.Scenario;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;

public class utils {
    public static AndroidDriver driver;
    public Scenario scenario;
    static ScreenshotUtil ss = new ScreenshotUtil(driver);

    public void findingElementAndClick(String accessibilityId, String name) throws InterruptedException {
        // Variabel untuk elemen yang akan dicari
        WebElement findElement = null;
        boolean find = false;
        ss.takeScreenshotWithResizedHeight(name);

        // Loop untuk klik tombol Next hingga elemen ditemukan atau batas trial tercapai
        int maxTrial = 10; // Maksimal trial swipeUp
        int trial = 0;

        while (trial < maxTrial && !find) {

            try {
                // Coba temukan elemen yang dicari
                findElement = driver.findElement(AppiumBy.accessibilityId(accessibilityId));
                find = true; // Jika elemen ditemukan, set find menjadi true
                ss.takeScreenshotWithResizedHeight(name);
            } catch (NoSuchElementException e) {
                // Jika elemen belum ditemukan, melakukan swipeUp
                swipeUp();
                // Tambahkan penundaan agar tampilan aplikasi sempat berganti sebelum pengecekan berikutnya
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));  // Tunggu 2 detik sebelum mencoba menemukan elemen lagi
            }
            trial++; // Tingkatkan hitungan trial
        }
        findElement.click();
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

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    public static void swipeUpALitte() {
        // Mendapatkan ukuran layar
        int height = driver.manage().window().getSize().getHeight();
        int width = driver.manage().window().getSize().getWidth();

        // Koordinat untuk swipe
        int startX = width / 2;
        int startY = (int) (height * 0.85); // Mulai dari 85% dari tinggi layar
        int endY = (int) (height * 0.70);   // Berhenti di 70% dari tinggi layar

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
                if(driver.findElement(locator).isDisplayed()){
                    ss.takeScreenshotWithResizedHeight("Find Product");
                    waitForElementVisibleWithRetry(locator, 5, 5);
//                    Thread.sleep(1000);
                }
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

    public static boolean waitForElementVisibleWithRetry(By locator, int timeoutOfSeconds, int retryAttempts) {
        int attempts = 0;
        while (attempts < retryAttempts) {
            try {
                System.out.println("Menunggu elemen terlihat, percobaan ke-" + (attempts + 1));
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutOfSeconds));
                wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                System.out.println("Elemen terlihat.");
                return true;
            } catch (Exception e) {
                System.out.println("Error terjadi saat menunggu elemen terlihat dalam percobaan ke-" + (attempts + 1) + ": " + e.getMessage());
            }
            attempts++;
        }
        System.out.println("Elemen tidak ditemukan setelah " + retryAttempts + " percobaan.");
        return false;
    }


}
