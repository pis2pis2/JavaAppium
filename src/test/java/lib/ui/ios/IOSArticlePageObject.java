package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class IOSArticlePageObject extends ArticlePageObject {
    static {
//        Заменить локаторы для ios
        title = "id:pcs-edit-section-title-description";
        pageSave = "id:org.wikipedia:id/page_save";
        navigateUpButton = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
        searchCloseButton = "id:org.wikipedia:id/search_close_btn";
        footerElement = "xpath://*[@resource-id='pcs-footer-container-readmore-heading'][@text='READ MORE']";
    }

    public IOSArticlePageObject(AppiumDriver driver){
        super(driver);
    }
}
