package com.orangeBank.steps;

import com.orangeBank.pageObjects.HomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class HomePageStep {
    
    private HomePage homePage = new HomePage();
    
    public HomePageStep(HomePage homePage){
        this.homePage = homePage;
    }
    
    @Given("user is on home page")
    public void userIsOnHomePage() {
        homePage.navigateToHomePage();
  }

    @And("user click on notre idee de banque")
    public void userClickOnNotreIdeeDeBanque() {
        homePage.clickOnNotreIdeeDeLaBanque();
    }

   
}
