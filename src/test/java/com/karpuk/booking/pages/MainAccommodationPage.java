package com.karpuk.booking.pages;

import com.karpuk.booking.components.BookingCalendar;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.LocalDate;
import java.util.List;

public class MainAccommodationPage extends AbstractPage {

    private static final String BASE_URL = "https://www.booking.com";
    private static final String FLY_DROPDOWN_CLOSE_XPATH = "//div[contains(@class,'fly-dropdown')]//div[contains(@class,'bicon')]";

    @FindBy(id = "ss")
    private WebElement regionSearchField;

    @FindBy(xpath = "//div[@data-mode='checkin']//button")
    private WebElement checkInButton;

    @FindBy(xpath = "//div[@data-mode='checkout']//button")
    private WebElement checkOutButton;

    @FindBy(xpath = "//label[@id='xp__guests__toggle']")
    private WebElement guestsButton;

    @FindBy(xpath = "//div[@class='xp__button']//button")
    private WebElement searchButton;

    @FindBy(xpath = "//ul/li[@role='option']")
    private WebElement regionOption;

    public MainAccommodationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public void openPage() {
        driver.get(BASE_URL);
    }

    public void selectRegion(String regionName) {
        regionSearchField.clear();
        regionSearchField.sendKeys(regionName);
        regionOption.click();
    }

    public void openCheckInCalendar() {
        checkInButton.click();
    }

    public void openCheckOutCalendar() {
        checkOutButton.click();
    }

    public void openGuestsInformationForm() {
        guestsButton.click();
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void closeFlyDropdown(){
        List<WebElement> flyDropdownClose = driver.findElements(By.xpath(FLY_DROPDOWN_CLOSE_XPATH));
        if(!flyDropdownClose.isEmpty()){
            flyDropdownClose.get(0).click();
        }
    }

    public void selectDates(LocalDate checkInDate, LocalDate checkOutDate) {
        BookingCalendar cl = new BookingCalendar(driver);
        cl.setDates(checkInDate, checkOutDate);
    }

}
