package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidSearchPageObject extends SearchPageObject {

    static {
            searchInitElement = "xpath://*[contains(@text, 'Search Wikipedia')]";
            searchInput = "xpath://*[contains(@text, 'Search Wikipedia')]";
            searchCancelButton = "id:org.wikipedia:id/search_close_btn";
            searchResultBySubstringTpl = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_description' and @text = '{SUBSTRING}']";
            searchResultByTitleAndDescriptionWithSubstringsTpl = "xpath://*[(@resource-id='org.wikipedia:id/page_list_item_title'" +
                    " and @text = '{TITLE}')]/..//*[(@resource-id='org.wikipedia:id/page_list_item_description' and @text = '{DESCRIPTION}')]";
    }

    public AndroidSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }

}
