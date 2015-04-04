package myEmailAccount;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class TestSendEmail {
    WebDriver driver=new FirefoxDriver();
    String baseUrl="https://www.gmail.com/";

    @BeforeTest
    public void navigateHomePage(){
        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        //verify login page title
        String expectedLoginPageTitle = "Gmail";
        String actualLoginPageTitle=driver.getTitle();
        Assert.assertEquals(actualLoginPageTitle,expectedLoginPageTitle);
    }


    @Test (priority = 1)
    public void loginToEmailAccount(){
        WebElement loginBox=driver.findElement(By.id("Email"));
        loginBox.sendKeys("codechallange1");

        WebElement pwdBox=driver.findElement(By.id("Passwd"));
        pwdBox.sendKeys("code1234");

        WebElement signInBtn=driver.findElement(By.id("signIn"));
        signInBtn.click();
    }

    @Test (priority = 2)
    public void goToInbox(){
        WebElement inboxLink=driver.findElement(By.xpath("//*[@id=':5a']/div/div[1]/span/a"));
        inboxLink.click();

        //verify Inbox Page title
        String expectedAccountPageTitle = "Inbox - codechallange1@gmail.com - Gmail";
        String actualAccountPageTitle=driver.getTitle();
        Assert.assertEquals(actualAccountPageTitle,expectedAccountPageTitle);
    }

    @Test (priority = 3)
    public void composeEmail(){
        WebElement composeBtn=driver.findElement(By.xpath(".//*[@id=':52']/div/div"));
        composeBtn.click();

        WebElement toBox=driver.findElement(By.xpath("//textarea[@name='to']"));
        toBox.sendKeys("hdilushan@gmail.com");

        WebElement subjBox=driver.findElement(By.name("subjectbox"));
        subjBox.sendKeys("Test Mail");

        //click on message area
        driver.findElement(By.xpath("//div[@class= 'Am Al editable LW-avf']")).click();

        WebElement msgBox=driver.findElement(By.xpath("//div[@class= 'Am Al editable LW-avf']"));
        msgBox.sendKeys("Hi Harsha");
    }

    @Test (priority = 3)
    public void sendEmail(){
        WebDriverWait waitSendBtn = new WebDriverWait(driver, 10);
        waitSendBtn.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Send']")));

        WebElement sendBtn=driver.findElement(By.xpath("//*[text()='Send']"));
        sendBtn.click();
    }

    @Test (priority = 5)
    public void verifySentMail(){
        WebElement sentLink=driver.findElement(By.xpath("//*[@id=':5e']/div/div[1]/span/a"));
        sentLink.click();

        //verify receiver's name
        String expectedReceiptName=driver.findElement(By.xpath("html/body/div[7]/div[3]/div/div[2]/div[1]/div[2]/div/div/div/div[2]/div[1]/div[1]/div/div[2]/div[4]/div[1]/div/table/tbody/tr/td[4]/div[2]/span")).getText();
        String actualReceiptName="hdilushan";
        Assert.assertEquals(actualReceiptName,expectedReceiptName);

        //verify sent mail's subject
        String expectedSentMailSubject=driver.findElement(By.xpath("html/body/div[7]/div[3]/div/div[2]/div[1]/div[2]/div/div/div/div[2]/div[1]/div[1]/div/div[2]/div[4]/div[1]/div/table/tbody/tr/td[6]/div/div/div/span[1]")).getText();
        String actualSentMailSubject="Test Mail";
        Assert.assertEquals(actualSentMailSubject,expectedSentMailSubject);
    }

    @Test (priority = 6)
    public void logoutFromEmailAccount(){
        WebElement emailLink=driver.findElement(By.xpath("//*[@id='gb']/div[1]/div[1]/div/div[3]/div[1]/a/span"));
        emailLink.click();

        WebElement signOut=driver.findElement(By.xpath("//*[@id='gb_71']"));
        signOut.click();
    }

}
