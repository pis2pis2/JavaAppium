package lib.ui;

import io.appium.java_client.AppiumDriver;
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
    navigateUpButton,
    searchCloseButton,
    footerElement,
    optionsRemoveFromMyListButton;

    public WebElement waitForTitleElement(){
        return this.waitForElementPresent(title, "Cannot find title article on page", 15);
    }

    public String getArticleTitle(){
        if(Platform.getInstance().isAndroid()) {
            return this.waitForTitleElement().getText();
        }else if (Platform.getInstance().isIOS()) {
            return this.waitForTitleElement().getAttribute("name");
        } else {
            return this.waitForTitleElement().getText();
        }
    }

    public void swipeToFooter(){
        if(Platform.getInstance().isAndroid()){
            this.swipeUpToFindElement(footerElement, "Cannot find the end of article", 40);
        } else if (Platform.getInstance().isIOS()){
            swipeUpTillElementAppear(footerElement, "Cannot find the end of article", 40);
        } else {
            this.scrollWebPageTillElementNotVisible(footerElement, "Cannot find the end of article", 40);
        }

    }

    public void addArticleToDefaultList(){
        this.waitForElementAndClick(pageSave, "Cannot find saveButton", 5);
    }

    public void removedArticleFromSavedIfItAdded(){
        if(this.isElementPresent(optionsRemoveFromMyListButton)){
            this.waitForElementAndClick(optionsRemoveFromMyListButton, "Cannot click button to remove from saved", 1);
        }
        this.waitForElementPresent(optionsRemoveFromMyListButton, "Cannot find button to add an article to saved list after removing it from this list before");
    }

    public void closeArticle(){
        if(Platform.getInstance().isMW()){
            return;
        }
        this.waitForElementAndClick(navigateUpButton, "Cannot find navigate up button", 5);
    }

    public void assertArticleTitlePresent(){
        this.assertElementPresent(title, "Заголовок статьи отсутствует, хотя должен присутствовать");
    }


}
