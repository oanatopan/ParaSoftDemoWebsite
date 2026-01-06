package tests;

import helpMethods.ElementsMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;

public class TransferFundsTest {
    public WebDriver driver;
    public ElementsMethods elementsMethods;

    @Test
    public void metodaTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://parabank.parasoft.com/parabank/register.htm");

        elementsMethods = new ElementsMethods(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        elementsMethods.fillElement(driver.findElement(By.id("customer.firstName")), "Oana");
        elementsMethods.fillElement(driver.findElement(By.id("customer.lastName")), "Topan");
        elementsMethods.fillElement(driver.findElement(By.id("customer.address.street")), "Republicii");
        elementsMethods.fillElement(driver.findElement(By.id("customer.address.city")), "Baia Mare");
        elementsMethods.fillElement(driver.findElement(By.id("customer.address.state")), "Romania");
        elementsMethods.fillElement(driver.findElement(By.id("customer.address.zipCode")), "12345");
        elementsMethods.fillElement(driver.findElement(By.id("customer.phoneNumber")), "0700000000");
        elementsMethods.fillElement(driver.findElement(By.id("customer.ssn")), "111");

        String uniqueUser = "user_" + System.currentTimeMillis();
        elementsMethods.fillElement(driver.findElement(By.id("customer.username")), uniqueUser);
        elementsMethods.fillElement(driver.findElement(By.id("customer.password")), "Parola123!");
        elementsMethods.fillElement(driver.findElement(By.id("repeatedPassword")), "Parola123!");

        elementsMethods.clickElement(driver.findElement(By.xpath("//input[@value='Register']")));

        WebElement transferLink = driver.findElement(By.linkText("Transfer Funds"));
        elementsMethods.clickElement(transferLink);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='fromAccountId']/option")));

        elementsMethods.fillElement(driver.findElement(By.id("amount")), "100");
        elementsMethods.clickElement(driver.findElement(By.xpath("//input[@value='Transfer']")));

        WebElement result = driver.findElement(By.id("showResult"));
        elementsMethods.waitVisible(result);
        Assert.assertTrue(result.getText().contains("Transfer Complete!"), "Eroare: Mesajul de confirmare a transferului nu a apărut!");

        driver.quit();
    }
}