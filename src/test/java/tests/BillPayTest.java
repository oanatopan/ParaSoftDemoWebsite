package tests;

import modelObject.BillPayModel;
import modelObject.RegisterModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import shareData.SharedData;

public class BillPayTest extends SharedData {

    @Test
    public void automationTest() {
        RegisterModel registerData = new RegisterModel("RegisterData.json");
        BillPayModel billData = new BillPayModel("BillPayData.json");

        registerPage.goToRegister();
        String uniqueUser = "bill" + System.currentTimeMillis();
        registerPage.registerUserUniq(registerData, uniqueUser);

        Assert.assertTrue(registerPage.isRegistrationSuccessful(), "Registration failed in BillPayTest!");
        Assert.assertTrue(homePage.isLogOutVisible(), "User not logged in in BillPayTest!");

        billPayPage.goToBillPay();
        billPayPage.payBill(billData);

        Assert.assertTrue(billPayPage.isPaymentSuccessful(), "Bill payment was not successful!");
    }
}