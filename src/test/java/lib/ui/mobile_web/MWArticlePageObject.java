package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {
    static {
        title = "css:span.mw-page-title-main";
        pageSave = "css:a#ca-watch";
        pageSaveLogined = "xpath://a[@id='ca-watch'][contains(@href, 'action=watch')]";
        footerElement = "css:footer.mw-footer.minerva-footer";
        optionsRemoveFromMyListButton = "xpath://a[@id='ca-watch'][contains(@href, 'action=unwatch')]";
    }

    public MWArticlePageObject(RemoteWebDriver driver){
        super(driver);
    }

}
