package com.orangebank.stepDefinitions;

import com.orangebank.pages.ForumOrangebankPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;

public class Bug663Stepdefs {
    @And("go to ForumBankOrange")
    public void goToForumBankOrange() throws InterruptedException {
        ForumOrangebankPage.gotoForum();
    }

    @And("click on first question and choose read more")
    public void clickOnFirstQuestionAndChooseReadMore() throws InterruptedException {
        ForumOrangebankPage.clickOnPremiereQuestion();
        ForumOrangebankPage.clickOnLireLasuite();
    }

    @When("user click on the dropdown")
    public void userClickOnTheDropdown() throws InterruptedException {
        ForumOrangebankPage.clickOnDropdown();
    }

    @Then("a content should be displayed in")
    public void aContentShouldBeDisplayedIn() throws IOException {
        ForumOrangebankPage.verificationBug663();
    }

}
