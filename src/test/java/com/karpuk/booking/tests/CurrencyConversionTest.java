package com.karpuk.booking.tests;

import com.karpuk.booking.service.CurrencyExchangeClient;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class CurrencyConversionTest extends BaseTest {

    private static final String CURRENCY_USD = "USD";
    private static final String CURRENCY_EURO = "EUR";
    private static final String LANGUAGE = "English (US)";
    private static final String REGION = "Minsk";
    private static final LocalDate CHECKIN_DATE = LocalDate.now().plusDays(1);
    private static final LocalDate CHECKOUT_DATE = CHECKIN_DATE.plusDays(10);

    private CurrencyExchangeClient currencyExchangeClient;

    @BeforeMethod
    public void setUpPreconditions() {
        steps.openMainPage();
        steps.setCurrency(CURRENCY_USD);
        steps.setLanguage(LANGUAGE);
        currencyExchangeClient = new CurrencyExchangeClient();
        logger.info("MainPage opened. Set currency and language");
    }

    @Test
    public void bookingUsdEuroConversionTest() {
        steps.selectRegion(REGION);
        steps.selectDates(CHECKIN_DATE, CHECKOUT_DATE);
        steps.clickSearchButton();
        logger.info("Start searching...");

        steps.selectAvailability();
        int originalPriceInUsd = steps.getFirstResultPriceInUsd();
        int actualPriceInEuro = steps.getFirstResultPriceInEuro();
        double rate = currencyExchangeClient.getRate(CURRENCY_USD, CURRENCY_EURO);

        double expectedPriceInEuro = originalPriceInUsd * rate;

        Assert.assertEquals(actualPriceInEuro, expectedPriceInEuro, actualPriceInEuro*0.05,
                "Expected price: " + expectedPriceInEuro + " should not differ from actual: " + actualPriceInEuro + " more then 5%" );
        logger.info("Test finished");
    }

}
