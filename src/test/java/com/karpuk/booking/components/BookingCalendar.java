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

    @FindBy(xpath = FURTHER_MONTH_XPATH)
    private List<WebElement> furtherMonthLinks;

    private WebDriver driver;

    public BookingCalendar(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickNextMonth(String dateType) {
        if (dateType.equals("checkin")) {
            furtherMonthLinks.get(0).click();
        } else if (dateType.equals("checkout")) {
            furtherMonthLinks.get(1).click();
        }
    }

    public void setDate(LocalDate date, String dateType) {
        int currentYear = LocalDate.now().getYear();
        // TODO: check year
        int currentMonth = LocalDate.now().getMonthValue();
        while (currentMonth != date.getMonthValue()) {
            clickNextMonth(dateType);
            currentMonth++;
        }
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

}

