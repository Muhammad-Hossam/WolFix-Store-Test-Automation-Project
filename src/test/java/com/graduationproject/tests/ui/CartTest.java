package com.graduationproject.tests.ui;

import com.graduationproject.drivers.GUIDriver;
import com.graduationproject.pages.CartPage;
import com.graduationproject.pages.ProductsPage;
import com.graduationproject.pages.components.NavigationBarComponent;
import com.graduationproject.tests.BaseTest;
import com.graduationproject.engine.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.*;

@Epic("GraduationProject - Cart Page")
@Feature("UI Cart Page")
@Story("Cart Page Sections")
@Severity(SeverityLevel.CRITICAL)
@Owner("Muhammad-Hossam")
public class CartTest extends BaseTest {



    //Scenario
    @Test(description = "Add product to cart from products page")
    public void addProductToCart(){
        new ProductsPage(driver)
                .navigate()
                .searchForProductWithName(testData.getJsonData("productToSearch"))
                .clickOnAddToCartButton()
                .validateProductAddedMessage(testData.getJsonData("messages.productAdded"));

    }

    @Test(dependsOnMethods = "addProductToCart",description = "Validate cart details after adding product")
    public void validateProductInCart(){
        new CartPage(driver)
                .navigate()
                .validateCartDetails(testData.getJsonData("productName"),
                                     testData.getJsonData("productPrice"),
                                     testData.getJsonData("cartTotalPrice"),
                                     testData.getJsonData("cartItemsCounter"));
    }

    @Test(dependsOnMethods = {"validateProductInCart","addProductToCart"},description = "Increase product quantity in cart")
    public void increaseProductQuantityInCart(){
        new CartPage(driver)
                .navigate()
                .increaseQuantityButton()
                .validateQuantityUpdate(testData.getJsonData("afterIncreaseQuantity.quantityCounterIcon"),
                                        testData.getJsonData("afterIncreaseQuantity.quantityUpdatedMessage"));
    }

    @Test(dependsOnMethods = {"validateProductInCart","addProductToCart","increaseProductQuantityInCart"},description = "Decrease product quantity in cart")
    public void decreaseProductQuantityInCart(){
        new CartPage(driver)
                .navigate()
                .decreaseQuantityButton()
                .validateQuantityUpdate(testData.getJsonData("quantityCounterIcon"),
                        testData.getJsonData("messages.productAdded"));
    }

    @Test(dependsOnMethods = {"validateProductInCart","addProductToCart","increaseProductQuantityInCart","decreaseProductQuantityInCart"},description = "Remove product from cart")
    public void removeProductFromCart(){
        new CartPage(driver)
                .navigate()
                .removeItemButton()
                .verifyProductRemovedMessage(testData.getJsonData("messages.productRemoved"))
                .verifyCartIsEmpty(testData.getJsonData("messages.cartIsEmpty"));
    }




    //configurations
    @BeforeClass
    public void setUp(){
        driver=new GUIDriver();
        new NavigationBarComponent(driver).navigate();
        testData=new JsonReader("cart-data");

    }

    @AfterClass
    public void tearDown(){
        driver.quitDriver();
    }
}
