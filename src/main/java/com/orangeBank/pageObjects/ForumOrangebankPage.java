package com.orangeBank.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import java.awt.*;

public class ForumOrangebankPage extends Page {
    private String pageUrl = "https://forum.orangebank.fr/";

    @FindBy(xpath = "//body/div[@id='bd']/div[@id='yui-main']/div[1]/div[1]/div[1]/div[5]/div[1]/ul[2]")
    public  WebElement cardDropdown;
    
    @FindBy (xpath = "//body/div[@id='bd']/div[@id='yui-main']/div[1]/div[1]/div[1]/div[5]/a[1]")
    public  WebElement dropdown;
    
    @FindBy(id = "didomi-notice-agree-button")
    public  WebElement forumBankAcceptCookie;
    
    @FindBy(xpath = "//body/div[@id='bd']/div[@id='yui-main']/div[1]/div[1]/div[12]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/ul[2]/li[1]/div[1]/div[1]")
    public  WebElement premiereQuestion;
    
    @FindBy(xpath="//body/div[@id='bd']/div[@id='yui-main']/div[1]/div[1]/div[12]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/ul[2]/li[1]/div[1]/div[1]/div[1]/div[2]/a[1]")
    public  WebElement lireLasuite;


    public void gotoForum(){
        get(pageUrl);
        if (forumBankAcceptCookie.isDisplayed())
        clickOn(forumBankAcceptCookie);
        
    }
    public void clickOnDropdown()  {
        clickOn(dropdown);
    }
    public  void clickOnLireLasuite(){
        clickOn(lireLasuite);
    }

    public void clickOnPremiereQuestion() {
        scroll(3000);
        clickOn(premiereQuestion);
    }
    public boolean verificationBug663() {
        
        Rectangle size = new Rectangle();
        System.out.println(size.getSize().getHeight());
        int height = (int) size.getSize().getHeight();
        System.out.println(height);
        if (height > 1){
            System.out.println("✅  Le contenu du menu est visible Bug corrige");
            return true;
        }else{
            System.out.println("❌  La contenu du menu n'est pas visible Bug non corrige");
        }
        return false;
    }
}
