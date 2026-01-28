package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoanPage extends BasePage {
    public LoanPage(WebDriver driver) { super(driver); }

    @FindBy(linkText = "Request Loan") private WebElement loanLink;
    @FindBy(id = "amount") private WebElement amountField;
    @FindBy(id = "downPayment") private WebElement downPaymentField;
    @FindBy(xpath = "//input[@value='Apply Now']") private WebElement applyBtn;
    @FindBy(id = "loanStatus") private WebElement loanStatus;

    public void goToRequestLoan() { elementsMethods.clickElement(loanLink); }

    public void applyForLoan(String amount, String downPayment) {
        elementsMethods.fillElement(amountField, amount);
        elementsMethods.fillElement(downPaymentField, downPayment);
        elementsMethods.clickElement(applyBtn);
    }

    public String getLoanStatusText() { return elementsMethods.getElementText(loanStatus); }
}