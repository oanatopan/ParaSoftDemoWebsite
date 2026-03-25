package tests;

import modelObject.RegisterModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import shareData.SharedData;

public class LoginValidTest extends SharedData {

    @Test
    public void automationTest() {
        RegisterModel registerData = new RegisterModel("RegisterData.json");

        registerPage.goToRegister();
        String userUniq = "log" + System.currentTimeMillis();
        registerPage.registerUserUniq(registerData, userUniq);

        Assert.assertTrue(registerPage.isRegistrationSuccessful(), "Registration failed in LoginValidTest!");
        Assert.assertTrue(homePage.isLogOutVisible(), "User not logged in after registration!");

        homePage.clickLogOut();
        Assert.assertTrue(homePage.isLoginVisible(), "Login button not visible after logout!");

        loginPage.loginProcess(userUniq, registerData.getPassword());

        Assert.assertTrue(homePage.isLogOutVisible(), "Valid login failed!");
    }
}