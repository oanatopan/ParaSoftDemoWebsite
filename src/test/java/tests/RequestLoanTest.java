package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoanPage;
import pages.RegisterPage;
import shareData.SharedData;

public class RequestLoanTest extends SharedData {
    @Test
    public void metodaTest() {
        RegisterPage registerPage = new RegisterPage(driver);
        LoanPage loanPage = new LoanPage(driver);

        registerPage.goToRegister();
        String userUnic = "loan" + System.currentTimeMillis();
        registerPage.registerUserUniq("Oana", "Topan", "Republicii", "Baia Mare", "Romania", "123456", "0722000000","123-45-678",userUnic, "Parola123!");

        Assert.assertTrue(registerPage.isRegistrationSuccessful());

        loanPage.goToRequestLoan();
        loanPage.applyForLoan("100", "10");

        String status = loanPage.getLoanStatusText();
        Assert.assertTrue(status.contains("Approved") || status.contains("Denied"), "Unknown status: " + status);
    }
}