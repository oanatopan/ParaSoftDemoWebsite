package tests;

import modelObject.RegisterModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import shareData.SharedData;

public class LogOutTest extends SharedData {

    @Test
    public void automationTest() {
        RegisterModel registerData = new RegisterModel("RegisterData.json");

        registerPage.goToRegister();

        String userUniq = "logout" + System.currentTimeMillis();

        registerPage.registerUserUniq(registerData, userUniq);

        Assert.assertTrue(registerPage.isRegistrationSuccessful(), "Registration failed!");
        Assert.assertTrue(homePage.isLogOutVisible(), "Log Out not visible after registration!");

        homePage.clickLogOut();

        Assert.assertTrue(homePage.isLoginVisible(), "Login button not visible after logout!");
    }
}