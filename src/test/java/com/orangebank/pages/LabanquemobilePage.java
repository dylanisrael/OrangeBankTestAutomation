package com.orangebank.pages;

import com.orangebank.utils.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.io.IOException;


public class LabanquemobilePage extends BaseClass {
    @FindBy(xpath = "//body/div[3]/main[1]/div[1]/article[1]/section[7]/div[2]/a[1]")
    public static WebElement campagneDeLancement2017Element;

    public static void clickOnCampagneDeLancement() throws InterruptedException {
        scroll(4000);
        attente(2);
        campagneDeLancement2017Element.click();
        attente(3);
    }

    public static void verifictionBug669() throws IOException {
        boolean testResult = filePresent();
        if (testResult){
            bugStatus = "KO";
            System.out.println("❌ "+bugStatus+" Le fichier est telecharge bug non corrige");
        }
        else {
            bugStatus = "OK";
            corrected = true;
            System.out.println("✅ "+bugStatus+" Le fichie n'est pas telecharge bug corrige");
        }
        writeResultInFile("669",bugStatus);
        Assert.assertTrue(corrected);

    }

}
