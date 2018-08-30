package com.karpuk.booking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.naming.OperationNotSupportedException;
import java.util.List;

public class SearchResultsPage extends AbstractPage {

    private static final String SEARCH_RESULT_HEADER_XPATH = "//div[@data-block-id='heading']//div[@role='heading']/*";
    private static final String AVAILABILITY_CHECKBOX_XPATH = "//a[@data-id='oos-1']";
    private static final String BUDGET_OPTIONS_XPATH = "//div[@id='filter_price']/div[contains(@class,'filteroptions')]/a[@data-id='pri-%d']";
    private static final String STAR_RATING_OPTIONS_XPATH = "//div[@id='filter_class']/div[contains(@class,'filteroptions')]/a[@data-id='class-%d']";
    private static final String FREE_CANCELLATION_CHECKBOX_XPATH = "//a[@data-id='fc-2']";

    @FindBy(xpath = SEARCH_RESULT_HEADER_XPATH)
    private WebElement searchResultText;

    @FindBy(xpath = AVAILABILITY_CHECKBOX_XPATH)
    private WebElement availabilityCheckbox;

    @FindBy(xpath = FREE_CANCELLATION_CHECKBOX_XPATH)
    private WebElement freeCancellationCheckbox;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public void openPage() throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }

    public int getNumberOfResults() {
        //TODO: add waiting
        String[] resultArr = searchResultText.getText().split(": ")[1].split(" ");
        int resultNumber = 0;
        for(String word : resultArr){
            try {
                resultNumber = Integer.parseInt(word);
                break;
            } catch (NumberFormatException e) {
                continue;
            }
        }
        return resultNumber;
    }

    public void selectAvailabilityCheckbox() {
        selectCheckbox(availabilityCheckbox);
    }

    public boolean selectBudgetGroup(int budgetGroupNumber) {
        String fullXpath = String.format(BUDGET_OPTIONS_XPATH, budgetGroupNumber);
        List<WebElement> budgetCheckBox = driver.findElements(By.xpath(fullXpath));
        if (budgetCheckBox.size() != 0) {
            budgetCheckBox.get(0).click();
            return true;
        }
        return false;
    }

    public boolean selectStarRating(int starRating) {
        String fullXpath = String.format(STAR_RATING_OPTIONS_XPATH, starRating);
        List<WebElement> starRatingCheckBox = driver.findElements(By.xpath(fullXpath));
        if (starRatingCheckBox.size() != 0) {
            starRatingCheckBox.get(0).click();
            return true;
        }
        return false;
    }

    public void selectFreeCancellation(){
        selectCheckbox(freeCancellationCheckbox);
    }

    private void selectCheckbox(WebElement checkbox){
        if (!Boolean.parseBoolean(checkbox.getAttribute("aria-checked"))) {
            checkbox.click();
        }
    }

    private void deselectCheckbox(WebElement checkbox){
        if (Boolean.parseBoolean(checkbox.getAttribute("aria-checked"))) {
            checkbox.click();
        }
    }

}
