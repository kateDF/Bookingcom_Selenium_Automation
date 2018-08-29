package com.karpuk.booking.pages;

import org.openqa.selenium.WebDriver;

import javax.naming.OperationNotSupportedException;

public abstract class AbstractPage {

    protected WebDriver driver;

    public abstract void openPage() throws OperationNotSupportedException;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

}
