package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AccountsOverviewPage;
import shareData.SharedData;

public class AccountHistoryTest extends SharedData {

    @Test
    public void metodaTest() {
        registerPage.goToRegister();
        String userUnic = "oana" + System.currentTimeMillis();
        registerPage.registerUserUniq("Oana", "Topan", "Republicii", "Baia Mare", "Romania", "123456", "0722000000","123-45-678",userUnic, "Parola123!");
        AccountsOverviewPage overviewPage = new AccountsOverviewPage(driver);

        accountsOverviewPage.goToAccountsOverview();
        accountsOverviewPage.selectFirstAccount();

        System.out.println("TEST PASSED: User created and account accessed.");
    }
}