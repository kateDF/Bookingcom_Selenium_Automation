package com.karpuk.booking.tests;

import static com.karpuk.booking.driver.DriverFactory.BrowserType;

import com.karpuk.booking.driver.DriverFactory;
import com.karpuk.booking.steps.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public abstract class BaseTest {

    protected MainPageSteps mainPageSteps;
    protected SearchPageSteps searchPageSteps;
    protected ApartmentDetailsPageSteps apartmentPageSteps;
    protected SecurePageSteps securePageSteps;

    protected final Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    @BeforeClass
    public void setUpTest() {
        WebDriver chromeDriver = DriverFactory.getInstance(BrowserType.CHROME);
        initSteps(chromeDriver);
        logger.info("Driver initialized");
    }

    @AfterClass
    public void closeDriver() {
        DriverFactory.closeDriver();
        logger.info("Driver closed");
    }

    private void initSteps(WebDriver driver) {
        mainPageSteps = new MainPageSteps(driver);
        searchPageSteps = new SearchPageSteps(driver);
        apartmentPageSteps = new ApartmentDetailsPageSteps(driver);
        securePageSteps = new SecurePageSteps(driver);
    }

}
