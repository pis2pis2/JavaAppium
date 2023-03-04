package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUiFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

import static java.lang.Thread.sleep;
import static lib.ui.MyListsPageObject.getRemoveButtonByTitle;


public class MyListTests extends CoreTestCase {
    private static final String
    login = "Pistepanov",
    password = "wiki123456";


    @Test
    public void testSaveFirstArticleToMyList() throws InterruptedException {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
        NavigationUi navigationUi = NavigationUiFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        articlePageObject.waitForTitleElement();
        String articleTitle = articlePageObject.getArticleTitle();
        System.out.println(articleTitle);
        articlePageObject.addArticleToDefaultList();
        if(Platform.getInstance().isMW()){
            AuthorizationPageObject auth = new AuthorizationPageObject(driver);
            auth.clickAuthButton();
            auth.enterLoginDate(login, password);
            auth.submitForm();
            String url = driver.getCurrentUrl();
            String new_url = url.substring(0,11) + "m." + url.substring(11);
            driver.get(new_url);
            articlePageObject.waitForTitleElement();
            assertEquals("We are not on the same page after login", articleTitle, articlePageObject.getArticleTitle());
        }
        if(Platform.getInstance().isAndroid()) {
            articlePageObject.closeArticle();
            searchPageObject.clickCancelButton();
            this.clickBack();
            this.clickBack();
            navigationUi.clickSavedButton();
            myListsPageObject.openFolderByName("Saved");
        } else if(Platform.getInstance().isIOS()) {
            articlePageObject.closeArticle();
            searchPageObject.clickCancelButtonIos();
            navigationUi.clickSavedButton();
        } else {
            navigationUi.openNavigation();
            navigationUi.clickToWatchList();
        }
        myListsPageObject.swipeArticleTitleToDelete(articleTitle);
    }

    @Test
    public void testSaveTwoArticles() throws InterruptedException {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
        NavigationUi navigationUi = NavigationUiFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        articlePageObject.waitForTitleElement();
        String articleTitle1 = articlePageObject.getArticleTitle();
        System.out.println(articleTitle1);
        articlePageObject.addArticleToDefaultList();
        if(Platform.getInstance().isMW()){
            AuthorizationPageObject auth = new AuthorizationPageObject(driver);
            auth.clickAuthButton();
            auth.enterLoginDate(login, password);
            auth.submitForm();
            String url = driver.getCurrentUrl();
            String new_url = url.substring(0,11) + "m." + url.substring(11);
            driver.get(new_url);
            articlePageObject.waitForTitleElement();
            assertEquals("We are not on the same page after login", articleTitle1, articlePageObject.getArticleTitle());
        }
        if(Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()) {
            articlePageObject.closeArticle();
        }else{
            searchPageObject.initSearchInput();
            searchPageObject.typeSearchLine("Java");
        }
        searchPageObject.clickByArticleWithSubstring("Island in Indonesia");
        articlePageObject.waitForTitleElement();
        String articleTitle2 = articlePageObject.getArticleTitle();
        System.out.println(articleTitle2);
        if(Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToDefaultList();
            articlePageObject.closeArticle();
            this.clickBack();
            this.clickBack();
            navigationUi.clickSavedButton();
            myListsPageObject.openFolderByName("Saved");
        } else if (Platform.getInstance().isIOS()){
            articlePageObject.addArticleToDefaultList();
            articlePageObject.closeArticle();
            searchPageObject.clickCancelButtonIos();
            navigationUi.clickSavedButton();
        } else {
            articlePageObject.addArticleToDefaultListAfterLogin();
            navigationUi.openNavigation();
            navigationUi.clickToWatchList();
        }
        myListsPageObject.swipeArticleTitleToDelete(articleTitle1);
        if(Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()) {

            searchPageObject.clickByArticleWithSubstring("Island in Indonesia");
        } else {
            myListsPageObject.openArticleByName("Java");
        }
        articlePageObject.waitForTitleElement();
        String articleTitle2AfterSaved = articlePageObject.getArticleTitle();
        assertEquals("Ожидаемый и фактический заголовок 2-ой статьи не совпадают", articleTitle2, articleTitle2AfterSaved);
    }
}
