package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.TestBase;

import java.util.List;

public class SauceLabsTests extends TestBase {


    @Test(priority = 1,groups = {"regression","smoke"})
    public void validateLoginFunc() {
        driver.get(ConfigReader.getProperty("SauceLabsURL"));
        SauceDemoLoginPage loginPage = new SauceDemoLoginPage();
        loginPage.login();
        String actualText = driver.findElement(By.xpath("//span[@class='title']")).getText();
        String expectedText = "PRODUCTS";

        Assert.assertEquals(actualText, expectedText);


    }

    @Test(priority = 2,groups = {"regression"})
    public void validateFilterByPriceTest() {
        driver.get(ConfigReader.getProperty("SauceLabsURL"));
        SauceDemoLoginPage loginPage = new SauceDemoLoginPage();
        loginPage.login();
        WebElement filter = driver.findElement(By.xpath("//select[@class='product_sort_container']"));
        Select select = new Select(filter);
        select.selectByValue("lohi");

        List<WebElement> prices = driver.findElements(By.xpath("//div[@class='inventory_item_price']"));

        for (int i = 1; i < prices.size(); i++) {
            String price = prices.get(i).getText().substring(1);
            double priceDouble = Double.parseDouble(price);

            String price2 = prices.get(i - 1).getText().substring(1);
            double priceDouble2 = Double.parseDouble(price2);

            Assert.assertTrue(priceDouble >= priceDouble2);
        }
    }

    @Test(priority = 3,groups = {"regression","smoke"})
    public void validateOrderFunctionalityTest() {
        driver.get(ConfigReader.getProperty("SauceLabsURL"));
        SauceDemoLoginPage loginPage = new SauceDemoLoginPage();
        loginPage.login();

        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        addToCartButton.click();

        WebElement cartButton = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
        cartButton.click();

        String price = driver.findElement(By.xpath("//div[@class='inventory_item_price']")).getText();
        driver.findElement(By.id("checkout")).click();

        driver.findElement(By.id("first-name")).sendKeys("Emrah");
        driver.findElement(By.id("last-name")).sendKeys("Durmus");
        driver.findElement(By.id("postal-code")).sendKeys("60640");
        driver.findElement(By.id("continue")).click();

        String checkOutPrice = driver.findElement(By.xpath("//div[@class='summary_subtotal_label']")).getText();
        Assert.assertEquals(checkOutPrice.substring(checkOutPrice.lastIndexOf(" ") + 1), price);

        driver.findElement(By.id("finish")).click();
        String actualSuccessMessage = driver.findElement(By.xpath("//h2[@class='complete-header']")).getText();
        String expectedSuccessMessage = "THANK YOU FOR YOUR ORDER";

        Assert.assertEquals(actualSuccessMessage, expectedSuccessMessage);

    }

    @Test(priority = 4,groups = {"regression","smoke"})
    public void validateCheckoutTotalTest() {
        driver.get(ConfigReader.getProperty("SauceLabsURL"));
        SauceDemoLoginPage loginPage = new SauceDemoLoginPage();
        loginPage.login();

        SauceDemoHomePage homePage = new SauceDemoHomePage();
        homePage.backPackProduct.click();
        homePage.bikeLightProduct.click();
        String backpackPrice = homePage.backPackPrice.getText();
        String bikelightPrice = homePage.bikeLightPrice.getText();
        homePage.cart.click();

        SauceDemoCartPage myCartPage = new SauceDemoCartPage();
        myCartPage.checkoutButton.click();

        SauceDemoCustomerInfoPage customerInfoPage = new SauceDemoCustomerInfoPage();
        customerInfoPage.checkOutWithValidInfo();

        SauceDemoCheckTotalPricePage checkTotalPricePage = new SauceDemoCheckTotalPricePage();
        String actualTotalPrice = checkTotalPricePage.totalPrice.getText();

        //validate --> backpackPrice +bikeLightPrice == actualTotalPrice

        double backpackPriceDouble = Double.parseDouble(backpackPrice.substring(1));
        double bikeLightPriceDouble = Double.parseDouble(bikelightPrice.substring(1));

        // actualTotalPrice --> "item total: $39.99

        double actualTotalPriceDouble = Double.parseDouble(actualTotalPrice.substring(actualTotalPrice.indexOf("$") + 1));

        Assert.assertTrue(backpackPriceDouble + bikeLightPriceDouble == actualTotalPriceDouble);


    }
}
