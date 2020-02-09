package com.example.pswbackend.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriver driver;

    @FindBy(id = "confirmButton")
    private WebElement confirmButton;

    @FindBy(id = "email")
    private WebElement loginEmail;

    @FindBy(id = "password")
    private WebElement loginPassword;

    public LoginPage() {
    }

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void ensureIsDisplayedEmail() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(loginEmail));
    }

    public void ensureIsNotVisibleModal() {
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("confirmButton")));
    }

    public void ensureIsDisplayedPassword() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(loginPassword));
    }

    public void ensureIsDisplayedButton() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(confirmButton));
    }


    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getLoginEmail() {
        return loginEmail;
    }

    public void setLoginEmail(WebElement loginEmail) {
        this.loginEmail = loginEmail;
    }

    public WebElement getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(WebElement loginPassword) {
        this.loginPassword = loginPassword;
    }

    public WebElement getConfirmButton() {
        return confirmButton;
    }

    public void setConfirmButton(WebElement confirmButton) {
        this.confirmButton = confirmButton;
    }

    public void login(String email, String password) {
        ensureIsDisplayedEmail();
        loginEmail.sendKeys(email);
        loginPassword.sendKeys(password);
        confirmButton.click();
        ensureIsNotVisibleModal();
    }
}