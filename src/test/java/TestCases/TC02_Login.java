package TestCases;

import Pages.P02_LoginPage;
import Pages.PageBase;
import RetryAnalyser.MyRetry;
import io.qameta.allure.*;
import org.testng.ITestContext;
import org.testng.annotations.Test;

@Epic("Check Home Screen")
public class TC02_Login extends TestBase {

    String password = "P@ssword123";

    // check login positive scenarios
    @Story("Home Screen")
    @Severity(SeverityLevel.NORMAL)
    @Description("Login with Valid Username and Password")
    @Test(priority = 2, description = "Login with Valid Username and Password", groups = "smoke", retryAnalyzer = MyRetry.class, timeOut = 20000)
    public void loginWithValidData_P(ITestContext context) {
        // Retrieve the username from the test context
        String userName = (String) context.getAttribute("username");

        PageBase pageBase = new PageBase(driver);
        pageBase.loginPage();

        new P02_LoginPage(driver)
                .setUsername(userName)
                .setPassword(password)
                .clickLogin();
    }
}
