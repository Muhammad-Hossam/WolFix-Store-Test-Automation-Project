package com.graduationproject.tests.ui;
import com.graduationproject.drivers.UIDriver;
import com.graduationproject.pages.components.NavigationBarComponent;
import com.graduationproject.tests.BaseTest;
import com.graduationproject.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("GraduationProject - Landing Page")
@Feature("UI Landing Page")
@Story("Landing Page Sections")
@Severity(SeverityLevel.CRITICAL)
@Owner("Muhammad-Hossam")
public class LandingTest extends BaseTest {

    // Tests
    @Test(description = "Verify landing page sections labels")
    public void verifyLandingPage(){
        System.out.println("TEST EXECUTING: verifyLandingPage");

        new NavigationBarComponent(driver)
                .verifyLandingPageSections(
                        testData.getJsonData("s1"),
                        testData.getJsonData("s2"),
                        testData.getJsonData("s3")
                );
    }

    @Test(description = "Verify Electronics page")
    public void verifyElectronicsEndpoint(){
        new NavigationBarComponent(driver)
                .navigate()
                .clickOnCategoryDropdown()
                .clickOnElectronicsLink()
                .verifyElectronicsEndpoints();

    }

    @Test(description = "Verify Home page")
    public void verifyHomeEndpoint(){
        new NavigationBarComponent(driver)
                .navigate()
                .clickOnCategoryDropdown()
                .clickOnHomeLink()
                .verifyHomeEndpoints();
    }

    @Test(description = "Verify Clothing page")
    public void verifyClothingEndpoint(){
        new NavigationBarComponent(driver)
                .navigate()
                .clickOnCategoryDropdown()
                .clickOnClothingLink()
                .verifyClothingEndpoints();
    }

    //configurations
    @BeforeClass
    protected void beforeClass(){
        testData = new JsonReader("landing-data");
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