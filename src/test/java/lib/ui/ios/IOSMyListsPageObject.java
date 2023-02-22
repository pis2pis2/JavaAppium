package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class IOSMyListsPageObject  extends MyListsPageObject {
//    Исправить локаторы для ios
    static {
        nameFolderTpl = "xpath://*[@resource-id='org.wikipedia:id/item_title' and @text='{nameFolder}']";
        articleByTitleTpl = "xpath://*[@text = '{title}']";
    }

    public IOSMyListsPageObject(AppiumDriver driver){
        super(driver);
    }
}
