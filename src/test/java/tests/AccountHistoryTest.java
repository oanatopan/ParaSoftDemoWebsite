package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class AccountHistoryTest {

    @Test
    public void metodaTest() {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));


        driver.get("https://parabank.parasoft.com/parabank/register.htm");

        WebElement firstNameElement = driver.findElement(By.id("customer.firstName"));
        String firstNameValue = "Oana";
        firstNameElement.sendKeys(firstNameValue);

        WebElement lastNameElement = driver.findElement(By.id("customer.lastName"));
        String lastNameValue = "Test";
        lastNameElement.sendKeys(lastNameValue);

        WebElement addressElement = driver.findElement(By.id("customer.address.street"));
        String addressValue = "Adresa";
        addressElement.sendKeys(addressValue);

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
        String phoneValue = "0740000000";
        phoneElement.sendKeys(phoneValue);

        WebElement ssnElement = driver.findElement(By.id("customer.ssn"));
        String ssnValue = "111";
        ssnElement.sendKeys(ssnValue);

        WebElement userElement = driver.findElement(By.id("customer.username"));
        String uniqueUser = "oana" + System.currentTimeMillis();
        userElement.sendKeys(uniqueUser);

        WebElement passElement = driver.findElement(By.id("customer.password"));
        String passValue = "parola";
        passElement.sendKeys(passValue);

        WebElement confirmPassElement = driver.findElement(By.id("repeatedPassword"));
        String confirmValue = "parola";
        confirmPassElement.sendKeys(confirmValue);

        WebElement registerBtn = driver.findElement(By.xpath("//input[@value='Register']"));
        registerBtn.click();


        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Accounts Overview"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@id='accountTable']//a"))).click();

        WebElement confirmare = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(), 'Account Details')]")));
        Assert.assertTrue(confirmare.getText().contains("Account Details"), "Pagina Account History nu s-a incarcat!");

        System.out.println("SUCCES: Testul a trecut pentru user: " + uniqueUser);

        driver.quit();
    }
}