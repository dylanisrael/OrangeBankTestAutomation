package com.orangebank.pages;

import com.orangebank.utils.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.io.IOException;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class ForumOrangebankPage extends BaseClass {
    public static String pageUrl = "https://forum.orangebank.fr/";

    @FindBy(xpath = "//body/div[@id='bd']/div[@id='yui-main']/div[1]/div[1]/div[1]/div[5]/div[1]/ul[2]")
    public static WebElement cardDropdown;
    @FindBy (xpath = "//body/div[@id='bd']/div[@id='yui-main']/div[1]/div[1]/div[1]/div[5]/a[1]")
    public static WebElement dropdown;
    @FindBy(xpath = "//body/div[@id='bd']/div[@id='yui-main']/div[1]/div[1]/div[12]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/ul[2]/li[1]/div[1]/div[1]")
    public static WebElement premiereQuestion;
    @FindBy(xpath="//body/div[@id='bd']/div[@id='yui-main']/div[1]/div[1]/div[12]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/ul[2]/li[1]/div[1]/div[1]/div[1]/div[2]/a[1]")
    public static WebElement lireLasuite;


    public static void gotoForum() throws InterruptedException {
        driver.get(pageUrl);
        attente(1);
    }
    public static void clickOnDropdown() throws InterruptedException {
        dropdown.click();
        attente(1);
    }
    public static void clickOnLireLasuite(){
        lireLasuite.click();
    }

    public static void clickOnPremiereQuestion() throws InterruptedException {
        scroll(3000);
        attente(1);
        premiereQuestion.click();
        attente(1);
    }
    public static void verificationBug663() throws IOException {
        Map size = getElementSize(cardDropdown);
        System.out.println(getElementSize(cardDropdown));
        int height =parseInt(size.get("height").toString());
        System.out.println(height);
        if (height > 1){
            bugStatus = "OK";
            corrected = true;
            System.out.println("✅ "+bugStatus+" Le contenu du menu est visible Bug corrige");
        }else{
            System.out.println("❌ "+bugStatus+" La contenu du menu n'est pas visible Bug non corrige");
        }
        writeResultInFile("663",bugStatus);
        Assert.assertTrue(corrected);
    }
}
