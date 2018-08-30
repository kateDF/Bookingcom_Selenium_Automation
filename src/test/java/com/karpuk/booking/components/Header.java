package com.karpuk.booking.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Header {

    private static final String CURRENCY_XPATH = "//*[@data-id='currency_selector']/a";
    private static final String ALL_CURRENCIES_XPATH = "//*[@class='currency_list']/li";

    private static final String LANGUAGE_SELECTOR_XPATH = "//*[@data-id='language_selector']/a";
    private static final String ALL_LANGUAGES_XPATH = "//*[@class='language_flags']/li";

    private WebDriver driver;

    @FindBy(xpath = CURRENCY_XPATH)
    private WebElement currencyButton;

    @FindBy(xpath = LANGUAGE_SELECTOR_XPATH)
    private WebElement languageButton;

    private List<WebElement> allCurrencies;
    private List<WebElement> allLanguages;

    public Header(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
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
