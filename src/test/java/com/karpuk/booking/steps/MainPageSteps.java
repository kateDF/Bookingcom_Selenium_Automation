package com.karpuk.booking.steps;

import com.karpuk.booking.pages.MainAccommodationPage;
import org.openqa.selenium.WebDriver;

import java.time.LocalDate;

public class MainPageSteps extends AbstractSteps {

    public MainPageSteps(WebDriver driver) {
        super(driver);
    }

    public MainAccommodationPage openMainPage() {
        MainAccommodationPage mainPage = new MainAccommodationPage(driver);
        mainPage.openPage();
        closeFlyDropdown(mainPage);
        return mainPage;
    }

    public void closeFlyDropdown(MainAccommodationPage mainPage){
        mainPage.closeFlyDropdown();
    }

    public void selectRegion(String regionName) {
        MainAccommodationPage mainPage = new MainAccommodationPage(driver);
        mainPage.selectRegion(regionName);
    }

    public void selectDates(LocalDate checkInDate, LocalDate checkOutDate) {
        MainAccommodationPage mainPage = new MainAccommodationPage(driver);
        mainPage.selectDates(checkInDate, checkOutDate);
    }

    public void clickSearchButton() {
        MainAccommodationPage mainPage = new MainAccommodationPage(driver);
        mainPage.clickSearchButton();
    }

}
