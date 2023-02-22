package lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class SearchPageObject extends MainPageObject{

    protected static String
            searchInitElement,
            searchInput,
            searchCancelButton,
            searchResultBySubstringTpl,
            searchCancelButtonIos,
            searchResultByTitleAndDescriptionWithSubstringsTpl;

    public SearchPageObject(AppiumDriver driver){
        super(driver);
    }
    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring){
        return searchResultBySubstringTpl.replace("{SUBSTRING}", substring);
    }

    private static String getResultSearchElementByTitleAndDescription(String title, String description){
        return searchResultByTitleAndDescriptionWithSubstringsTpl.replace("{TITLE}", title).replace("{DESCRIPTION}", description);
    }
    /* TEMPLATES METHODS */
    public void initSearchInput(){
        this.waitForElementPresent(searchInitElement, "Cannot find searchInitElement, before clicking", 5);
        this.waitForElementAndClick(searchInitElement, "Cannot find searchInitElement", 5);
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(searchCancelButton, "Cannot find searchCancelButton", 5);
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(searchCancelButton, "SearchCancelButton is present", 5);
    }

    public void clickCancelButton() {
        waitForElementAndClick(searchCancelButton, "Cannot click searchCancelButton", 5);
    }

    public void clickCancelButtonIos(){
        waitForElementAndClick(searchCancelButtonIos, "Cannot click searchCancelButton", 5);
    }

    public void typeSearchLine(String searchLine){
        this.waitForElementAndSendCase(searchInput, searchLine, "Cannot find searchLineElement", 5);
    }

    public void waitForSearchResultToAppear(String substring){
        String searchResultXpath = getResultSearchElement(substring);
        this.waitForElementPresent(searchResultXpath, "Cannot find searchResult with substring " + substring);
    }

    public void waitForSearchResultToDisappear(String substring){
        String searchResultXpath = getResultSearchElement(substring);
        this.waitForElementNotPresent(searchResultXpath, "SearchResult with substring " + substring + " is present", 15);
    }

    public void clickByArticleWithSubstring(String substring){
        String searchResultXpath = getResultSearchElement(substring);
        this.waitForElementAndClick(searchResultXpath, "Cannot find and click searchResult with substring " + substring, 10);
    }

    public void waitForElementByTitleAndDescription(String title, String description){
        String searchResultXpath = getResultSearchElementByTitleAndDescription(title, description);
        this.waitForElementPresent(searchResultXpath, "Cannot find searchResult with title " + title + " and description " + description);
    }

}
