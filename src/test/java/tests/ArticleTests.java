package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;


@Epic("Tests for articles")
public class ArticleTests extends CoreTestCase {
    @Test
    @DisplayName("Compare article title with expected one")
    @Description("We open article and check the title of article")
    @Step("Starting test 'testCompareArticleTitle'")
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @Severity(value = SeverityLevel.BLOCKER)
    public void testCompareArticleTitle() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        articlePageObject.waitForTitleElement();
        String articleHeader = articlePageObject.getArticleTitle();
//        articlePageObject.takeScreenShot("article_page");
        Assert.assertEquals("Ожидаемое название статьи не соответствует актуальному",
                "Java (programming language)", articleHeader);
    }

    @Test
    @Description("We open article and swipe it to the footer")
    @DisplayName("Swipe article to the footer")
    @Step("Starting test 'testSwipeArticleTitle'")
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @Severity(value = SeverityLevel.CRITICAL)
    public void testSwipeArticleTitle() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.clickByArticleWithSubstring("Automation for Apps");
        articlePageObject.waitForTitleElement();
        articlePageObject.swipeToFooter();
    }

    @Test
    @DisplayName("Check article title")
    @Description("We open article and check that article title is present")
    @Step("Starting test 'testAssertTitle'")
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @Severity(value = SeverityLevel.MINOR)
    public void testAssertTitle() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        articlePageObject.assertArticleTitlePresent();
    }
}
