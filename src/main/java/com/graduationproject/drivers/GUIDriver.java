package com.graduationproject.drivers;

import com.graduationproject.engine.Actions.AlertActions;
import com.graduationproject.engine.Actions.BrowserActions;
import com.graduationproject.engine.Actions.ElementActions;
import com.graduationproject.engine.Actions.FrameActions;
import com.graduationproject.engine.dataReader.PropertyReader;
import com.graduationproject.engine.logs.LogsManager;
import com.graduationproject.validations.Validation;
import com.graduationproject.validations.Verification;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;


public class GUIDriver {
    private final String browserType= PropertyReader.getProperty("browserType");

    private  ThreadLocal<WebDriver> driverThreadLocal= new ThreadLocal<>();


    public GUIDriver(){
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
