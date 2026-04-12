package pages;

import modelObject.TransferFundsModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.LogUtility;

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

    @FindBy(xpath = "//h1[contains(text(),'Transfer Complete!')]")
    private WebElement transferCompleteTitle;

    public TransferFundsPage goToTransferFunds() {
        elementsMethods.clickElement(transferLink);
        LogUtility.infoLog("The user clicks on Transfer Funds link from the side menu");

        // REPARAT: wait pentru amountField SI pentru dropdown-urile async
        elementsMethods.waitVisible(amountField);

        // Dropdownurile fromAccountId si toAccountId se populeaza async
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                By.cssSelector("#fromAccountId option"), 0));
        LogUtility.infoLog("The user waits for the transfer form and dropdowns to be populated.");
        return this;
    }

    public TransferFundsPage makeTransfer(String amountValue) {
        elementsMethods.fillElement(amountField, amountValue);
        LogUtility.infoLog("The user fills the transfer amount with value: " + amountValue);

        selectMethods.selectByIndex(fromAccountDropdown, 0);
        LogUtility.infoLog("The user selects the first available account from 'From Account' drop down");

        // REPARAT: wait pentru toAccountId — se populeaza dupa selectarea fromAccountId
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                By.cssSelector("#toAccountId option"), 1));

        selectMethods.selectByIndex(toAccountDropdown, 1);
        LogUtility.infoLog("The user selects the second available account from 'To Account' drop down");

        elementsMethods.clickElement(transferBtn);
        LogUtility.infoLog("The user clicks on Transfer button");

        elementsMethods.waitVisible(transferCompleteTitle);
        elementsMethods.waitVisible(amountResult);

        System.out.println("Transfer Result Amount: " + amountResult.getText());
        return this;
    }

    public TransferFundsPage makeTransfer(TransferFundsModel data) {
        LogUtility.infoLog("The user starts the transfer process using data from the model");
        return makeTransfer(data.getAmount());
    }

    public String getTransferredAmountText() {
        String amountText = elementsMethods.getElementText(amountResult);
        LogUtility.infoLog("The user retrieves the transferred amount text: " + amountText);
        return amountText;
    }

    public boolean isTransferSuccessful(TransferFundsModel data) {
        String titleText = elementsMethods.getElementText(transferCompleteTitle);
        boolean isSuccess = titleText.contains(data.getSuccessMessage());

        if (isSuccess) {
            LogUtility.infoLog("The user validates that the transfer success message is correct: " + data.getSuccessMessage());
        } else {
            LogUtility.errorLog("The transfer success message is incorrect. Actual message: " + titleText);
        }
        return isSuccess;
    }
}