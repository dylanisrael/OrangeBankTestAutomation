package com.orangeBank.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.io.IOException;


public class LaBanqueMobilePage extends Page {
    
    @FindBy(xpath = "//body/div[3]/main[1]/div[1]/article[1]/section[7]/div[2]/a[1]")
    private  WebElement campagneDeLancement2017Element;

    public  void clickOnCampagneDeLancement() throws InterruptedException {
        scroll(4000);
        clickOn(campagneDeLancement2017Element);
    }

    public  boolean verifictionBug669() throws IOException {
        boolean testResult = filePresent();
        if (testResult){
            System.out.println("❌  Le fichier est telecharge bug non corrige");
        }
        else {
            System.out.println("✅   Le fichie n'est pas telecharge bug corrige");
        }
        return testResult;
    }

}
