package com.karpuk.booking.pages;

import com.karpuk.booking.components.GuestsInformationForm;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.security.InvalidParameterException;
import java.util.List;

public class MainAccommodationPage extends AbstractPage {

    private static final String BASE_URL = "https://www.booking.com";

    private static final String CURRENCY_XPATH = "//li[@data-id='currency_selector']/a";
    private static final String ALL_CURRENCIES_XPATH = "//ul[@class='currency_list']/li";

    private static final String LANGUAGE_SELECTOR_XPATH = "//li[@data-id='language_selector']/a";
    private static final String ALL_LANGUAGES_XPATH = "//ul[@class='language_flags']/li";

    private static final String REGION_SEARCH_FIELD_ID = "ss";
    private static final String REGION_OPTION_XPATH = "//ul/li[@role='option']";
    private static final String CHECKIN_DATE_XPATH = "//div[@data-mode='checkin']//button";
    private static final String CHECKOUT_DATE_XPATH = "//div[@data-mode='checkout']//button";
    private static final String GUESTS_LABEL_XPATH = "//label[@id='xp__guests__toggle']";
    private static final String SUBMIT_SEARCH_FORM_XPATH = "//div[@class='xp__button']//button";

    @FindBy(xpath = CURRENCY_XPATH)
    private WebElement currencyButton;

    @FindBy(xpath = LANGUAGE_SELECTOR_XPATH)
    private WebElement languageButton;

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

    private List<WebElement> allCurrencies;
    private List<WebElement> allLanguages;

    public MainAccommodationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public void openPage() {
        driver.get(BASE_URL);
    }

    public void selectRegion(String regionName) {
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

    public void selectGuestsInformation(int numberOfRooms, int numberOfAdults, int numberOfChildren){
        GuestsInformationForm guestsForm = new GuestsInformationForm(driver);
        guestsForm.selectGuestsInformation(numberOfRooms, numberOfAdults, numberOfChildren);
    }

    public boolean selectCurrency(String currencyName) {
        String actualCurrency = currencyButton.getText();
        if (!actualCurrency.equals(currencyName.toUpperCase())) {
            currencyButton.click();
            return setCurrency(currencyName);
            }
        return true;
    }

    public boolean selectLanguage(String language) {
        languageButton.click();
        return setLanguage(language);
    }

    private boolean setCurrency(String currencyName) {
        allCurrencies = driver.findElements(By.xpath(ALL_CURRENCIES_XPATH));
        for (WebElement cur : allCurrencies) {
            if (currencyName.equalsIgnoreCase(cur.getAttribute("data-lang"))) {
                cur.findElement(By.tagName("a")).click();
                return true;
            }
        }
        return false;
    }

    private boolean setLanguage(String language) {
        allLanguages = driver.findElements(By.xpath(ALL_LANGUAGES_XPATH));
        for (WebElement lang : allLanguages) {
            if (lang.getText().equalsIgnoreCase(language)) {
                lang.findElement(By.tagName("a")).click();
                return true;
            }
        }
        return false;
    }

}
