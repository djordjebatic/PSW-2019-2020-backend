package com.example.pswbackend.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Djordje Batic
 */
public class ClinicSelectedPage {
    private WebDriver driver;

    @FindBy(id = "predefinedButton")
    private WebElement predefinedButton;


    public void ensurePredefinedtButtonIsDisplayed() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(predefinedButton));
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getPredefinedButton() {
        return predefinedButton;
    }

    public void setPredefinedButton(WebElement predefinedButton) {
        this.predefinedButton = predefinedButton;
    }
    public ClinicSelectedPage(WebDriver driver) {
        super();
        this.driver = driver;
    }

    public ClinicSelectedPage() {
        super();
    }
}
