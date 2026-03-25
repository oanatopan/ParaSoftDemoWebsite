package pages;

import modelObject.LoanModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoanPage extends BasePage {
    public LoanPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(linkText = "Request Loan") private WebElement loanLink;
    @FindBy(id = "amount") private WebElement amountField;
    @FindBy(id = "downPayment") private WebElement downPaymentField;
    @FindBy(xpath = "//input[@value='Apply Now']") private WebElement applyBtn;
    @FindBy(id = "loanStatus") private WebElement loanStatus;

    public LoanPage goToRequestLoan() {
        elementsMethods.clickElement(loanLink);
        return this;
    }

    public LoanPage applyForLoan(String amount, String downPayment) {
        elementsMethods.fillElement(amountField, amount);
        elementsMethods.fillElement(downPaymentField, downPayment);
        elementsMethods.clickElement(applyBtn);
        return this;
    }

    public String getLoanStatusText() {
        return elementsMethods.getElementText(loanStatus);
    }
    public LoanPage applyForLoan(LoanModel data) {
        return applyForLoan(data.getLoanAmount(), data.getDownPayment());
    }

    public boolean isLoanStatusVisible() {
        return elementsMethods.isElementDisplayed(loanStatus);
    }
}

