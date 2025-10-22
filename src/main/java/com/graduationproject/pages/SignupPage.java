package com.graduationproject.pages;

import com.graduationproject.drivers.UIDriver;
import com.graduationproject.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class SignupPage {
    private UIDriver driver;
    private final String pageEndPoint = "/signup";

    public SignupPage(UIDriver driver) {
        this.driver = driver;
    }

    //locators
    private final By fullNameFeild= By.cssSelector("[formcontrolname=\"name\"]");
    private final By emailFeild= By.cssSelector("[formcontrolname=\"email\"]");
    private final By passwordFeild= By.id("password");
    private final By confirmPasswordFeild= By.id("confirmPassword");
    private final By registerButton= By.cssSelector("[type=\"submit\"]");

    private final By inValidNameMessage= By.xpath("//div//span[.='Please enter a valid name.']");
    private final By passwordNotMatchMessage= By.xpath("//div//span[.='Passwords do not match.']");
    private final By emailAlreadyExistMessage= By.xpath("//div//span[.=' Email already exists ']");

    private final By registerationMessage= By.id("toast-container");


    //actions
    @Step("Navigate to Signup Page")
    public SignupPage navigate()
    {
        driver.browser().navigateTo(PropertyReader.getProperty("baseurl") +pageEndPoint);
        return this;
    }

    @Step("Filling the Signup form")
    public SignupPage fillSignupForm(String fullName, String email, String password, String confirmPassword)
    {
        driver.element()
                .type(fullNameFeild,fullName)
                .type(emailFeild,email)
                .type(passwordFeild,password)
                .type(confirmPasswordFeild,confirmPassword);
        return this;
    }

    @Step("Click on Register Button")
    public SignupPage clickOnSignupButton()
    {
        driver.element().click(registerButton);
        return this;
    }

    //validations
    @Step("Verify that the user is registered successfully")
    public SignupPage verifyUserRegisteredSuccessfully(){
        driver.verification().isElementVisible(registerationMessage);
        return this;
    }

    @Step("Verify that the user cannot register using an invalid name")
    public SignupPage verifyInvalidNameMessage(String expectedMessage){
        driver.verification().Equals(driver.element().getText(inValidNameMessage),expectedMessage,"Invalid name registration passed");
        return this;
    }

    @Step("Verify that the user cannot register using non-matching passwords")
    public SignupPage verifyPasswordNotMatchMessage(String expectedMessage){
        driver.verification().Equals(driver.element().getText(passwordNotMatchMessage),expectedMessage,"Non-matching passwords registration passed");
        return this;
    }

    @Step("Verify that the user cannot register using an existing email")
    public SignupPage verifyEmailRegisteredBefore(String expectedMessage){
        driver.verification().Equals(driver.element().getText(emailAlreadyExistMessage),expectedMessage,"Existing email registration passed");
        return this;
    }

}
