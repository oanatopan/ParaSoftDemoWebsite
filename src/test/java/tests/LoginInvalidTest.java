package tests;

import modelObject.LoginModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import shareData.SharedData;
import utils.LogUtility;

public class LoginInvalidTest extends SharedData {

    @Test
    public void automationTest() {
        LogUtility.startTest("Negative Login Test - Invalid Credentials");

        LoginModel loginData = new LoginModel("src/test/resources/LoginData.json");

        loginPage.loginInvalid(loginData);

        Assert.assertTrue(loginPage.isErrorDisplayed(),
                "The error message for invalid login credentials is not displayed.");

        LogUtility.infoLog("VALIDATION: The error message for invalid credentials is displayed successfully.");
        LogUtility.finishTest("Negative Login Test - Invalid Credentials");
    }
}