package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import modelObject.LoanModel;
import modelObject.RegisterModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import shareData.SharedData;
@Feature("@FEATURE - LOAN MANAGEMENT")
@Story("@STORY - CUSTOMER LOAN REQUEST")
public class RequestLoanTest extends SharedData {

    @Test
    public void automationTest() {
        LoanModel loanData = new LoanModel("src/test/resources/LoanData.json");
        RegisterModel registerData = new RegisterModel("src/test/resources/RegisterData.json");

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