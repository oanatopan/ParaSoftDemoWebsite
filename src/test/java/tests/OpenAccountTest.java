package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class OpenAccountTest {
    public WebDriver driver;

    @Test
    public void metodaTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        driver.get("https://parabank.parasoft.com/parabank/register.htm");

        WebElement firstNameElement = driver.findElement(By.id("customer.firstName"));
        String firstNameValue = "Oana";
        firstNameElement.sendKeys(firstNameValue);

        WebElement lastNameElement = driver.findElement(By.id("customer.lastName"));
        String lastNameValue = "Topan";
        lastNameElement.sendKeys(lastNameValue);

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
        String ssnValue = "999-00-11";
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

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("rightPanel"), "Your account was created successfully"));

        WebElement openAccountLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Open New Account")));
        js.executeScript("arguments[0].click();", openAccountLink);

        WebElement dropdownType = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("type")));
        String accountTypeText = "SAVINGS";
        Select accountTypeSelect = new Select(dropdownType);
        accountTypeSelect.selectByVisibleText(accountTypeText);

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//select[@id='fromAccountId']/option")));

        WebElement openAccountButton = driver.findElement(By.xpath("//input[@value='Open New Account']"));
        js.executeScript("arguments[0].click();", openAccountButton);

        boolean isSuccess = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("openAccountResult"))).isDisplayed();
        Assert.assertTrue(isSuccess);

        driver.quit();
    }
}