package com.karpuk.booking.pages;

import com.karpuk.booking.components.Header;
import com.karpuk.booking.entity.Apartment;
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

    @FindBy(xpath = HOTEL_NAME_XPATH)
    private WebElement hotelName;

    @FindBy(xpath = RATING_XPATH)
    private WebElement rating;

    @FindBy(xpath = SCORE_XPATH)
    private WebElement score;

    @FindBy(xpath = LOCATION_XPATH)
    private WebElement location;

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
        apartment.setScore(Double.parseDouble(score.getText().replaceAll(",",".")));
        apartment.setLocation(location.getText());

        return apartment;
    }

}
