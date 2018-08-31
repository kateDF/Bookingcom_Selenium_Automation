package com.karpuk.booking.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FreeCancellationBookingTest extends BaseTest {

    private static final String CURRENCY = "USD";
    private static final String LANGUAGE = "English (US)";
    private static final String REGION = "Minsk";
    private static final LocalDate CHECKIN_DATE = LocalDate.now().plusDays(1);
    private static final LocalDate CHECKOUT_DATE = CHECKIN_DATE.plusDays(10);
    private static final int NUMBER_OF_CHILDREN = 2;

    @BeforeMethod
    public void setUpPreconditions() {
        steps.openMainPage();
        steps.setCurrency(CURRENCY);
        steps.setLanguage(LANGUAGE);
    }

    @Test
    public void availableFreeCancellationWithChildrenTest() {
        steps.selectRegion(REGION);
        steps.selectCheckInDate(CHECKIN_DATE);
        steps.selectCheckOutDate(CHECKOUT_DATE);
        steps.selectNumberOfChildren(NUMBER_OF_CHILDREN);
        steps.clickSearchButton();
        steps.selectAvailability();
        steps.selectFreeCancellation();

        List<String> actualResultsHotels = new ArrayList<>();
        do {
            actualResultsHotels.addAll(steps.getResultsHotelsNames());
        } while (steps.clickNextResultsPageArrow());
        int expectedMinNumberOfHotelsFromTitle = steps.getNumberOfSearchResults();

        Assert.assertTrue(actualResultsHotels.size() >= expectedMinNumberOfHotelsFromTitle,
                "Expected:" + expectedMinNumberOfHotelsFromTitle + ", Actual:" + actualResultsHotels.size());
    }

}