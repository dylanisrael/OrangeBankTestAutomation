package com.orangeBank.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LaBanqueMobilePage extends Page {
    
    @FindBy(css = ".ob-grid-row > a")
    private  List<WebElement> campagneDeLancement2017Element;

    public  void clickOnCampagneDeLancement()  {
        scroll(4000);
        clickOn(campagneDeLancement2017Element.stream()
                .filter(card ->card.getAttribute("href")
                        .toLowerCase().contains("lancement"))
                .findFirst().get());
    }

    public  boolean verifictionBug669()  {
        boolean testResult = filePresent();
        if (testResult){
            Log.info("❌  Le fichier est telecharge bug non corrige");
        }
        else {
            Log.info("✅   Le fichie n'est pas telecharge bug corrige");
        }
        return testResult;
    }

}
