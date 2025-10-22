package com.graduationproject.tests.ui;

import com.graduationproject.drivers.UIDriver;
import com.graduationproject.pages.components.NavigationBarComponent;
import com.graduationproject.tests.BaseTest;
import com.graduationproject.utils.TimeManager;
import com.graduationproject.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("GraduationProject - Signup Page")
@Feature("UI Signup Page")
@Story("Signup Page Sections")
@Severity(SeverityLevel.CRITICAL)
@Owner("Muhammad-Hossam")
public class SignupTest extends BaseTest {
    private final String timestamp = TimeManager.getSimpleTimestamp();


    //Tests
    @Test(description = "Verify user can register successfully with valid data")
    public void registerSuccessfullyWithValidData(){
        new NavigationBarComponent(driver)
                .navigate()
                .clickOnSignupButton()
                .fillSignupForm(
                        testData.getJsonData("fullName"),
                        testData.getJsonData("email")+timestamp+"@test.com",
                        testData.getJsonData("password"),
                        testData.getJsonData("password"))
                .clickOnSignupButton()
                .verifyUserRegisteredSuccessfully();
    }

    @Test(description = "Verify user can't register with same email twice")
    public void invalidRegisterWithSameEmail(){
        new NavigationBarComponent(driver)
                .navigate()
                .clickOnSignupButton()
                .fillSignupForm(
                        testData.getJsonData("fullName"),
                        testData.getJsonData("existingEmail"),
                        testData.getJsonData("password"),
                        testData.getJsonData("password"))
                .clickOnSignupButton()
                .verifyEmailRegisteredBefore(testData.getJsonData("messages.emailAlreadyExists"));
    }

    @Test(description = "Verify user can't register with invalid name")
    public void invalidRegisterWithInvalidName(){
        new NavigationBarComponent(driver)
                .navigate()
                .clickOnSignupButton()
                .fillSignupForm(
                        testData.getJsonData("invalidFullName"),
                        testData.getJsonData("email")+timestamp+"@test.com",
                        testData.getJsonData("password"),
                        testData.getJsonData("password"))
                .verifyInvalidNameMessage(testData.getJsonData("messages.invalidName"));
    }

    @Test(description = "Verify user can't register with unmatched password")
    public void invalidRegisterWithUnmatchedPassword(){
        new NavigationBarComponent(driver)
                .navigate()
                .clickOnSignupButton()
                .fillSignupForm(
                        testData.getJsonData("fullName"),
                        testData.getJsonData("email")+timestamp+"@test.com",
                        testData.getJsonData("password"),
                        testData.getJsonData("invalidPassword"))
                .verifyPasswordNotMatchMessage(testData.getJsonData("messages.passwordNotMatch"));
    }


    //configurations
    @BeforeClass
    protected void beforeClass(){
        testData = new JsonReader("signup-data");
    }

    @BeforeMethod
    public void setUp(){
        driver=new UIDriver();
        new NavigationBarComponent(driver).navigate();

    }

    @AfterMethod
    public void tearDown(){
        driver.quitDriver();
    }
}
