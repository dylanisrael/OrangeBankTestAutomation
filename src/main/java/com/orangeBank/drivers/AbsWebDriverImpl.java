package com.orangeBank.drivers;

import com.orangeBank.config.Properties;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.nio.file.Paths;
import java.util.HashMap;

public abstract class AbsWebDriverImpl {

    protected ChromeOptions getChromeOptions(){
        /**
         * change default chromedownload directory
         */
        String downloadDir = Paths.get("target").toAbsolutePath().toString();
        HashMap<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", downloadDir); // Bypass default download directory in Chrome
        prefs.put("safebrowsing.enabled", "false");
        
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setAcceptInsecureCerts(true);
        chromeOptions.setExperimentalOption("prefs",prefs);
        chromeOptions.setHeadless(Properties.Config.getHeadless());

            if (Properties.Config.getHeadless()){
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("window-size=1200,1100");

            }
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--enable-automation");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--disable-browser-side-navigation");

        return chromeOptions;
    }

    protected FirefoxOptions getFirefoxOptions(){
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setAcceptUntrustedCertificates(true);
        firefoxProfile.setAssumeUntrustedCertificateIssuer(true);

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setAcceptInsecureCerts(true);
        firefoxOptions.setHeadless(Properties.Config.getHeadless());
        firefoxOptions.setProfile(firefoxProfile);
        firefoxOptions.setCapability("acceptSslCerts", true);

        return firefoxOptions;
    }
}
