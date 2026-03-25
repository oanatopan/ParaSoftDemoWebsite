package tests;

import modelObject.OpenAccountModel;
import modelObject.RegisterModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import shareData.SharedData;

public class OpenAccountTest extends SharedData {

    @Test
    public void automationTest() {
        RegisterModel registerData = new RegisterModel("RegisterData.json");
        OpenAccountModel openData = new OpenAccountModel("OpenAccountData.json");

        registerPage.goToRegister();
        String userUniq = "open" + System.currentTimeMillis();
        registerPage.registerUserUniq(registerData, userUniq);

        Assert.assertTrue(registerPage.isRegistrationSuccessful(), "Registration failed in OpenAccountTest!");
        Assert.assertTrue(homePage.isLogOutVisible(), "User not logged in in OpenAccountTest!");

        openAccountPage.goToOpenAccount();
        openAccountPage.openNewAccount(openData);

        Assert.assertTrue(openAccountPage.isAccountOpened(openData), "Open account confirmation mismatch!");
        Assert.assertTrue(openAccountPage.getNewAccountIdText().length() > 0, "New account id was not generated!");
    }
}