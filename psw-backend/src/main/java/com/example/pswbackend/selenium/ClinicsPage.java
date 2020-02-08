package com.example.pswbackend.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Djordje Batic
 */
public class ClinicsPage {

    private WebDriver driver;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[1]/div[2]/div[2]/div/div[1]/div[3]/div[1]/div/div[4]/div/button")
    private WebElement visitClinic;


    public void ensureVisitButtonIsDisplayed() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(visitClinic));
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getVisitClinic() {
        return visitClinic;
    }

    public void setVisitClinic(WebElement visitClinic) {
        this.visitClinic = visitClinic;
    }
    public ClinicsPage(WebDriver driver) {
        super();
        this.driver = driver;
    }

    public ClinicsPage() {
        super();
    }
}
