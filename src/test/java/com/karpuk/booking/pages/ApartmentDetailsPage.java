package com.karpuk.booking.pages;

import com.karpuk.booking.entity.Apartment;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.naming.OperationNotSupportedException;
import java.util.List;

public class ApartmentDetailsPage extends AbstractPage {

    private static final String SELECT_NUMBER_OF_ROOMS_XPATH = "//td[contains(@class,'hprt-table-room-select')]//select";
    private static final String OPTION_ROOMS_XPATH =  "/option[@value='%d']";

    @FindBy(xpath = "//*[@class='hp__hotel-name']")
    private WebElement hotelName;

    @FindBy(xpath = "//div[@id='js--hp-gallery-scorecard']//span[@class='review-score-widget__text']")
    private WebElement rating;

    @FindBy(xpath = "//div[@id='js--hp-gallery-scorecard']//span[@class='review-score-badge']")
    private WebElement score;

    @FindBy(xpath = "//span[contains(@class,'hp_address_subtitle')]")
    private WebElement location;

    @FindBy(xpath = "//a[contains(@class,'show_map')]")
    private WebElement mapLink;

    @FindBy(xpath = "//div[contains(@class,'current')]")
    private WebElement currentLocationOnMap;

    @FindBy(id = "map_hotel_overlay_title")
    private WebElement locationTitleOnMap;

    @FindBy(xpath = "//div[@id='map_hotel_overlay_content_wrapper']//span[@class='review-score-badge']")
    private WebElement locationScoreOnMap;

    @FindBy(xpath = "//div[@id='map_hotel_overlay_content_wrapper']//span[@class='review-score-widget__text']")
    private WebElement locationRatingOnMap;

    @FindBy(xpath = SELECT_NUMBER_OF_ROOMS_XPATH)
    private WebElement selectRooms;

    @FindBy(xpath = "//div[@class='hprt-reservation-cta']/button")
    private WebElement reservationButton;

    public ApartmentDetailsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public void openPage() throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }

    public Apartment getApartment(){
        Apartment apartment = new Apartment();

        apartment.setName(hotelName.getText());
        apartment.setRating(rating.getText());
        apartment.setScore(parseDouble(score));
        apartment.setLocation(location.getText());

        return apartment;
    }

    public void navigateToMap(){
        mapLink.click();
    }

    public void selectCurrentLocationOnMap() {
        currentLocationOnMap.click();
    }

    public Apartment getApartmentInfoFromCurrentPositionOnMap(){
        Apartment apartment = new Apartment();

        apartment.setName(locationTitleOnMap.getText());
        apartment.setRating(locationRatingOnMap.getText());
        apartment.setScore(parseDouble(locationScoreOnMap));

        return apartment;
    }


    public boolean selectNumberOfRoomsXpath(int numberOfRooms) {
        waitForClickable(selectRooms);
        selectRooms.click();
        List <WebElement> option = selectRooms.findElements(By.xpath(String.format(SELECT_NUMBER_OF_ROOMS_XPATH + OPTION_ROOMS_XPATH, numberOfRooms)));
        if(!option.isEmpty()){
            waitForClickable(option.get(0));
            option.get(0).click();
            return true;
        }
        return false;
    }

    public void clickReservationButton(){
        waitForClickable(reservationButton);
        reservationButton.click();
    }

    private double parseDouble(WebElement element){
        return Double.parseDouble(element.getText().replaceAll(",","."));
    }

    private void waitForClickable(WebElement element){
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(element));
    }

}
