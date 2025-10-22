package com.graduationproject.pages;

import com.graduationproject.drivers.UIDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class E2E {
    private UIDriver driver;
    public E2E(UIDriver driver) {
        this.driver = driver;
    }

    //locators
    private final By orderConfirmedMessage=By.cssSelector("div>h1");
    private final By paymentStatus=By.xpath("//p[text()=' Completed']");
    private final By orderStatus=By.cssSelector("[class=\"order-status\"]");
    private final By orderCreatedMessage=By.cssSelector("[role=\"alert\"]");


    //validations
    @Step("Verify order created message")
    public E2E VerifyOrderCreatedMessage(String expectedMessage){
        driver.verification().Equals(driver.element().getText(orderCreatedMessage),expectedMessage,"order creation failed");
        return this;
    }

    @Step("Verify order confirmed message")
    public E2E VerifyOrderConfirmedMessage(String expectedMessage){
        driver.verification().Equals(driver.element().getText(orderConfirmedMessage),expectedMessage,"order confirmation failed");
        return this;
    }

    @Step("Verify order status")
    public E2E VerifyOrderStatus(String expectedMessage){
        driver.verification().Equals(driver.element().getText(orderStatus),expectedMessage,"order status unclear");
        return this;
    }

    @Step("Verify order payment status")
    public E2E VerifyOrderPaymentStatus(String expectedMessage){
        driver.verification().Equals(driver.element().getText(paymentStatus),expectedMessage,"order payment status unclear");
        return this;
    }

}
