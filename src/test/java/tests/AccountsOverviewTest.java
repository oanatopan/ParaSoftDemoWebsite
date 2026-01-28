package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AccountsOverviewPage;
import pages.HomePage;
import pages.RegisterPage;
import shareData.SharedData;

import java.util.Arrays;
import java.util.List;

public class AccountsOverviewTest extends SharedData {

    @Test
    public void metodaTest() {
        RegisterPage registerPage = new RegisterPage(driver);
        AccountsOverviewPage overviewPage = new AccountsOverviewPage(driver);
        HomePage homePage = new HomePage(driver);

        List<String> firstNames = Arrays.asList("Oana 1", "Oana 2", "Oana 3");

        for (int i = 0; i < firstNames.size(); i++) {
            registerPage.goToRegister();

            String registerUserUniq = "oana" + System.currentTimeMillis() + i;
            String password = "Parola123!" + (i + 1) + "!";

            registerPage.registerUserUniq("Oana", "Topan", "Republicii", "Baia Mare", "Romania", "123456", "0722000000","123-45-678",registerUserUniq, "Parola123!");

            overviewPage.goToAccountsOverview();

            Assert.assertTrue(overviewPage.isTitleDisplayed(),
                    "The Accounts Overview page did not load correctly for: " + registerUserUniq);

            Assert.assertEquals(overviewPage.getPageTitleText(), "Accounts Overview",
                    "The page title does not match!");

            homePage.clickLogOut();

            System.out.println("SUCCESS: User " + registerUserUniq + " a fost verificat complet.");
        }
    }
}