package pages;

import modelObject.LoanModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.LogUtility;

public class LoanPage extends BasePage {
    public LoanPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(linkText = "Request Loan")
    private WebElement loanLink;

    @FindBy(id = "amount")
    private WebElement amountField;

    @FindBy(id = "downPayment")
    private WebElement downPaymentField;

    @FindBy(xpath = "//input[@value='Apply Now']")
    private WebElement applyBtn;

    @FindBy(id = "loanStatus")
    private WebElement loanStatus;

    // REPARAT: Am scos "LoanPage" de dinainte de nume. Acum e o metodă curată.
    public void goToRequestLoan() {
        elementsMethods.clickElement(loanLink);
        LogUtility.infoLog("The user clicks on the Request Loan link from the side menu");
    }

    // REPARAT: Schimbat în void și am șters "return this" pentru a scăpa de avertismentul galben
    public void applyForLoan(String amount, String downPayment) {
        elementsMethods.fillElement(amountField, amount);
        LogUtility.infoLog("The user fills the loan amount field: " + amount);

        elementsMethods.fillElement(downPaymentField, downPayment);
        LogUtility.infoLog("The user fills the down payment field: " + downPayment);

        elementsMethods.clickElement(applyBtn);
        LogUtility.infoLog("The user clicks on Apply Now button.");
    }

    // REPARAT: Schimbat în void pentru a scăpa de avertismentul galben
    public void applyForLoan(LoanModel data) {
        LogUtility.infoLog("The user starts the loan application process using model data");
        applyForLoan(data.getLoanAmount(), data.getDownPayment());
    }

    public String getLoanStatusText() {
        String status = elementsMethods.getElementText(loanStatus);
        LogUtility.infoLog("The user retrieves the loan status text: " + status);
        System.out.println("Loan Application Final Status: " + status);
        return status;
    }

    public boolean isLoanStatusVisible() {
        boolean isVisible = elementsMethods.isElementDisplayed(loanStatus);
        if (isVisible) {
            LogUtility.infoLog("The user validates that the loan status message is displayed");
        } else {
            LogUtility.errorLog("The loan status message was not found");
        }
        return isVisible;
    }
}