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

    protected RegisterPage registerPage;
    protected HomePage homePage;
    protected LoanPage loanPage;
    protected BillPayPage billPayPage;
    protected TransferFundsPage transferFundsPage;
    protected AccountsOverviewPage accountsOverviewPage;
    protected AccountActivityPage accountActivityPage;
    protected OpenAccountPage openAccountPage;
    protected LoginPage loginPage;
    protected FindTransactionsPage findTransactionsPage;

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
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://parabank.parasoft.com/parabank/index.htm");

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