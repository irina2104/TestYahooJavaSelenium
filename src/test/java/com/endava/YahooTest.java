package com.endava;

import org.junit.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class YahooTest {

    private static WebDriver webDriver;

    @BeforeClass
    public static void before(){

        //Change the path for your PC
        System.setProperty("webdriver.chrome.driver","C:/Users/amagureanu/Documents/My Received Files/chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
    }

    @Before
    public void goToPage(){
        webDriver.get("http://mail.yahoo.com/");
    }

     @AfterClass
     public static void closeBrowser(){
        webDriver.close();
     }

    @Test
    public void sendEmail() {

         //Add email address
        WebElement emailField = webDriver.findElement(By.xpath(".//*[@id='login-username']"));
        emailField.sendKeys("");

        WebElement nextButton= webDriver.findElement(By.xpath(".//*[@id='login-signin']"));
        nextButton.click();

        //Add password
        WebElement passwordField = webDriver.findElement(By.xpath(".//*[@id='login-passwd']"));
        passwordField.sendKeys("");

        WebElement signInButton= webDriver.findElement(By.xpath(".//*[@id='login-signin']"));
        signInButton.click();

        WebElement sendPhoneNotifButton= webDriver.findElement(By.xpath(".//*[@id='challenge-selector-challenge']/form/ul[1]/li[2]/button"));
        sendPhoneNotifButton.click();

        WebElement composeButton = webDriver.findElement(By.id("Compose"));
        composeButton.click();

        //Add the email address on To Field
        WebElement toField = webDriver.findElement(By.xpath(".//*[@id='to-field']"));
        toField.sendKeys(" ");

        WebElement subjectField = webDriver.findElement(By.xpath(".//*[@id='subject-field']"));
        subjectField.sendKeys("test");

        WebElement contentField = webDriver.findElement(By.xpath(".//*[@id='rtetext']"));
        contentField.sendKeys("content test");

        WebElement sendButton = webDriver.findElement(By.xpath(".//div[contains(@class,'bottomToolbar squeeze-toolbar')]//span[@class='btn default']"));
        sendButton.click();

        //Add your name in the path to find the mail sent by you
        WebElement openEmail = webDriver.findElement(By.xpath(".//div[contains(@class,'name first') and contains(text(), '***your name***')]"));
        openEmail.click();

        String findContent = webDriver.findElement(By.xpath(".//div[contains(@class,'email-wrapped')]")).getText();

        Assert.assertEquals("content test", findContent );

        WebElement goSentMails = webDriver.findElement(By.xpath(".//*[@id='Sent']/a/span"));
        goSentMails.click();

        WebDriverWait wait = new WebDriverWait(webDriver, 5000);
        WebElement lastMailSent = webDriver.findElement(By.xpath(".//div[@class='list-view-items-page']/div[3]/div/div[2]/div[1]/div[1]/div"));
        wait.until(ExpectedConditions.visibilityOf(lastMailSent));

        System.out.println(lastMailSent.getText());

        //Add the email you want to make the assert with
        Assert.assertEquals(" ", lastMailSent.getText());

        WebElement nameButton = webDriver.findElement(By.xpath(".//*[@id='yui_3_10_3_1_1375219693637_127']/b"));
        nameButton.click();

        WebElement logOut = webDriver.findElement(By.xpath(".//*[@id='yucs-signout']"));
        logOut.click();
    }}
