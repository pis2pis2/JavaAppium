package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsPageObject extends MainPageObject{
    public MyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
    private static String getFolderXpathByName(String nameFolder) {
        return nameFolderTpl.replace("{nameFolder}", nameFolder);
    }

    private static String getSavedArticleXpathByTitle(String title) {
        return articleByTitleTpl.replace("{title}", title);
    }

    public static String getRemoveButtonByTitle(String title) {
        return removeFromSavedButton.replace("{title}", title);
    }

    public static String getAfterRemoveButtonByTitle(String title) {
        return afterRemoveFromSavedButton.replace("{title}", title);
    }

    protected static String
            nameFolderTpl,
            articleByTitleTpl,
            removeFromSavedButton,
            afterRemoveFromSavedButton;

    public void openFolderByName(String nameFolder){
        this.waitForElementAndClick(getFolderXpathByName(nameFolder), "Cannot find folder by name " + nameFolder, 5);
    }

    public void openArticleByName(String articleTitle){
        String locator = getSavedArticleXpathByTitle(articleTitle);
        this.waitForElementAndClick(locator, "Cannot find open article by name " + articleTitle, 5);
    }

    public void swipeArticleTitleToDelete(String articleTitle){
        this.waitForArticleToAppearByTitle(articleTitle);
        if(Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()){
            this.swipeElementToLeft(getSavedArticleXpathByTitle(articleTitle), "Cannot find saved article by title " + articleTitle);
            if(Platform.getInstance().isIOS()) {
                this.clickElementToTheRightUpperCorner(getSavedArticleXpathByTitle(articleTitle), "Cannot find saved article");
            }
        } else {
                String removeLocator = getRemoveButtonByTitle(articleTitle);
                this.waitForElementAndClick(removeLocator, "Cannot click button to remove article from saved", 10);
                String afterRemoveLocator = getAfterRemoveButtonByTitle(articleTitle);
                this.waitForElementPresent(afterRemoveLocator, "Cannot find button after remove article from saved", 10);
                driver.navigate().refresh();
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
