package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.RegisterPage;
import shareData.SharedData;

public class LogOutTest extends SharedData {

    @Test
    public void metodaTest() {
        RegisterPage registerPage = new RegisterPage(driver);
        HomePage homePage = new HomePage(driver);

        registerPage.goToRegister();
        String userUniq = "user" + System.currentTimeMillis();
        registerPage.registerUserUniq("Oana", "Topan", "Republicii", "Baia Mare", "Romania", "123456", "0722000000", "123-45-678", userUniq, "Parola123!");

        Assert.assertTrue(registerPage.isRegistrationSuccessful(), "Registration failed!");

        Assert.assertTrue(homePage.isLogOutVisible(), "The Log Out button is not visible after registration!");
        homePage.clickLogOut();

        driver.get("https://parabank.parasoft.com/parabank/index.htm");

        Assert.assertTrue(homePage.isLoginVisible(), "The Login button did not appear after Logout!");
    }
}