package tests;

import modelObject.RegisterModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import shareData.SharedData;

public class RegisterTest extends SharedData {

    @Test
    public void automationTest() {
        RegisterModel registerData = new RegisterModel("RegisterData.json");
        registerPage.goToRegister();

        String userUniq = "reg" + System.currentTimeMillis();

        registerPage.registerUserUniq(registerData, userUniq);

        Assert.assertTrue(registerPage.isRegistrationSuccessful(), "Registration failed!");
        Assert.assertTrue(homePage.isLogOutVisible(), "Log Out not visible after registration!");

        homePage.clickLogOut();
        loginPage.loginProcess(userUniq, registerData.getPassword());

        Assert.assertTrue(homePage.isLogOutVisible(), "Login valid failed (Log Out not visible)!");
    }
}