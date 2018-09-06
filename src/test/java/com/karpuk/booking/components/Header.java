package com.karpuk.booking.components;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Header {

    private WebDriver driver;

    @FindBy(xpath = "//*[@data-id='currency_selector']/a")
    private WebElement currencyButton;

    @FindBy(xpath = "//*[@data-id='currency_selector']/input")
    private WebElement currencyValue;

    @FindBy(xpath = "//*[@data-id='language_selector']/a")
    private WebElement languageButton;

    @FindBy(xpath = "//*[@class='currency_list']/li")
    private List<WebElement>  allCurrencies;

    @FindBy(xpath = "//*[@class='language_flags']/li")
    private List<WebElement> allLanguages;

    public Header(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean selectCurrency(String currencyName) {
        String actualCurrency = currencyValue.getAttribute("value");
        if (!actualCurrency.equals(currencyName.toUpperCase())) {
            waitAndClickHeaderButton(currencyButton);
            return setCurrency(currencyName);
        }
        return true;
    }

    public boolean selectLanguage(String language) {
        waitAndClickHeaderButton(languageButton);
        return setLanguage(language);
    }

    private boolean setCurrency(String currencyName) {
        for (WebElement cur : allCurrencies) {
            if (currencyName.equalsIgnoreCase(cur.getAttribute("data-lang"))) {
                waitAndClickHeaderButton(cur.findElement(By.tagName("a")));
                return true;
            }
        }
        return false;
    }

    private boolean setLanguage(String language) {
        for (WebElement lang : allLanguages) {
            if (lang.getText().equalsIgnoreCase(language)) {
                waitAndClickHeaderButton(lang.findElement(By.tagName("a")));
                return true;
            }
        }
        return false;
    }

    private void waitAndClickHeaderButton(WebElement button) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(button));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
    }

}
