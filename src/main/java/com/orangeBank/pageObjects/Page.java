package com.orangeBank.pageObjects;

import com.orangeBank.config.Configuration;
import com.orangeBank.config.Properties;
import com.orangeBank.context.ScenarioContext;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.function.Function;

import static org.apache.logging.log4j.LogManager.getLogger;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class Page {

    @FindBy( className = "didomi-popup-notice-with-data-processing")
    private WebElement popInCookieWrap;
    
    @FindBy(id = "didomi-notice-agree-button")
    private WebElement popInCookieButton;

    
    String jsClickCode = "arguments[0].scrollIntoView(true); arguments[0].click();";

    protected WebDriver driver;
    protected JavascriptExecutor js;
    protected Actions action;

    /**** waiter\*/
    protected WebDriverWait wait;
    protected WebDriverWait shortWait;
    protected WebDriverWait middleWait;
    protected WebDriverWait longWait;
    protected Logger Log;

    protected Configuration config = Properties.Config;
    protected String env = Properties.Config.getEnvironment();
    protected ScenarioContext context;

    //    Page constructor
    Page(){

        // Init
        driver = Properties.DriverManager.getDriver();
        PageFactory.initElements(driver, this);
        Log = getLogger(this);

        js = (JavascriptExecutor) driver;
        action = new Actions(driver);

        //Waiter
        wait        = new WebDriverWait(driver, Duration.ofSeconds(4));
        shortWait   = new WebDriverWait(driver, Duration.ofSeconds(10));
        middleWait  = new WebDriverWait(driver, Duration.ofSeconds(20));
        longWait    = new WebDriverWait(driver, Duration.ofSeconds(30));

    }

    // waiters functions
    protected <V>boolean waitUntil(Function<? super WebDriver, V> isTrue){
        try{
            wait.until(isTrue);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    protected <V>boolean shortUntil(Function<? super WebDriver, V> isTrue){
        try{
            shortWait.until(isTrue);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /***
     *
     * @param isTrue
     * @param <V>
     * @return
     */
    protected <V>boolean longUntil(Function<? super WebDriver, V> isTrue){
        try{
            longWait.until(isTrue);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    
    /**
     * Waiting for a page to loaded*
     */
    protected void waitForLoadingPage( ){
        if(!longUntil(driver->js.executeScript("return document.readyState").equals("complete") || js.executeScript("return document.readyState").equals("interactive") )){
            throw new RuntimeException(driver.getCurrentUrl()+" not loaded");
        }
    }

    /***
     *get a page
     * @param url
     */
    protected void get(String url){
        driver.get(url);
        waitForLoadingPage();
    }
    
    /**
     * Click on an element
     * @param element
     */
    protected void clickOn(WebElement element){
    
        try {
            if (!shortUntil(visibilityOf(element))) {
                // Logger
                throw new RuntimeException("Element not visible after click");
            }
        
            if (!shortUntil(elementToBeClickable(element))) {
                // Logger
                throw new RuntimeException("Element not clickable");
            }
            element.click();
        }catch(Exception e){
            ((JavascriptExecutor) driver).executeScript(jsClickCode, element);
        }
    }
    
    /**
     * vertical scrolling a predefined height on the page
     * @param height
     */
    protected void scroll(int height){
        js.executeScript("window.scrollBy(0,"+height+")", "");
    }
    
    /**
     * scroll to an element
     * @param element
     */
    protected void scrollTo(WebElement element){js.executeScript("arguments[0].scrollIntoView(true);", element);}
    
    /**
     * get http request response code
     * @param url
     * @return
     */
    public Boolean checkUrlResponseCode(String url){

        try {
            HttpURLConnection c= (HttpURLConnection)new URL(url).openConnection();
            c.setRequestMethod("HEAD");
            c.connect();
            int r = c.getResponseCode();
            System.out.println("Http response code: " + r);
            if(r != 200) return true;
            return false;
        }catch (Exception e){
            return false;
        }

    }
    
    /**
     * get a webb page url
     * @return
     */
    protected String getPageUrl(){
        return driver.getCurrentUrl();
    }
    
    /**
     * accept cookies
     */
    protected void cookieManager( ){
        if(waitUntil(visibilityOf(popInCookieWrap))) clickOn(popInCookieButton);
    }
    
    /**
     * Save ScreenShot for Allure
     */
    @Attachment(value = "screenshot", type = "image/png")
    public void saveScreenShotPNG(){
        Allure.addAttachment("screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }
    
    /**
     * verify element color
     * @param target
     * @param cssValue
     * @return
     */
    public String ColorVerify(WebElement target, String cssValue)
    {
        /* This method used to verify color code*/
        String colorCode= target.getCssValue(cssValue);
        String hexacolor = Color.fromString(colorCode).asHex();
        return hexacolor;
    }
    
    /**
     * get elemt size as rectangle
     * @param element
     * @return
     */
    protected Rectangle getElementSize(WebElement element){
        return element.getRect();
    }
    
    /**
     * Check if elements are overlapped
     * @param element1
     * @param element2
     * @return
     */
    protected boolean areElementsOverlapping(WebElement element1, WebElement element2) {
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
    
    /**
     * Check if file is present in download directory
     * @return
     */
    protected boolean filePresent() {
        String downloadDir = Paths.get("target").toAbsolutePath().toString();
        File folder = new File(downloadDir);
        //List the files on that folder
        File[] listOfFiles = folder.listFiles();
        boolean found = false;
        File f = null;
        //Look for the file in the files
        // You should write smart REGEX according to the filename
        try {
            assert listOfFiles != null;
            for (File listOfFile : listOfFiles) {
                if (listOfFile.isFile()) {
                    String fileName = listOfFile.getName();
                    // System.out.println("File " + listOfFile.getName());
                    if (fileName.matches("oembed")) {
                        f = new File(downloadDir+fileName);
                        found = true;
                        System.out.println("fichier trouve  " + found);
                        System.out.println(f);
                        f.deleteOnExit();
                    }
                }
            }
        }catch (Exception e){
            Log.info("any file found");
        }
        return found;
    }

}
