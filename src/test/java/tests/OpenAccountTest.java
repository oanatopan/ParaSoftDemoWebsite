package tests;

import helpMethods.ElementsMethods;
import helpMethods.SelectMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class OpenAccountTest {
    public WebDriver driver;
    public ElementsMethods elementsMethods;
    public SelectMethods selectMethods;

    @Test
    public void metodaTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

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
        elementsMethods.fillElement(driver.findElement(By.id("customer.ssn")), "999-00-11");

        String uniqueUser = "oana" + System.currentTimeMillis();
        elementsMethods.fillElement(driver.findElement(By.id("customer.username")), uniqueUser);
        elementsMethods.fillElement(driver.findElement(By.id("customer.password")), "Parola123!");
        elementsMethods.fillElement(driver.findElement(By.id("repeatedPassword")), "Parola123!");

        elementsMethods.clickElement(driver.findElement(By.xpath("//input[@value='Register']")));

        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Open New Account"))).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='fromAccountId']/option")));

        WebElement dropdownType = driver.findElement(By.id("type"));
        selectMethods.selectByText(dropdownType, "SAVINGS");

        elementsMethods.clickElement(driver.findElement(By.xpath("//input[@value='Open New Account']")));

        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("openAccountResult")));
        Assert.assertTrue(successMessage.isDisplayed());

        driver.quit();
    }
}