package com.graduationproject.pages;

import com.graduationproject.drivers.GUIDriver;
import com.graduationproject.engine.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ProductsPage {

    private GUIDriver driver;
    private String pageEndpoint= "/products";

    public ProductsPage(GUIDriver driver)
    {
        this.driver = driver;
    }

    //locators
    private final By searchBox = By.cssSelector("[name=\"searchInput\"]");
    private final By searchButton = By.cssSelector(".search-input-group >i");
    private final By productDetails= By.cssSelector("[content=\"Details\"]");

    private final By addedToCartButton = By.cssSelector("[content=\"Add to Cart\"]");
    private final By productAddedMessage=By.cssSelector("[role=\"alert\"]");



    private final By productCategory = By.cssSelector("[class=\"product-category\"]");
    private final By productCompany = By.cssSelector("[class=\"product-brand\"]");


    //actions
    @Step("Navigate to the products page")
    public ProductsPage navigate(){
        driver.browser().navigateTo(PropertyReader.getProperty("baseurl")+pageEndpoint);
        return this;
    }

    @Step("Search for a product with name")
    public ProductsPage searchForProductWithName(String productName){
        driver.element().type(searchBox,productName).click(searchButton);
        return this;
    }
    @Step("Click on the product details button")
    public ProductDetailsPage clickOnProductDetailsButton(){
        driver.element().hover(productDetails).click(productDetails);
        return new ProductDetailsPage(driver);
    }

    @Step("Click on added to cart button")
    public ProductsPage clickOnAddToCartButton(){
        driver.element().hover(addedToCartButton).click(addedToCartButton);
        return this;
    }


    //validations
    @Step("Validate Searched product category and company")
    public ProductsPage validateSearchedProductInfo(String categoryName, String companyName){
        driver.validation().Equals(driver.element().getText(productCategory),categoryName,"Product category is not as expected")
                .Equals(driver.element().getText(productCompany),companyName,"Product company is not as expected");
        return this;
    }

    @Step("Validate product added message")
    public CartPage validateProductAddedMessage(String expectedMessage){
        driver.validation().Equals(driver.element().getText(productAddedMessage),expectedMessage,"Product added message is not as expected");
        return new CartPage(driver);
    }
}
