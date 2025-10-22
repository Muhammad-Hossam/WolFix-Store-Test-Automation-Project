package com.graduationproject.pages;

import com.graduationproject.drivers.UIDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class LoginPage {
    private UIDriver driver;


    public LoginPage(UIDriver driver) {
        this.driver = driver;
    }

    //locators
    private final By emailField = By.cssSelector("[formcontrolname=\"email\"]");
    private final By passwordField = By.cssSelector("[type=\"password\"]");
    private final By loginButton = By.xpath("//button[.=\"Login\"]");

    private final By invalidLoginMessage = By.xpath("//span[.=' Incorrect email or password ']");
    private final By welcomeMessage = By.id("toast-container");
    private final By userLabel = By.xpath("//button[.=' ITI ']");


    //actions
    @Step("Enter email in login form")
    public LoginPage enterEmail(String email) {
        driver.element().type(emailField, email);
        return this;
    }

    @Step("Enter password in login form")
    public LoginPage enterPassword(String password) {
        driver.element().type(passwordField, password);
        return this;
    }

    @Step("Click on login button")
    public LoginPage clickOnLoginButton() {
        driver.element().click(loginButton);
        return this;
    }


    //validations
    @Step("Verify that the user is logged in successfully")
    public LoginPage verifyUserLoggedInSuccessfully() {
        driver.validation().isElementVisible(welcomeMessage)
                .isElementVisible(userLabel);
        return this;
    }

    @Step("Verify that the invalid credentials message is displayed")
    public LoginPage verifyLoginFailedWithInvalidCredentials() {
        driver.validation().isElementVisible(invalidLoginMessage);
        return this;
    }
}