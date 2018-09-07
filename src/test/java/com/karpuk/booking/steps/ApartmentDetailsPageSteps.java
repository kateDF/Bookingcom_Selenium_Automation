package com.karpuk.booking.steps;

import com.karpuk.booking.entity.Apartment;
import com.karpuk.booking.pages.ApartmentDetailsPage;
import org.openqa.selenium.WebDriver;

public class ApartmentDetailsPageSteps extends AbstractSteps {

    public ApartmentDetailsPageSteps(WebDriver driver) {
        super(driver);
    }

    public Apartment getApartmentFromDetailsPage(){
        ApartmentDetailsPage detailsPage = new ApartmentDetailsPage(driver);
        return detailsPage.getApartment();
    }

    public void navigateToMapFromApartDetailsPage(){
        ApartmentDetailsPage apartmentDetailsPage = new ApartmentDetailsPage(driver);
        apartmentDetailsPage.navigateToMap();
    }

    public void selectCurrentPositionOnMap(){
        ApartmentDetailsPage apartmentDetailsPage = new ApartmentDetailsPage(driver);
        apartmentDetailsPage.selectCurrentLocationOnMap();
    }

    public Apartment getApartmentFromCurrentPositionOnMap() {
        ApartmentDetailsPage apartmentDetailsPage = new ApartmentDetailsPage(driver);
        return apartmentDetailsPage.getApartmentInfoFromCurrentPositionOnMap();
    }

    public boolean selectRoomsInReservationTable(int numberOfRooms) {
        ApartmentDetailsPage apartmentDetailsPage = new ApartmentDetailsPage(driver);
        return apartmentDetailsPage.selectNumberOfRoomsXpath(numberOfRooms);
    }

    public void clickReservationButton() {
        ApartmentDetailsPage apartmentDetailsPage = new ApartmentDetailsPage(driver);
        apartmentDetailsPage.clickReservationButton();
    }

}
