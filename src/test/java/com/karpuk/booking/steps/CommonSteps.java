package com.karpuk.booking.steps;

import com.karpuk.booking.driver.DriverSingleton;
import org.openqa.selenium.WebDriver;

public class CommonSteps {

    private WebDriver driver;

    public CommonSteps() {
    }

    public void initDriver() {
        driver = DriverSingleton.getDriver();
    }

    public void closeDriver() {
        DriverSingleton.closeDriver();
    }

}
