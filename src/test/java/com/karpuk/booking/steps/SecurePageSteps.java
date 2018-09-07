package com.karpuk.booking.steps;

import com.karpuk.booking.entity.Guest;
import com.karpuk.booking.pages.SecureBookingPage;
import org.openqa.selenium.WebDriver;

public class SecurePageSteps extends AbstractSteps {

    public SecurePageSteps(WebDriver driver) {
        super(driver);
    }

    public void fillInGuestInformation(Guest guest){
        SecureBookingPage securePage = new SecureBookingPage(driver);
        securePage.fillGuestInformation(guest);
    }

    public void clickNextSecureDetailsButton(){
        SecureBookingPage securePage = new SecureBookingPage(driver);
        securePage.clickNextDetailsButton();
    }

    public Guest getGuestInfoFromFinalDetailsSecure(){
        SecureBookingPage securePage = new SecureBookingPage(driver);
        return securePage.getGuestInfoFromFinalDetails();
    }

    public void closeModalMaskInSecurePage(){
        SecureBookingPage securePage = new SecureBookingPage(driver);
        securePage.closeModalMask();
    }


}
