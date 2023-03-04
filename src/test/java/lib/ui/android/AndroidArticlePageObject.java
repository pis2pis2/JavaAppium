package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidArticlePageObject extends ArticlePageObject {
    static {
                title = "id:pcs-edit-section-title-description";
                pageSave = "id:org.wikipedia:id/page_save";
                navigateUpButton = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
                searchCloseButton = "id:org.wikipedia:id/search_close_btn";
                footerElement = "xpath://*[@resource-id='pcs-footer-container-readmore-heading'][@text='READ MORE']";
    }

    public AndroidArticlePageObject(RemoteWebDriver driver){
        super(driver);
    }
}
