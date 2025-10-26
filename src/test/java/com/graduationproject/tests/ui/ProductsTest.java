package com.graduationproject.tests.ui;

import com.graduationproject.drivers.GUIDriver;
import com.graduationproject.pages.components.NavigationBarComponent;
import com.graduationproject.tests.BaseTest;
import com.graduationproject.engine.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("GraduationProject - Products Page")
@Feature("UI Search")
@Story("Products Page Sections")
@Severity(SeverityLevel.CRITICAL)
@Owner("Muhammad-Hossam")
public class ProductsTest extends BaseTest {



    //test
    @Test(description = "verify that the user can search for a specific product by name and validate the results")
    public void searchForProductByName(){
        new NavigationBarComponent(driver)
                .navigate()
                .clickOnProductsButton()
                .searchForProductWithName(testData.getJsonData("productName"))
                .validateSearchedProductInfo(testData.getJsonData("categoryName"),
                                             testData.getJsonData("companyName"));
    }

    //configurations
    @BeforeClass
    public void beforeClass(){
        testData=new JsonReader("products-data");
    }

    @BeforeMethod
    public void setUp(){
        driver=new GUIDriver();
        new NavigationBarComponent(driver).navigate();

    }

    @AfterMethod
    public void tearDown(){
        driver.quitDriver();
    }

}
