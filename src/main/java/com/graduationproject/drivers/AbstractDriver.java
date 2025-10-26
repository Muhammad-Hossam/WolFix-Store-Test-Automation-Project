package com.graduationproject.drivers;

import com.graduationproject.engine.dataReader.PropertyReader;
import org.openqa.selenium.WebDriver;

public abstract class AbstractDriver {

    protected final String remoteHost = PropertyReader.getProperty("remoteHost");
    protected final String remotePort = PropertyReader.getProperty("remotePort");
    public abstract WebDriver createDriver();
}
