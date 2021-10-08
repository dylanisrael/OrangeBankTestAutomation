package com.orangeBank.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import java.awt.*;

public class ForumOrangebankPage extends Page {
    
    private String pageUrl = "https://forum.orangebank.fr/";
    
    @FindBy (css = ".question-actions .btn-actions")
    public  WebElement dropdown;
    
    @FindBy(id = "didomi-notice-agree-button")
    public  WebElement forumBankAcceptCookie;
    
    @FindBy(className = "category-id-3639")
    public  WebElement firstQuestion;
    
    @FindBy(css = ".category-id-3639 .read-more")
    public  WebElement readMore;


    public void gotoForum(){
        get(pageUrl);
        if (forumBankAcceptCookie.isDisplayed())
        clickOn(forumBankAcceptCookie);
        
    }
    public void clickOnDropdown()  {
        clickOn(dropdown);
    }
    public  void clickOnLireLasuite(){
        clickOn(readMore);
    }

    public void clickOnFirstQuestion() {
        scroll(3000);
        clickOn(firstQuestion);
    }
    public boolean verificationBug663() {
        
        Rectangle size = new Rectangle();
        System.out.println(size.getSize().getHeight());
        int height = (int) size.getSize().getHeight();
        System.out.println(height);
        if (height > 1){
            Log.info("✅  Le contenu du menu est visible Bug corrige");
            return true;
        }else{
            Log.info("❌  La contenu du menu n'est pas visible Bug non corrige");
        }
        return false;
    }
}
