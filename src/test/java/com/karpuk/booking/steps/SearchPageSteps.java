package com.karpuk.booking.steps;

import com.karpuk.booking.entity.Apartment;
import com.karpuk.booking.pages.ApartmentDetailsPage;
import com.karpuk.booking.pages.SearchResultsPage;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class SearchPageSteps extends AbstractSteps {

    public SearchPageSteps(WebDriver driver) {
        super(driver);
    }

    public int getNumberOfSearchResults() {
        SearchResultsPage resultsPage = new SearchResultsPage(driver);
        return resultsPage.getNumberOfResults();
    }

    public void selectOnlyAvailable() {
        SearchResultsPage resultsPage = new SearchResultsPage(driver);
        resultsPage.selectAvailabilityCheckbox();
    }

    public boolean selectStarRating(int starRating) {
        SearchResultsPage resultsPage = new SearchResultsPage(driver);
        return resultsPage.selectStarRating(starRating);
    }

    public boolean selectBudget(int budgetGroupNumber) {
        SearchResultsPage resultsPage = new SearchResultsPage(driver);
        return resultsPage.selectBudgetGroup(budgetGroupNumber);
    }

    public void selectFreeCancellation(){
        SearchResultsPage resultsPage = new SearchResultsPage(driver);
        resultsPage.selectFreeCancellation();
    }

    public List<String> getResultsHotelsNames(){
        SearchResultsPage resultsPage = new SearchResultsPage(driver);
        return resultsPage.getOnePageListOfHotels();
    }

    public List<Integer> getResultsPricesInUsd(){
        setCurrency("USD");
        SearchResultsPage resultsPage = new SearchResultsPage(driver);
        return resultsPage.getOnePageListOfPricesInUsd();
    }

    public int getFirstResultPriceInUsd() {
        setCurrency("USD");
        SearchResultsPage resultsPage = new SearchResultsPage(driver);
        return resultsPage.getFirstResultPriceInUsd();
    }

    public int getFirstResultPriceInEuro() {
        setCurrency("EUR");
        SearchResultsPage resultsPage = new SearchResultsPage(driver);
        return resultsPage.getFirstResultPriceInEuro();
    }

    public boolean clickNextResultsPageArrow(){
        SearchResultsPage resultsPage = new SearchResultsPage(driver);
        return resultsPage.clickNextResultsPage();
    }

    public void sortLowestUsdPriceFirst(){
        SearchResultsPage resultsPage = new SearchResultsPage(driver);
        resultsPage.sortLowestPriceFirst();
    }

    public Apartment getFirstResultApartmentFromSearchPage(){
        SearchResultsPage resultsPage = new SearchResultsPage(driver);
        return resultsPage.getFirstSearchResult();
    }

    public ApartmentDetailsPage openAndSwitchToFirstResultDetailsPage(){
        SearchResultsPage resultsPage = new SearchResultsPage(driver);
        if(resultsPage.openFirstResultApartmentDetailsPage()){
            switchToWindowHandle();
            return new ApartmentDetailsPage(driver);
        }
        return null;
    }

    public void switchToWindowHandle(){
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
    }



}
