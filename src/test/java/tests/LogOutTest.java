package tests;

import helpMethods.ElementsMethods;
import helpMethods.SelectMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LogOutTest {

    public WebDriver driver;
    public ElementsMethods elementsMethods;
    public SelectMethods selectMethods;

    @Test
    public void metodaTest() {
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();

        driver.get("https://parabank.parasoft.com/parabank/register.htm");

        elementsMethods = new ElementsMethods(driver);
        selectMethods = new SelectMethods(driver);

        elementsMethods.fillElement(driver.findElement(By.id("customer.firstName")), "Oana");
        elementsMethods.fillElement(driver.findElement(By.id("customer.lastName")), "Topan");
        elementsMethods.fillElement(driver.findElement(By.id("customer.address.street")), "Strada Republicii");
        elementsMethods.fillElement(driver.findElement(By.id("customer.address.city")), "Baia Mare");
        elementsMethods.fillElement(driver.findElement(By.id("customer.address.state")), "MM");
        elementsMethods.fillElement(driver.findElement(By.id("customer.address.zipCode")), "43000");
        elementsMethods.fillElement(driver.findElement(By.id("customer.phoneNumber")), "0744111222");
        elementsMethods.fillElement(driver.findElement(By.id("customer.ssn")), "999");

        String userValue = "oana" + System.currentTimeMillis();
        String passValue = "Parola123!";

        elementsMethods.fillElement(driver.findElement(By.id("customer.username")), userValue);
        elementsMethods.fillElement(driver.findElement(By.id("customer.password")), passValue);
        elementsMethods.fillElement(driver.findElement(By.id("repeatedPassword")), passValue);

        elementsMethods.clickElement(driver.findElement(By.xpath("//input[@value='Register']")));

        WebElement logOutLink = driver.findElement(By.linkText("Log Out"));
        elementsMethods.waitVisible(logOutLink);
        elementsMethods.clickElement(logOutLink);

        WebElement loginButton = driver.findElement(By.xpath("//input[@value='Log In']"));
        elementsMethods.waitVisible(loginButton);

        Assert.assertTrue(loginButton.isDisplayed(), "Eroare: Utilizatorul nu a fost delogat (butonul Log In nu e vizibil)!");

        driver.quit();
    }
}