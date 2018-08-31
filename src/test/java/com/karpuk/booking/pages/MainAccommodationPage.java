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

    private static final String REGION_SEARCH_FIELD_ID = "ss";
    private static final String REGION_OPTION_XPATH = "//ul/li[@role='option']";
    private static final String CHECKIN_DATE_XPATH = "//div[@data-mode='checkin']//button";
    private static final String CHECKOUT_DATE_XPATH = "//div[@data-mode='checkout']//button";
    private static final String GUESTS_LABEL_XPATH = "//label[@id='xp__guests__toggle']";
    private static final String SUBMIT_SEARCH_FORM_XPATH = "//div[@class='xp__button']//button";

    @FindBy(id = REGION_SEARCH_FIELD_ID)
    private WebElement regionSearchField;

    @FindBy(xpath = CHECKIN_DATE_XPATH)
    private WebElement checkInButton;

    @FindBy(xpath = CHECKOUT_DATE_XPATH)
    private WebElement checkOutButton;

    @FindBy(xpath = GUESTS_LABEL_XPATH)
    private WebElement guestsButton;

    @FindBy(xpath = SUBMIT_SEARCH_FORM_XPATH)
    private WebElement searchButton;

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
        driver.findElement(By.xpath(REGION_OPTION_XPATH)).click();
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
