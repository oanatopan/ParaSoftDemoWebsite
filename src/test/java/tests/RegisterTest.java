package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class RegisterTest {
    public WebDriver driver;

    @Test
    public void metodaTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        driver.get("https://parabank.parasoft.com/parabank/register.htm");

        WebElement firstElement = driver.findElement(By.id("customer.firstName"));
        String firstValue = "Oana";
        firstElement.sendKeys(firstValue);

        WebElement lastElement = driver.findElement(By.id("customer.lastName"));
        String lastValue = "Topan";
        lastElement.sendKeys(lastValue);

        WebElement streetElement = driver.findElement(By.id("customer.address.street"));
        String streetValue = "Republicii ";
        streetElement.sendKeys(streetValue);

        WebElement cityElement = driver.findElement(By.id("customer.address.city"));
        String cityValue = "Baia Mare";
        cityElement.sendKeys(cityValue);

        WebElement stateElement = driver.findElement(By.id("customer.address.state"));
        String stateValue = "Romania";
        stateElement.sendKeys(stateValue);

        WebElement zipElement = driver.findElement(By.id("customer.address.zipCode"));
        String zipValue = "123456";
        zipElement.sendKeys(zipValue);

        WebElement phoneElement = driver.findElement(By.id("customer.phoneNumber"));
        String phoneValue = "0722000000";
        phoneElement.sendKeys(phoneValue);

        WebElement ssnElement = driver.findElement(By.id("customer.ssn"));
        String ssnValue = "123-45-678";
        ssnElement.sendKeys(ssnValue);

        WebElement userElement = driver.findElement(By.id("customer.username"));
        String uniqueUser = "oana" + System.currentTimeMillis();
        userElement.sendKeys(uniqueUser);

        WebElement passElement = driver.findElement(By.id("customer.password"));
        String passValue = "Parola123!";
        passElement.sendKeys(passValue);

        WebElement confirmPassElement = driver.findElement(By.id("repeatedPassword"));
        String confirmValue = "Parola123!";
        confirmPassElement.sendKeys(confirmValue);

        WebElement registerButton = driver.findElement(By.xpath("//input[@value='Register']"));
        js.executeScript("arguments[0].click();", registerButton);

        WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'account was created successfully')]")));
        Assert.assertTrue(successMsg.isDisplayed());

        driver.quit();
    }
}