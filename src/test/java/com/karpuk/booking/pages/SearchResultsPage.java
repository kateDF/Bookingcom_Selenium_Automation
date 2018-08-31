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
import java.util.ArrayList;
import java.util.List;

public class SearchResultsPage extends AbstractPage {

    private static final String SEARCH_RESULT_HEADER_XPATH = "//div[@data-block-id='heading']//div[@role='heading']/*";

    private static final String LIST_OF_HOTELS_RESULTS_FULL_ELEMENTS = "//div[@id='hotellist_inner']//div[contains(@class,'sr_item_content')]";
    private static final String LIST_OF_HOTELS_NAMES_WITH_RECOMMENDED_OUTSIDE_XPATH = "//span[contains(@class,'sr-hotel__name')]";
    private static final String LIST_OF_HOTELS_NAMES_XPATH = "//span[contains(@class,'bicon-direction-arrow')]/following-sibling::span/../../preceding-sibling::*[contains(@class,'sr-hotel__title')]";
    private static final String LIST_PRICES_INFO_FOR_USD_XPATH = "//*[contains(@class,'price')]//b[contains(text(),'US')] |  //div[contains(@class, 'totalPrice')][contains(text(),'US')] | //b[@class='sr_gs_price_total']";
    private static final String LIST_PRICES_INFO_FOR_EURO_XPATH = "//*[contains(@class,'price')]//b[contains(text(),'€')] |  //div[contains(@class, 'totalPrice')][contains(text(),'€')] | //b[@class='sr_gs_price_total']";

    private static final String RATING_TITLES_XPATH = "//span[@class='review-score-widget__body']/span[@class='review-score-widget__text']";
    private static final String SCORE_TITLES_XPATH = "//span[@class='review-score-badge']";
    private static final String LOCATION_XPATH = "//a[contains(@class,'district_link visited_link')]";
    private static final String HOTEL_FULL_INFO_BUTTON_XPATH = "//a[contains(@class,'b-button_primary')]";

    private static final String MARK_HOTELS_RECOMMENDED_OUTSIDE_ARIA_XPATH = "//div[contains(@class,'distfromdest--highlight')]";

    private static final String ARROW_NEXT_RESULTS_PAGE_XPATH = "//li[contains(@class,'bui-pagination__next-arrow')]/a";
    private static final String LOAD_WAIT_SPINNER_XPATH = "//*[@id='b2searchresultsPage']//div[@class='sr-usp-overlay__container']";

    private static final String AVAILABILITY_CHECKBOX_XPATH = "//a[@data-id='oos-1']";
    private static final String BUDGET_OPTIONS_XPATH = "//div[@id='filter_price']/div[contains(@class,'filteroptions')]/a[@data-id='pri-%d']";
    private static final String STAR_RATING_OPTIONS_XPATH = "//div[@id='filter_class']/div[contains(@class,'filteroptions')]/a[@data-id='class-%d']";
    private static final String FREE_CANCELLATION_CHECKBOX_XPATH = "//a[@data-id='fc-2']";

    private static final String SORT_BY_PRICE_XPATH = "//li[contains(@class,'sort_price')]/a";

    @FindBy(xpath = SEARCH_RESULT_HEADER_XPATH)
    private WebElement searchResultText;

    @FindBy(xpath = AVAILABILITY_CHECKBOX_XPATH)
    private WebElement availabilityCheckbox;

    @FindBy(xpath = FREE_CANCELLATION_CHECKBOX_XPATH)
    private WebElement freeCancellationCheckbox;

    @FindBy(xpath = SORT_BY_PRICE_XPATH)
    private WebElement sortByPriceButton;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public void openPage() throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }

    public int getNumberOfResults() {
        waitLoadEnd();
        String[] resultArr = searchResultText.getText().split(": ")[1].split(" ");
        int resultNumber = 0;
        for (String word : resultArr) {
            try {
                resultNumber = Integer.parseInt(word);
                break;
            } catch (NumberFormatException e) {
                continue;
            }
        }
        return resultNumber;
    }

    public List<String> getOnePageListOfHotels() {
        waitLoadEnd();
        List<WebElement> results = driver.findElements(By.xpath(LIST_OF_HOTELS_NAMES_XPATH));

        List<String> hotelsNames = new ArrayList<>();
        for (WebElement result : results) {
            hotelsNames.add(result.getText());
        }
        return hotelsNames;
    }

    public List<Integer> getOnePageListOfPricesInUsd() {
        waitLoadEnd();
        List<WebElement> resultsFull = driver.findElements(By.xpath(LIST_PRICES_INFO_FOR_USD_XPATH));
        List<Integer> prices = new ArrayList<>();
        for (int i = 0; i < resultsFull.size(); i++) {
            if (i >= resultsFull.size() - 5) {
                if (!resultsFull.get(i).findElements(By.xpath(MARK_HOTELS_RECOMMENDED_OUTSIDE_ARIA_XPATH)).isEmpty()) {
                    break;
                }
            }
            String[] res = resultsFull.get(i).getText().split("\\$");
            prices.add(Integer.parseInt(res[1].replaceAll(",", "")));
        }
        return prices;
    }

    public boolean clickNextResultsPage() {
        List<WebElement> nextButton = driver.findElements(By.xpath(ARROW_NEXT_RESULTS_PAGE_XPATH));
        if (!nextButton.isEmpty()) {
            nextButton.get(0).click();
            return true;
        }
        return false;
    }

    public Apartment getFirstSearchResult() {
        waitLoadEnd();
        WebElement result = driver.findElement(By.xpath(LIST_OF_HOTELS_RESULTS_FULL_ELEMENTS));
        Apartment apartment = new Apartment();

        String name = result.findElement(By.xpath(LIST_OF_HOTELS_NAMES_WITH_RECOMMENDED_OUTSIDE_XPATH)).getText();
        apartment.setName(name);

        String rating = result.findElement(By.xpath(RATING_TITLES_XPATH)).getText();
        apartment.setRating(rating);

        double score = Double.parseDouble(result.findElement(By.xpath(SCORE_TITLES_XPATH)).getText().replaceAll(",", "."));
        apartment.setScore(score);

        String locationFull = result.findElement(By.xpath(LOCATION_XPATH)).getText();
        String[] location = locationFull.split("–");
        apartment.setLocation(location[0]);

        return apartment;
    }

    public int getFirstResultPriceInUsd(){
        waitLoadEnd();
        WebElement firstResultLineWithPrice = driver.findElement(By.xpath(LIST_PRICES_INFO_FOR_USD_XPATH));
        String[] priceResult = firstResultLineWithPrice.getText().split("\\$");
        return Integer.parseInt(priceResult[1].replaceAll(",","."));
    }

    public int getFirstResultPriceInEuro(){
        waitLoadEnd();
        WebElement firstResultLineWithPrice = driver.findElement(By.xpath(LIST_PRICES_INFO_FOR_EURO_XPATH));
        String[] priceResult = firstResultLineWithPrice.getText().split("€");
        return Integer.parseInt(priceResult[1].replaceAll(" ", "").replaceAll(",", ""));
    }


    public boolean openFirstResultApartmentDetailsPage(){
        List<WebElement> buttonList = driver.findElements(By.xpath(HOTEL_FULL_INFO_BUTTON_XPATH));
        if(!buttonList.isEmpty()){
            buttonList.get(0).click();
            return true;
        }
        return false;
    }

    public void selectAvailabilityCheckbox() {
        selectCheckbox(availabilityCheckbox);
    }

    public boolean selectBudgetGroup(int budgetGroupNumber) {
        String fullXpath = String.format(BUDGET_OPTIONS_XPATH, budgetGroupNumber);
        List<WebElement> budgetCheckBox = driver.findElements(By.xpath(fullXpath));
        if (!budgetCheckBox.isEmpty()) {
            budgetCheckBox.get(0).click();
            return true;
        }
        return false;
    }

    public boolean selectStarRating(int starRating) {
        String fullXpath = String.format(STAR_RATING_OPTIONS_XPATH, starRating);
        List<WebElement> starRatingCheckBox = driver.findElements(By.xpath(fullXpath));
        if (!starRatingCheckBox.isEmpty()) {
            starRatingCheckBox.get(0).click();
            return true;
        }
        return false;
    }

    public void selectFreeCancellation() {
        selectCheckbox(freeCancellationCheckbox);
    }

    public void sortLowestPriceFirst() {
        sortByPriceButton.click();
    }

    private void selectCheckbox(WebElement checkbox) {
        if (!Boolean.parseBoolean(checkbox.getAttribute("aria-checked"))) {
            checkbox.click();
        }
    }

    private void deselectCheckbox(WebElement checkbox) {
        if (Boolean.parseBoolean(checkbox.getAttribute("aria-checked"))) {
            checkbox.click();
        }
    }

    private void waitLoadEnd() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOAD_WAIT_SPINNER_XPATH)));
    }

}
