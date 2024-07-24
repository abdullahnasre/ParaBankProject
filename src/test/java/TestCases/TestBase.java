package TestCases;

import Drivers.DriverFactory;
import com.github.javafaker.Faker;
import Drivers.DriverHolder;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;


import static Util.Utility.startHtmlReport;


@Listeners(listeners.Listener.class)
public class TestBase {
    protected static WebDriver driver;
    protected Faker faker = new Faker();
    // extend report
    protected static ExtentSparkReporter htmlReporter;
    protected static ExtentReports extent;
    protected static ExtentTest test;
    private static String PROJECT_NAME = null;
    private static String PROJECT_URL = null;

    // define Suite Elements
    static Properties prop;
    static FileInputStream readProperty;

    // log4j
    protected Logger log;

    @BeforeSuite
    public void defineSuiteElements() throws IOException {
        // log4j
        DOMConfigurator.configure(System.getProperty("user.dir") + "/log4j.xml");
        log = Logger.getLogger(getClass());

        // initialize the HtmlReporter
        htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/testReport.html");

        //initialize ExtentReports and attach the HtmlReporter
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        setProjectDetails();

        // initialize test
        test = extent.createTest(PROJECT_NAME + " Test Automation Project");

        //configuration items to change the look and fee add content, manage tests etc
        htmlReporter.config().setDocumentTitle(PROJECT_NAME + " Test Automation Report");
        htmlReporter.config().setReportName(PROJECT_NAME + " Test Report");
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
    }

    private void setProjectDetails() throws IOException {
        // TODO: Step1: define object of properties file
        readProperty = new FileInputStream(
                System.getProperty("user.dir") + "/src/test/resources/properties/environment.properties");
        prop = new Properties();
        prop.load(readProperty);

        // define project name from properties file
        PROJECT_NAME = prop.getProperty("projectName");
        PROJECT_URL = prop.getProperty("url");
    }


    @Parameters("browser")
    @BeforeTest
    public void setupDriver(@Optional("edge") String browser)  {
        // start recording
        // MyScreenRecorder.startRecording("sprint1");
        driver = DriverFactory.getNewInstance(browser);
        DriverHolder.setDriver(driver);

        //set implicit wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7000));
        driver.manage().deleteAllCookies(); // Clear cookies
        clearLocalStorage(); // Clear local storage

        driver.get(PROJECT_URL);
    }

    private void clearLocalStorage() {
        driver.get(PROJECT_URL); // Navigate to a blank page
        ((JavascriptExecutor) driver).executeScript("window.localStorage.clear();");
    }


    @AfterTest
    public void quit() {
        driver.quit();
    }

    @AfterSuite
    public void tearDown() throws IOException {
        extent.flush();
        //start html report after test end
        startHtmlReport(System.getProperty("user.dir"), "/testReport.html");
    }

    @AfterMethod
    public void getResult(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, result.getName() + " failed with the following error: " + result.getThrowable());
            Reporter.log("Failed to perform " + result.getName(), 10, true);
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, result.getName());
            Reporter.log("Successfully perform " + result.getName(), 10, true);
        } else {
            test.log(Status.SKIP, result.getName());
            Reporter.log("Skip " + result.getName(), 10, true);
        }
    }
}
