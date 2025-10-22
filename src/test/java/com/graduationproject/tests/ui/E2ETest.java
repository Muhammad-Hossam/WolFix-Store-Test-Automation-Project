package com.graduationproject.tests.ui;

import com.graduationproject.drivers.UIDriver;
import com.graduationproject.pages.CartPage;
import com.graduationproject.pages.CheckoutPage;
import com.graduationproject.pages.E2E;
import com.graduationproject.pages.components.NavigationBarComponent;
import com.graduationproject.tests.BaseTest;
import com.graduationproject.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Epic("GraduationProject - End-to-End Scenario")
@Feature("UI End-to-End Test")
@Story("End-to-End Test Scenario")
@Severity(SeverityLevel.CRITICAL)
@Owner("Muhammad-Hossam")
public class E2ETest extends BaseTest {

    //scenario
    @Test(description = "login with valid user")
    public void login(){
        new NavigationBarComponent(driver)
                .navigate()
                .clickOnLoginButton()
                .enterEmail(testData.getJsonData("login.email"))
                .enterPassword(testData.getJsonData("login.password"))
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
    @Test(dependsOnMethods = {"proceedToCheckout","addProduct","login"}, description = "proceed to order confirmation with valid shipping and payment data")
    public void fillShippingAndPaymentForm(){
        new CheckoutPage(driver)
                .navigate()
                .validateCheckoutPageDisplayed(testData.getJsonData("title"))
                .fillShippingInformation(
                        testData.getJsonData("shippingInfo.streetAddress"),
                        testData.getJsonData("shippingInfo.cityName"),
                        testData.getJsonData("shippingInfo.stateName"),
                        testData.getJsonData("shippingInfo.zipCode"),
                        testData.getJsonData("shippingInfo.phoneNumber"))
                .fillPaymentInformation(
                                        testData.getJsonData("paymentInfo.cardNumber"),
                                        testData.getJsonData("paymentInfo.month"),
                                        testData.getJsonData("paymentInfo.year"),
                                        testData.getJsonData("paymentInfo.cvv"),
                                        testData.getJsonData("paymentInfo.nameOnCard"))
                .validateOrderSummeryDetails(
                                        testData.getJsonData("summery.productName"),
                                        testData.getJsonData("summery.quantity"),
                                        testData.getJsonData("summery.cartTotalPrice"))
                .clickPlaceOrderButton();
    }
    @Test(dependsOnMethods = {"fillShippingAndPaymentForm","proceedToCheckout","addProduct","login"}, description = "validate order Created Successfully")
    public void validateOrderCreatedSuccessfully(){
        new E2E(driver)
                .VerifyOrderCreatedMessage(testData.getJsonData("messages.orderCreatedMessage"))
                .VerifyOrderConfirmedMessage(testData.getJsonData("messages.orderConfirmedMessage"))
                .VerifyOrderStatus(testData.getJsonData("messages.orderStatus"))
                .VerifyOrderPaymentStatus(testData.getJsonData("messages.paymentStatus"));
    }


    //configurations
    @BeforeClass
    public void setUp(){
        driver=new UIDriver();
        new NavigationBarComponent(driver).navigate();
        testData=new JsonReader("E2E-data");

    }

    @AfterClass
    public void tearDown(){
        driver.quitDriver();
    }
}
