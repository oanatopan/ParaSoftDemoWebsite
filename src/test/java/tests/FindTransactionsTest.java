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

public class FindTransactionsTest {

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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        elementsMethods.fillElement(driver.findElement(By.id("customer.firstName")), "Oana");
        elementsMethods.fillElement(driver.findElement(By.id("customer.lastName")), "Topan");
        elementsMethods.fillElement(driver.findElement(By.id("customer.address.street")), "Strada Republicii");
        elementsMethods.fillElement(driver.findElement(By.id("customer.address.city")), "Baia Mare");
        elementsMethods.fillElement(driver.findElement(By.id("customer.address.state")), "MM");
        elementsMethods.fillElement(driver.findElement(By.id("customer.address.zipCode")), "430000");
        elementsMethods.fillElement(driver.findElement(By.id("customer.phoneNumber")), "0744123456");
        elementsMethods.fillElement(driver.findElement(By.id("customer.ssn")), "123");

        String userUnic = "oana" + System.currentTimeMillis();
        elementsMethods.fillElement(driver.findElement(By.id("customer.username")), userUnic);
        elementsMethods.fillElement(driver.findElement(By.id("customer.password")), "Parola123!");
        elementsMethods.fillElement(driver.findElement(By.id("repeatedPassword")), "Parola123!");

        elementsMethods.clickElement(driver.findElement(By.xpath("//input[@value='Register']")));


        elementsMethods.clickElement(driver.findElement(By.linkText("Accounts Overview")));

        By accountLinkLoc = By.xpath("//table[@id='accountTable']//td/a");
        WebElement accountLink = wait.until(ExpectedConditions.visibilityOfElementLocated(accountLinkLoc));
        elementsMethods.clickElement(accountLink);

        WebElement monthDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("month")));
        selectMethods.selectByText(monthDropdown, "All");

        WebElement typeDropdown = driver.findElement(By.id("transactionType"));
        selectMethods.selectByText(typeDropdown, "All");

        elementsMethods.clickElement(driver.findElement(By.xpath("//input[@value='Go']")));

        WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Account Activity']")));
        Assert.assertEquals(titleElement.getText(), "Account Activity");

        driver.quit();
    }
}