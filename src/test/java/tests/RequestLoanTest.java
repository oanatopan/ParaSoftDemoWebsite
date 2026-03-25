package tests;

import modelObject.LoanModel;
import modelObject.RegisterModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import shareData.SharedData;

public class RequestLoanTest extends SharedData {

    @Test
    public void automationTest() {
        RegisterModel registerData = new RegisterModel("RegisterData.json");
        LoanModel loanData = new LoanModel("LoanData.json");

        registerPage.goToRegister();
        String userUniq = "loan" + System.currentTimeMillis();
        registerPage.registerUserUniq(registerData, userUniq);

        Assert.assertTrue(registerPage.isRegistrationSuccessful(), "Registration failed in RequestLoanTest!");
        Assert.assertTrue(homePage.isLogOutVisible(), "User not logged in in RequestLoanTest!");

        loanPage.goToRequestLoan();
        loanPage.applyForLoan(loanData);

        Assert.assertTrue(loanPage.isLoanStatusVisible(), "Loan status not visible!");

        String status = loanPage.getLoanStatusText();
        Assert.assertTrue(
                status.contains(loanData.getApprovedText()) || status.contains(loanData.getDeniedText()),
                "Unexpected loan status: " + status
        );
    }
}