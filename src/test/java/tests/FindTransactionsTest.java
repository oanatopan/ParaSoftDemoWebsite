package tests;

import modelObject.OpenAccountModel;
import modelObject.RegisterModel;
import modelObject.TransferFundsModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import shareData.SharedData;

public class FindTransactionsTest extends SharedData {

    @Test
    public void automationTest() {

        RegisterModel registerData = new RegisterModel("RegisterData.json");
        TransferFundsModel transferData = new TransferFundsModel("TransferFunds.json");
        OpenAccountModel openData = new OpenAccountModel("OpenAccountData.json");

        registerPage.goToRegister();
        String uniqueUser = "txn" + System.currentTimeMillis();
        registerPage.registerUserUniq(registerData, uniqueUser);

        Assert.assertTrue(registerPage.isRegistrationSuccessful(),
                "Registration failed in FindTransactionsTest!");
        Assert.assertTrue(homePage.isLogOutVisible(),
                "User not logged in after registration in FindTransactionsTest!");

        openAccountPage.goToOpenAccount();
        openAccountPage.openNewAccount(openData);

        Assert.assertTrue(openAccountPage.isAccountOpened(openData),
                "Second account was not opened before transfer!");
        Assert.assertTrue(openAccountPage.getNewAccountIdText().length() > 0,
                "New account id was not generated!");

        transferFundsPage.goToTransferFunds();
        transferFundsPage.makeTransfer(transferData);

        Assert.assertTrue(transferFundsPage.isTransferSuccessful(),
                "Transfer step failed before transaction search!");

        findTransactionsPage.goToFindTransactions();

        Assert.assertTrue(findTransactionsPage.isFindTransactionsPageLoaded(),
                "Find Transactions page not loaded!");

        findTransactionsPage.filterTransactionsByAmount(transferData.getAmount());

        Assert.assertTrue(findTransactionsPage.isSearchResultDisplayed(),
                "No search result was displayed after filtering by amount!");
    }
}