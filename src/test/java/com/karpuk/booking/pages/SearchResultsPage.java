package com.karpuk.booking.pages;

import com.karpuk.booking.entity.Apartment;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

    private static final String LIST_OF_HOTELS_NAMES_WITH_RECOMMENDED_OUTSIDE_XPATH = "//span[contains(@class,'sr-hotel__name')]";
    private static final String LIST_PRICES_INFO_FOR_USD_XPATH = "//*[contains(@class,'price')]//b[contains(text(),'US')] |  //div[contains(@class, 'totalPrice')][contains(text(),'US')] | //b[@class='sr_gs_price_total']";
    private static final String LIST_PRICES_INFO_FOR_EURO_XPATH = "//*[contains(@class,'price')]//b[contains(text(),'\u20ac')] |  //div[contains(@class, 'totalPrice')][contains(text(),'\u20ac')] | //b[@class='sr_gs_price_total']";

    private static final String RATING_TITLES_XPATH = "//span[@class='review-score-widget__body']/span[@class='review-score-widget__text']";
    private static final String SCORE_TITLES_XPATH = "//span[@class='review-score-badge']";
    private static final String LOCATION_XPATH = "//a[contains(@class,'district_link visited_link')]";

    private static final String LOAD_WAIT_SPINNER_XPATH = "//*[@id='b2searchresultsPage']//div[@class='sr-usp-overlay__container']";

    private static final String BUDGET_OPTIONS_XPATH = "//div[@id='filter_price']/div[contains(@class,'filteroptions')]/a[@data-id='pri-%d']";
    private static final String STAR_RATING_OPTIONS_XPATH = "//div[@id='filter_class']/div[contains(@class,'filteroptions')]/a[@data-id='class-%d']";

    @FindBy(xpath = "//div[@data-block-id='heading']//div[@role='heading']/*")
    private WebElement searchResultText;

    @FindBy(xpath = "//a[@data-id='oos-1']")
    private WebElement availabilityCheckbox;

    @FindBy(xpath = "//a[@data-id='fc-2']")
    private WebElement freeCancellationCheckbox;

    @FindBy(xpath = "//li[contains(@class,'sort_price')]/a")
    private WebElement sortByPriceButton;

    @FindBy(xpath = "//div[@id='hotellist_inner']//div[contains(@class,'sr_item_content')]")
    private List<WebElement> resultsFullElements;

    @FindBy(xpath = "//span[contains(@class,'bicon-direction-arrow')]/following-sibling::span/../../preceding-sibling::*[contains(@class,'sr-hotel__title')]")
    private List<WebElement> resultsNames;

    @FindBy(xpath = "//a[contains(@class,'b-button_primary')]")
    private List<WebElement> buttonsFullInfo;

    @FindBy(xpath = "//div[contains(@class,'distfromdest--highlight')]")
    private List<WebElement> resultsOutside;

    @FindBy(xpath = LIST_PRICES_INFO_FOR_USD_XPATH)
    private List<WebElement> allPricesInUsd;

    @FindBy(xpath = LIST_PRICES_INFO_FOR_EURO_XPATH)
    private List<WebElement> allPricesInEuro;

    @FindBy(xpath = "//li[contains(@class,'bui-pagination__next-arrow')]/a")
    private List<WebElement> nextPageArrow;

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

        List<String> hotelsNames = new ArrayList<>();
        for (WebElement result : resultsNames) {
            hotelsNames.add(result.getText());
        }
        return hotelsNames;
    }

    public List<Integer> getOnePageListOfPricesInUsd() {
        waitLoadEnd();
        int numberOfOutsideAria = countApartmentOutsideArea();

        List<Integer> prices = new ArrayList<>();
        for (int i = 0; i < allPricesInUsd.size() - numberOfOutsideAria; i++) {
            String[] res = allPricesInUsd.get(i).getText().split("\\$");
            prices.add(Integer.parseInt(res[1].replaceAll("\\D+", "")));
        }
        return prices;
    }

    public boolean clickNextResultsPage() {
        if (!nextPageArrow.isEmpty()) {
            nextPageArrow.get(0).click();
            return true;
        }
        return false;
    }

    public Apartment getFirstSearchResult() {
        waitLoadEnd();
        Apartment apartment = new Apartment();

        String name = resultsFullElements.get(0).findElement(By.xpath(LIST_OF_HOTELS_NAMES_WITH_RECOMMENDED_OUTSIDE_XPATH)).getText();
        apartment.setName(name);

        String rating = resultsFullElements.get(0).findElement(By.xpath(RATING_TITLES_XPATH)).getText();
        apartment.setRating(rating);

        double score = Double.parseDouble(resultsFullElements.get(0).findElement(By.xpath(SCORE_TITLES_XPATH)).getText().replaceAll(",", "."));
        apartment.setScore(score);

        String locationFull = resultsFullElements.get(0).findElement(By.xpath(LOCATION_XPATH)).getText();
        String[] location = locationFull.split("–");
        apartment.setLocation(location[0]);

        return apartment;
    }

    public int getFirstResultPriceInUsd(){
        waitLoadEnd();
        WebElement firstResultLineWithPrice = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(LIST_PRICES_INFO_FOR_USD_XPATH)));
        String[] priceResult = firstResultLineWithPrice.getText().split("\\$");
        return Integer.parseInt(priceResult[1].replaceAll("\\D+", ""));
    }

    public int getFirstResultPriceInEuro(){
        waitLoadEnd();
        WebElement firstResultLineWithPrice = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(LIST_PRICES_INFO_FOR_EURO_XPATH)));
        String[] priceResult = firstResultLineWithPrice.getText().split("\u20ac");
        return Integer.parseInt(priceResult[1].replaceAll("\\D+", ""));
    }


    public boolean openFirstResultApartmentDetailsPage(){
        waitLoadEnd();
        if(!buttonsFullInfo.isEmpty()){
            buttonsFullInfo.get(0).click();
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
            waitAndClickCheckbox(budgetCheckBox.get(0));
            return true;
        }
        return false;
    }

    public boolean selectStarRating(int starRating) {
        String fullXpath = String.format(STAR_RATING_OPTIONS_XPATH, starRating);
        List<WebElement> starRatingCheckBox = driver.findElements(By.xpath(fullXpath));
        if (!starRatingCheckBox.isEmpty()) {
            waitAndClickCheckbox(starRatingCheckBox.get(0));
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

    private int countApartmentOutsideArea(){
        return resultsOutside.size();
    }

    private void waitLoadEnd() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOAD_WAIT_SPINNER_XPATH)));
    }

    private void waitAndClickCheckbox(WebElement checkbox) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(checkbox));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
    }

}
