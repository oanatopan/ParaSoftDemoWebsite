package tests;

import helpMethods.ElementsMethods;
import helpMethods.SelectMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginValidTest {

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
        elementsMethods.fillElement(driver.findElement(By.id("customer.address.street")), "Strada");
        elementsMethods.fillElement(driver.findElement(By.id("customer.address.city")), "Baia Mare");
        elementsMethods.fillElement(driver.findElement(By.id("customer.address.state")), "MM");
        elementsMethods.fillElement(driver.findElement(By.id("customer.address.zipCode")), "430000");
        elementsMethods.fillElement(driver.findElement(By.id("customer.phoneNumber")), "0744123456");
        elementsMethods.fillElement(driver.findElement(By.id("customer.ssn")), "123");

        String userUnicValue = "oana" + System.currentTimeMillis();
        String passValue = "Parola123!";

        elementsMethods.fillElement(driver.findElement(By.id("customer.username")), userUnicValue);
        elementsMethods.fillElement(driver.findElement(By.id("customer.password")), passValue);
        elementsMethods.fillElement(driver.findElement(By.id("repeatedPassword")), passValue);

        elementsMethods.clickElement(driver.findElement(By.xpath("//input[@value='Register']")));

        elementsMethods.waitVisible(driver.findElement(By.linkText("Log Out")));
        elementsMethods.clickElement(driver.findElement(By.linkText("Log Out")));

        elementsMethods.fillElement(driver.findElement(By.name("username")), userUnicValue);
        elementsMethods.fillElement(driver.findElement(By.name("password")), passValue);
        elementsMethods.clickElement(driver.findElement(By.xpath("//input[@value='Log In']")));

        elementsMethods.waitVisible(driver.findElement(By.linkText("Log Out")));
        Assert.assertTrue(driver.findElement(By.linkText("Log Out")).isDisplayed(), "Login-ul NU a reusit!");

        driver.quit();
    }
}