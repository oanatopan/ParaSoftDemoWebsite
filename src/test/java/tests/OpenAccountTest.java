package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import modelObject.OpenAccountModel;
import modelObject.RegisterModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import shareData.SharedData;
import utils.LogUtility;

public class OpenAccountTest extends SharedData {
    @Feature("@FEATURE - ACCOUNT MANAGEMENT")
    @Story("@STORY - OPEN NEW BANK ACCOUNT")
    @Test
    public void automationTest() {
        LogUtility.startTest("Open New Account Test");

        OpenAccountModel openData = new OpenAccountModel("src/test/resources/OpenAccountData.json");
        RegisterModel registerData = new RegisterModel("src/test/resources/RegisterData.json");

        registerPage.goToRegister();
        String userUniq = "user" + System.currentTimeMillis();
        registerPage.registerUserUniq(registerData, userUniq);

        Assert.assertTrue(registerPage.isRegistrationSuccessful(), "Registration failed!");

        openAccountPage.goToOpenAccount();
        openAccountPage.openNewAccount(openData);

        Assert.assertTrue(openAccountPage.isAccountOpened(openData),
                "The account opening confirmation message is incorrect!");

        String newAccountId = openAccountPage.getNewAccountIdText();
        Assert.assertFalse(newAccountId.isEmpty(), "The new account ID was not generated!");

        LogUtility.finishTest("Open New Account Test");
    }
}