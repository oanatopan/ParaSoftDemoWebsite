package shareData;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.*;
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
        options.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080", "--remote-allow-origins=*", "--incognito");

        driver = new ChromeDriver(options);
        LogUtility.startTest(testName);
        LogUtility.infoLog("The Chrome browser has been opened successfully.");

        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        initializePages();
    }

    // METODA DE SCREENSHOT - Trebuie să fie aici, singură!
    @Attachment(value = "Screenshot la eroare", type = "image/png")
    public byte[] saveScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    private void initializePages() {
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