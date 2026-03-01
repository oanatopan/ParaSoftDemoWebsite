package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import shareData.SharedData;

import java.util.Arrays;
import java.util.List;

public class AccountsOverviewTest extends SharedData {

    @Test
    public void automationTest() {
        List<String> users = Arrays.asList("Oana1", "Oana2", "Oana3");

        for (int i = 0; i < users.size(); i++) {
            registerPage.goToRegister();

            String userUnic = "user" + System.currentTimeMillis() + i;

            registerPage.registerUserUniq(users.get(i), "Topan", "Republicii", "Baia Mare", "Romania", "123456", "072200000" + i, "123-45-67" + i, userUnic, "Parola123!");

            accountsOverviewPage.goToAccountsOverview();

            Assert.assertTrue(accountsOverviewPage.isTitleDisplayed(),
                    "Accounts Overview page not displayed!");
            Assert.assertEquals(
                    accountsOverviewPage.getPageTitleText(),
                    "Accounts Overview",
                    "Page title mismatch!"
            );

            homePage.clickLogOut();
        }
    }
}