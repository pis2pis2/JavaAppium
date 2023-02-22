package lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class NavigationUi extends MainPageObject{
    public NavigationUi(AppiumDriver driver) {
        super(driver);
    }
    protected static String
    savedButton;

    public void clickSavedButton(){
        this.waitForElementAndClick(savedButton, "Cannot find saved button", 5);
    }
}
