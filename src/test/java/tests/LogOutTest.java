package tests;

import modelObject.RegisterModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import shareData.SharedData;
import utils.LogUtility;

public class LogOutTest extends SharedData {

    @Test
    public void automationTest() {
        LogUtility.startTest("Session Termination Test (Log Out)");

        RegisterModel registerData = new RegisterModel("src/test/resources/RegisterData.json");

        registerPage.goToRegister();
        String userUniq = "logout" + System.currentTimeMillis();
        registerPage.registerUserUniq(registerData, userUniq);

        Assert.assertTrue(registerPage.isRegistrationSuccessful(), "Registration failed!");
        Assert.assertTrue(homePage.isLogOutVisible(), "Log Out not visible after registration!");
        LogUtility.infoLog("STEP: User is currently logged in and session is active.");

        homePage.clickLogOut();
        LogUtility.infoLog("STEP: User clicked the Log Out link.");

        Assert.assertTrue(homePage.isLoginVisible(), "Login button not visible after logout!");

        Assert.assertFalse(homePage.isLogOutVisible(), "Log Out link is still visible after session termination!");

        LogUtility.infoLog("VALIDATION: Session successfully terminated. User redirected to login state.");
        LogUtility.finishTest("Session Termination Test (Log Out)");
    }
}