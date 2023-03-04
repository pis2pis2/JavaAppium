package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;

public class GetStartedTest extends CoreTestCase {

    public void testPassThroughWelcome(){
        if(Platform.getInstance().isAndroid() || Platform.getInstance().isMW()) {
            return;
        }
        WelcomePageObject welcomePageObject = new WelcomePageObject(driver);
        welcomePageObject.waitForLearnMoreLink();
        welcomePageObject.clickNextButton();
        welcomePageObject.waitForNewWayToExploreText();
        welcomePageObject.clickNextButton();
        welcomePageObject.waitForAddOrEditPreferredLangText();
        welcomePageObject.clickNextButton();
        welcomePageObject.waitForLearnMoreAboutDataCollectedText();
        welcomePageObject.clickGetStartedButton();
    }
}
