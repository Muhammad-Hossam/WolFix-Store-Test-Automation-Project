package com.graduationproject.tests;

import com.graduationproject.drivers.UIDriver;
import com.graduationproject.drivers.WebDriverProvider;
import com.graduationproject.utils.dataReader.JsonReader;
import org.openqa.selenium.WebDriver;

public class BaseTest implements WebDriverProvider {

    protected UIDriver driver;
    protected JsonReader testData;





    @Override
    public WebDriver getWebDriver() {
        return driver.get();
    }
}
