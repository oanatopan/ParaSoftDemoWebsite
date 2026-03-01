package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import shareData.SharedData;

public class TransferFundsTest extends SharedData {

    @Test
    public void automationTest() {

        registerPage.goToRegister();

        String userUnic = "transfer" + System.currentTimeMillis();

        registerPage.registerUserUniq(
                "Oana",
                "Topan",
                "Republicii",
                "Baia Mare",
                "Romania",
                "123456",
                "0722000000",
                "123-45-678",
                userUnic,
                "Parola123!"
        );

        Assert.assertTrue(
                registerPage.isRegistrationSuccessful(),
                "Registration failed!"
        );

        transferFundsPage.goToTransferFunds();
        transferFundsPage.makeTransfer("50");

        Assert.assertTrue(
                transferFundsPage.isTransferSuccessful(),
                "Transfer was not successful!"
        );
    }
}