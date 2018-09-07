package com.karpuk.booking.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class BookingWithBudgetTest extends BaseTest {

    private static final String CURRENCY = "EUR";
    private static final String LANGUAGE = "English (US)";
    private static final String REGION = "Minsk";
    private static final LocalDate CHECKIN_DATE = LocalDate.now().plusDays(14);
    private static final LocalDate CHECKOUT_DATE = CHECKIN_DATE.plusDays(20);
    private static final int BUDGET_GROUP = 1;

    @BeforeMethod
    public void setUpPreconditions() {
        mainPageSteps.openMainPage();
        mainPageSteps.setCurrency(CURRENCY);
        mainPageSteps.setLanguage(LANGUAGE);
        logger.info("MainPage opened. Set currency and language");
    }

    @Test
    public void availableBookingWithLowBudget() {
        mainPageSteps.selectRegion(REGION);
        mainPageSteps.selectDates(CHECKIN_DATE, CHECKOUT_DATE);
        mainPageSteps.clickSearchButton();
        logger.info("Start searching...");

        searchPageSteps.selectOnlyAvailable();
        int availableWithoutBudgetMark = searchPageSteps.getNumberOfSearchResults();
        searchPageSteps.selectBudget(BUDGET_GROUP);
        int availableWithBudgetMark = searchPageSteps.getNumberOfSearchResults();

        Assert.assertTrue(availableWithBudgetMark <= availableWithoutBudgetMark,
                "Number of results with selected budget: " + availableWithBudgetMark + " should not be more than " + availableWithoutBudgetMark);
        logger.info("Test finished");
    }

}
