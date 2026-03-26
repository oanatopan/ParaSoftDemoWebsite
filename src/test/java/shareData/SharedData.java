package shareData;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.AccountActivityPage;
import pages.AccountsOverviewPage;
import pages.BillPayPage;
import pages.FindTransactionsPage;
import pages.HomePage;
import pages.LoanPage;
import pages.LoginPage;
import pages.OpenAccountPage;
import pages.RegisterPage;
import pages.TransferFundsPage;
import utils.LogUtility;

import java.time.Duration;

public class SharedData {

    private WebDriver driver;
    private String testName;

    public RegisterPage registerPage;
    public HomePage homePage;
    public LoanPage loanPage;
    public BillPayPage billPayPage;
    public TransferFundsPage transferFundsPage;
    public AccountsOverviewPage accountsOverviewPage;
    public AccountActivityPage accountActivityPage;
    public OpenAccountPage openAccountPage;
    public LoginPage loginPage;
    public FindTransactionsPage findTransactionsPage;

    @BeforeMethod(alwaysRun = true)
    public void prepareEnvironment() {
        testName = this.getClass().getSimpleName();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--incognito");

        driver = new ChromeDriver(options);
        LogUtility.infoLog("The Chrome browser has been opened successfully.");
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        LogUtility.infoLog("The user navigates to the following URL: https://parabank.parasoft.com/parabank/index.htm");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        LogUtility.infoLog("Browser maximized and implicit wait set to 10 seconds.");
        LogUtility.startTest(testName);
        LogUtility.infoLog("URL: " + driver.getCurrentUrl() + " | Title: " + driver.getTitle());

        registerPage = new RegisterPage(driver);
        homePage = new HomePage(driver);
        loanPage = new LoanPage(driver);
        billPayPage = new BillPayPage(driver);
        transferFundsPage = new TransferFundsPage(driver);
        accountsOverviewPage = new AccountsOverviewPage(driver);
        accountActivityPage = new AccountActivityPage(driver);
        openAccountPage = new OpenAccountPage(driver);
        loginPage = new LoginPage(driver);
        findTransactionsPage = new FindTransactionsPage(driver);

    }

    @AfterMethod(alwaysRun = true)
    public void clearEnvironment() {
        if (driver != null) {
            driver.quit();
        }
        LogUtility.finishTest(testName);
    }

    public WebDriver getDriver() {
        return driver;
    }
}