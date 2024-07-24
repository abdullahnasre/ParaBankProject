package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P01_RegisterPage {

    WebDriver driver;

    private final By FIRSTNAME = By.xpath("//input[@id='customer.firstName']");
    private final By LASTNAME = By.xpath("//input[@id='customer.lastName']");
    private final By Address = By.xpath("//input[@id='customer.address.street']");
    private final By City = By.xpath("//input[@id='customer.address.city']");
    private final By State = By.xpath("//input[@id='customer.address.state']");
    private final By ZipCode = By.xpath("//input[@id='customer.address.zipCode']");
    private final By Phone = By.xpath("//input[@id='customer.phoneNumber']");
    private final By SSN = By.xpath("//input[@id='customer.ssn']");
    private final By Username = By.xpath("//input[@id='customer.username']");
    private final By Password = By.xpath("//input[@id='customer.password']");
    private final By Confirm = By.xpath("//input[@id='repeatedPassword']");
    private final By REGISTER = By.xpath("//input[@value='Register']");
    private final By Logout = By.xpath("//a[normalize-space()='Log Out']");

    public P01_RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public P01_RegisterPage setFirstName(String firstName) {
        driver.findElement(FIRSTNAME).sendKeys(firstName);
        return this;
    }

    public P01_RegisterPage setLastName(String lastName) {
        driver.findElement(LASTNAME).sendKeys(lastName);
        return this;
    }

    public P01_RegisterPage setAddress(String address) {
        driver.findElement(Address).sendKeys(address);
        return this;
    }

    public P01_RegisterPage setCity(String city) {
        driver.findElement(City).sendKeys(city);
        return this;
    }

    public P01_RegisterPage setState(String state) {
        driver.findElement(State).sendKeys(state);
        return this;
    }

    public P01_RegisterPage setZipCode(String zipCode) {
        driver.findElement(ZipCode).sendKeys(zipCode);
        return this;
    }

    public P01_RegisterPage setPhone(String phone) {
        driver.findElement(Phone).sendKeys(phone);
        return this;
    }

    public P01_RegisterPage setSSN(String ssn) {
        driver.findElement(SSN).sendKeys(ssn);
        return this;
    }

    public P01_RegisterPage setUsername(String username) {
        driver.findElement(Username).sendKeys(username);
        return this;
    }

    public P01_RegisterPage setPassword(String password) {
        driver.findElement(Password).sendKeys(password);
        return this;
    }

    public P01_RegisterPage setConfirm(String confirm) {
        driver.findElement(Confirm).sendKeys(confirm);
        return this;
    }

    public P01_RegisterPage clickRegister() {
        driver.findElement(REGISTER).click();
        return this;
    }

    public P01_RegisterPage clickLogout(){
        driver.findElement(Logout).click();
        return this;
    }
}
