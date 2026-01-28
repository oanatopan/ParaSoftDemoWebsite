package shareData;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.*;
import java.time.Duration;

public class SharedData {
    public WebDriver driver;

    public RegisterPage registerPage;
    public LoginPage loginPage;
    public AccountsOverviewPage accountsOverviewPage;
    public AccountActivityPage accountActivityPage;

    @BeforeMethod
    public void prepareDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
        accountsOverviewPage = new AccountsOverviewPage(driver);
        accountActivityPage = new AccountActivityPage(driver);
    }

    @AfterMethod
    public void clearEnvironment() {

        driver.quit();
    }
    public WebDriver getDriver() {
        return driver;
    }
}