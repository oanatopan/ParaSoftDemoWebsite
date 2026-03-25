package tests;

import modelObject.RegisterModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import shareData.SharedData;

public class AccountsOverviewTest extends SharedData {

    @Test
    public void automationTest() {
        RegisterModel registerData = new RegisterModel("RegisterData.json");

        for (int i = 0; i < registerData.getFirstNames().size(); i++) {
            registerPage.goToRegister();

            String dynamicFirstName = registerData.getFirstNames().get(i);
            String userUniq = "usr" + System.currentTimeMillis() + i;

            registerPage.registerUserUniq(registerData, userUniq, dynamicFirstName);

            Assert.assertTrue(homePage.isLogOutVisible(),
                    "Log Out not visible after registration for user: " + userUniq);

            accountsOverviewPage.goToAccountsOverview();
            Assert.assertTrue(accountsOverviewPage.isAccountTableDisplayed(),
                    "Accounts Overview page not displayed for user: " + userUniq);

            homePage.clickLogOut();
            Assert.assertTrue(homePage.isLoginVisible(),
                    "Login button not visible after logout for user: " + userUniq);
        }
    }
}