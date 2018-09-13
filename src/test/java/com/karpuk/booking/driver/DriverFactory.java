package com.karpuk.booking.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

import java.util.concurrent.TimeUnit;

public class DriverFactory {

    private static final String WEBDRIVER_CHROME_DRIVER = "webdriver.chrome.driver";
    private static final String WEBDRIVER_FIREFOX_DRIVER = "webdriver.gecko.driver";
    private static final String WEBDRIVER_IE_DRIVER = "webdriver.ie.driver";
    private static final String WEBDRIVER_OPERA_DRIVER = "webdriver.opera.driver";

    private static WebDriver driver;

    public enum BrowserType {
        FIREFOX,
        CHROME,
        IE,
        OPERA,
    }

    private DriverFactory() {
    }

    public static WebDriver getInstance(BrowserType type) {
        if (driver == null) {
            switch (type) {
                case CHROME:
                    checkDriverProperty(WEBDRIVER_CHROME_DRIVER);
                    driver = new ChromeDriver();
                    break;
                case FIREFOX:
                    checkDriverProperty(WEBDRIVER_FIREFOX_DRIVER);
                    driver = new FirefoxDriver();
                    break;
                case IE:
                    checkDriverProperty(WEBDRIVER_IE_DRIVER);
                    driver = new InternetExplorerDriver();
                    break;
                case OPERA:
                    checkDriverProperty(WEBDRIVER_OPERA_DRIVER);
                    driver = new OperaDriver();
                    break;
            }
            driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
    }

    private static void checkDriverProperty(String webDriverName) {
        if (System.getProperty(webDriverName) == null) {
            System.setProperty(webDriverName, System.getenv(webDriverName));
        }
    }

}
