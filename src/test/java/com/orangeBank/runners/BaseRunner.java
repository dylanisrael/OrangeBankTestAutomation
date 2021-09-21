package com.orangeBank.runners;

import com.orangeBank.config.Properties;

import com.orangeBank.utils.AllureFilesManager;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;

import java.io.IOException;

public class BaseRunner extends AbstractTestNGCucumberTests {
    
    private static final Logger Log =  LogManager.getLogger( BaseRunner.class);
    
    
    @Parameters({"browser", "device"})
    @BeforeClass
    public static void beforeTest(@Optional String browser, @Optional String device){
        String browserA;
        browserA = java.util.Optional
                .ofNullable(browser)
                .orElse(Properties.Config.getBrowser());
        Log.info("Tests are starting");
        Properties.DriverManager.setDriver(browserA);
        
    }
    
    @AfterClass
    public static void tearDown() throws ConfigurationException, IOException {
        AllureFilesManager.createEnvironmentPropertiesFile();
        AllureFilesManager.createCategorieJsonFile();
        AllureFilesManager.createExecutorJsonFile();
        Properties.DriverManager.getDriver().quit();
    }
}
