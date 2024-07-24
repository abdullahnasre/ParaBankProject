package TestCases;

import Pages.P01_RegisterPage;
import Pages.PageBase;
import RetryAnalyser.MyRetry;
import io.qameta.allure.*;
import org.testng.ITestContext;
import org.testng.annotations.Test;

@Epic("Check Home Screen")
public class TC01_Register extends TestBase {

    String firstname = faker.name().firstName();
    String lastname = faker.name().lastName();
    String address = faker.address().streetAddress();
    String city = faker.address().city();
    String state = faker.address().state();
    String zipCode = faker.address().zipCode();
    String phone = faker.phoneNumber().phoneNumber();
    String userName = faker.name().username(); // Generate username
    String ssn = faker.idNumber().ssnValid();
    String password = "P@ssword123";
    String confirm = "P@ssword123";

    // check login positive scenarios
    @Story("Home Screen")
    @Severity(SeverityLevel.NORMAL)
    @Description("Register with Valid Data")
    @Test(priority = 1, description = "Register with Valid Data", groups = "smoke", retryAnalyzer = MyRetry.class, timeOut = 20000)
    public void CheckSignUpLinkClickable(ITestContext context) {
        // Store the generated username in the test context
        context.setAttribute("username", userName);

        PageBase pageBase = new PageBase(driver);
        pageBase.registerPage();

        new P01_RegisterPage(driver)
                .setFirstName(firstname)
                .setLastName(lastname)
                .setAddress(address)
                .setCity(city)
                .setState(state)
                .setZipCode(zipCode)
                .setPhone(phone)
                .setSSN(ssn)
                .setUsername(userName)
                .setPassword(password)
                .setConfirm(confirm)
                .clickRegister()
                .clickLogout();
    }
}
