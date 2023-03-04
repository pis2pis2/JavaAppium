package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WelcomePageObject extends MainPageObject{

    private final static String
    stepLearnMoreLink = "id:Learn more about Wikipedia",
    stepNewWaysToExplore = "id:New ways to explore",
    stepAddOrEditPreferredLangLink = "id:Add or edit preferred languages",
    stepLearnMoreAboutDataCollectedLink = "id:Learn more about data collected",
    nextLink = "id:Next",
    getStartedButton = "id:Get started",
    skip = "id:Skip";

    public WelcomePageObject(RemoteWebDriver driver){
        super(driver);
    }

    public void waitForLearnMoreLink(){
        this.waitForElementPresent(stepLearnMoreLink, "Cannot find 'Learn more about Wikipedia' link", 10);
    }

    public void waitForNewWayToExploreText(){
        this.waitForElementPresent(stepNewWaysToExplore, "Cannot find 'New ways to explore' link", 10);
    }

    public void waitForAddOrEditPreferredLangText(){
        this.waitForElementPresent(stepAddOrEditPreferredLangLink, "Cannot find 'Add or edit preferred languages' link", 10);
    }

    public void waitForLearnMoreAboutDataCollectedText(){
        this.waitForElementPresent(stepLearnMoreAboutDataCollectedLink, "Cannot find 'Learn more about data collected' link", 10);
    }

    public void clickNextButton(){
        this.waitForElementAndClick(nextLink, "Cannot find and click 'Next' link", 10);
    }

    public void clickGetStartedButton(){
        this.waitForElementAndClick(getStartedButton, "Cannot find and click 'Get started' link", 10);
    }

    public void clickSkip(){
        this.waitForElementAndClick(skip, "Cannot find and click skip button", 5);
    }
}
