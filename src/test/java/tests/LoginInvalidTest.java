package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import shareData.SharedData;

public class LoginInvalidTest extends SharedData {

    @Test
    public void metodaTest() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.loginProcess("utilizator_inexistent", "parola123");

        Assert.assertTrue(loginPage.isErrorDisplayed(), "Mesajul de eroare NU a apărut după un login invalid!");

        System.out.println("SUCCESS: Testul negativ de Login a trecut. Eroarea este vizibilă.");
    }
}