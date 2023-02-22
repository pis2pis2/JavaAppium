package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import lib.Platform;


abstract public class ArticlePageObject extends MainPageObject {

    public ArticlePageObject(AppiumDriver driver){
        super(driver);
    }

    protected static String
    title,
    pageSave,
    navigateUpButton,
    searchCloseButton,
    footerElement;

    public WebElement waitForTitleElement(){
        return this.waitForElementPresent(title, "Cannot find title article on page", 15);
    }

    public String getArticleTitle(){
        if(Platform.getInstance().isAndroid()) {
            return this.waitForTitleElement().getText();
        }else {
            return this.waitForTitleElement().getAttribute("name");
        }
    }

    public void swipeToFooter(){
        if(Platform.getInstance().isAndroid()){
            this.swipeUpToFindElement(footerElement, "Cannot find the end of article", 40);
        } else {
            swipeUpTillElementAppear(footerElement, "Cannot find the end of article", 40);
        }

    }

    public void addArticleToDefaultList(){
        this.waitForElementAndClick(pageSave, "Cannot find saveButton", 5);
    }

    public void closeArticle(){
        this.waitForElementAndClick(navigateUpButton, "Cannot find navigate up button", 5);
    }

    public void assertArticleTitlePresent(){
        this.assertElementPresent(title, "Заголовок статьи отсутствует, хотя должен присутствовать");
    }


}
