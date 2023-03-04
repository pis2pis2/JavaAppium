package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSMyListsPageObject  extends MyListsPageObject {
//    Исправить локаторы для ios
    static {
        nameFolderTpl = "xpath://*[@resource-id='org.wikipedia:id/item_title' and @text='{nameFolder}']";
        articleByTitleTpl = "xpath://*[@text = '{title}']";
    }

    public IOSMyListsPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
