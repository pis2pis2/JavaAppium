package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUi;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSNavigationUi extends NavigationUi {
//    Изменить локатор для ios
    static {
        savedButton = "xpath://android.widget.FrameLayout[@content-desc='Saved']/android.widget.FrameLayout/android.widget.ImageView";
    }

    public IOSNavigationUi(RemoteWebDriver driver){
        super(driver);
    }
}
