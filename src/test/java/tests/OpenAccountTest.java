package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.OpenAccountPage;
import pages.RegisterPage;
import shareData.SharedData;

public class OpenAccountTest extends SharedData {

    @Test
    public void openAccountMetodaTest() {
        RegisterPage registerPage = new RegisterPage(driver);
        OpenAccountPage openAccountPage = new OpenAccountPage(driver);

        registerPage.goToRegister();
        String userUnic = "oanaOA" + System.currentTimeMillis();
        registerPage.registerUserUniq("Oana", "Topan", "Republicii", "Baia Mare", "Romania", "123456", "0722000000","123-45-678",userUnic, "Parola123!");
        Assert.assertTrue(registerPage.isRegistrationSuccessful(), "Registration failed!");

        openAccountPage.goToOpenAccount();

        openAccountPage.openNewAccount("SAVINGS");

        Assert.assertTrue(openAccountPage.isSuccessDisplayed(), "The new account has not been opened!");
    }
}