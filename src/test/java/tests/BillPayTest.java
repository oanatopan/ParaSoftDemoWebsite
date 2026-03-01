package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import shareData.SharedData;

public class BillPayTest extends SharedData {

    @Test
    public void automationTest() {
        registerPage.goToRegister();

        String userUnic = "bill" + System.currentTimeMillis();
        registerPage.registerUserUniq(
                "Oana", "Topan", "Republicii", "Baia Mare", "Romania",
                "123456", "0722000000", "123-45-678", userUnic, "Parola123!"
        );

        Assert.assertTrue(registerPage.isRegistrationSuccessful(), "Registration failed!");

        billPayPage.goToBillPay();
        billPayPage.payBill(
                "Electrica SA",
                "Str. Energiei 10",
                "Baia Mare",
                "Maramures",
                "430001",
                "0744111222",
                "12345",
                "12345",
                "50"
        );

        Assert.assertTrue(billPayPage.isPaymentSuccessful(), "Bill payment was not successful!");
    }
}