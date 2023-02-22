package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUi;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUiFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;


public class MyListTests extends CoreTestCase {
    @Test
    public void testSaveFirstArticleToMyList() {
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
        articlePageObject.closeArticle();
        if(Platform.getInstance().isAndroid()) {
            searchPageObject.clickCancelButton();
            this.clickBack();
            this.clickBack();
            navigationUi.clickSavedButton();
            myListsPageObject.openFolderByName("Saved");
        } else {
            searchPageObject.clickCancelButtonIos();
            navigationUi.clickSavedButton();
        }

        myListsPageObject.swipeArticleTitleToDelete(articleTitle);
    }

    @Test
    public void testSaveTwoArticles() {
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
        articlePageObject.closeArticle();
        searchPageObject.clickByArticleWithSubstring("Island in Indonesia");
        articlePageObject.waitForTitleElement();
        String articleTitle2 = articlePageObject.getArticleTitle();
        System.out.println(articleTitle2);
        articlePageObject.addArticleToDefaultList();
        articlePageObject.closeArticle();
        if(Platform.getInstance().isAndroid()) {
            this.clickBack();
            this.clickBack();
            navigationUi.clickSavedButton();
            myListsPageObject.openFolderByName("Saved");
        } else {
            searchPageObject.clickCancelButtonIos();
            navigationUi.clickSavedButton();
        }
        myListsPageObject.swipeArticleTitleToDelete(articleTitle1);
        searchPageObject.clickByArticleWithSubstring("Island in Indonesia");
        articlePageObject.waitForTitleElement();
        String articleTitle2AfterSaved = articlePageObject.getArticleTitle();
        assertEquals("Ожидаемый и фактический заголовок 2-ой статьи не совпадают", articleTitle2, articleTitle2AfterSaved);
    }
}
