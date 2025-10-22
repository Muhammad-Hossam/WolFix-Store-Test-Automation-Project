package com.graduationproject.pages;

import com.graduationproject.drivers.UIDriver;
import com.graduationproject.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ProductDetailsPage {
    private UIDriver driver;
    private String pageEndpoint= "/product/1";

    public ProductDetailsPage(UIDriver driver)
    {
        this.driver = driver;
    }


    //locators
    private final By productLabel=By.cssSelector(".product-info >h1");
    private final By productName=By.xpath("//div//span[text()='watchOS']");
    private final By productPrice=By.xpath("//span[.='$174.30 ']");
    private final By discountDetails=By.xpath("//div[@class='product-price']//span[text()='30% OFF']");

    private final By addToCartButton=By.cssSelector("app-btn-primary > button");
    private final By productAddedMessage=By.cssSelector("[role=\"alert\"]");


    //actions
    @Step("Navigate to the product details page")
    public ProductDetailsPage navigate(){
        driver.browser().navigateTo(PropertyReader.getProperty("baseurl")+pageEndpoint);
        return this;
    }
    @Step("add product to cart")
    public ProductDetailsPage addProductToCart(){
        driver.element().hover(addToCartButton).click(addToCartButton);
        return this;
    }


    //validations
    @Step("Validate product details information")
    public ProductDetailsPage validateProductDetailsInfo(String label,String pName, String price, String expectedDiscount){
        driver.validation().Equals(driver.element().getText(productLabel),label,"Product label is not as expected")
                .Equals(driver.element().getText(productName),pName,"Product name is not as expected")
                .Equals(driver.element().getText(productPrice),price,"Product price is not as expected")
                .Equals(driver.element().getText(discountDetails),expectedDiscount,"Product discount is not as expected");
        return this;
    }

    @Step("verify product added to cart message")
    public ProductDetailsPage verifyProductAddedToCartMessage(String expectedMessage){
        driver.validation().Equals(driver.element().getText(productAddedMessage),expectedMessage,"Product added to cart message is not as expected");
        return this;
    }

}
