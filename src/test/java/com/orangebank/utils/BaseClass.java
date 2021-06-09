package com.orangebank.utils;


import com.orangebank.pages.HomePage;
import com.paulhammant.ngwebdriver.NgWebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;


public class BaseClass {

    public static WebDriver driver;
    static NgWebDriver NgWebDriver;
    static  JavascriptExecutor jsDriver;
    public static String bugStatus = "KO";
    public static boolean corrected = false;

    public static LocalDate date;
    public static LocalTime tps;
    public static String value_tps = getTime();
    public static String value_date = getDate();
    static String os = System.getProperty("os.name");
    static String path;
    static ExcelManager excel = new ExcelManager();


    public static void setupDriver(){
        if (os.startsWith("W")){
            path = (System.getProperty("user.dir")+"\\src\\test\\resources\\webDrivers\\chromedriver.exe");
        } else {
            path = (System.getProperty("user.dir")+"/src/test/resources/webDrivers/chromedriver");
        }


        System.setProperty("webdriver.chrome.driver", path);
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        options.addArguments("--disable-blink-features");
//        options.addArguments("incognito");
        //capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println(path);


    }

    public static void navigateToHomePage() throws InterruptedException {
       driver.get(HomePage.HOME_PAGE_URL);
        attente(2);
        cookieHandler();
        attente(3);

    }


    public static void attente(int temps) throws InterruptedException {
        int sec = temps * 1000;
        Thread.sleep(sec);
    }

    public static void cookieHandler(){
        if(HomePage.acceptCookieButton.isDisplayed()){
            HomePage.acceptCookieButton.click();
        }
    }

    public static void closeDriver(WebDriver driver){
        driver.quit();
    }
    public static String getDate(){
        date = LocalDate.now();
        value_date = date.toString();
        return value_date;
    }

    public static String getTime(){
        tps = LocalTime.now();
        value_tps = tps.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        return value_tps;
    }

    public static void testCorrections(String bug_statut, String message){
        Assert.assertEquals(bug_statut,"OK", message);
    }
    public static void scroll(int height) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,"+height+")", "");
        Thread.sleep(3000);
    }


    public static void writeResultInFile(String NUMBUG, String bug_statut) throws IOException {
        value_date = getDate();
        value_tps = getTime();
        excel.excelWriting(NUMBUG,bug_statut, value_date, value_tps);

    }


    public static boolean areElementsOverlapping(WebElement element1, WebElement element2) {
        Rectangle r1 = element1.getRect();
        Point topRight1 = r1.getPoint().moveBy(r1.getWidth(), 0);
        Point bottomLeft1 = r1.getPoint().moveBy(0, r1.getHeight());

        Rectangle r2 = element2.getRect();
        Point topRight2 = r2.getPoint().moveBy(r2.getWidth(), 0);
        Point bottomLeft2 = r2.getPoint().moveBy(0, r2.getHeight());

        if (topRight1.getY() > bottomLeft2.getY()
                || bottomLeft1.getY() < topRight2.getY()) {
            return false;
        }
        if (topRight1.getX() < bottomLeft2.getX()
                || bottomLeft1.getX() > topRight2.getX()) {
            return false;
        }
        return true;
    }
    public static Map<String, Integer> getElementSize(WebElement element){
        Rectangle elementArea = element.getRect();
        Map<String,Integer> size = new HashMap<>();
        size.put("width",elementArea.getDimension().getWidth());
        size.put("height",elementArea.getDimension().getHeight());
        return size;
    }

    public static void setBrowserSize(int width,int height) {
        driver.manage().window().setPosition(new Point(0,0));
        driver.manage().window().setSize(new Dimension(width, height));
    }
    public static void gotoSomePage(String url) {
        driver.navigate().to(url);
    }

    public static boolean filePresent() {
        String path = "/Users/dylanIsrael/Downloads/";
        File folder = new File(path);
//List the files on that folder
        File[] listOfFiles = folder.listFiles();
        boolean found = false;
        File f = null;
        //Look for the file in the files
        // You should write smart REGEX according to the filename
        try {
            for (File listOfFile : listOfFiles) {
                if (listOfFile.isFile()) {
                    String fileName = listOfFile.getName();
                    // System.out.println("File " + listOfFile.getName());
                    if (fileName.matches("oembed")) {
                        f = new File(path+fileName);
                        found = true;
                        System.out.println("fichier trouve  " + found);
                        System.out.println(f);
                        f.delete();

                    }
                }
            }
        }catch (Exception e){
            Assert.assertTrue(found, "Downloaded document is not found");
        }
        f.deleteOnExit();
        return found;
    }

}
