package tests;

import helpMethods.ElementsMethods;
import helpMethods.SelectMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BillPayTest {

    public WebDriver driver;
    public ElementsMethods elementsMethods;
    public SelectMethods selectMethods;

    @Test
    public void metodaTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://parabank.parasoft.com/parabank/register.htm");

        elementsMethods = new ElementsMethods(driver);
        selectMethods = new SelectMethods(driver);

        elementsMethods.fillElement(driver.findElement(By.id("customer.firstName")), "Oana");
        elementsMethods.fillElement(driver.findElement(By.id("customer.lastName")), "Topan");
        elementsMethods.fillElement(driver.findElement(By.id("customer.address.street")), "Republicii");
        elementsMethods.fillElement(driver.findElement(By.id("customer.address.city")), "Baia Mare");
        elementsMethods.fillElement(driver.findElement(By.id("customer.address.state")), "MM");
        elementsMethods.fillElement(driver.findElement(By.id("customer.address.zipCode")), "123456");
        elementsMethods.fillElement(driver.findElement(By.id("customer.phoneNumber")), "0744000111");
        elementsMethods.fillElement(driver.findElement(By.id("customer.ssn")), "111");

        String userNameValue = "oana" + System.currentTimeMillis();
        elementsMethods.fillElement(driver.findElement(By.id("customer.username")), userNameValue);
        elementsMethods.fillElement(driver.findElement(By.id("customer.password")), "parola123");
        elementsMethods.fillElement(driver.findElement(By.id("repeatedPassword")), "parola123");

        elementsMethods.clickElement(driver.findElement(By.xpath("//input[@value='Register']")));

        elementsMethods.clickElement(driver.findElement(By.linkText("Bill Pay")));

        elementsMethods.fillElement(driver.findElement(By.name("payee.name")), "Electrica SA");
        elementsMethods.fillElement(driver.findElement(By.name("payee.address.street")), "Strada Energiei 10");
        elementsMethods.fillElement(driver.findElement(By.name("payee.address.city")), "Baia Mare");
        elementsMethods.fillElement(driver.findElement(By.name("payee.address.state")), "Maramures");
        elementsMethods.fillElement(driver.findElement(By.name("payee.address.zipCode")), "430001");
        elementsMethods.fillElement(driver.findElement(By.name("payee.phoneNumber")), "0744111222");
        elementsMethods.fillElement(driver.findElement(By.name("payee.accountNumber")), "12345");
        elementsMethods.fillElement(driver.findElement(By.name("verifyAccount")), "12345");
        elementsMethods.fillElement(driver.findElement(By.name("amount")), "50");

        selectMethods.selectByIndex(driver.findElement(By.name("fromAccountId")), 0);

        elementsMethods.clickElement(driver.findElement(By.xpath("//input[@value='Send Payment']")));

        WebElement resultElement = driver.findElement(By.xpath("//h1[contains(text(), 'Complete')]"));
        elementsMethods.waitVisible(resultElement);

        String actualTitle = resultElement.getText();
        Assert.assertEquals(actualTitle, "Bill Payment Complete");

        driver.quit();
    }
}