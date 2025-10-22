package com.graduationproject.tests.ui;

import com.graduationproject.drivers.UIDriver;
import com.graduationproject.pages.CartPage;
import com.graduationproject.pages.CheckoutPage;
import com.graduationproject.pages.components.NavigationBarComponent;
import com.graduationproject.tests.BaseTest;
import com.graduationproject.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Epic("GraduationProject - Checkout Page")
@Feature("UI Checkout Page")
@Story("Checkout Page Sections")
@Severity(SeverityLevel.CRITICAL)
@Owner("Muhammad-Hossam")
public class CheckoutTest extends BaseTest {


    //scenario
    @Test(description = "login with valid user")
    public void login(){
        new NavigationBarComponent(driver)
                .navigate()
                .clickOnLoginButton()
                .enterEmail(testData.getJsonData("login-data.email"))
                .enterPassword(testData.getJsonData("login-data.password"))
                .clickOnLoginButton()
                .verifyUserLoggedInSuccessfully();
    }


    @Test( dependsOnMethods = "login", description = "add product to cart")
    public void addProduct(){
        new NavigationBarComponent(driver)
                .navigate()
                .clickOnProductsButton()
                .searchForProductWithName(testData.getJsonData("product.productName"))
                .validateSearchedProductInfo(testData.getJsonData("product.categoryName"),
                                             testData.getJsonData("product.companyName"))
                .clickOnAddToCartButton();
    }

    @Test(dependsOnMethods = {"addProduct","login"}, description = "Proceed to checkout process")
    public void proceedToCheckout(){
        new CartPage(driver)
                .navigate()
                .validateCartDetails(testData.getJsonData("cart.productName"),
                                     testData.getJsonData("cart.productPrice"),
                                     testData.getJsonData("cart.cartTotalPrice"),
                                     testData.getJsonData("cart.cartItemsCounter"))
                .clickOnCheckoutButton();
    }

    @Test(dependsOnMethods = {"proceedToCheckout","addProduct","login"}, description = "Validate checkout page Header and url")
    public void validateCheckoutPage(){
        new CheckoutPage(driver)
                .navigate()
                .validateCheckoutPageDisplayed(testData.getJsonData("title"))
                .fillShippingInformation(
                        testData.getJsonData("shippingInfo.streetAddress"),
                        testData.getJsonData("shippingInfo.cityName"),
                        testData.getJsonData("shippingInfo.stateName"),
                        testData.getJsonData("shippingInfo.zipCode"),
                        testData.getJsonData("shippingInfo.phoneNumber")
                );
    }

    @Test(dependsOnMethods = {"validateCheckoutPage","proceedToCheckout","addProduct","login"}, description = "Verify user can't proceed with invalid card number")
    public void verifyInvalidCardNumber(){
        new CheckoutPage(driver)
                .fillPaymentInformation(
                        testData.getJsonData("paymentInfo.invalidCardNumber"),
                        testData.getJsonData("paymentInfo.month"),
                        testData.getJsonData("paymentInfo.year"),
                        testData.getJsonData("paymentInfo.cvv"),
                        testData.getJsonData("paymentInfo.nameOnCard")
                ).verifyInvalidCardNumberMessageDisplayed(testData.getJsonData("messages.invalidCardNumber"));
    }
    @Test(dependsOnMethods = {"validateCheckoutPage","proceedToCheckout","addProduct","login"}, description = "Verify user can't proceed with invalid expiry month")
    public void verifyInvalidExpirationMonth(){
        new CheckoutPage(driver)
                .fillPaymentInformation(
                        testData.getJsonData("paymentInfo.cardNumber"),
                        testData.getJsonData("paymentInfo.invalidMonth"),
                        testData.getJsonData("paymentInfo.year"),
                        testData.getJsonData("paymentInfo.cvv"),
                        testData.getJsonData("paymentInfo.nameOnCard")
                ).verifyInvalidExpiryMonthMessageDisplayed(testData.getJsonData("messages.invalidMonth"));
    }
    @Test(dependsOnMethods = {"validateCheckoutPage","proceedToCheckout","addProduct","login"}, description = "Verify user can't proceed with invalid expiry year")
    public void verifyInvalidExpirationYear(){
        new CheckoutPage(driver)
                .fillPaymentInformation(
                        testData.getJsonData("paymentInfo.cardNumber"),
                        testData.getJsonData("paymentInfo.month"),
                        testData.getJsonData("paymentInfo.invalidYear"),
                        testData.getJsonData("paymentInfo.cvv"),
                        testData.getJsonData("paymentInfo.nameOnCard")
                ).verifyInvalidExpiryYearMessageDisplayed(testData.getJsonData("messages.invalidYear"));
    }
    @Test(dependsOnMethods = {"validateCheckoutPage","proceedToCheckout","addProduct","login"}, description = "Verify user can't proceed with invalid CVV")
    public void verifyInvalidExpirationCvv(){
        new CheckoutPage(driver)
                .fillPaymentInformation(
                        testData.getJsonData("paymentInfo.cardNumber"),
                        testData.getJsonData("paymentInfo.month"),
                        testData.getJsonData("paymentInfo.year"),
                        testData.getJsonData("paymentInfo.invalidCvv"),
                        testData.getJsonData("paymentInfo.nameOnCard")
                ).verifyInvalidCVVMessageDisplayed(testData.getJsonData("messages.invalidCvv"));
    }
    @Test(dependsOnMethods = {"validateCheckoutPage","proceedToCheckout","addProduct","login"}, description = "Validate order summery details after enter valid payment information")
    public void validateOrderSummeryDetails(){
        new CheckoutPage(driver)
                .fillPaymentInformation(
                        testData.getJsonData("paymentInfo.cardNumber"),
                        testData.getJsonData("paymentInfo.month"),
                        testData.getJsonData("paymentInfo.year"),
                        testData.getJsonData("paymentInfo.cvv"),
                        testData.getJsonData("paymentInfo.nameOnCard")
                ).validateOrderSummeryDetails(
                        testData.getJsonData("summery.productName"),
                        testData.getJsonData("summery.quantity"),
                        testData.getJsonData("summery.cartTotalPrice"));
    }

    //configurations
    @BeforeClass
    public void setUp(){
        driver=new UIDriver();
        new NavigationBarComponent(driver).navigate();
        testData=new JsonReader("checkout-data");

    }

    @AfterClass
    public void tearDown(){
        driver.quitDriver();
    }
}
