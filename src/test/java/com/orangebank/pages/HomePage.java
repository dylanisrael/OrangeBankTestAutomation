package com.orangebank.pages;

import com.orangebank.utils.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;

public class HomePage extends BaseClass {

    public static String bugStatus ="KO";
    public static String HOME_PAGE_URL = "https://www.orangebank.fr/";

    @FindBy(xpath = "//a[contains(text(),'Notre idée de la banque')]")
    public static WebElement notreIdeeDeLaBanqueButton;


    @FindBy(xpath = "//button[@id='didomi-notice-agree-button']")
    public static WebElement acceptCookieButton;


    public static void clickOnNotreIdeeDeLaBanque() {

        driver.navigate().to(notreIdeeDeLaBanqueButton.getAttribute("href"));
    }




}
