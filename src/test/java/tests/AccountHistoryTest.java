package tests;

import modelObject.RegisterModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import shareData.SharedData;

public class AccountHistoryTest extends SharedData {

    @Test
    public void automationTest() {
        RegisterModel registerData = new RegisterModel("RegisterData.json");
        registerPage.goToRegister();

        String userUniq = "oana" + System.currentTimeMillis();
        registerPage.registerUserUniq(registerData, userUniq);
        Assert.assertTrue(registerPage.isRegistrationSuccessful(), "Registration failed!");
        Assert.assertTrue(homePage.isLogOutVisible(), "User not logged in after registration!");

        accountsOverviewPage.goToAccountsOverview();
        accountsOverviewPage.selectFirstAccount();

        Assert.assertTrue(accountActivityPage.isActivityContentPresent(), "Account Activity page not displayed!");
        Assert.assertTrue(accountActivityPage.isTransactionSectionDisplayed(),
                "Neither transaction table nor no transactions message is displayed!");
    }
}