package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import modelObject.FindTransactionsModel;
import modelObject.RegisterModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import shareData.SharedData;
import utils.LogUtility;


@Feature("@FEATURE - ACCOUNT MANAGEMENT")
@Story("@STORY - VIEW CUSTOMER ACCOUNTS OVERVIEW")
public class AccountsOverviewTest extends SharedData {

    @Test
    public void automationTest() {
        LogUtility.startTest("Multiple Users Accounts Overview Test");

        RegisterModel registerData = new RegisterModel("src/test/resources/RegisterData.json");
        FindTransactionsModel findData = new FindTransactionsModel("src/test/resources/FindTransactionsData.json");

        for (int i = 0; i < registerData.getFirstNames().size(); i++) {

            String dynamicFirstName = registerData.getFirstNames().get(i);
            String userUniq = "usr" + System.currentTimeMillis() + i;

            LogUtility.infoLog("--- Starting iteration " + (i + 1) + " for user: " + dynamicFirstName + " ---");

            registerPage.goToRegister();
            registerPage.registerUserUniq(registerData, userUniq, dynamicFirstName);
            Assert.assertTrue(homePage.isLogOutVisible(),
                    "Log Out not visible after registration for user: " + userUniq);


            accountsOverviewPage.goToAccountsOverview();

            Assert.assertTrue(accountsOverviewPage.isTitleDisplayed(),
                    "Accounts Overview TITLE not displayed for user: " + userUniq);


            Assert.assertTrue(accountsOverviewPage.isAccountTableDisplayed(),
                    "Accounts Overview TABLE not displayed for user: " + userUniq);

            homePage.clickLogOut();
            Assert.assertTrue(homePage.isLoginVisible(),
                    "Login button not visible after logout for user: " + userUniq);

            LogUtility.infoLog("--- Finished iteration " + (i + 1) + " successfully ---");
        }

        LogUtility.finishTest("Multiple Users Accounts Overview Test");
    }
}