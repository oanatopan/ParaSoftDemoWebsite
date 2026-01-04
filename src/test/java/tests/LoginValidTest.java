package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginValidTest {

    public WebDriver driver;

    @Test
    public void metodaTest() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.get("https://parabank.parasoft.com/parabank/register.htm");

        WebElement regFirstElement = driver.findElement(By.id("customer.firstName"));
        String regFirstValue = "Oana";
        regFirstElement.sendKeys(regFirstValue);

        WebElement regLastElement = driver.findElement(By.id("customer.lastName"));
        String regLastValue = "Topan";
        regLastElement.sendKeys(regLastValue);

        WebElement regAddressElement = driver.findElement(By.id("customer.address.street"));
        String regAddressValue = "Strada";
        regAddressElement.sendKeys(regAddressValue);

        WebElement regCityElement = driver.findElement(By.id("customer.address.city"));
        String regCityValue = "Baia Mare";
        regCityElement.sendKeys(regCityValue);

        WebElement regStateElement = driver.findElement(By.id("customer.address.state"));
        String regStateValue = "MM";
        regStateElement.sendKeys(regStateValue);

        WebElement regZipElement = driver.findElement(By.id("customer.address.zipCode"));
        String regZipValue = "430000";
        regZipElement.sendKeys(regZipValue);

        WebElement regPhoneElement = driver.findElement(By.id("customer.phoneNumber"));
        String regPhoneValue = "0744123456";
        regPhoneElement.sendKeys(regPhoneValue);

        WebElement regSsnElement = driver.findElement(By.id("customer.ssn"));
        String regSsnValue = "123";
        regSsnElement.sendKeys(regSsnValue);

        WebElement regUserElement = driver.findElement(By.id("customer.username"));
        String userUnicValue = "oana" + System.currentTimeMillis();
        regUserElement.sendKeys(userUnicValue);

        WebElement regPassElement = driver.findElement(By.id("customer.password"));
        String passValue = "Parola123!";
        regPassElement.sendKeys(passValue);

        WebElement regConfirmElement = driver.findElement(By.id("repeatedPassword"));
        regConfirmElement.sendKeys(passValue);

        WebElement regBtn = driver.findElement(By.xpath("//input[@value='Register']"));
        regBtn.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Log Out"))).click();

        WebElement loginUserElement = driver.findElement(By.name("username"));
        String loginUserValue = userUnicValue;
        loginUserElement.sendKeys(loginUserValue);

        WebElement loginPassElement = driver.findElement(By.name("password"));
        String loginPassValue = passValue;
        loginPassElement.sendKeys(loginPassValue);

        WebElement loginBtn = driver.findElement(By.xpath("//input[@value='Log In']"));
        loginBtn.click();

        WebElement logOutLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Log Out")));
        Assert.assertTrue(logOutLink.isDisplayed(), "Login-ul NU a reusit!");

        driver.quit();
    }
}