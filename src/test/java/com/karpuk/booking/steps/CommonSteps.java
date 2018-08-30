package com.karpuk.booking.steps;

import com.karpuk.booking.components.Header;
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

    public MainAccommodationPage openMainPage(){
        MainAccommodationPage mainPage = new MainAccommodationPage(driver);
        mainPage.openPage();
        return mainPage;
    }

    public void selectRegion(String regionName){
        MainAccommodationPage mainPage = new MainAccommodationPage(driver);
        mainPage.selectRegion(regionName);
    }

    public void selectGuestsInformation(int numberOfRooms, int numberOfAdults, int numberOfChildren){
        MainAccommodationPage mainPage = new MainAccommodationPage(driver);
        mainPage.openGuestsInformationForm();
        mainPage.selectGuestsInformation(numberOfRooms, numberOfAdults, numberOfChildren);
    }

    public void clickSearchButton(){
        MainAccommodationPage mainPage = new MainAccommodationPage(driver);
        mainPage.clickSearchButton();
    }

    public boolean setCurrency(String currencyName) {
        Header header = new Header(driver);
        return header.selectCurrency(currencyName);
    }

    public boolean setLanguage(String language) {
        Header header = new Header(driver);
        return header.selectLanguage(language);
    }

}
