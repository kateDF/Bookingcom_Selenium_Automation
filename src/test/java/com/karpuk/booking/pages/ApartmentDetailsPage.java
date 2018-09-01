package com.karpuk.booking.pages;

import com.karpuk.booking.components.Header;
import com.karpuk.booking.entity.Apartment;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.naming.OperationNotSupportedException;

public class ApartmentDetailsPage extends AbstractPage {

    private static final String HOTEL_NAME_XPATH = "//*[@class='hp__hotel-name']";
    private static final String RATING_XPATH = "//div[@id='js--hp-gallery-scorecard']//span[@class='review-score-widget__text']";
    private static final String SCORE_XPATH = "//div[@id='js--hp-gallery-scorecard']//span[@class='review-score-badge']";
    private static final String LOCATION_XPATH = "//span[contains(@class,'hp_address_subtitle')]";
    private static final String MAP_LINK_XPATH = "//a[contains(@class,'show_map')]";

    private static final String CURRENT_LOCATION_ON_MAP_XPATH = "//div[contains(@class,'current')]";
    private static final String LOCATION_TITLE_ON_MAP_ID = "map_hotel_overlay_title";
    private static final String LOCATION_SCORE_ON_MAP_XPATH = "//div[@id='map_hotel_overlay_content_wrapper']//span[@class='review-score-badge']";
    private static final String LOCATION_RATING_ON_MAP_XPATH = "//div[@id='map_hotel_overlay_content_wrapper']//span[@class='review-score-widget__text']";

    @FindBy(xpath = HOTEL_NAME_XPATH)
    private WebElement hotelName;

    @FindBy(xpath = RATING_XPATH)
    private WebElement rating;

    @FindBy(xpath = SCORE_XPATH)
    private WebElement score;

    @FindBy(xpath = LOCATION_XPATH)
    private WebElement location;

    @FindBy(xpath = MAP_LINK_XPATH)
    private WebElement mapLink;

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
        driver.findElement(By.xpath(CURRENT_LOCATION_ON_MAP_XPATH)).click();
    }

    public Apartment getApartmentInfoFromCurrentPositionOnMap(){
        Apartment apartment = new Apartment();

        apartment.setName(driver.findElement(By.id(LOCATION_TITLE_ON_MAP_ID)).getText());
        apartment.setRating(driver.findElement(By.xpath(LOCATION_RATING_ON_MAP_XPATH)).getText());
        apartment.setScore(parseDouble(driver.findElement(By.xpath(LOCATION_SCORE_ON_MAP_XPATH))));

        return apartment;
    }

    private double parseDouble(WebElement element){
        return Double.parseDouble(score.getText().replaceAll(",","."));
    }

}
