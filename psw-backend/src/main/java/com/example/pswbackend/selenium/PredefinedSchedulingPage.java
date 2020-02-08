package com.example.pswbackend.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Djordje Batic
 */
public class PredefinedSchedulingPage {
    private WebDriver driver;

    @FindBy(xpath = "//*[@id=\"sheduleButton\"]")
    private WebElement schedule;


    public void enshurePredefinedButtonExists() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(schedule));
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getScheduleButton() {
        return schedule;
    }

    public void setScheduleButton(WebElement visitClinic) {
        this.schedule = visitClinic;
    }

    public PredefinedSchedulingPage(WebDriver driver) {
        super();
        this.driver = driver;
    }

    public PredefinedSchedulingPage() {
        super();
    }
}
