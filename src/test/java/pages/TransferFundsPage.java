package pages;

import modelObject.TransferFundsModel;
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

    @FindBy(id = "fromAccountId")
    private WebElement fromAccountDropdown;

    @FindBy(id = "toAccountId")
    private WebElement toAccountDropdown;

    @FindBy(xpath = "//input[@value='Transfer']")
    private WebElement transferBtn;

    @FindBy(id = "amountResult")
    private WebElement amountResult;

    @FindBy(xpath = "//h1[text()='Transfer Complete!']")
    private WebElement transferCompleteTitle;

    public TransferFundsPage goToTransferFunds() {
        elementsMethods.clickElement(transferLink);
        elementsMethods.waitVisible(amountField);
        return this;
    }

    public TransferFundsPage makeTransfer(String amountValue) {
        elementsMethods.fillElement(amountField, amountValue);

        selectMethods.selectByIndex(fromAccountDropdown, 0);
        selectMethods.selectByIndex(toAccountDropdown, 1);

        elementsMethods.clickElement(transferBtn);
        elementsMethods.waitVisible(transferCompleteTitle);
        elementsMethods.waitVisible(amountResult);
        return this;
    }

    public TransferFundsPage makeTransfer(TransferFundsModel data) {
        return makeTransfer(data.getAmount());
    }

    public String getTransferredAmountText() {
        return elementsMethods.getElementText(amountResult);
    }

    public boolean isTransferSuccessful() {
        return elementsMethods.isElementDisplayed(transferCompleteTitle);
    }
}