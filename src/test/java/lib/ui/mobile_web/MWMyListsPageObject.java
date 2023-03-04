package lib.ui.mobile_web;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListsPageObject extends MyListsPageObject {
    static {
        nameFolderTpl = "xpath://*[@resource-id='org.wikipedia:id/item_title' and @text='{nameFolder}']";
        articleByTitleTpl = "xpath://h3[text()='{title}']";
        removeFromSavedButton = "xpath://h3[text()='{title}']//ancestor::ul[contains(@class, 'content-unstyled')]//a[contains(@href, 'action=unwatch')]";
        afterRemoveFromSavedButton = "xpath://h3[text()='{title}']//ancestor::ul[contains(@class, 'content-unstyled')]//a[contains(@href, 'action=watch')]";
    }

    public MWMyListsPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
