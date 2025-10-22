package com.graduationproject.tests.ui;

import com.graduationproject.drivers.UIDriver;
import com.graduationproject.pages.ProductDetailsPage;
import com.graduationproject.pages.ProductsPage;
import com.graduationproject.pages.components.NavigationBarComponent;
import com.graduationproject.tests.BaseTest;
import com.graduationproject.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("GraduationProject - Product Details Page")
@Feature("UI Product Details")
@Story("Product Details Page Sections")
@Severity(SeverityLevel.CRITICAL)
@Owner("Muhammad-Hossam")
public class ProductDetailsTest extends BaseTest {


    //test
    @Test(description = "verify product details information Label, Name, Price, Discount")
    public void validateProductDetailsInformation(){
        new ProductsPage(driver)
                .navigate()
                .searchForProductWithName(testData.getJsonData("productToSearch"))
                .clickOnProductDetailsButton()
                .validateProductDetailsInfo(
                        testData.getJsonData("productLabel"),
                        testData.getJsonData("productName"),
                        testData.getJsonData("productPrice"),
                        testData.getJsonData("discountedPercent")
                );
    }


    @Test(description = "verify product added to cart message")
    public void verifyProductAddedToCartMessage(){
        new ProductDetailsPage(driver)
                .navigate()
                .addProductToCart()
                .verifyProductAddedToCartMessage(
                        testData.getJsonData("messages.productAdded")
                );
    }


    //configurations
    @BeforeClass
    public void beforeClass(){
        testData=new JsonReader("productDetails-data");
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
