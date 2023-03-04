package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUi extends MainPageObject{
    protected static String
    savedButton,
    openNavigation;

    public NavigationUi(RemoteWebDriver driver) {
        super(driver);
    }


    public void clickSavedButton(){
        this.waitForElementAndClick(savedButton, "Cannot find saved button", 5);
    }

    public void openNavigation() {
        if (Platform.getInstance().isMW()) {
            this.waitForElementAndClick(openNavigation, "Cannot find and click open navugation button", 5);
        } else {
            System.out.println("Method openNavigation() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public void clickToWatchList(){
        if (Platform.getInstance().isMW()){
            this.tryClickElementWithFewAttempts(savedButton,"Cannot find navigation button to watchlist", 5);
        }
    }
}
