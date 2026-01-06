package tests;

import helpMethods.ElementsMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class AccountsOverviewTest {

    public WebDriver driver;
    public ElementsMethods elementsMethods;

    @Test
    public void metodaTest() {

        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();

        elementsMethods = new ElementsMethods(driver);

        List<String> firstNames = Arrays.asList("Oana 1", "Oana 2", "Oana 3");
        List<String> lastNames = Arrays.asList("Topan 1", "Topan 2", "Topan 3");
        List<String> passwords = Arrays.asList("Parola1!", "Parola2!", "Parola3!");

        for (int i = 0; i < firstNames.size(); i++) {
            driver.get("https://parabank.parasoft.com/parabank/register.htm");


            elementsMethods.fillElement(driver.findElement(By.id("customer.firstName")), firstNames.get(i));
            elementsMethods.fillElement(driver.findElement(By.id("customer.lastName")), lastNames.get(i));
            elementsMethods.fillElement(driver.findElement(By.id("customer.address.street")), "Strada Republicii " + i);
            elementsMethods.fillElement(driver.findElement(By.id("customer.address.city")), "Baia Mare");
            elementsMethods.fillElement(driver.findElement(By.id("customer.address.state")), "Romania");
            elementsMethods.fillElement(driver.findElement(By.id("customer.address.zipCode")), "123456");
            elementsMethods.fillElement(driver.findElement(By.id("customer.phoneNumber")), "072200000" + i);
            elementsMethods.fillElement(driver.findElement(By.id("customer.ssn")), "999-00-" + i);

            String userName = "oana" + System.currentTimeMillis() + i;
            elementsMethods.fillElement(driver.findElement(By.id("customer.username")), userName);

            String currentPassword = passwords.get(i);
            elementsMethods.fillElement(driver.findElement(By.id("customer.password")), currentPassword);
            elementsMethods.fillElement(driver.findElement(By.id("repeatedPassword")), currentPassword);


            elementsMethods.clickJS(driver.findElement(By.xpath("//input[@value='Register']")));


            WebElement titleElement = driver.findElement(By.className("title"));
            elementsMethods.waitVisible(titleElement);
            String actualText = titleElement.getText();
            String expectedText = "Welcome " + userName;

            Assert.assertEquals(actualText, expectedText, "Mesajul de bun venit nu coincide pentru userul: " + userName);


            elementsMethods.clickJS(driver.findElement(By.linkText("Log Out")));
            System.out.println("SUCCES: Utilizatorul " + userName + " a fost verificat.");
        }

        driver.quit();
    }
}