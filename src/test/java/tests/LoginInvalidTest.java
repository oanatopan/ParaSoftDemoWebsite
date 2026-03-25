package tests;

import modelObject.LoginModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import shareData.SharedData;

public class LoginInvalidTest extends SharedData {

    @Test
    public void automationTest() {
        LoginModel loginData = new LoginModel("LoginData.json");

        loginPage.loginInvalid(loginData);

        Assert.assertTrue(loginPage.isErrorDisplayed(), "Error message not displayed for invalid login!");
    }
}