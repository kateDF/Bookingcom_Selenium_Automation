package com.karpuk.booking.tests;

import com.karpuk.booking.entity.Guest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class ReservationTest extends BaseTest {

    private static final String CURRENCY = "EUR";
    private static final String LANGUAGE = "English (US)";
    private static final String REGION = "Moskow";
    private static final LocalDate CHECKIN_DATE = LocalDate.now().plusDays(2);
    private static final LocalDate CHECKOUT_DATE = CHECKIN_DATE.plusDays(15);

    private static final int NUMBER_OF_ROOMS = 1;
    private static final Guest EXPECTED_GUEST = new Guest("TestName", "TestSurname", "TestEmail@gmail.com", Guest.GuestTitleEnum.MR);

    @BeforeMethod
    public void open() {
        steps.openMainPage();
        steps.setCurrency(CURRENCY);
        steps.setLanguage(LANGUAGE);
    }

    @Test
    public void fillInReservationFormTest() {
        steps.selectRegion(REGION);
        steps.selectDates(CHECKIN_DATE, CHECKOUT_DATE);
        steps.clickSearchButton();

        steps.selectAvailability();
        steps.selectFreeCancellation();
        steps.openAndSwitchToFirstResultDetailsPage();

        steps.selectRoomsInReservationTable(NUMBER_OF_ROOMS);
        steps.clickReservationButton();

        steps.fillInGuestInformation(EXPECTED_GUEST);
        steps.clickNextSecureDetailsButton();

        Guest actualGuestInfo = steps.getGuestInfoFromFinalDetailsSecure();

        Assert.assertEquals(actualGuestInfo.getFirstName(), EXPECTED_GUEST.getFirstName(),
                "Expected First name: " + EXPECTED_GUEST.getFirstName() + ". Actual First name: " + actualGuestInfo.getFirstName());
        Assert.assertEquals(actualGuestInfo.getLastName(), EXPECTED_GUEST.getLastName(),
                "Expected Last name: " + EXPECTED_GUEST.getLastName() + ". Actual Last name: " + actualGuestInfo.getLastName());
        Assert.assertEquals(actualGuestInfo.getEmail(), EXPECTED_GUEST.getEmail(),
                "Expected Email: " + EXPECTED_GUEST.getEmail() + ". Actual Email: " + actualGuestInfo.getEmail());


    }

}
