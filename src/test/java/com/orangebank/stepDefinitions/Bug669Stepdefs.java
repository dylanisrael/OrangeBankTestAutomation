package com.orangebank.stepDefinitions;

import com.orangebank.pages.HomePage;
import com.orangebank.pages.LabanquemobilePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;

public class Bug669Stepdefs {
    @Given("user is on home page")
    public void userIsOnHomePage() throws InterruptedException {
        HomePage.navigateToHomePage();
  }

    @And("user click on notre idee de banque")
    public void userClickOnNotreIdeeDeBanque() {
        HomePage.clickOnNotreIdeeDeLaBanque();
    }

    @When("user click on campagne de lancement")
    public void userClickOnCampagneDeLancement() throws InterruptedException {
        LabanquemobilePage.clickOnCampagneDeLancement();
    }

    @Then("No files should be downloaded")
    public void aucunFichierNeDevraitEtreTelecharge() throws IOException {
        LabanquemobilePage.verifictionBug669();
    }
}
