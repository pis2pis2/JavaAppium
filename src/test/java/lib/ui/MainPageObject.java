package lib.ui;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import lib.Platform;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {
    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver){
        this.driver = driver;
    }

    public WebElement waitForElementPresent(String locator, String errorMessage, long timeoutInSeconds){
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public List<WebElement> waitForElementsPresent(String locator, String errorMessage, long timeoutInSeconds){
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    public WebElement waitForElementPresent(String locator, String errorMessage){
        return waitForElementPresent(locator, errorMessage, 5);
    }

    public WebElement waitForElementAndClick(String locator, String errorMessage, long timeoutInSeconds){
        WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendCase(String locator, String value, String errorMessage, long timeoutInSeconds){
        WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }


    public boolean waitForElementNotPresent(String locator, String errorMessage, long timeoutInSeconds){
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public WebElement waitForElementAndClear(String locator, String errorMessage, long timeoutInSeconds){
        WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSeconds);
        element.clear();
        return element;
    }

    public void assertElementHasText(String locator, String errorMessage, String expectedMessage){
        By by = this.getLocatorByString(locator);
        Assert.assertEquals(errorMessage, expectedMessage, driver.findElement(by).getText());

    }

    public void assertElementPresent(String locator, String errorMessage){
        By by = this.getLocatorByString(locator);
        Boolean element = driver.findElement(by).isDisplayed();
        Assert.assertTrue(errorMessage, element);
    }

    public void assertElementsContainsText(String locator, String errorMessage, String expectedMessage){
        int numberElementsOfSearchResult = waitForElementsPresent(locator, "Cannot get numbers of element search result", 5).size();
        for (int i = 0; i < numberElementsOfSearchResult; i++) {
            WebElement element = waitForElementsPresent(locator, "Cannot find results of search", 5).get(i);
            String actualMessage = element.getText();
            Assert.assertTrue(errorMessage, actualMessage.contains(expectedMessage));
            System.out.println(actualMessage);
        }
    }

    public void swipeUp(int timeOfSwipe){
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width/2;
        int start_y = (int) (size.height*0.8);
        int end_y = (int) (size.height*0.2);
        action
                .press(PointOption.point(x, start_y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeOfSwipe)))
                .moveTo(PointOption.point(x, end_y))
                .release()
                .perform();
    }

    public void swipeUpQuick(){
        swipeUp(200);
    }

    public void swipeUpToFindElement(String locator, String error_message, int max_swipes){
        By by = this.getLocatorByString(locator);
        int already_swiped = 0;
        while (driver.findElements(by).size()==0){
            if (already_swiped > max_swipes){
                waitForElementPresent(locator, "Cannot find element by swiping up.\n" + error_message, 0);
                return;
            }
            swipeUpQuick();
            ++already_swiped;
        }

    }

    public void clickBack(){
        driver.navigate().back();
    }

    public void swipeUpTillElementAppear(String locator, String errorMessage, int maxSwipes) {
        int alreadySwiped = 0;
        while (!this.isElementLocatedOnTheScreen(locator)){
            if(alreadySwiped > maxSwipes){
                Assert.assertTrue(errorMessage, this.isElementLocatedOnTheScreen(locator));
            }
            swipeUpQuick();
            ++alreadySwiped;
        }

    }

    public boolean isElementLocatedOnTheScreen(String locator){
        int elementLocationByY = this.waitForElementPresent(locator, "Cannot find element by locator", 1).getLocation().getY();
        int screenSizeByY = driver.manage().window().getSize().getHeight();
        return elementLocationByY < screenSizeByY;

    }

    public void swipeElementToLeft(String locator, String error_message){
        WebElement element = waitForElementPresent(locator, error_message, 10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;
        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(right_x, middle_y));
        action.waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)));
                if (Platform.getInstance().isAndroid()){
                   action.moveTo(PointOption.point(left_x, middle_y));
                } else {
                    int offsetX = (-1 * element.getSize().getWidth());
                    action.moveTo(PointOption.point(offsetX, 0));
                }

        action.release();
        action.perform();
    }

    public int getAmountOfElements(String locator){
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }

    public String waitForElementAndGetAttribute(String locator, String attribute, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresent(locator, error_message, 5);
        return element.getAttribute(attribute);
    }

    private By getLocatorByString(String locatorWithType){
        String [] explodedLocator = locatorWithType.split(Pattern.quote(":"), 2);
        String byType = explodedLocator[0];
        String locator = explodedLocator[1];

        if (byType.equals("xpath")){
            return By.xpath(locator);
        } else if (byType.equals("id")){
            return By.id(locator);
        } else {
            throw new IllegalArgumentException("Cannot get type of locator. Locator :" + locatorWithType);
        }

    }

    public void clickElementToTheRightUpperCorner(String locator, String errorMessage){
        WebElement element = this.waitForElementPresent(locator + "/..", errorMessage, 5);
        int rightX = element.getLocation().getX();
        int upperY = element.getLocation().getY();
        int lowerY = element.getLocation().getY() + element.getSize().getHeight();
        int middleY = (upperY + lowerY) / 2;
        int width = element.getSize().getWidth();
        int pointToClickX = (rightX + width) - 3;
        int pointToClickY = middleY;
        TouchAction action = new TouchAction<>(driver);
        action.tap(PointOption.point(pointToClickX, pointToClickY)).perform();
    }
}
