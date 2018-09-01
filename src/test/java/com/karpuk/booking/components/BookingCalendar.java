package com.karpuk.booking.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class BookingCalendar {

    private static final String FURTHER_MONTH_XPATH = "//*[contains(@class,'c2-button-further')]";
    private static final String DAYS_XPATH = "//table[@class='c2-month-table']/descendant::th[contains(text(), '%s')]/ancestor::table/tbody//span";
    private static final String MONTHS_XPATH = "//table[@class='c2-month-table']/descendant::th[contains(text(), '%s')]";

    @FindBy(xpath = FURTHER_MONTH_XPATH)
    private List<WebElement> furtherMonthLinks;

    @FindBy(xpath = MONTHS_XPATH)
    private List<WebElement> mothsAndYearTitle;

    private WebDriver driver;

    public BookingCalendar(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setDates(LocalDate checkInDate, LocalDate checkOutDate) {
        selectCheckInMonth(checkInDate);
        selectDay(checkInDate);
        selectCheckOutMonth(checkInDate, checkOutDate);
        selectDay(checkOutDate);
    }

    private void selectCheckInMonth(LocalDate checkInDate) {
        int currentMonth = LocalDate.now().getMonthValue();
        if(checkInDate.getYear() == LocalDate.now().getYear()){
            changeMonth(currentMonth, checkInDate);
        } else if(checkInDate.getYear() > LocalDate.now().getYear()){
            System.out.println(currentMonth);

            for(int i = currentMonth; i <= 12; i++){
                System.out.println("i " + i);
                furtherMonthLinks.get(0).click();
            }
            currentMonth = 1;
            changeMonth(currentMonth, checkInDate);
        }
    }

    private void changeMonth(int currentMonth, LocalDate checkInDate){
        while (currentMonth != checkInDate.getMonthValue()) {
            furtherMonthLinks.get(0).click();
            currentMonth++;
        }
    }

    private void selectCheckOutMonth(LocalDate checkInDate, LocalDate checkOutDate) {
        int currentMonth = checkInDate.getMonthValue();
        while (currentMonth != checkOutDate.getMonthValue()) {
            furtherMonthLinks.get(1).click();
            currentMonth++;
        }
    }

    private void selectDay(LocalDate date) {
        String month = date.format(DateTimeFormatter.ofPattern("MMMM", Locale.ENGLISH));
        String fullDaysXpath = String.format(DAYS_XPATH, month);
        List<WebElement> days = driver.findElements(By.xpath(fullDaysXpath));

        for (WebElement day : days) {
            String dayNumber = day.getText().replaceAll(" ", "");
            if (!dayNumber.isEmpty()) {
                if (Integer.parseInt(dayNumber) == date.getDayOfMonth()) {
                    day.click();
                    break;
                }
            }
        }
    }

    private void selectYear(LocalDate checkInDate){
        System.out.println("chec " + checkInDate.getYear());
        System.out.println("nnn " + LocalDate.now().getYear());
        if(checkInDate.getYear() > LocalDate.now().getYear()){
            int currentMonth = LocalDate.now().getMonthValue();
            System.out.println(currentMonth);

            for(int i = currentMonth; i < 13; i++){
                System.out.println("i " + i);
                furtherMonthLinks.get(0).click();
            }
        }
    }

}

