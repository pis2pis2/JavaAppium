package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class SearchPageObject extends MainPageObject{

    protected static String
            searchInitElement,
            searchInput,
            searchCancelButton,
            searchResultBySubstringTpl,
            searchCancelButtonIos,
            searchResultByTitleAndDescriptionWithSubstringsTpl;

    public SearchPageObject(RemoteWebDriver driver){
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

    @Step("Initialazing search field")
    public void initSearchInput(){
        this.waitForElementPresent(searchInitElement, "Cannot find searchInitElement, before clicking", 5);
        this.waitForElementAndClick(searchInitElement, "Cannot find searchInitElement", 5);
    }

    @Step("Waiting for button to cancel search result")
    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(searchCancelButton, "Cannot find searchCancelButton", 5);
    }

    @Step("Waiting for search cancel button to disappeared")
    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(searchCancelButton, "SearchCancelButton is present", 5);
    }

    @Step("Clicking button to cancel search result")
    public void clickCancelButton() {
        waitForElementAndClick(searchCancelButton, "Cannot click searchCancelButton", 5);
    }

    @Step("Clicking button to cancel search result (IOS)")
    public void clickCancelButtonIos(){
        waitForElementAndClick(searchCancelButtonIos, "Cannot click searchCancelButton", 5);
    }

    @Step("Typing '{searchLine}' to the search line")
    public void typeSearchLine(String searchLine){
        this.waitForElementAndSendCase(searchInput, searchLine, "Cannot find searchLineElement", 5);
    }

    @Step("Waiting for search result")
    public void waitForSearchResultToAppear(String substring){
        String searchResultXpath = getResultSearchElement(substring);
        this.waitForElementPresent(searchResultXpath, "Cannot find searchResult with substring " + substring);
    }

    @Step("Waiting for search result to disappear")
    public void waitForSearchResultToDisappear(String substring){
        String searchResultXpath = getResultSearchElement(substring);
        this.waitForElementNotPresent(searchResultXpath, "SearchResult with substring " + substring + " is present", 15);
    }

    @Step("Click by article with substring")
    public void clickByArticleWithSubstring(String substring){
        String searchResultXpath = getResultSearchElement(substring);
        this.waitForElementAndClick(searchResultXpath, "Cannot find and click searchResult with substring " + substring, 10);
    }

    @Step("Wait for element by title and description")
    public void waitForElementByTitleAndDescription(String title, String description){
        String searchResultXpath = getResultSearchElementByTitleAndDescription(title, description);
        this.waitForElementPresent(searchResultXpath, "Cannot find searchResult with title " + title + " and description " + description);
    }

}
