package com.graduationproject.pages.components;

import com.graduationproject.drivers.UIDriver;
import com.graduationproject.pages.*;
import com.graduationproject.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class NavigationBarComponent {
    private UIDriver driver;
    private final String electronicEndpoint= PropertyReader.getProperty("baseurl")+"/category/Electronics";
    private final String homeEndpoint= PropertyReader.getProperty("baseurl")+"/category/Home";
    private final String clothingEndpoint= PropertyReader.getProperty("baseurl")+"/category/Clothing";



    public NavigationBarComponent(UIDriver driver ){
        this.driver=driver;
    }


    //locators
    private final By loginButton=By.cssSelector("[content=\"Login\"]");
    private final By homeButton=By.xpath("//a[.=\" Home \"]");
    private final By productsButton=By.xpath("//a[.=\" Products \"]");
    private final By cartBadge=By.id("cartCountBadge");
    private final By categoryButton=By.xpath("//a[.=\" Categories \"]");
    private final By electronicsLink=By.xpath("//*[@class=\"dropdown-item\"][contains(., \"Electronics\")]");
    private final By homeLink=By.xpath("//*[@class=\"dropdown-item\"][contains(., 'Home')]");
    private final By clothingLink=By.xpath("//*[@class=\"dropdown-item\"][contains(., 'Clothing')]");
    private final By contactButton=By.xpath("//a[.=\" Contact \"]");
    private final By signupButton=By.xpath("//a[.=\"Sign Up\"]");
    private final By logoutButton= By.xpath("//a[.=\" Logout\"]");

    //dynamic category locator
    private By pageSection(String sectionName)
    {;
        return By.xpath("//div[.='"+sectionName+"']");
    }


    //actions
    @Step("Navigate to the home page")
    public NavigationBarComponent navigate(){
        driver.browser().navigateTo(PropertyReader.getProperty("baseurl"));
        return this;
    }

    @Step("Click on the login button")
    public LoginPage clickOnLoginButton(){
        driver.element().click(loginButton);
        return new LoginPage(driver);
    }

    @Step("Click on the home button")
    public NavigationBarComponent clickOnHomeButton(){
        driver.element().click(homeButton);
        return this;
    }

    @Step("Click on the products button")
    public ProductsPage clickOnProductsButton(){
        driver.element().click(productsButton);
        return new ProductsPage(driver);
    }
    @Step("Click on the category button")
    public NavigationBarComponent clickOnCategoryDropdown(){
        driver.element().click(categoryButton);
        return this;
    }
    @Step("Click on the electronics link")
    public NavigationBarComponent clickOnElectronicsLink(){
        driver.element().click(electronicsLink);
        return this;
    }
    @Step("Click on the home link")
    public NavigationBarComponent clickOnHomeLink(){
        driver.element().click(homeLink);
        return this;
    }
    @Step("Click on the clothing link")
    public NavigationBarComponent clickOnClothingLink(){
        driver.element().click(clothingLink);
        return this;
    }

    @Step("Click on the cart button")
    public CartPage clickOnCartButton(){
        driver.element().click(cartBadge);
        return new CartPage(driver);
    }

    @Step("Click on the signup button")
    public SignupPage clickOnSignupButton(){
        driver.element().click(signupButton);
        return new SignupPage(driver);
    }
//    //TODO: contact page
//    @Step("Click on the logout button")
//    public LogoutPage clickOnLogoutButton(){
//        driver.element().click(logoutButton);
//        return new LogoutPage(driver);
//    }

    //TODO: contact page
//    @Step("Click on the contact button")
//    public ContactPage clickOnContactButton(){
//        driver.element().click(contactButton);
//        return new ContactPage(driver);
//    }

    //validations
    @Step("Verify landing page sections")
    public NavigationBarComponent verifyLandingPageSections(String s1 , String s2 , String s3){
        driver.validation().Equals(driver.element().getText(pageSection(s1)),s1,"Section name is not as expected")
                .Equals(driver.element().getText(pageSection(s2)),s2,"Section name is not as expected")
                .Equals(driver.element().getText(pageSection(s3)),s3,"Section name is not as expected");
        return this;
    }

    @Step("verify Electronics endpoints")
    public NavigationBarComponent verifyElectronicsEndpoints(){
        driver.validation().Equals(driver.browser().getCurrentUrl(), electronicEndpoint,"Electronics endpoint is not as expected");
        return this;
    }
    @Step("verify Home endpoints")
    public NavigationBarComponent verifyHomeEndpoints(){
        driver.validation().Equals(driver.browser().getCurrentUrl(), homeEndpoint,"Home endpoint is not as expected");
        return this;
    }
    @Step("verify Clothing endpoints")
    public NavigationBarComponent verifyClothingEndpoints(){
        driver.validation().Equals(driver.browser().getCurrentUrl(), clothingEndpoint,"Clothing endpoint is not as expected");
        return this;
    }
}
