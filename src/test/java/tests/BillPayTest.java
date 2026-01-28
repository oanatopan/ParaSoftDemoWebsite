package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BillPayPage;
import pages.RegisterPage;
import shareData.SharedData;

public class BillPayTest extends SharedData {
    @Test
    public void metodaTest() {
        driver.get("https://parabank.parasoft.com/parabank/index.htm");

        RegisterPage registerPage = new RegisterPage(driver);
        BillPayPage billPayPage = new BillPayPage(driver);

        registerPage.goToRegister();
        String userUnic = "oanaBP" + System.currentTimeMillis();

        registerPage.registerUserUniq("Oana", "Topan", "Republicii", "Baia Mare", "Romania", "123456", "0722000000","123-45-678", userUnic, "Parola123!");

        billPayPage.goToBillPay();
        billPayPage.payBill("Electrica SA", "Str. Energiei 10", "Baia Mare", "Maramures", "430001",
                "0744111222", "12345", "12345", "50", "13579");

        Assert.assertTrue(billPayPage.isPaymentSuccessful(), "Plata nu a fost finalizată!");
        System.out.println("SUCCESS: The invoice has been paid for the user: " + userUnic);
    }
}