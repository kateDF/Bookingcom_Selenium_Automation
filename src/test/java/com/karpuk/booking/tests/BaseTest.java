package com.karpuk.booking.tests;

import com.karpuk.booking.steps.CommonSteps;
import org.testng.annotations.*;

public abstract class BaseTest {

    protected CommonSteps steps;

    @BeforeClass
    public void setUpTest() {
        steps = new CommonSteps();
        steps.initDriver();
    }

    @AfterClass
    public void closeDriver() {
        steps.closeDriver();
    }

}
