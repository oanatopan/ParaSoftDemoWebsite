package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import modelObject.BillPayModel;
import modelObject.RegisterModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import shareData.SharedData;
import utils.LogUtility;

@Feature("@FEATURE - BILL PAYMENT")
@Story("@STORY - PAY BILL FROM BANK ACCOUNT")
// AM ȘTERS IMPORTURILE REPETATE DE AICI - Ele provocau eroarea [8,1]
public class BillPayTest extends SharedData {

    @Test
    public void automationTest() {
        LogUtility.startTest("Bill Payment Functional Test");

        BillPayModel billData = new BillPayModel("src/test/resources/BillPayData.json");
        RegisterModel registerData = new RegisterModel("src/test/resources/RegisterData.json");

        registerPage.goToRegister();
        String uniqueUser = "bill" + System.currentTimeMillis();
        registerPage.registerUserUniq(registerData, uniqueUser);

        Assert.assertTrue(registerPage.isRegistrationSuccessful(), "Registration failed in BillPayTest!");
        Assert.assertTrue(homePage.isLogOutVisible(), "User not logged in in BillPayTest!");
        LogUtility.infoLog("STEP: User registered and logged in successfully");

        billPayPage.goToBillPay();
        LogUtility.infoLog("STEP: Navigating to Bill Pay page");

        billPayPage.payBill(billData);

        Assert.assertTrue(billPayPage.isPaymentSuccessful(), "Bill payment was not successful!");
        LogUtility.infoLog("VALIDATION: Bill payment completed and success message is displayed");

        LogUtility.finishTest("Bill Payment Functional Test");
    }
}