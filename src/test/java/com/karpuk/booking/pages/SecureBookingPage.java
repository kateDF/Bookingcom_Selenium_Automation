package com.karpuk.booking.pages;

import com.karpuk.booking.entity.Guest;
import com.sun.org.apache.bcel.internal.ExceptionConstants;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.naming.OperationNotSupportedException;
import java.util.List;

public class SecureBookingPage extends AbstractPage{

    private static final String MODAL_MASK_CLOSE_XPATH = "//button[@class='modal-mask-closeBtn']";

    private static final String FIRST_NAME_FIELD_ID = "firstname";
    private static final String LAST_NAME_FIELD_ID = "lastname";
    private static final String EMAIL_FIELD_ID = "email";
    private static final String EMAIL_CONFIRM_FIELD_ID = "email_confirm";
    private static final String BOOKER_TITLE_SELECT_ID = "booker_title";

    private static final String NEXT_FINAL_DETAILS_BUTTON_XPATH = "//div[contains(@class,'submit_holder_button_wrap')]//button";

    private static final String GUEST_INFO_FROM_FINAL_DETAILS_XPATH = "//div[contains(@class,'book-form-field-value')]";


    @FindBy(id = FIRST_NAME_FIELD_ID)
    private WebElement firstNameField;

    @FindBy(id = LAST_NAME_FIELD_ID)
    private WebElement lastNameField;

    @FindBy(id = EMAIL_FIELD_ID)
    private WebElement emailField;

    @FindBy(id = EMAIL_CONFIRM_FIELD_ID)
    private WebElement emailConfirmField;

    @FindBy(xpath = NEXT_FINAL_DETAILS_BUTTON_XPATH)
    private WebElement nextDetailsButton;

    @FindBy(id = BOOKER_TITLE_SELECT_ID)
    private WebElement bookerTitleSelect;

    public SecureBookingPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public void openPage() throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }

    public void closeModalMask(){
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.elementToBeClickable(By.xpath(MODAL_MASK_CLOSE_XPATH)))
                .click();
    }

    public void fillGuestInformation(Guest guest){
        selectBookerTitle(guest.getGuestTitle().toString());
        fillInField(firstNameField, guest.getFirstName());
        fillInField(lastNameField, guest.getLastName());
        fillInField(emailField, guest.getEmail());
        fillInField(emailConfirmField, guest.getEmail());
    }

    public void clickNextDetailsButton(){
        waitForClickable(nextDetailsButton);
        nextDetailsButton.click();
    }

    public Guest getGuestInfoFromFinalDetails(){
        try{
            return getGuestInfo();
        } catch (WebDriverException e){
            closeModalMask();
            return getGuestInfo();
        }
    }

    private void selectBookerTitle(String bookerTitle){
        try {
            selectOption(bookerTitle);
        } catch (WebDriverException e){
            closeModalMask();
            selectOption(bookerTitle);
        }
    }

    private void fillInField(WebElement field, String value){
        try {
            updateFieldValue(field, value);
        } catch (WebDriverException e){
            closeModalMask();
            updateFieldValue(field, value);
        }
    }

    private void updateFieldValue(WebElement field, String value){
        field.clear();
        field.sendKeys(value);
    }

    private void selectOption(String option){
        (new Select(bookerTitleSelect)).selectByValue(option.toLowerCase());
    }

    private Guest getGuestInfo(){
        List<WebElement> guestInfo = driver.findElements(By.xpath(GUEST_INFO_FROM_FINAL_DETAILS_XPATH));
        String[] guestName = guestInfo.get(0).getText().split(" ");
        Guest guest = new Guest();

        if(guestName.length == 1){
            guest.setLastName(guestName[0]);
        } else {
            guest.setFirstName(guestName[0]);
            guest.setLastName(guestName[1]);
        }

        guest.setEmail(guestInfo.get(1).getText());
        return guest;
    }

    private void waitForClickable(WebElement element){
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(element));
    }

}
