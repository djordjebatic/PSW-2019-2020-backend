package com.example.pswbackend.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SchedulingFormPage {

    private WebDriver driver;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[1]/div[2]/div/div[1]/div/div/div/div/button")
    private WebElement scheduleButton;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/span/div/div/div")
    private WebElement successMessage;

    public SchedulingFormPage() {
    }

    public SchedulingFormPage(WebDriver driver) {
        this.driver = driver;
    }

    public void ensureScheduleButtonDisplayed() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(scheduleButton));
    }

    public void ensureSuccessMessageDisplayed() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(successMessage));
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getScheduleButton() {
        return scheduleButton;
    }

    public void setScheduleButton(WebElement scheduleButton) {
        this.scheduleButton = scheduleButton;
    }

    public WebElement getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(WebElement successMessage) {
        this.successMessage = successMessage;
    }
}
