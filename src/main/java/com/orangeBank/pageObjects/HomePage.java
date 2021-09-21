package com.orangeBank.pageObjects;

import com.orangeBank.config.Properties;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends Page {
    
    @FindBy(xpath = "//a[contains(text(),'Notre idée de la banque')]")
    private WebElement notreIdeeDeLaBanqueButton;


    @FindBy(xpath = "//button[@id='didomi-notice-agree-button']")
   private  WebElement acceptCookieButton;
    
    private String env = Properties.Config.getEnvironment();


    public void clickOnNotreIdeeDeLaBanque() {
        get(notreIdeeDeLaBanqueButton.getAttribute("href"));
    }
    
    public void navigateToHomePage(){
        get(env);
    }




}
