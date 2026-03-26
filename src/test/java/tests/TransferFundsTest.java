package tests;

import modelObject.OpenAccountModel;
import modelObject.RegisterModel;
import modelObject.TransferFundsModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import shareData.SharedData;
import utils.LogUtility;

public class TransferFundsTest extends SharedData {

    @Test
    public void automationTest() {
        LogUtility.startTest("Internal Funds Transfer E2E Test");

        TransferFundsModel transferData = new TransferFundsModel("src/test/resources/TransferFunds.json");
        RegisterModel registerData = new RegisterModel("src/test/resources/RegisterData.json");
        OpenAccountModel openData = new OpenAccountModel("src/test/resources/OpenAccountData.json");

        registerPage.goToRegister();
        String userUniq = "user" + System.currentTimeMillis();
        registerPage.registerUserUniq(registerData, userUniq);

        Assert.assertTrue(registerPage.isRegistrationSuccessful(), "Registration failed!");
        LogUtility.infoLog("STEP: User registered with username: " + userUniq);

        homePage.clickLogOut();
        loginPage.loginProcess(userUniq, registerData.getPassword());
        Assert.assertTrue(homePage.isLogOutVisible(), "Login failed after registration!");
        LogUtility.infoLog("CHECKPOINT: User successfully logged in. Session is valid.");

        openAccountPage.goToOpenAccount();
        openAccountPage.openNewAccount(openData);

        Assert.assertTrue(openAccountPage.isAccountOpened(openData), "Second account was not opened!");
        String newAccount = openAccountPage.getNewAccountIdText();
        LogUtility.infoLog("CHECKPOINT: Destination account opened with ID: " + newAccount);

        transferFundsPage.goToTransferFunds();
        LogUtility.infoLog("STEP: Initiating transfer of " + transferData.getAmount() + "$");
        transferFundsPage.makeTransfer(transferData);

        Assert.assertTrue(transferFundsPage.isTransferSuccessful(transferData), "Transfer step failed!");

        String actualAmount = transferFundsPage.getTransferredAmountText();
        Assert.assertTrue(actualAmount.contains(transferData.getAmount()),
                "Transferred amount mismatch! Expected: " + transferData.getAmount() + " but found: " + actualAmount);

        LogUtility.infoLog("VALIDATION: Transfer of " + transferData.getAmount() + "$ was successful.");
        LogUtility.finishTest("Internal Funds Transfer E2E Test");
    }
}