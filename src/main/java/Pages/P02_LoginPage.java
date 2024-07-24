package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P02_LoginPage {

    WebDriver driver;
    private final By username = By.xpath("//input[@name='username']");
    private final By password = By.xpath("//input[@name='password']");
    private final By LoginButton = By.xpath("//input[@value='Log In']");

    public P02_LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to set the username
    public P02_LoginPage setUsername(String userName) {
        driver.findElement(username).sendKeys(userName);
        return this;
    }

    // Method to set the password
    public P02_LoginPage setPassword(String passWord) {
        driver.findElement(password).sendKeys(passWord);
        return this;
    }

    // Method to click the login button
    public P02_LoginPage clickLogin() {
        driver.findElement(LoginButton).click();
        return this;
    }
}
