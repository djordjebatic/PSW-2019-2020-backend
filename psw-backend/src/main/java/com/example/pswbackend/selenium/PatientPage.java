package com.example.pswbackend.selenium;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Djordje Batic
 */
public class PatientPage {

    private WebDriver driver;

    @FindBy(id = "displayClinics")
    private WebElement displayClinics;

    public void ensureClinicButtonExists() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(displayClinics));
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getClinics() {
        return displayClinics;
    }

    public void setClinics(WebElement displayClinics) {
        this.displayClinics = displayClinics;
    }

    public PatientPage(WebDriver driver) {
        super();
        this.driver = driver;
    }

    public PatientPage() {
        super();
    }
}
