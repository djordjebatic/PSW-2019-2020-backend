package com.example.pswbackend.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Djordje Batic
 */
public class ClinicsPage {

    private WebDriver driver;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[1]/div[2]/div[2]/div/div[1]/div[3]/div[1]/div/div[4]/div/button")
    private WebElement visitClinic;

    @FindBy(xpath = "//*[@id=\"type\"]")
    private Select selectOption;

    @FindBy(xpath = "//*[@id=\"date\"]")
    private WebElement dateOption;

    @FindBy(id = "filterButton")
    private WebElement filterButton;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[1]/div[2]/div[2]/div/div[1]/div[3]/div[1]/div/div[5]/div/button")
    private WebElement availableDoctorsButton;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[1]/div[2]/div/div[2]/div/div[1]/div[3]/div[1]/div/div[5]/div/button[2]")
    private WebElement preferedTimeButton;

    public void ensureVisitButtonIsDisplayed() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(visitClinic));
    }

    public void ensureAvailableDoctorsButtonVisible() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(availableDoctorsButton));
    }

    public void ensurePreferedTimeButtonVisible() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(preferedTimeButton));
    }

    /*public void ensureOptionIsVisible() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(selectOption));
    }*/

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getVisitClinic() {
        return visitClinic;
    }

    public Select getSelectOption() {

        Select option = new Select(this.driver.findElement(By.id("type")));
        return option;
    }

    public void setSelectOption(Select selectOption) {
        this.selectOption = selectOption;
    }

    public void setVisitClinic(WebElement visitClinic) {
        this.visitClinic = visitClinic;
    }
    public ClinicsPage(WebDriver driver) {
        super();
        this.driver = driver;
    }

    public WebElement getDateOption() {
        dateOption.sendKeys("1002");
        dateOption.sendKeys(Keys.TAB);
        dateOption.sendKeys("2020");
        return dateOption;
    }

    public void setDateOption(WebElement dateOption) {
        this.dateOption = dateOption;
    }

    public WebElement getFilterButton() {
        return filterButton;
    }

    public void setFilterButton(WebElement filterButton) {
        this.filterButton = filterButton;
    }

    public WebElement getAvailableDoctorsButton() {
        return availableDoctorsButton;
    }

    public void setAvailableDoctorsButton(WebElement availableDoctorsButton) {
        this.availableDoctorsButton = availableDoctorsButton;
    }

    public WebElement getPreferedTimeButton() {
        return preferedTimeButton;
    }

    public void setPreferedTimeButton(WebElement preferedTimeButton) {
        this.preferedTimeButton = preferedTimeButton;
    }

    public ClinicsPage() {
        super();
    }
}
