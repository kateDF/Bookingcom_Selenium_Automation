package com.karpuk.booking.steps;

import com.karpuk.booking.driver.DriverSingleton;
import com.karpuk.booking.pages.MainAccommodationPage;
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

    public boolean setCurrency(String currencyName) {
        MainAccommodationPage mainPage = new MainAccommodationPage(driver);
        mainPage.openPage();
        return mainPage.selectCurrency(currencyName);
    }

    public boolean setLanguage(String language) {
        MainAccommodationPage mainPage = new MainAccommodationPage(driver);
        mainPage.openPage();
        return mainPage.selectLanguage(language);
    }

}
