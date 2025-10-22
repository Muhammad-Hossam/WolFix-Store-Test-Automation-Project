package com.graduationproject.pages;

import com.graduationproject.drivers.UIDriver;
import com.graduationproject.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class CartPage {
    private UIDriver driver;
    private String pageEndpoint= "/cart";


    public CartPage(UIDriver driver) {
        this.driver = driver;
    }



    //locators

    private final By increaseQuantityButton=By.xpath("//button[.='+']");
    private final By decreaseQuantityButton=By.xpath("//button[.='-']");
    private final By removeItemButton=By.cssSelector("[class=\"bi bi-x-lg\"]");

    private final By productName=By.cssSelector("[class=\"cart-item-name\"]");
    private final By productPrice=By.cssSelector("[class=\"cart-item-price\"]");
    private final By cartTotalPrice=By.id("cartTotal");
    private final By cartItemsCounter=By.cssSelector(".cart-header >span");

    private final By emptyCartLabel=By.cssSelector("[class=\"empty-cart-title\"]");
    private final By quantityCounterIcon=By.id("cartCountBadge");

    private final By popupProductRemovedMessage=By.cssSelector("[aria-label=\"Removed from Cart ❌\"]");
    private final By productIncreasedMessage=By.cssSelector("[role=\"alert\"]");
    private final By checkoutButton=By.xpath("//a[.=' Checkout']");



    //actions
    @Step("Navigate to the cart page")
    public CartPage navigate(){
        driver.browser().navigateTo(PropertyReader.getProperty("baseurl")+pageEndpoint);
        return this;
    }

    @Step("Click on increase quantity button")
    public CartPage increaseQuantityButton(){
        driver.element().hover(increaseQuantityButton).click(increaseQuantityButton);
        return this;
    }

    @Step("Click on decrease quantity button")
    public CartPage decreaseQuantityButton(){
        driver.element().click(decreaseQuantityButton);
        return this;
    }

    @Step("Click on remove item button")
    public CartPage removeItemButton(){
        driver.element().click(removeItemButton);
        return this;
    }

    @Step("Click on checkout button")
    public CheckoutPage clickOnCheckoutButton(){
        driver.element().click(checkoutButton);
        return new CheckoutPage(driver);
    }

    //validations
    @Step("Validate Cart details productName Price cartTotalPrice")
    public CartPage validateCartDetails(String pName, String pPrice, String cPrice,String itemsNumber){
        driver.validation().Equals(driver.element().getText(productName),pName,"product name is not correct")
                .Equals(driver.element().getText(productPrice),pPrice,"product price is not correct")
                .Equals(driver.element().getText(cartTotalPrice),cPrice,"cart total price is not correct")
                .Equals(driver.element().getText(cartItemsCounter),itemsNumber,"cart items number is not correct");
        return this;
    }

    @Step("Validate Quantity update")
    public CartPage validateQuantityUpdate(String cartIconQuantity,String quantityUpdatedMessage){
        driver.validation().Equals(driver.element().getText(quantityCounterIcon),cartIconQuantity,"cart icon quantity is not correct")
                .Equals(driver.element().getText(productIncreasedMessage),quantityUpdatedMessage,"quantity updated message is not correct");
        return this;
    }

    @Step("verify product removed from cart")
    public CartPage verifyProductRemovedMessage(String popupMessage){
        driver.verification().Equals(driver.element().getText(popupProductRemovedMessage),popupMessage,"product removed message is not correct");
        return this;
    }
    @Step("verify cart is empty")
    public CartPage verifyCartIsEmpty(String emptyCartText) {
        driver.verification().Equals(driver.element().getText(emptyCartLabel), emptyCartText, "cart is not empty");
        return this;
    }

}
