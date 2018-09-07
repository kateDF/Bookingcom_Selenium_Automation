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
    private static final LocalDate CHECKIN_DATE = LocalDate.now().plusDays(1);
    private static final LocalDate CHECKOUT_DATE = CHECKIN_DATE.plusDays(10);

    @BeforeMethod
    public void open() {
        mainPageSteps.openMainPage();
        mainPageSteps.setCurrency(CURRENCY);
        mainPageSteps.setLanguage(LANGUAGE);
        logger.info("MainPage opened. Set currency and language");
    }

    @Test
    public void sortedResultsByLowestUsdPriceFirst() {
        mainPageSteps.selectRegion(REGION);
        mainPageSteps.selectDates(CHECKIN_DATE, CHECKOUT_DATE);
        mainPageSteps.clickSearchButton();
        logger.info("Start searching...");

        searchPageSteps.selectOnlyAvailable();
        searchPageSteps.selectStarRating(STAR_RATING);

        searchPageSteps.sortLowestUsdPriceFirst();
        List<Integer> pricesList = new ArrayList<>();
        do {
            pricesList.addAll(searchPageSteps.getResultsPricesInUsd());
        } while (searchPageSteps.clickNextResultsPageArrow());

        for (int i = 0; i < pricesList.size() - 1; i++) {
            Assert.assertTrue(
                    pricesList.get(i) <= pricesList.get(i + 1),
                    "Price " + pricesList.get(i) + "$ shouldn`t be higher than " + pricesList.get(i + 1) + "$");
        }
        logger.info("Test finished");
    }

}
