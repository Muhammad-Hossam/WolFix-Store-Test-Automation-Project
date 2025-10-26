package com.graduationproject.tests;

import com.graduationproject.drivers.GUIDriver;
import com.graduationproject.drivers.WebDriverProvider;
import com.graduationproject.engine.dataReader.JsonReader;
import org.openqa.selenium.WebDriver;

public class BaseTest implements WebDriverProvider {

    protected GUIDriver driver;
    protected JsonReader testData;


    @Override
    public WebDriver getWebDriver() {
        return driver.get();
    }
}
