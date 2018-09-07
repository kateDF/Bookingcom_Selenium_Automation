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
    private static final LocalDate CHECKIN_DATE = LocalDate.now().plusDays(5);
    private static final LocalDate CHECKOUT_DATE = CHECKIN_DATE.plusDays(5);

    @BeforeMethod
    public void open() {
        mainPageSteps.openMainPage();
        mainPageSteps.setCurrency(CURRENCY);
        mainPageSteps.setLanguage(LANGUAGE);
        logger.info("MainPage opened. Set currency and language");
    }

    @Test
    public void apartmentInformationFromDifferentPagesTest() {
        mainPageSteps.selectRegion(REGION);
        mainPageSteps.selectDates(CHECKIN_DATE, CHECKOUT_DATE);
        mainPageSteps.selectGuestsInformation(1, 2, 1);
        mainPageSteps.clickSearchButton();
        logger.info("Start searching...");

        searchPageSteps.selectOnlyAvailable();
        Apartment expectedApartment = searchPageSteps.getFirstResultApartmentFromSearchPage();
        searchPageSteps.openAndSwitchToFirstResultDetailsPage();
        Apartment actualApartment = apartmentPageSteps.getApartmentFromDetailsPage();

        Assert.assertEquals(expectedApartment.getName(), actualApartment.getName(),
                "Expected name: " + expectedApartment.getName() + ". Actual name: " +  actualApartment.getName());
        Assert.assertEquals(expectedApartment.getRating(), actualApartment.getRating(),
                "Expected rating: " + expectedApartment.getRating() + ". Actual rating: " +  actualApartment.getRating());
        Assert.assertEquals(expectedApartment.getScore(), actualApartment.getScore(),
                "Expected score: " + expectedApartment.getScore() + ". Actual score: " +  actualApartment.getScore());
        logger.info("Test finished");
    }

}
