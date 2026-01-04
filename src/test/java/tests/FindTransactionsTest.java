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

public class FindTransactionsTest {

    public WebDriver driver;

    @Test
    public void metodaTest() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.get("https://parabank.parasoft.com/parabank/register.htm");

        WebElement firstNameElement = driver.findElement(By.id("customer.firstName"));
        String firstNameValue = "Oana";
        firstNameElement.sendKeys(firstNameValue);

        WebElement lastNameElement = driver.findElement(By.id("customer.lastName"));
        String lastNameValue = "Topan";
        lastNameElement.sendKeys(lastNameValue);

        WebElement streetElement = driver.findElement(By.id("customer.address.street"));
        String streetValue = "Strada Republicii";
        streetElement.sendKeys(streetValue);

        WebElement cityElement = driver.findElement(By.id("customer.address.city"));
        String cityValue = "Baia Mare";
        cityElement.sendKeys(cityValue);

        WebElement stateElement = driver.findElement(By.id("customer.address.state"));
        String stateValue = "MM";
        stateElement.sendKeys(stateValue);

        WebElement zipElement = driver.findElement(By.id("customer.address.zipCode"));
        String zipValue = "430000";
        zipElement.sendKeys(zipValue);

        WebElement phoneElement = driver.findElement(By.id("customer.phoneNumber"));
        String phoneValue = "0744123456";
        phoneElement.sendKeys(phoneValue);

        WebElement ssnElement = driver.findElement(By.id("customer.ssn"));
        String ssnValue = "123";
        ssnElement.sendKeys(ssnValue);

        WebElement userElement = driver.findElement(By.id("customer.username"));
        String userUnic = "oana" + System.currentTimeMillis();
        userElement.sendKeys(userUnic);

        WebElement passwordElement = driver.findElement(By.id("customer.password"));
        String passValue = "Parola123!";
        passwordElement.sendKeys(passValue);

        WebElement confirmPassElement = driver.findElement(By.id("repeatedPassword"));
        String confirmPassValue = "Parola123!";
        confirmPassElement.sendKeys(confirmPassValue);

        WebElement registerButton = driver.findElement(By.xpath("//input[@value='Register']"));
        js.executeScript("arguments[0].click();", registerButton);

        WebElement accountsOverviewLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Accounts Overview")));
        js.executeScript("arguments[0].click();", accountsOverviewLink);

        for (int i = 0; i < 3; i++) {
            if (driver.findElements(By.id("accountTable")).isEmpty()) {
                driver.navigate().refresh();
                wait.until(ExpectedConditions.presenceOfElementLocated(By.id("rightPanel")));
            } else {
                break;
            }
        }

        WebElement accountLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@id='accountTable']/tbody/tr[1]/td[1]/a")));
        js.executeScript("arguments[0].click();", accountLink);

        WebElement monthSelectElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("month")));
        String monthValue = "All";
        Select monthDropdown = new Select(monthSelectElement);
        monthDropdown.selectByVisibleText(monthValue);

        WebElement typeSelectElement = driver.findElement(By.id("transactionType"));
        String typeValue = "All";
        Select typeDropdown = new Select(typeSelectElement);
        typeDropdown.selectByVisibleText(typeValue);

        WebElement goButton = driver.findElement(By.xpath("//input[@value='Go']"));
        js.executeScript("arguments[0].click();", goButton);

        WebElement activityTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Account Activity']")));
        String actualTitle = activityTitle.getText();
        String expectedTitle = "Account Activity";
        Assert.assertEquals(actualTitle, expectedTitle);

        boolean isTablePresent = !driver.findElements(By.id("transactionTable")).isEmpty();
        boolean isNoTransactionsMsgPresent = !driver.findElements(By.xpath("//*[contains(text(), 'No transactions found')]")).isEmpty();

        Assert.assertTrue(isTablePresent || isNoTransactionsMsgPresent, "Pagina de activitate nu s-a incarcat corect!");

        System.out.println("TEST PASSED: Navigarea la activitatea contului a reusit pentru " + userUnic);

        driver.quit();
    }
}