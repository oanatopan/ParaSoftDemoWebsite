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

public class RequestLoanTest {
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // 1. Înregistrare utilizator (Refactorizat cu fillElement)
        elementsMethods.fillElement(driver.findElement(By.id("customer.firstName")), "Oana");
        elementsMethods.fillElement(driver.findElement(By.id("customer.lastName")), "Topan");
        elementsMethods.fillElement(driver.findElement(By.id("customer.address.street")), "Republicii");
        elementsMethods.fillElement(driver.findElement(By.id("customer.address.city")), "Baia Mare");
        elementsMethods.fillElement(driver.findElement(By.id("customer.address.state")), "Romania");
        elementsMethods.fillElement(driver.findElement(By.id("customer.address.zipCode")), "12345");
        elementsMethods.fillElement(driver.findElement(By.id("customer.phoneNumber")), "0700000000");
        elementsMethods.fillElement(driver.findElement(By.id("customer.ssn")), "999");

        String uniqueUser = "oana" + System.currentTimeMillis();
        elementsMethods.fillElement(driver.findElement(By.id("customer.username")), uniqueUser);
        elementsMethods.fillElement(driver.findElement(By.id("customer.password")), "parola");
        elementsMethods.fillElement(driver.findElement(By.id("repeatedPassword")), "parola");

        elementsMethods.clickElement(driver.findElement(By.xpath("//input[@value='Register']")));

        // 2. Request Loan
        WebElement requestLoanLink = driver.findElement(By.linkText("Request Loan"));
        elementsMethods.waitVisible(requestLoanLink);
        elementsMethods.clickElement(requestLoanLink);

        // 3. Completare date împrumut
        WebElement amountField = driver.findElement(By.id("amount"));
        elementsMethods.waitVisible(amountField);
        elementsMethods.fillElement(amountField, "100");

        elementsMethods.fillElement(driver.findElement(By.id("downPayment")), "10");

        // SINCRONIZARE CRITICĂ: Așteptăm ca dropdown-ul 'fromAccountId' să aibă cel puțin o opțiune
        // Dacă dăm click pe Apply înainte ca sistemul să vadă de unde vin banii, împrumutul va fi respins (Actual: "")
        wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(By.id("fromAccountId"), By.tagName("option")));

        elementsMethods.clickElement(driver.findElement(By.xpath("//input[@value='Apply Now']")));

        // 4. Validare Status
        WebElement statusElement = driver.findElement(By.id("loanStatus"));
        elementsMethods.waitVisible(statusElement);

        String actualStatusText = statusElement.getText();
        Assert.assertEquals(actualStatusText, "Approved", "Imprumutul a fost respins!");

        driver.quit();
    }
}