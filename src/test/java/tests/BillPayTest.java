package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class BillPayTest {

    public WebDriver driver;

    @Test
    public void metodaTest() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.get("https://parabank.parasoft.com/parabank/register.htm");

        WebElement firstNameElement = driver.findElement(By.id("customer.firstName"));
        String firstNameValue = "Oana";
        firstNameElement.sendKeys(firstNameValue);

        WebElement lastNameElement = driver.findElement(By.id("customer.lastName"));
        String lastNameValue = "Topan";
        lastNameElement.sendKeys(lastNameValue);

        WebElement addressElement = driver.findElement(By.id("customer.address.street"));
        String addressValue = "Republicii";
        addressElement.sendKeys(addressValue);

        WebElement cityElement = driver.findElement(By.id("customer.address.city"));
        String cityValue = "Baia Mare";
        cityElement.sendKeys(cityValue);

        WebElement stateElement = driver.findElement(By.id("customer.address.state"));
        String stateValue = "MM";
        stateElement.sendKeys(stateValue);

        WebElement zipElement = driver.findElement(By.id("customer.address.zipCode"));
        String zipValue = "123456";
        zipElement.sendKeys(zipValue);

        WebElement phoneElement = driver.findElement(By.id("customer.phoneNumber"));
        String phoneValue = "0744000111";
        phoneElement.sendKeys(phoneValue);

        WebElement ssnElement = driver.findElement(By.id("customer.ssn"));
        String ssnValue = "111";
        ssnElement.sendKeys(ssnValue);

        WebElement userElement = driver.findElement(By.id("customer.username"));
        String userNameValue = "oana" + System.currentTimeMillis();
        userElement.sendKeys(userNameValue);

        WebElement passElement = driver.findElement(By.id("customer.password"));
        String passValue = "parola123";
        passElement.sendKeys(passValue);

        WebElement confirmPassElement = driver.findElement(By.id("repeatedPassword"));
        String confirmPassValue = "parola123";
        confirmPassElement.sendKeys(confirmPassValue);

        WebElement registerBtn = driver.findElement(By.xpath("//input[@value='Register']"));
        registerBtn.click();

        WebElement billPayLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Bill Pay")));
        billPayLink.click();

        WebElement payeeNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("payee.name")));
        String payeeNameValue = "Electrica SA";
        payeeNameElement.sendKeys(payeeNameValue);

        WebElement pAddressElement = driver.findElement(By.name("payee.address.street"));
        String pAddressValue = "Strada Energiei 10";
        pAddressElement.sendKeys(pAddressValue);

        WebElement pCityElement = driver.findElement(By.name("payee.address.city"));
        String pCityValue = "Baia Mare";
        pCityElement.sendKeys(pCityValue);

        WebElement pStateElement = driver.findElement(By.name("payee.address.state"));
        String pStateValue = "Maramures";
        pStateElement.sendKeys(pStateValue);

        WebElement pZipElement = driver.findElement(By.name("payee.address.zipCode"));
        String pZipValue = "430001";
        pZipElement.sendKeys(pZipValue);

        WebElement pPhoneElement = driver.findElement(By.name("payee.phoneNumber"));
        String pPhoneValue = "0744111222";
        pPhoneElement.sendKeys(pPhoneValue);

        WebElement accountElement = driver.findElement(By.name("payee.accountNumber"));
        String accountValue = "12345";
        accountElement.sendKeys(accountValue);

        WebElement verifyAccountElement = driver.findElement(By.name("verifyAccount"));
        String verifyAccountValue = "12345";
        verifyAccountElement.sendKeys(verifyAccountValue);

        WebElement amountElement = driver.findElement(By.name("amount"));
        String amountValue = "50";
        amountElement.sendKeys(amountValue);

        WebElement fromAccountSelect = driver.findElement(By.name("fromAccountId"));
        Select selectAccount = new Select(fromAccountSelect);
        selectAccount.selectByIndex(0);

        WebElement sendPaymentBtn = driver.findElement(By.xpath("//input[@value='Send Payment']"));
        sendPaymentBtn.click();

        WebElement successTitleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(), 'Complete')]")));
        String actualTitle = successTitleElement.getText();
        String expectedTitle = "Bill Payment Complete";
        Assert.assertEquals(actualTitle, expectedTitle);

        WebElement resultMsgElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), '" + payeeNameValue + "')]")));
        String actualMsg = resultMsgElement.getText();

        System.out.println("LOG: Username-ul tau este: " + userNameValue);
        Assert.assertTrue(actualMsg.contains(payeeNameValue));

        driver.quit();
    }
}