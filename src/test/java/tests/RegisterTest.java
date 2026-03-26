package tests;

import modelObject.RegisterModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import shareData.SharedData;
import utils.LogUtility;

public class RegisterTest extends SharedData {

    @Test
    public void automationTest() {
        LogUtility.startTest("New User Registration and Login Verification");

        RegisterModel registerData = new RegisterModel("src/test/resources/RegisterData.json");

        registerPage.goToRegister();
        String userUniq = "reg" + System.currentTimeMillis();
        LogUtility.infoLog("STEP: Prepared unique username for registration: " + userUniq);

        registerPage.registerUserUniq(registerData, userUniq);

        Assert.assertTrue(registerPage.isRegistrationSuccessful(), "Registration failed!");
        Assert.assertTrue(homePage.isLogOutVisible(), "Log Out not visible after registration!");
        LogUtility.infoLog("CHECKPOINT: Registration successful. Session is active.");

        homePage.clickLogOut();
        LogUtility.infoLog("STEP: User logged out to verify account persistence.");

        loginPage.loginProcess(userUniq, registerData.getPassword());

        Assert.assertTrue(homePage.isLogOutVisible(), "Login failed for the newly created user: " + userUniq);
        LogUtility.infoLog("VALIDATION: New account is fully functional. User can log back in.");

        LogUtility.finishTest("New User Registration and Login Verification");
    }
}