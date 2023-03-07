package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;


abstract public class ArticlePageObject extends MainPageObject {

    public ArticlePageObject(RemoteWebDriver driver){
        super(driver);
    }

    protected static String
    title,
    pageSave,
    pageSaveLogined,
    navigateUpButton,
    searchCloseButton,
    footerElement,
    optionsRemoveFromMyListButton;


    @Step("Waiting for title of the article page")
    public WebElement waitForTitleElement(){
        return this.waitForElementPresent(title, "Cannot find title article on page", 15);
    }

    @Step("Get article title")
    public String getArticleTitle(){
        screenshot(this.takeScreenShot("article_title"));
        if(Platform.getInstance().isAndroid()) {
            return this.waitForTitleElement().getText();
        }else if (Platform.getInstance().isIOS()) {
            return this.waitForTitleElement().getAttribute("name");
        } else {
            return this.waitForTitleElement().getText();
        }
    }

    @Step("Swipe to footer")
    public void swipeToFooter(){
        if(Platform.getInstance().isAndroid()){
            this.swipeUpToFindElement(footerElement, "Cannot find the end of article", 40);
        } else if (Platform.getInstance().isIOS()){
            swipeUpTillElementAppear(footerElement, "Cannot find the end of article", 40);
        } else {
            this.scrollWebPageTillElementNotVisible(footerElement, "Cannot find the end of article", 40);
        }

    }

    @Step("Add article title to default list")
    public void addArticleToDefaultList(){
        this.waitForElementAndClick(pageSave, "Cannot find saveButton", 5);
    }

    @Step("Add article title to default list after login")
    public void addArticleToDefaultListAfterLogin(){
        try {
            this.waitForElementAndClick(pageSaveLogined, "Cannot find saveButton", 5);
        } catch (Exception exception) {
            System.out.println("Вы попытались добавить в избранное страницу, которая была добавлена ранее");
        }
    }

    public void removedArticleFromSavedIfItAdded(){
        if(this.isElementPresent(optionsRemoveFromMyListButton)){
            this.waitForElementAndClick(optionsRemoveFromMyListButton, "Cannot click button to remove from saved", 1);
        }
        this.waitForElementPresent(optionsRemoveFromMyListButton, "Cannot find button to add an article to saved list after removing it from this list before");
    }

    @Step("Close article")
    public void closeArticle(){
        if(Platform.getInstance().isMW()){
            return;
        }
        this.waitForElementAndClick(navigateUpButton, "Cannot find navigate up button", 5);
    }

    @Step("Check that article title is present")
    public void assertArticleTitlePresent(){
        this.assertElementPresent(title, "Заголовок статьи отсутствует, хотя должен присутствовать");
    }


}
