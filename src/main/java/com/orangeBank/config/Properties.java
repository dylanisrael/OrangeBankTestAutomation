package com.orangeBank.config;

import com.orangeBank.driverManager.WebDriverManager;

public abstract class Properties {

    public final static Configuration       Config          = Configuration.getInstance();
    public final static WebDriverManager    DriverManager   = WebDriverManager.getInstance();

}
