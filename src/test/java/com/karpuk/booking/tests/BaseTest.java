package com.karpuk.booking.tests;

import com.karpuk.booking.steps.CommonSteps;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public abstract class BaseTest {

    protected CommonSteps steps;

    @BeforeSuite
    public void setUpTest() {
        steps = new CommonSteps();
        steps.initDriver();
    }

    @AfterSuite
    public void closeDriver() {
        steps.closeDriver();
    }

}
