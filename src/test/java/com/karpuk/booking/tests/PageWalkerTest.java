package com.karpuk.booking.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PageWalkerTest extends BaseTest {

    private static final String CURRENCY = "USD";
    private static final String LANGUAGE = "English (US)";
    private static final String REGION = "Minsk";
    private static final LocalDate CHECKIN_DATE = LocalDate.now().plusDays(1);
    private static final LocalDate CHECKOUT_DATE = CHECKIN_DATE.plusDays(10);
    private static final int NUMBER_OF_CHILDREN = 2;

    @BeforeMethod
    public void setUpPreconditions() {
        mainPageSteps.openMainPage();
        mainPageSteps.setCurrency(CURRENCY);
        mainPageSteps.setLanguage(LANGUAGE);
        logger.info("MainPage opened. Set currency and language");
    }

    @Test
    public void numberOfResultsInTitleEqualToActualInAllPages() {
        mainPageSteps.selectRegion(REGION);
        mainPageSteps.selectDates(CHECKIN_DATE, CHECKOUT_DATE);
        mainPageSteps.selectNumberOfChildren(NUMBER_OF_CHILDREN);
        mainPageSteps.clickSearchButton();
        logger.info("Start searching...");

        searchPageSteps.selectOnlyAvailable();
        List<String> actualResultsHotels = new ArrayList<>();
        do {
            actualResultsHotels.addAll(searchPageSteps.getResultsHotelsNames());
        } while (searchPageSteps.clickNextResultsPageArrow());
        int expectedNumberOfHotelsFromTitle = searchPageSteps.getNumberOfSearchResults();

        Assert.assertEquals(actualResultsHotels.size(), expectedNumberOfHotelsFromTitle,
                "Expected:" + expectedNumberOfHotelsFromTitle + ", Actual:" + actualResultsHotels.size());
        logger.info("Test finished");
    }

}
