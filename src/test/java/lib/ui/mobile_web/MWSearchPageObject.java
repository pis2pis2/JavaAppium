package lib.ui.mobile_web;
import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {
    static {
        searchInitElement = "css:button#searchIcon";
        searchInput = "css:form>input[placeholder='Search Wikipedia']";
        searchCancelButton = "xpath://form[@class='search-box']//button[@type='button']";
        searchResultBySubstringTpl = "xpath://div[contains(@class, 'wikidata-description')][contains(text(), '{SUBSTRING}')]";
        searchResultByTitleAndDescriptionWithSubstringsTpl = "xpath://div[contains(@class, 'wikidata-description')][contains(text(), '{DESCRIPTION}')]//ancestor::li[@title = '{TITLE}']";
    }

    public MWSearchPageObject(RemoteWebDriver driver){
        super(driver);
    }
}

