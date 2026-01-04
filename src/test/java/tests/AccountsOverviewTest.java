package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class AccountsOverviewTest {

    public WebDriver driver;

    @Test
    public void metodaTest() {


        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        List<String> firstNames = Arrays.asList("Oana 1", "Oana 2", "Oana 3");
        List<String> lastNames = Arrays.asList("Topan 1", "Topan 2", "Topan 3");
        List<String> passwords = Arrays.asList("Parola1!", "Parola2!", "Parola3!");

        for (int i = 0; i < firstNames.size(); i++) {

            driver.get("https://parabank.parasoft.com/parabank/register.htm");


            WebElement firstNameElement = driver.findElement(By.id("customer.firstName"));
            String firstName = firstNames.get(i);
            firstNameElement.sendKeys(firstName);

            WebElement lastNameElement = driver.findElement(By.id("customer.lastName"));
            String lastName = lastNames.get(i);
            lastNameElement.sendKeys(lastName);

            WebElement addressElement = driver.findElement(By.id("customer.address.street"));
            addressElement.sendKeys("Strada Republicii " + i);

            WebElement cityElement = driver.findElement(By.id("customer.address.city"));
            cityElement.sendKeys("Baia Mare");

            WebElement stateElement = driver.findElement(By.id("customer.address.state"));
            stateElement.sendKeys("Romania");

            WebElement zipElement = driver.findElement(By.id("customer.address.zipCode"));
            zipElement.sendKeys("123456");

            WebElement phoneElement = driver.findElement(By.id("customer.phoneNumber"));
            String phoneNumber = "072200000" + i;
            phoneElement.sendKeys(phoneNumber);

            WebElement ssnElement = driver.findElement(By.id("customer.ssn"));
            ssnElement.sendKeys("999-00-" + i);

            WebElement userElement = driver.findElement(By.id("customer.username"));
            String userName = "oana" + System.currentTimeMillis() + i;

            System.out.println("Linia din cod a generat username-ul: " + userName);
            userElement.sendKeys(userName);

            WebElement passwordElement = driver.findElement(By.id("customer.password"));
            String currentPassword = passwords.get(i);
            passwordElement.sendKeys(currentPassword);

            WebElement confirmPassElement = driver.findElement(By.id("repeatedPassword"));
            confirmPassElement.sendKeys(currentPassword);

            WebElement registerButton = driver.findElement(By.xpath("//input[@value='Register']"));
            js.executeScript("arguments[0].click();", registerButton);

            WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("title")));
            String actualText = titleElement.getText();
            String expectedText = "Welcome " + userName;

            Assert.assertEquals(actualText, expectedText, "Mesajul de bun venit nu coincide pentru userul: " + userName);

            WebElement logOutButton = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Log Out")));
            js.executeScript("arguments[0].click();", logOutButton);

            System.out.println("SUCCES: Utilizatorul " + userName + " a fost înregistrat și verificat.");
        }

        driver.quit();
    }
}