package com.karpuk.booking.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SortResultsByPriceTest extends BaseTest {

    private static final String CURRENCY = "USD";
    private static final String LANGUAGE = "English (US)";
    private static final String REGION = "Minsk";
    private static final int STAR_RATING = 5;
    private static LocalDate CHECKIN_DATE = LocalDate.now().plusDays(1);
    private static LocalDate CHECKOUT_DATE = CHECKIN_DATE.plusDays(10);

    @BeforeMethod
    public void open() {
        steps.openMainPage();
        steps.setCurrency(CURRENCY);
        steps.setLanguage(LANGUAGE);
    }

    // Attention: you should wait for test success (method works not so fast as I want)
    @Test
    public void sortedResultsByLowestUsdPriceFirst() {
        steps.selectRegion(REGION);
        steps.selectDates(CHECKIN_DATE, CHECKOUT_DATE);
        steps.clickSearchButton();
        steps.selectAvailability();
        steps.selectStarRating(STAR_RATING);
        steps.sortLowestUsdPriceFirst();
        List<Integer> pricesList = new ArrayList<>();
        //TODO: should work faster
        do {
            pricesList.addAll(steps.getResultsPricesInUsd());
        } while (steps.clickNextResultsPageArrow());

        for (int i = 0; i < pricesList.size() - 1; i++) {
            Assert.assertTrue(pricesList.get(i) <= pricesList.get(i + 1), "Price " + pricesList.get(i) + "$ shouldn`t be higher than " + pricesList.get(i + 1) + "$");
        }
    }

}
