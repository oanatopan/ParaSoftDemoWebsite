package tests;

import helpMethods.ElementsMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterTest {

    public WebDriver driver;
    public ElementsMethods elementsMethods;

    @Test
    public void metodaTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://parabank.parasoft.com/parabank/register.htm");

        elementsMethods = new ElementsMethods(driver);

        elementsMethods.fillElement(driver.findElement(By.id("customer.firstName")), "Oana");
        elementsMethods.fillElement(driver.findElement(By.id("customer.lastName")), "Topan");
        elementsMethods.fillElement(driver.findElement(By.id("customer.address.street")), "Republicii");
        elementsMethods.fillElement(driver.findElement(By.id("customer.address.city")), "Baia Mare");
        elementsMethods.fillElement(driver.findElement(By.id("customer.address.state")), "Romania");
        elementsMethods.fillElement(driver.findElement(By.id("customer.address.zipCode")), "12345");
        elementsMethods.fillElement(driver.findElement(By.id("customer.phoneNumber")), "0700000000");
        elementsMethods.fillElement(driver.findElement(By.id("customer.ssn")), "111");

        String uniqueUser = "oana" + System.currentTimeMillis();
        elementsMethods.fillElement(driver.findElement(By.id("customer.username")), uniqueUser);
        elementsMethods.fillElement(driver.findElement(By.id("customer.password")), "Parola123!");
        elementsMethods.fillElement(driver.findElement(By.id("repeatedPassword")), "Parola123!");

        elementsMethods.clickElement(driver.findElement(By.xpath("//input[@value='Register']")));

        String successMsg = driver.findElement(By.id("rightPanel")).getText();
        Assert.assertTrue(successMsg.contains("Your account was created successfully"));

        driver.quit();
    }
}