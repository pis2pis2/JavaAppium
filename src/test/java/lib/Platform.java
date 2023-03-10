package lib;

import io.appium.java_client.AppiumDriver;
import lib.ui.MainPageObject;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;

public class Platform {
    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";
    private static final String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";
    private static final String PLATFORM_MOBILE_WEB = "mobile_web";

    private static Platform instance;
    private Platform() {}

    public static Platform getInstance() {
        if (instance == null) {
            instance = new Platform();
        }
        return instance;
    }

    public RemoteWebDriver getDriver() throws Exception {
        URL URL = new URL(APPIUM_URL);
        if (this.isAndroid()) {
            return new AppiumDriver(URL, getAndroidDesiredCapabilities());
        } else if (this.isIOS()) {
            return new AppiumDriver(URL, getIOSDesiredCapabilities());
        }else if (this.isMW()) {
            return new ChromeDriver(this.getMWChromeOptions());
        } else {
            throw new Exception("Cannot detect type of Driver. Platform value: " + this.getPlatformVar());
        }
    }

    public boolean isAndroid(){
        return isPlatform(PLATFORM_ANDROID);
    }

    public boolean isIOS(){
        return isPlatform(PLATFORM_IOS);
    }
    public boolean isMW(){return isPlatform(PLATFORM_MOBILE_WEB);}


    private DesiredCapabilities getAndroidDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", "org.wikipedia.main.MainActivity");
        capabilities.setCapability("noReset", true);
        capabilities.setCapability("app", "C:\\Users\\Pavel_Stepanov\\Desktop\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");
        capabilities.setCapability("orientation", "PORTRAIT");
        return capabilities;
    }

    private DesiredCapabilities getIOSDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone SE");
        capabilities.setCapability("platformVersion", "11.3");
        capabilities.setCapability("app", "/Users/Pavel_Stepanov/Desktop/JavaAppiumAutomation/apks/Wikipedia.app");
        capabilities.setCapability("orientation", "PORTRAIT");
        return capabilities;
    }
    private ChromeOptions getMWChromeOptions(){
        HashMap<String, Object> deviceMetrics = new HashMap<String, Object>();
        deviceMetrics.put("width", 360);
        deviceMetrics.put("height", 640);
        deviceMetrics.put("pixelRatio", 3.0);

        HashMap<String, Object> mobileEmulations = new HashMap<String, Object>();
        mobileEmulations.put("deviceMetrics", deviceMetrics);
        mobileEmulations.put("userAgent", "Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("window-size=340,640");

        return chromeOptions;
    }

    private boolean isPlatform(String myPlatform){
        String platform = this.getPlatformVar();
        return myPlatform.equals(platform);
    }

    public String getPlatformVar(){
        return System.getenv("PLATFORM");
    }
}
