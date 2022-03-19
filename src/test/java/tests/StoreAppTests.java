package tests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.StoreAppCreateAccountPage;
import pages.StoreAppHomePage;
import pages.StoreAppLoginPage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.TestBase;

import java.util.Random;

public class StoreAppTests extends TestBase {

    String email;
    String passwordSignIn;

    @DataProvider(name = "registerData")
    public static Object[][] registerData(){
        Object[][] data = new Object[][]{
                {"Patel","Harsh","password","123 My Road","Chicago","13","7777777777","12345","Home Address"},
                {"Kim","Yan","123","1 My Road","New York","32","7777777777","23543","Work Address"},
                {"John","Doe","1234567","1 My Road","Los Angeles","5","7777777777","23543","Work Address"},
        };
        return data;
    }

    @Test(dataProvider = "registerData")
    public void validateRegisterFunctionalityTest() {
        driver.get(ConfigReader.getProperty("StoreAppURL"));

        StoreAppHomePage homePage = new StoreAppHomePage();
        homePage.loginButton.click();

        StoreAppLoginPage loginPage = new StoreAppLoginPage();
        Random random=new Random();
        int randomNum= random.nextInt();
        email=randomNum+"emrah@gmail.con";
        loginPage.registerEmailBox.sendKeys(randomNum+"emrah@gmail.con");
        loginPage.createAccountButton.click();

        StoreAppCreateAccountPage createAccountPage=new StoreAppCreateAccountPage();
        createAccountPage.firstName.sendKeys("Brad");
        createAccountPage.lastName.sendKeys("Pitt");
        createAccountPage.password.sendKeys("password");
        createAccountPage.firstNameOnAddress.sendKeys("selenium");
        createAccountPage.lastNameOnAddress.sendKeys("Williams");
        createAccountPage.address.sendKeys("123 MyRoad St");
        createAccountPage.city.sendKeys("Chicago");
        BrowserUtils.selectDropdownByValue(createAccountPage.state,"13");
        createAccountPage.mobilePhone.sendKeys("7777777777");
        createAccountPage.zipCode.sendKeys("12345");
        createAccountPage.shippingAddress.sendKeys("1234 My Road");
        createAccountPage.registerButton.click();

        String actualTitle= driver.getTitle();
        String expectedTitle= "My account - My Store";

        Assert.assertEquals(actualTitle,expectedTitle);

    }
    @Test(dependsOnMethods = "validateRegisterFunctionalityTest",groups ={"regression","smoke"})
    public void validateSigniInFunctionalityTest(){
        driver.get(ConfigReader.getProperty("StoreAppURL"));
        StoreAppHomePage homePage=new StoreAppHomePage();
        homePage.loginButton.click();
        StoreAppLoginPage loginPage=new StoreAppLoginPage();
        loginPage.signIn(email,passwordSignIn);
        String actualTitle= driver.getTitle();
        String expectedTitle="My account - My Store";
        Assert.assertEquals(actualTitle,expectedTitle);



    }




}
