package lib.ui.mobile_web;

import lib.ui.NavigationUi;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUi extends NavigationUi {
    static {
        savedButton = "xpath://span[text()='Watchlist']";
        openNavigation = "css:label#mw-mf-main-menu-button";
    }

    public MWNavigationUi(RemoteWebDriver driver){
        super(driver);
    }
}
