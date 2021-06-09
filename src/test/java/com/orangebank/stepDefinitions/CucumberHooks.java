package com.orangebank.stepDefinitions;

import com.orangebank.pages.*;
import com.orangebank.utils.BaseClass;
import com.orangebank.utils.ExcelManager;
import com.orangebank.utils.SendEmail;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.support.PageFactory;
import java.io.IOException;

public class CucumberHooks extends BaseClass {

    @Before
    public void setup(){
        setupDriver();
        System.out.println("voici le driver "+driver);
        PageFactory.initElements(driver, HomePage.class);
        PageFactory.initElements(driver,LabanquemobilePage.class);
        PageFactory.initElements(driver,ForumOrangebankPage.class);
        driver.manage().window().maximize();
    }
    @After
    public void teardown() throws IOException {
        closeDriver(driver);
        ExcelManager.formatResult("Verification.xlsx","Data");
//        SendEmail.sendEmailTo("fokourou@zenity.fr","OrangeBank");
        SendEmail.sendEmailTo("israel.mouofopk68@gmail.com","OrangeBank");
    }

}
