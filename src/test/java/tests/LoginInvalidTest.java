package tests;

import helpMethods.ElementsMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginInvalidTest {

    public WebDriver driver;
    public ElementsMethods elementsMethods;

    @Test
    public void metodaTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://parabank.parasoft.com/parabank/index.htm");

        elementsMethods = new ElementsMethods(driver);

        elementsMethods.fillElement(driver.findElement(By.name("username")), "utilizator_inexistent");
        elementsMethods.fillElement(driver.findElement(By.name("password")), "parola123");

        elementsMethods.clickJS(driver.findElement(By.xpath("//input[@value='Log In']")));


        String errorMsg = driver.findElement(By.xpath("//p[@class='error']")).getText();
        Assert.assertTrue(errorMsg.length() > 0);

        driver.quit();
    }
}