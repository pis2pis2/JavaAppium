package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidMyListsPageObject extends MyListsPageObject {
    static {
                nameFolderTpl = "xpath://*[@resource-id='org.wikipedia:id/item_title' and @text='{nameFolder}']";
                articleByTitleTpl = "xpath://*[@text = '{title}']";
    }

    public AndroidMyListsPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
