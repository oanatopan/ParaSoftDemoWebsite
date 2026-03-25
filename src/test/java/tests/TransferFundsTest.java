package tests;

import modelObject.OpenAccountModel;
import modelObject.RegisterModel;
import modelObject.TransferFundsModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import shareData.SharedData;

public class TransferFundsTest extends SharedData {

    @Test
    public void automationTest() {
        RegisterModel registerData = new RegisterModel("RegisterData.json");
        TransferFundsModel transferData = new TransferFundsModel("TransferFunds.json");
        OpenAccountModel openData = new OpenAccountModel("OpenAccountData.json");

        registerPage.goToRegister();
        String userUniq = "oana" + System.currentTimeMillis();
        registerPage.registerUserUniq(registerData, userUniq);

        Assert.assertTrue(registerPage.isRegistrationSuccessful(), "Registration failed in TransferFundsTest!");
        Assert.assertTrue(homePage.isLogOutVisible(), "User not logged in after registration in TransferFundsTest!");

        homePage.clickLogOut();
        Assert.assertTrue(homePage.isLoginVisible(), "Login button not visible after logout in TransferFundsTest!");

        loginPage.loginProcess(userUniq, registerData.getPassword());
        Assert.assertTrue(homePage.isLogOutVisible(), "User not logged in in TransferFundsTest!");

        openAccountPage.goToOpenAccount();
        openAccountPage.openNewAccount(openData);

        Assert.assertTrue(openAccountPage.isAccountOpened(openData), "Second account was not opened before transfer!");
        Assert.assertTrue(openAccountPage.getNewAccountIdText().length() > 0, "New account id was not generated!");

        transferFundsPage.goToTransferFunds();
        transferFundsPage.makeTransfer(transferData);

        Assert.assertTrue(transferFundsPage.isTransferSuccessful(), "Transfer was not successful!");
        Assert.assertTrue(
                transferFundsPage.getTransferredAmountText().contains(transferData.getAmount()),
                "Transferred amount is not correct! Actual: " + transferFundsPage.getTransferredAmountText()
        );
    }
}