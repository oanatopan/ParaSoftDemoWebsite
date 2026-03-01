package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TransferFundsPage extends BasePage {
    public TransferFundsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(linkText = "Transfer Funds")
    private WebElement transferLink;

    @FindBy(id = "amount")
    private WebElement amountField;

    @FindBy(xpath = "//input[@value='Transfer']")
    private WebElement transferBtn;

    @FindBy(id = "showResult")
    private WebElement result;

    public TransferFundsPage goToTransferFunds() {
        elementsMethods.clickElement(transferLink);
        return this;
    }

    public TransferFundsPage makeTransfer(String amountValue) {
        elementsMethods.waitVisible(amountField);
        elementsMethods.fillElement(amountField, amountValue);
        elementsMethods.clickElement(transferBtn);
        return this;
    }

    public String getResultMessage() {
        return elementsMethods.getElementText(result);
    }

    public boolean isTransferSuccessful() {
        return getResultMessage().toLowerCase().contains("transfer complete");
    }
}