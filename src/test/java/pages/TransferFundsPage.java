package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TransferFundsPage extends BasePage {
    public TransferFundsPage(WebDriver driver) { super(driver); }

    @FindBy(linkText = "Transfer Funds") private WebElement transferLink;
    @FindBy(xpath = "//select[@id='fromAccountId']/option") private WebElement accountOptions;
    @FindBy(id = "amount") private WebElement amountField;
    @FindBy(xpath = "//input[@value='Transfer']") private WebElement transferBtn;
    @FindBy(id = "showResult") private WebElement result;

    public void goToTransferFunds() { elementsMethods.clickElement(transferLink); }

    public void makeTransfer(String amountValue) {
        elementsMethods.waitVisible(amountField);
        elementsMethods.fillElement(amountField, amountValue);
        elementsMethods.clickElement(transferBtn);
    }

    public String getResultMessage() { return elementsMethods.getElementText(result); }
}