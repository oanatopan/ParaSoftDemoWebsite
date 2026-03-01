package shareData;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.*;

public class SharedData {
    public WebDriver driver;

    public RegisterPage registerPage;
    public LoginPage loginPage;
    public HomePage homePage;
    public AccountsOverviewPage accountsOverviewPage;
    public AccountActivityPage accountActivityPage;
    public BillPayPage billPayPage;
    public FindTransactionsPage findTransactionsPage;
    public LoanPage loanPage;
    public TransferFundsPage transferFundsPage;
    public OpenAccountPage openAccountPage;

    private static final String BASE_URL = "https://parabank.parasoft.com/parabank";

    @BeforeMethod
    public void prepareDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();

        driver.get(BASE_URL + "/index.htm");

        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        accountsOverviewPage = new AccountsOverviewPage(driver);
        accountActivityPage = new AccountActivityPage(driver);
        billPayPage = new BillPayPage(driver);
        findTransactionsPage = new FindTransactionsPage(driver);
        loanPage = new LoanPage(driver);
        transferFundsPage = new TransferFundsPage(driver);
        openAccountPage = new OpenAccountPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void clearEnvironment() {
        if (driver != null) driver.quit();
    }
}