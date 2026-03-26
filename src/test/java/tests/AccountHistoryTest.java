package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import modelObject.FindTransactionsModel;
import modelObject.RegisterModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import shareData.SharedData;
import utils.LogUtility;

@Feature("@FEATURE - ACCOUNT ACTIVITY")
@Story("@STORY - VIEW ACCOUNT ACTIVITY")

public class AccountHistoryTest extends SharedData {

    @Test
    public void automationTest() {
        LogUtility.startTest("Account History Visibility Test");

        RegisterModel registerData = new RegisterModel("src/test/resources/RegisterData.json");
        FindTransactionsModel filterData = new FindTransactionsModel("src/test/resources/FindTransactionsData.json");

        registerPage.goToRegister();
        String userUniq = "oana" + System.currentTimeMillis();
        registerPage.registerUserUniq(registerData, userUniq);

        Assert.assertTrue(registerPage.isRegistrationSuccessful(), "Registration failed!");

        accountsOverviewPage.goToAccountsOverview();
        Assert.assertTrue(accountsOverviewPage.isAccountTableDisplayed(), "The accounts table is not displayed!");

        accountsOverviewPage.selectFirstAccount();
        Assert.assertTrue(accountActivityPage.isActivityContentPresent(), "Account Activity page not displayed!");
        Assert.assertTrue(accountActivityPage.isPageTitleCorrect(filterData), "Account Activity title is not correct!");

        accountActivityPage.filterTransactions(filterData);

        Assert.assertTrue(accountActivityPage.isTransactionSectionDisplayed(),
                "Neither transaction table nor the no-transactions message is displayed!");

        LogUtility.finishTest("Account History Visibility Test");
    }
}