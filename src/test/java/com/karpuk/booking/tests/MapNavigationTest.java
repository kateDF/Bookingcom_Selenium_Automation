package com.karpuk.booking.tests;

import com.karpuk.booking.entity.Apartment;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class MapNavigationTest extends BaseTest {

    private static final String CURRENCY = "EUR";
    private static final String LANGUAGE = "English (US)";
    private static final String REGION = "Minsk";
    private static final LocalDate CHECKIN_DATE = LocalDate.now().plusDays(2);
    private static final LocalDate CHECKOUT_DATE = CHECKIN_DATE.plusDays(5);

    @BeforeMethod
    public void open() {
        mainPageSteps.openMainPage();
        mainPageSteps.setCurrency(CURRENCY);
        mainPageSteps.setLanguage(LANGUAGE);
        logger.info("MainPage opened. Set currency and language");
    }

    @Test
    public void mapNavigationTest() {
        mainPageSteps.selectRegion(REGION);
        mainPageSteps.selectDates(CHECKIN_DATE, CHECKOUT_DATE);
        mainPageSteps.clickSearchButton();
        logger.info("Start searching...");

        searchPageSteps.selectOnlyAvailable();
        searchPageSteps.openAndSwitchToFirstResultDetailsPage();
        Apartment expectedApartment = apartmentPageSteps.getApartmentFromDetailsPage();
        apartmentPageSteps.navigateToMapFromApartDetailsPage();
        Apartment actualApartment = apartmentPageSteps.getApartmentFromCurrentPositionOnMap();

        Assert.assertTrue(actualApartment.getName().contains(expectedApartment.getName()) || expectedApartment.getName().contains(actualApartment.getName()),
                "Expected name: " + expectedApartment.getName() + ". Actual name: " +  actualApartment.getName());
        Assert.assertEquals(actualApartment.getRating(), expectedApartment.getRating(),
                "Expected rating: " + expectedApartment.getRating() + ". Actual rating: " +  actualApartment.getRating());
        Assert.assertEquals(actualApartment.getScore(), expectedApartment.getScore(),
                "Expected score: " + expectedApartment.getScore() + ". Actual score: " +  actualApartment.getScore());
        logger.info("Test finished");
    }

}
