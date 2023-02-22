package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUi;

public class AndroidNavigationUi extends NavigationUi {
    static {
        savedButton = "xpath://android.widget.FrameLayout[@content-desc='Saved']/android.widget.FrameLayout/android.widget.ImageView";
    }

    public AndroidNavigationUi(AppiumDriver driver){
        super(driver);
    }
}
