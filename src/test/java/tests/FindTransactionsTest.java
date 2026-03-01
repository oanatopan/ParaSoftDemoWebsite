package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import shareData.SharedData;

public class FindTransactionsTest extends SharedData {

    @Test
    public void automationTest() {
        registerPage.goToRegister();

        String userUnic = "txn" + System.currentTimeMillis();
        registerPage.registerUserUniq("Oana", "Topan", "Republicii", "Baia Mare", "Romania", "123456", "0722000000", "123-45-678", userUnic, "Parola123!"
        );

        accountsOverviewPage.goToAccountsOverview();
        accountsOverviewPage.selectFirstAccount();

        accountActivityPage.filterTransactions("All", "All");

        Assert.assertTrue(accountActivityPage.isActivityContentPresent(),
                "Account Activity page not displayed!");
        Assert.assertTrue(accountActivityPage.isTransactionTableDisplayed(),
                "Transaction table not displayed!");
    }
}