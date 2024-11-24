package StepDef;

import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.Then;
import pages.Draw;
import utils.ScreenshotUtil;
import utils.DriverManager;


public class drawStepDef {

    @Then("User can Draw")
    public void userCanDraw() {
        AndroidDriver driver = DriverManager.getDriver();
        Draw draw = new Draw(driver);
        draw.draw();
        ScreenshotUtil ss = new ScreenshotUtil(driver);
        ss.takeScreenshotWithResizedHeight("Draw Result");
    }
}
