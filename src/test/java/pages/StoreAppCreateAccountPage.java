package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class StoreAppCreateAccountPage {

    public StoreAppCreateAccountPage(){
        WebDriver driver= Driver.getDriver();
        PageFactory.initElements(driver,this);
    }
    @FindBy(id="customer_firstname")
    public WebElement firstName;
    @FindBy(id = "customer_lastname")
    public WebElement lastName;
    @FindBy(id = "email")
    public WebElement email;
    @FindBy(id = "passwd")
    public WebElement password;
    @FindBy(id = "firstname")
    public WebElement firstNameOnAddress;
    @FindBy(id = "lastname")
    public WebElement lastNameOnAddress;
    @FindBy(id = "address1")
    public WebElement address;
    @FindBy(id = "city")
    public WebElement city;
    @FindBy(id = "id_state")
    public WebElement state;
    @FindBy(id = "phone_mobile")
    public WebElement mobilePhone;
    @FindBy(id = "alias")
    public WebElement shippingAddress;
    @FindBy(id = "postcode")
    public WebElement zipCode;
    @FindBy(id = "submitAccount")
    public WebElement registerButton;

}
