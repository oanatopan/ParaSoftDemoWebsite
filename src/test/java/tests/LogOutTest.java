package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LogOutTest {

    public WebDriver driver;

    @Test
    public void metodaTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.get("https://parabank.parasoft.com/parabank/register.htm");

        WebElement firstElement = driver.findElement(By.id("customer.firstName"));
        String firstValue = "Oana";
        firstElement.sendKeys(firstValue);

        WebElement lastElement = driver.findElement(By.id("customer.lastName"));
        String lastValue = "Topan";
        lastElement.sendKeys(lastValue);

        WebElement streetElement = driver.findElement(By.id("customer.address.street"));
        String streetValue = "Strada Republicii";
        streetElement.sendKeys(streetValue);

        WebElement cityElement = driver.findElement(By.id("customer.address.city"));
        String cityValue = "Baia Mare";
        cityElement.sendKeys(cityValue);

        WebElement stateElement = driver.findElement(By.id("customer.address.state"));
        String stateValue = "MM";
        stateElement.sendKeys(stateValue);

        WebElement zipElement = driver.findElement(By.id("customer.address.zipCode"));
        String zipValue = "43000";
        zipElement.sendKeys(zipValue);

        WebElement phoneElement = driver.findElement(By.id("customer.phoneNumber"));
        String phoneValue = "0744111222";
        phoneElement.sendKeys(phoneValue);

        WebElement ssnElement = driver.findElement(By.id("customer.ssn"));
        String ssnValue = "999";
        ssnElement.sendKeys(ssnValue);

        WebElement userElement = driver.findElement(By.id("customer.username"));
        String userValue = "oana" + System.currentTimeMillis();
        userElement.sendKeys(userValue);

        WebElement passElement = driver.findElement(By.id("customer.password"));
        String passValue = "Parola123!";
        passElement.sendKeys(passValue);

        WebElement confirmElement = driver.findElement(By.id("repeatedPassword"));
        String confirmValue = "Parola123!";
        confirmElement.sendKeys(confirmValue);

        WebElement registerBtn = driver.findElement(By.xpath("//input[@value='Register']"));
        registerBtn.click();

        WebElement logOutLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Log Out")));
        logOutLink.click();

        WebElement loginButtonElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@value='Log In']")));
        boolean esteAfisat = loginButtonElement.isDisplayed();
        Assert.assertTrue(esteAfisat);

        driver.quit();
    }
}