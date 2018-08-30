package com.karpuk.booking.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.naming.OperationNotSupportedException;

public class SearchResultsPage extends AbstractPage {

    private static final String SEARCH_RESULT_HEADER_XPATH = "//div[@data-block-id='heading']//div[@role='heading']/h1";
    private static final String AVAILABILITY_CHECKBOX_XPATH = "//a[@data-id='oos-1']";


    @FindBy(xpath = SEARCH_RESULT_HEADER_XPATH)
    private WebElement searchResultText;

    @FindBy(xpath = AVAILABILITY_CHECKBOX_XPATH)
    private WebElement availabilityCheckbox;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public void openPage() throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }

    public int getNumberOfResults(){
        //only for english locale
       String[] tempArr =  searchResultText.getText().split(": ");
       String result = tempArr[1].split(" ")[0];
       return Integer.parseInt(result);
    }

    public void selectAvailabilityCheckbox(){
        if(!Boolean.parseBoolean(availabilityCheckbox.getAttribute("aria-checked"))){
            availabilityCheckbox.click();
        }
    }

}
