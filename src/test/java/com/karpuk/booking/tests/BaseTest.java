package com.karpuk.booking.tests;

import com.karpuk.booking.driver.ChromeWebDriver;
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
        WebDriver chromeDriver = ChromeWebDriver.getInstance();
        initSteps(chromeDriver);

        logger.info("Driver initialized");
    }

    @AfterClass
    public void closeDriver() {
        ChromeWebDriver.closeDriver();
        logger.info("Driver closed");
    }

    private void initSteps(WebDriver driver) {
        mainPageSteps = new MainPageSteps(driver);
        searchPageSteps = new SearchPageSteps(driver);
        apartmentPageSteps = new ApartmentDetailsPageSteps(driver);
        securePageSteps = new SecurePageSteps(driver);
    }

}
