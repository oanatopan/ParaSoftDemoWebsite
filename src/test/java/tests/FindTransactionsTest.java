package tests;

import modelObject.FindTransactionsModel;
import modelObject.OpenAccountModel;
import modelObject.RegisterModel;
import modelObject.TransferFundsModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import shareData.SharedData;
import utils.LogUtility;

public class FindTransactionsTest extends SharedData {

    @Test
    public void automationTest() {
        LogUtility.startTest("End-to-End: Find Transactions by Amount");

        RegisterModel registerData = new RegisterModel("src/test/resources/RegisterData.json");
        TransferFundsModel transferData = new TransferFundsModel("src/test/resources/TransferFunds.json");
        OpenAccountModel openData = new OpenAccountModel("src/test/resources/OpenAccountData.json");
        FindTransactionsModel findData = new FindTransactionsModel("src/test/resources/FindTransactionsData.json");

        registerPage.goToRegister();
        String uniqueUser = "txn" + System.currentTimeMillis();
        registerPage.registerUserUniq(registerData, uniqueUser);

        Assert.assertTrue(registerPage.isRegistrationSuccessful(), "Registration failed!");
        Assert.assertTrue(homePage.isLogOutVisible(), "User not logged in!");
        LogUtility.infoLog("CHECKPOINT: User registered and authenticated.");

        openAccountPage.goToOpenAccount();
        openAccountPage.openNewAccount(openData);

        Assert.assertTrue(openAccountPage.isAccountOpened(openData), "Second account was not opened!");
        LogUtility.infoLog("CHECKPOINT: New account created for transaction history.");

        transferFundsPage.goToTransferFunds();
        transferFundsPage.makeTransfer(transferData);

        Assert.assertTrue(transferFundsPage.isTransferSuccessful(transferData), "Transfer step failed!");
        LogUtility.infoLog("CHECKPOINT: Transfer completed. Searching for this amount in history...");

        Assert.assertEquals(findData.getAmount(), transferData.getAmount(),
                "The find transaction amount does not match the transfer amount from test data!");

        findTransactionsPage.goToFindTransactions();
        Assert.assertTrue(findTransactionsPage.isFindTransactionsPageLoaded(), "Find Transactions page not loaded!");

        findTransactionsPage.filterTransactionsByAmount(findData.getAmount());

        Assert.assertTrue(findTransactionsPage.isSearchResultDisplayed(),
                "No search result was displayed for amount: " + findData.getAmount());

        LogUtility.infoLog("VALIDATION: Transaction was successfully found in the history table.");
        LogUtility.finishTest("End-to-End: Find Transactions by Amount");
    }
}