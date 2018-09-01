package com.karpuk.booking.tests;

import com.karpuk.booking.entity.Apartment;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class ApartmentInformationTest extends BaseTest {

    private static final String CURRENCY = "EUR";
    private static final String LANGUAGE = "English (US)";
    private static final String REGION = "Moskow";
    private static LocalDate CHECKIN_DATE = LocalDate.now().plusDays(5);
    private static LocalDate CHECKOUT_DATE = CHECKIN_DATE.plusDays(5);

    @BeforeMethod
    public void open() {
        steps.openMainPage();
        steps.setCurrency(CURRENCY);
        steps.setLanguage(LANGUAGE);
    }

    @Test
    public void apartmentInformationFromDifferentPagesTest() {
        steps.selectRegion(REGION);
        steps.selectDates(CHECKIN_DATE, CHECKOUT_DATE);
        steps.selectGuestsInformation(1, 2, 1);
        steps.clickSearchButton();
        steps.selectAvailability();
        Apartment firstApartmentFromSearchResultPage = steps.getFirstResultApartmentFromSearchPage();
        steps.openAndSwitchToFirstResultDetailsPage();
        Apartment apartmentFromDetailsPage = steps.getApartmentFromDetailsPage();

        Assert.assertEquals(firstApartmentFromSearchResultPage.getName(), apartmentFromDetailsPage.getName());
        Assert.assertEquals(firstApartmentFromSearchResultPage.getRating(), apartmentFromDetailsPage.getRating());
        Assert.assertEquals(firstApartmentFromSearchResultPage.getScore(), apartmentFromDetailsPage.getScore());
    }

}
