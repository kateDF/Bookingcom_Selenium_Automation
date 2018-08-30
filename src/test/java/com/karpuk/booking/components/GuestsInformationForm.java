package com.karpuk.booking.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.swing.*;

public class GuestsInformationForm {

    private static final String GUESTS_FORM_ID = "xp__guests__toggle";
    private static final String SELECT_ROOMS_ID = "no_rooms";
    private static final String SELECT_GROUP_ADULTS_ID = "group_adults";
    private static final String SELECT_GROUP_CHILDREN_ID = "group_children";
    private static final String OPTION_XPATH = "//*[@id='%s']/option[@value='%d']";

    @FindBy(id = SELECT_ROOMS_ID)
    private WebElement selectRooms;

    @FindBy(id = SELECT_GROUP_ADULTS_ID)
    private WebElement selectAdults;

    @FindBy(id = SELECT_GROUP_CHILDREN_ID)
    private WebElement selectChildren;

    private WebDriver driver;

    public GuestsInformationForm(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openGuestForm(){
        driver.findElement(By.id(GUESTS_FORM_ID)).click();
    }

    public void selectRooms(int numberOfRooms){
        selectRooms.click();
        selectOptions(SELECT_ROOMS_ID, numberOfRooms);
    }

    public void selectAdults(int numberOfAdults){
        selectAdults.click();
        selectOptions(SELECT_GROUP_ADULTS_ID, numberOfAdults);
    }

    public void selectChildren(int numberOfChildren){
        selectChildren.click();
        selectOptions(SELECT_GROUP_CHILDREN_ID, numberOfChildren);
    }

    public void selectGuestsInformation(int numberOfRooms, int numberOfAdults, int numberOfChildren) {
        selectRooms(numberOfRooms);
        selectAdults(numberOfAdults);
        selectChildren(numberOfChildren);
    }

    private void selectOptions(String selectElement, int value) {
        driver.findElement(By.xpath(String.format(OPTION_XPATH, selectElement, value))).click();
    }


}
