package tests;

import helpMethods.ElementsMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AccountHistoryTest {

    public WebDriver driver;
    public ElementsMethods elementsMethods;

    @Test
    public void metodaTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://parabank.parasoft.com/parabank/register.htm");

        elementsMethods = new ElementsMethods(driver);

        elementsMethods.fillElement(driver.findElement(By.id("customer.firstName")), "Oana");
        elementsMethods.fillElement(driver.findElement(By.id("customer.lastName")), "Test");
        elementsMethods.fillElement(driver.findElement(By.id("customer.address.street")), "Adresa");
        elementsMethods.fillElement(driver.findElement(By.id("customer.address.city")), "Baia Mare");
        elementsMethods.fillElement(driver.findElement(By.id("customer.address.state")), "Romania");
        elementsMethods.fillElement(driver.findElement(By.id("customer.address.zipCode")), "123456");
        elementsMethods.fillElement(driver.findElement(By.id("customer.phoneNumber")), "0740000000");
        elementsMethods.fillElement(driver.findElement(By.id("customer.ssn")), "111");

        String uniqueUser = "oana" + System.currentTimeMillis();
        elementsMethods.fillElement(driver.findElement(By.id("customer.username")), uniqueUser);
        elementsMethods.fillElement(driver.findElement(By.id("customer.password")), "parola");
        elementsMethods.fillElement(driver.findElement(By.id("repeatedPassword")), "parola");
        elementsMethods.clickElement(driver.findElement(By.xpath("//input[@value='Register']")));

        elementsMethods.clickElement(driver.findElement(By.linkText("Accounts Overview")));


        By accountLink = By.xpath("//table[@id='accountTable']//a[contains(@href, 'activity.htm')]");
        elementsMethods.waitVisibleBy(accountLink);
        elementsMethods.clickElement(driver.findElement(accountLink));


        By titleLocator = By.xpath("//h1[contains(text(), 'Account Details')]");
        elementsMethods.waitVisibleBy(titleLocator);

        String confirmareText = driver.findElement(titleLocator).getText();
        Assert.assertTrue(confirmareText.contains("Account Details"), "Pagina Account History nu s-a incarcat!");

        driver.quit();
    }
}