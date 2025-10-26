package com.graduationproject.pages;

import com.graduationproject.drivers.GUIDriver;
import com.graduationproject.engine.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class CheckoutPage {
    private GUIDriver driver;
    private final String checkooutPage= (PropertyReader.getProperty("baseurl")+"/checkout");
    public CheckoutPage(GUIDriver driver) {
        this.driver = driver;
    }

    //locators
    private final By streetAddress=By.cssSelector("[formcontrolname=\"address\"]");
    private final By city=By.cssSelector("[formcontrolname=\"city\"]");
    private final By stateDropdown=By.id("state");
    private final By zipCode=By.cssSelector("[formcontrolname=\"zipCode\"]");
    private final By phoneNumber=By.cssSelector("[formcontrolname=\"phone\"]");

    private final By cardNumberFeld=By.cssSelector("[formcontrolname=\"cardNumber\"]");
    private final By expiryMonthField=By.cssSelector("[formcontrolname=\"expiryMonth\"]");
    private final By expiryYearField=By.cssSelector("[formcontrolname=\"expiryYear\"]");
    private final By cvvField=By.cssSelector("[formcontrolname=\"cvv\"]");
    private final By nameOnCardField=By.cssSelector("[formcontrolname=\"cardName\"]");

    private final By placeOrderButton=By.cssSelector("[class=\"btn-checkout-primary\"]");

    private final By checkoutTile= By.cssSelector("[class=\"checkout-title\"]");
    private final By itemName= By.cssSelector("[class=\"checkout-item-name\"]");
    private final By totalPrice= By.xpath("(//div//span[.='$174.30'])[2]");
    private final By quantity= By.cssSelector("[class=\"checkout-item-quantity\"]");

    private final By invalidExpiryYearMessage=By.xpath("//span[text()='Invalid year']");
    private final By invalidExpiryMonthMessage=By.xpath("//span[text()='Invalid month']");
    private final By invalidCardNumberMessage=By.xpath("//span[text()='Invalid card number']");
    private final By invalidCVVMessage=By.xpath("//span//span[.='Invalid CVV']");

    //actions
    @Step("Navigate to checkoutPage")
    public CheckoutPage navigate(){
        driver.browser().navigateTo(checkooutPage);
        return this;
    }

    @Step("Filling shipping information form")
    public CheckoutPage fillShippingInformation(String streetAddress, String cityName, String stateName, String zipCode, String phoneNumber){
        driver.element().type(this.streetAddress,streetAddress)
                        .type(this.city,cityName)
                        .click(this.stateDropdown)
                        .click(By.xpath("//li//span[.='"+stateName+"']"))
                        .type(this.zipCode,zipCode)
                        .type(this.phoneNumber,phoneNumber);
        return this;
    }

    @Step("Filling payment information form")
    public CheckoutPage fillPaymentInformation(String cardNum, String mm, String yy, String cvv, String nameOnCard){
        driver.element().type(this.cardNumberFeld,cardNum)
                        .type(this.expiryMonthField,mm)
                        .type(this.expiryYearField,yy)
                        .type(this.cvvField,cvv)
                        .type(this.nameOnCardField,nameOnCard);
        return this;
    }

    @Step("Clicking on Place Order button")
    public E2E clickPlaceOrderButton(){
        driver.element().click(placeOrderButton);
        return new E2E(driver);
    }



    //validations
    @Step("Validating Checkout Page is displayed")
    public CheckoutPage validateCheckoutPageDisplayed(String title){
        driver.validation().Equals(driver.browser().getCurrentUrl(),checkooutPage,"incorrect Checkout Page URL")
                           .Equals(driver.element().getText(checkoutTile),title,"Checkout Page is not displayed");
        return this;
    }

    @Step("Validating order summery details")
    public CheckoutPage validateOrderSummeryDetails(String expectedItemName, String expectedQuantity, String expectedTotalPrice){
        driver.validation().Equals(driver.element().getText(itemName),expectedItemName,"Item name mismatch in order summery")
                           .Equals(driver.element().getText(quantity),expectedQuantity,"Item quantity mismatch in order summery")
                           .Equals(driver.element().getText(totalPrice),expectedTotalPrice,"Total price mismatch in order summery");
        return this;
    }

    @Step("Verify yy invalid message is displayed")
    public CheckoutPage verifyInvalidExpiryYearMessageDisplayed(String expectedMessage){
        driver.validation().Equals(driver.element().getText(invalidExpiryYearMessage),expectedMessage,"Invalid expiry year message is not displayed");
        return this;
    }
    @Step("Verify mm invalid message is displayed")
    public CheckoutPage verifyInvalidExpiryMonthMessageDisplayed(String expectedMessage){
        driver.validation().Equals(driver.element().getText(invalidExpiryMonthMessage),expectedMessage,"Invalid expiry month message is not displayed");
        return this;
    }
    @Step("Verify card number invalid message is displayed")
    public CheckoutPage verifyInvalidCardNumberMessageDisplayed(String expectedMessage){
        driver.verification().Equals(driver.element().getText(invalidCardNumberMessage),expectedMessage,"Invalid card number message is not displayed");
        return this;
    }

    @Step("Verify CVV invalid message is displayed")
    public CheckoutPage verifyInvalidCVVMessageDisplayed(String expectedMessage){
        driver.validation().Equals(driver.element().getText(invalidCVVMessage),expectedMessage,"Invalid CVV message is not displayed");
        return this;
    }
}
