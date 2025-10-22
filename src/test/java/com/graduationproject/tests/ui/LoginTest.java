package com.graduationproject.tests.ui;

import com.graduationproject.drivers.UIDriver;
import com.graduationproject.pages.components.NavigationBarComponent;
import com.graduationproject.tests.BaseTest;
import com.graduationproject.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
@Epic("GraduationProject - Login Page")
@Feature("UI Login Page")
@Story("Login Page Sections")
@Severity(SeverityLevel.CRITICAL)
@Owner("Muhammad-Hossam")
public class LoginTest extends BaseTest {


    //test
    @Test(description = "verify that the user can login with valid credentials")
    public void loginWithValidCredentials(){
        new NavigationBarComponent(driver)
                .navigate()
                .clickOnLoginButton()
                .enterEmail(testData.getJsonData("email"))
                .enterPassword(testData.getJsonData("password"))
                .clickOnLoginButton()
                .verifyUserLoggedInSuccessfully();
    }


    @Test(description = "verify that the user cannot login with unregistered email")
    public void loginWithUnregisteredEmail(){
        new NavigationBarComponent(driver)
                .navigate()
                .clickOnLoginButton()
                .enterEmail(testData.getJsonData("unregisteredmail"))
                .enterPassword(testData.getJsonData("password"))
                .clickOnLoginButton()
                .verifyLoginFailedWithInvalidCredentials();
    }

    @Test(description = "verify that the user cannot login with invalid password")
    public void loginWithInvalidPassword(){
        new NavigationBarComponent(driver)
                .navigate()
                .clickOnLoginButton()
                .enterEmail(testData.getJsonData("email"))
                .enterPassword(testData.getJsonData("invalidpassword"))
                .clickOnLoginButton()
                .verifyLoginFailedWithInvalidCredentials();
    }

    //configurations
    @BeforeClass
    public void beforeClass(){
        testData=new JsonReader("login-data");
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
