package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;

abstract public class MyListsPageObject extends MainPageObject{
    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }
    private static String getFolderXpathByName(String nameFolder) {
        return nameFolderTpl.replace("{nameFolder}", nameFolder);
    }

    private static String getSavedArticleXpathByTitle(String title) {
        return articleByTitleTpl.replace("{title}", title);
    }

    protected static String
            nameFolderTpl,
            articleByTitleTpl;

    public void openFolderByName(String nameFolder){
        this.waitForElementAndClick(getFolderXpathByName(nameFolder), "Cannot find folder by name " + nameFolder, 5);
    }

    public void swipeArticleTitleToDelete(String articleTitle){
        this.waitForArticleToAppearByTitle(articleTitle);
        this.swipeElementToLeft(getSavedArticleXpathByTitle(articleTitle), "Cannot find saved article by title " + articleTitle);
        if(Platform.getInstance().isIOS()){
            this.clickElementToTheRightUpperCorner(getSavedArticleXpathByTitle(articleTitle), "Cannot find saved article");
        }
        this.waitForArticleToDisappearByTitle(articleTitle);

    }

    public void waitForArticleToDisappearByTitle(String articleTitle){
        this.waitForElementNotPresent(getSavedArticleXpathByTitle(articleTitle), "Cannot delete saved article by title " + articleTitle, 10);
    }

    public void waitForArticleToAppearByTitle(String articleTitle){
        this.waitForElementPresent(getSavedArticleXpathByTitle(articleTitle), "Cannot find saved article by title " + articleTitle, 10);
    }
}
