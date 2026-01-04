package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginInvalidTest {

    public WebDriver driver;

    @Test
    public void metodaTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        driver.get("https://parabank.parasoft.com/parabank/index.htm");

        WebElement usernameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        String userInexistent = "utilizator_inexistent";
        usernameElement.sendKeys(userInexistent);

        WebElement passwordElement = driver.findElement(By.name("password"));
        String parolaInvalida = "parola123";
        passwordElement.sendKeys(parolaInvalida);

        WebElement loginButton = driver.findElement(By.xpath("//input[@value='Log In']"));
        js.executeScript("arguments[0].click();", loginButton);

        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error")));
        Assert.assertTrue(errorElement.getText().length() > 0);

        driver.quit();
    }
}