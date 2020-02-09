package com.example.pswbackend.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.springframework.test.annotation.Rollback;

import static org.junit.Assert.assertEquals;

/**
 * @author Djordje Batic
 */
public class ScheduleRegularAppointment {

    private WebDriver browser;

    private LoginPage loginPage;

    private PatientPage patientPage;

    private ClinicsPage clinicsPage;

    private ClinicSelectedPage clinicSelectedPage;

    private PredefinedSchedulingPage predefinedSchedulingPage;

    private SchedulingFormPage schedulingFormPage;

    @Before
    public void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        browser = new ChromeDriver();
        browser.manage().window().maximize();
        browser.navigate().to("http://localhost:3000/login");
        loginPage = PageFactory.initElements(browser, LoginPage.class);
        patientPage = PageFactory.initElements(browser, PatientPage.class);
        clinicsPage =  PageFactory.initElements(browser, ClinicsPage.class);
        clinicSelectedPage = PageFactory.initElements(browser, ClinicSelectedPage.class);
        predefinedSchedulingPage = PageFactory.initElements(browser, PredefinedSchedulingPage.class);
        schedulingFormPage = PageFactory.initElements(browser, SchedulingFormPage.class);

    }

    @Rollback
    @Test
    public void ScheduleRegularAppointmentPass() throws InterruptedException {
        loginPage.login("patijent@gmail.com", "patijent");

        patientPage.ensureClinicButtonExists();
        assertEquals("http://localhost:3000/patient", browser.getCurrentUrl());
        patientPage.getClinics().click();

        assertEquals("http://localhost:3000/clinics", browser.getCurrentUrl());

        clinicsPage.getDateOption().click();
        clinicsPage.getSelectOption().selectByVisibleText("Kardiologija");
        clinicsPage.getFilterButton().click();
        clinicsPage.ensureAvailableDoctorsButtonVisible();
        clinicsPage.getAvailableDoctorsButton().click();

        clinicsPage.ensurePreferedTimeButtonVisible();
        clinicsPage.getPreferedTimeButton().click();

        assertEquals("http://localhost:3000/scheduling-form/4/1/08:45%2010.02.2020/1/Kardiologija", browser.getCurrentUrl());
        schedulingFormPage.ensureScheduleButtonDisplayed();
        schedulingFormPage.getScheduleButton().click();

        schedulingFormPage.ensureSuccessMessageDisplayed();

    }

    @Rollback
    @Test
    public void ScheduleRegularAppointment_NoDateSelected() throws InterruptedException {
        loginPage.login("patijent@gmail.com", "patijent");

        patientPage.ensureClinicButtonExists();
        assertEquals("http://localhost:3000/patient", browser.getCurrentUrl());
        patientPage.getClinics().click();

        assertEquals("http://localhost:3000/clinics", browser.getCurrentUrl());

        clinicsPage.getSelectOption().selectByVisibleText("Kardiologija");
        clinicsPage.getFilterButton().click();

        assertEquals(browser.findElement(By.id("date")).getAttribute("validationMessage"), "Please fill out this field.");

    }

    @Rollback
    @Test
    public void ScheduleRegularAppointment_No_Appointment_Type_Selected() throws InterruptedException {
        loginPage.login("patijent@gmail.com", "patijent");

        patientPage.ensureClinicButtonExists();
        assertEquals("http://localhost:3000/patient", browser.getCurrentUrl());
        patientPage.getClinics().click();

        assertEquals("http://localhost:3000/clinics", browser.getCurrentUrl());

        clinicsPage.getDateOption().click();
        clinicsPage.getFilterButton().click();

        assertEquals(browser.findElement(By.id("type")).getAttribute("validationMessage"), "Please select an item in the list.");
    }

    @After
    public void tearDown() {
        browser.close();
    }
}
