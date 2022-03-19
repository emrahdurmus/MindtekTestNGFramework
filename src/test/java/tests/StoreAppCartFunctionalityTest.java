package tests;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.StoreAppCartPage;
import pages.StoreAppHomePage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.TestBase;

import java.io.IOException;

public class StoreAppCartFunctionalityTest extends TestBase {

    @Test
    public void validateAddCartFunctionalityTest() throws InterruptedException, IOException {
        driver.get(ConfigReader.getProperty("StoreAppURL"));
        BrowserUtils.scrollUpOrDown(500);

        StoreAppHomePage storeAppHomePage=new StoreAppHomePage();
        BrowserUtils.hoverOver(storeAppHomePage.product1);

        storeAppHomePage.product1AddToCart.click();
        Thread.sleep(10000);
        BrowserUtils.takeScreenshot();
        String actualMessage= storeAppHomePage.addToCartSuccessMessage.getText();
        String expectedMessage="Product successfully added to your shopping cart";

        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(actualMessage,expectedMessage);

        String addedProductName=storeAppHomePage.addedProductName.getText();

        storeAppHomePage.closeWindowIcon.click();
        storeAppHomePage.cart.click();

        StoreAppCartPage storeAppCartPage=new StoreAppCartPage();
        String addedProductNameInCart=storeAppCartPage.productName.getText();

        softAssert.assertEquals(addedProductNameInCart,addedProductName);
        softAssert.assertAll();


    }

}
