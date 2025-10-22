package com.graduationproject.drivers;

import com.graduationproject.utils.Actions.AlertActions;
import com.graduationproject.utils.Actions.BrowserActions;
import com.graduationproject.utils.Actions.ElementActions;
import com.graduationproject.utils.Actions.FrameActions;
import com.graduationproject.utils.dataReader.PropertyReader;
import com.graduationproject.utils.logs.LogsManager;
import com.graduationproject.validations.Validation;
import com.graduationproject.validations.Verification;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;


public class UIDriver {
    private final String browserType= PropertyReader.getProperty("browserType");

    private  ThreadLocal<WebDriver> driverThreadLocal= new ThreadLocal<>();


    public UIDriver(){
        Browser browser= Browser.valueOf(browserType.toUpperCase());
        LogsManager.info("Initializing driver =>" +browserType+ "<=");
        AbstractDriver abstractDriver=browser.getDriverFactory();
        WebDriver driver= ThreadGuard.protect(abstractDriver.createDriver());
        driverThreadLocal.set(driver);
    }

    public ElementActions element(){
        return new ElementActions(get());
    }

    public BrowserActions browser(){
        return new BrowserActions(get());
    }

    public FrameActions frame(){
        return new FrameActions(get());
    }

    public AlertActions alert(){
        return new AlertActions(get());
    }
    //Soft Assertions
    public Validation validation(){
        return new Validation(get());
    }

    //Hard Assertions
    public Verification verification(){
        return new Verification(get());
    }



    public  WebDriver get(){
        return driverThreadLocal.get();
    }
    public  void quitDriver(){
        driverThreadLocal.get().quit();
    }

}
