package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

import static java.lang.Thread.sleep;

public class AuthorizationPageObject extends MainPageObject{
    private static final String
    LOGIN_BUTTON = "xpath://a[contains(@class, 'mw-ui-button') and text()='Log in']",
    LOGIN_INPUT = "css:input#wpName1",
    PASSWORD_INPUT = "css:input#wpPassword1",
    SUBMIT = "css:button#wpLoginAttempt",
    RETURN_TO_PREVIOUS_PAGE = "xpath://a[text()='Return to the previous page.']";

    public AuthorizationPageObject(RemoteWebDriver driver){
        super(driver);
    }

    public void clickAuthButton(){
        this.waitForElementPresent(LOGIN_BUTTON, "Cannot find auth button", 5);
        this.waitForElementAndClick(LOGIN_BUTTON, "Cannot find and click auth button", 5);
    }

    public void enterLoginDate(String login, String password){
        this.waitForElementAndSendCase(LOGIN_INPUT, login, "Cannot find and put login to login input", 5);
        this.waitForElementAndSendCase(PASSWORD_INPUT, password, "Cannot find and put password to password input", 5);
    }

    public void submitForm() throws InterruptedException {
        this.waitForElementAndClick(SUBMIT, "Cannot find and click submit auth button", 5);
    }

    public void returnToPreviousPage(){
        this.waitForElementAndClick(RETURN_TO_PREVIOUS_PAGE, "Cannot find and click RETURN_TO_PREVIOUS_PAGE link", 5);
    }
}
