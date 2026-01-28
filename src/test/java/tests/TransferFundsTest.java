package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RegisterPage;
import pages.TransferFundsPage;
import shareData.SharedData;

public class TransferFundsTest extends SharedData {

    @Test
    public void transferBaniTest() {
        RegisterPage registerPage = new RegisterPage(driver);
        TransferFundsPage transferPage = new TransferFundsPage(driver);

        registerPage.goToRegister();

        String uniqueUser = "user" + System.currentTimeMillis();
        registerPage.registerUserUniq("Oana", "Topan", "Republicii", "Baia Mare", "Romania", "123456", "0722000000","123-45-678",uniqueUser, "Parola123!");

        Assert.assertTrue(registerPage.isRegistrationSuccessful(), "Registration failed!");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        transferPage.goToTransferFunds();

        transferPage.makeTransfer("50");

        String actualMessage = transferPage.getResultMessage();
        Assert.assertTrue(actualMessage.contains("Transfer Complete"),
                "The success message did not appear! Current message: " + actualMessage);

        System.out.println("LOG: Transfer successfully completed for user: " + uniqueUser);
    }
}