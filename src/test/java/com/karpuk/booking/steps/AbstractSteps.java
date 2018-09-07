package com.karpuk.booking.steps;

import com.karpuk.booking.components.GuestsInformationForm;
import com.karpuk.booking.components.Header;
import org.openqa.selenium.WebDriver;

public abstract class AbstractSteps {

    protected WebDriver driver;

    public AbstractSteps(WebDriver driver) {
        this.driver = driver;
    }

    public boolean setCurrency(String currencyName) {
        Header header = new Header(driver);
        return header.selectCurrency(currencyName);
    }

    public boolean setLanguage(String language) {
        Header header = new Header(driver);
        return header.selectLanguage(language);
    }

    public void selectGuestsInformation(int numberOfRooms, int numberOfAdults, int numberOfChildren) {
        GuestsInformationForm guestsForm = new GuestsInformationForm(driver);
        guestsForm.openGuestForm();
        guestsForm.selectGuestsInformation(numberOfRooms, numberOfAdults, numberOfChildren);
    }

    public void selectNumberOfChildren(int numberOfChildren){
        GuestsInformationForm guestsForm = new GuestsInformationForm(driver);
        guestsForm.openGuestForm();
        guestsForm.selectChildren(numberOfChildren);
    }

}
