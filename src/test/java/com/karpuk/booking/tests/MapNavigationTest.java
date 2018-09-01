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
    private static LocalDate CHECKIN_DATE = LocalDate.now().plusDays(2);
    private static LocalDate CHECKOUT_DATE = CHECKIN_DATE.plusDays(5);

    @BeforeMethod
    public void open() {
        steps.openMainPage();
        steps.setCurrency(CURRENCY);
        steps.setLanguage(LANGUAGE);
    }

    @Test
    public void mapNavigationTest() {
        steps.selectRegion(REGION);
        steps.selectDates(CHECKIN_DATE, CHECKOUT_DATE);
        steps.clickSearchButton();
        steps.selectAvailability();
        steps.openAndSwitchToFirstResultDetailsPage();
        Apartment expectedApartment = steps.getApartmentFromDetailsPage();
        steps.navigateToMapFromApartDetailsPage();
        Apartment actualApartment = steps.getApartmentFromCurrentPositionOnMap();

        Assert.assertEquals(actualApartment.getName(), expectedApartment.getName(),
                "Expected name: " + expectedApartment.getName() + ". Actual name: " +  actualApartment.getName());
        Assert.assertEquals(actualApartment.getRating(), expectedApartment.getRating(),
                "Expected rating: " + expectedApartment.getRating() + ". Actual rating: " +  actualApartment.getRating());
        Assert.assertEquals(actualApartment.getScore(), expectedApartment.getScore(),
                "Expected score: " + expectedApartment.getScore() + ". Actual score: " +  actualApartment.getScore());
    }



}