package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import modelObject.LoginModel;
import modelObject.RegisterModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import shareData.SharedData;
import utils.LogUtility;
@Feature("@FEATURE - AUTHENTICATION")
@Story("@STORY - USER LOGIN WITH VALID CREDENTIALS")
public class LoginValidTest extends SharedData {

    @Test
    public void automationTest() {
        LogUtility.startTest("Positive Login Test - New Registered User");

        LoginModel loginData = new LoginModel("src/test/resources/LoginData.json");
        RegisterModel registerData = new RegisterModel("src/test/resources/RegisterData.json");

        registerPage.goToRegister();
        String userUniq = "log" + System.currentTimeMillis();
        registerPage.registerUserUniq(registerData, userUniq);

        Assert.assertTrue(registerPage.isRegistrationSuccessful(), "Registration failed in LoginValidTest!");
        Assert.assertTrue(homePage.isLogOutVisible(), "User not logged in after registration!");
        LogUtility.infoLog("STEP: New user created: " + userUniq);

        homePage.clickLogOut();
        Assert.assertTrue(homePage.isLoginVisible(), "Login button not visible after logout!");
        LogUtility.infoLog("STEP: User logged out successfully. Ready for re-login.");

        loginPage.loginProcess(userUniq, registerData.getPassword());

        Assert.assertTrue(homePage.isLogOutVisible(), "Valid login failed for user: " + userUniq);
        LogUtility.infoLog("VALIDATION: Login successful. Logout link is visible again.");

        LogUtility.finishTest("Positive Login Test - New Registered User");
    }
}