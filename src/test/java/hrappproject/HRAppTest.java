package hrappproject;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.TestBase;

public class HRAppTest extends TestBase {

    @Test(groups = {"smoke", "regression"})
    public void login(){
        driver.get(ConfigReader.getProperty("HrAppURL"));

        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Mindtek");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("MindtekStudent");
        driver.findElement(By.xpath("//button[@class='btn btn-success']")).click();
        String actual=driver.findElement(By.xpath("//label[@for='departments']")).getText();
        String expected="Select Department";
        Assert.assertEquals(actual,expected);

    }

    @Test
    public void negativeLogin(){
        driver.get(ConfigReader.getProperty("HrAppURL"));
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Mindtek");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("MindtekStudent123");
        driver.findElement(By.xpath("//button[@class='btn btn-success']")).click();
        String actual=driver.findElement(By.xpath("//div[@class='alert alert-waring']")).getText();
        String expected="Invalid credentials";
        Assert.assertEquals(actual,expected);

    }
   @Test
   public void logout(){
       driver.get(ConfigReader.getProperty("HrAppURL"));
       driver.get(ConfigReader.getProperty("HrAppURL"));
       driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Mindtek");
       driver.findElement(By.xpath("//input[@name='password']")).sendKeys("MindtekStudent");
       driver.findElement(By.xpath("//button[@class='btn btn-success']")).click();
       driver.findElement(By.xpath("//a[@routerlink='/logout']")).click();
       String actualLogoutMessage=driver.findElement(By.xpath("/html/body/app-root/div[1]/app-log-out/h1")).getText();
       String expectedLogoutMessage="You are logged out";
   }



}
