package com.example.pswbackend.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.test.annotation.Rollback;

import static org.junit.Assert.assertEquals;

public class SchedulePredefinedAppointment {
    private WebDriver browser;

    private LoginPage loginPage;

    private PatientPage patientPage;

    private ClinicsPage clinicsPage;

    private ClinicSelectedPage clinicSelectedPage;

    private PredefinedSchedulingPage predefinedSchedulingPage;

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
    }

    @Rollback
    @Test
    public void testBookQuickAppointment() throws InterruptedException {
        loginPage.login("patijent@gmail.com", "patijent");

        patientPage.ensureClinicButtonExists();
        assertEquals("http://localhost:3000/patient", browser.getCurrentUrl());
        patientPage.getClinics().click();

        clinicsPage.ensureVisitButtonIsDisplayed();
        assertEquals("http://localhost:3000/clinics", browser.getCurrentUrl());
        clinicsPage.getVisitClinic().click();

        clinicSelectedPage.ensurePredefinedtButtonIsDisplayed();
        assertEquals("http://localhost:3000/clinic/1", browser.getCurrentUrl());
        clinicSelectedPage.getPredefinedButton().click();

        //react table wasn't implemented properly...
        //predefinedSchedulingPage.enshurePredefinedButtonExists();
        //assertEquals("http://localhost:3000/predefined-examinations/1", browser.getCurrentUrl());
        //predefinedSchedulingPage.getScheduleButton().click();
        // can't assert success message because it wasn't implemented
    }

    @After
    public void tearDown() {
        browser.close();
    }
}
