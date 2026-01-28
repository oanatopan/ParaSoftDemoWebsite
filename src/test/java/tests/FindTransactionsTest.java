package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AccountActivityPage;
import pages.AccountsOverviewPage;
import pages.RegisterPage;
import shareData.SharedData;

public class FindTransactionsTest extends SharedData {

    @Test
    public void metodaTest() {
        driver.get("https://parabank.parasoft.com/parabank/index.htm");

        RegisterPage registerPage = new RegisterPage(driver);
        AccountsOverviewPage accountsOverviewPage = new AccountsOverviewPage(driver);
        AccountActivityPage accountActivityPage = new AccountActivityPage(driver);
        registerPage.goToRegister();
        String userUniq = "oana" + System.currentTimeMillis();
        registerPage.registerUserUniq("Oana", "Topan", "Republicii", "Baia Mare", "Romania", "123456", "0722000000","123-45-678",userUniq, "Parola123!");

        accountsOverviewPage.goToAccountsOverview();
        accountsOverviewPage.refreshUntilTableVisible();
        accountsOverviewPage.selectFirstAccount();

        accountActivityPage.filterTransactions("All", "All");

        String actualTitle = accountActivityPage.getPageTitleText();
        Assert.assertEquals(actualTitle, "Account Details", "Titlul paginii de activitate nu coincide!");
        Assert.assertTrue(accountActivityPage.isActivityContentPresent(),
                "The activity page did not load correctly for the user: " + userUniq);

        System.out.println("TEST PASSED for user: " + userUniq);
    }
}