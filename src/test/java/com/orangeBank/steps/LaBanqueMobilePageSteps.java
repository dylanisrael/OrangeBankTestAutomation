package com.orangeBank.steps;

import com.orangeBank.pageObjects.LaBanqueMobilePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.IOException;

public class LaBanqueMobilePageSteps {
    
    LaBanqueMobilePage laBanqueMobilePage =new LaBanqueMobilePage();
    
    public LaBanqueMobilePageSteps(LaBanqueMobilePage laBanqueMobilePage){
        this.laBanqueMobilePage = laBanqueMobilePage;
    }
    
    @When("user click on campagne de lancement")
    public void userClickOnCampagneDeLancement() throws InterruptedException {
        laBanqueMobilePage.clickOnCampagneDeLancement();
    }
    
    @Then("No files should be downloaded")
    public void aucunFichierNeDevraitEtreTelecharge() throws IOException {
        Assert.assertFalse(laBanqueMobilePage.verifictionBug669(),"❌ le fichier est telechargé");
    }
}
