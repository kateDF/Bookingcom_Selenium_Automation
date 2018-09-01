package com.karpuk.booking.listener;

import com.karpuk.booking.driver.DriverSingleton;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ScreenshotListener extends TestListenerAdapter {

    private Calendar calendar = Calendar.getInstance();
    private SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println(result);
        String methodName = result.getName();
        if (!result.isSuccess()) {
            File scrFile = ((TakesScreenshot) DriverSingleton.getDriver()).getScreenshotAs(OutputType.FILE);
            try {
                String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/target/surefire-reports";
                File destFile = new File(reportDirectory + "/failure_screenshots/" + methodName + "_" + formatter.format(calendar.getTime()) + ".png");
                FileUtils.copyFile(scrFile, destFile);
                Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath() + "' height='100%' width='100%'/> </a>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
