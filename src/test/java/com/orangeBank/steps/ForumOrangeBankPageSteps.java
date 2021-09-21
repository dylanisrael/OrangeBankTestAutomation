package com.orangeBank.steps;

import com.orangeBank.pageObjects.ForumOrangebankPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
//import io.cucumber.java8.En;

import java.io.IOException;

public class ForumOrangeBankPageSteps {
    
    private final ForumOrangebankPage forumOrangebankPage;
    
    public ForumOrangeBankPageSteps(ForumOrangebankPage forumOrangebankPage) {
        this.forumOrangebankPage = forumOrangebankPage;
    }
    
    @And("go to ForumBankOrange")
    public void goToForumBankOrange()  {
       forumOrangebankPage.gotoForum();
    }

    @And("click on first question and choose read more")
    public void clickOnFirstQuestionAndChooseReadMore()  {
        forumOrangebankPage.clickOnPremiereQuestion();
        forumOrangebankPage.clickOnLireLasuite();
    }

    @When("user click on the dropdown")
    public void userClickOnTheDropdown(){
        forumOrangebankPage.clickOnDropdown();
    }

    @Then("a content should be displayed in")
    public void aContentShouldBeDisplayedIn() {
        forumOrangebankPage.saveScreenShotPNG();
        Assert.assertTrue(forumOrangebankPage.verificationBug663(),"❌ Le contenu du menu n'est pas visible Bug non corrige");
    }

}
