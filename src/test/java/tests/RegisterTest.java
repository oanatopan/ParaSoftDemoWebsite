package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RegisterPage;
import shareData.SharedData;

public class RegisterTest extends SharedData {

    @Test
    public void metodaTest() {
        RegisterPage registerPage = new RegisterPage(driver);

        registerPage.goToRegister();

        String userUniq = "oana" + System.currentTimeMillis();

        registerPage.registerUserUniq("Oana", "Topan", "Republicii", "Baia Mare", "Romania", "123456", "0722000000","123-45-678",userUniq, "Parola123!");

        Assert.assertTrue(registerPage.isRegistrationSuccessful(),
                "Registration failed for user: " + userUniq);

        System.out.println("SUCCES: User " + userUniq + " was created and verified.");
    }
}