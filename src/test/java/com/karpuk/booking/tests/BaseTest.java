package com.karpuk.booking.tests;

import com.karpuk.booking.steps.CommonSteps;
import org.apache.log4j.Logger;
import org.testng.annotations.*;

public abstract class BaseTest {

    protected CommonSteps steps;

    protected final Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    @BeforeClass
    public void setUpTest() {
        steps = new CommonSteps();
        steps.initDriver();
        logger.info("Driver initialized");
    }

    @AfterClass
    public void closeDriver() {
        steps.closeDriver();
        logger.info("Driver closed");
    }

}
